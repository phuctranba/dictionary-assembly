package com.dictionaryassembly.AssemblyEditActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.dictionaryassembly.LoginActivity.LoginActivity;
import com.dictionaryassembly.Objects.AssemblyForm;
import com.dictionaryassembly.Objects.EnumType;
import com.dictionaryassembly.Objects.User;
import com.dictionaryassembly.R;
import com.dictionaryassembly.UserActivity.UserActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.UUID;

public class AssemblyEditActivity extends AppCompatActivity {

    AssemblyForm assemblyForm;
    EditText editTextTitle, editTextContent, editTextDescription, editTextNgatInterrupt, editTextHamInterrupt, editTextTypeInterrupt;
    Button buttonCancel, buttonSave, buttonPickImage;
    ImageView imageView;
    CheckBox checkBoxActive;
    LinearLayout linearLayoutInterrupt;
    RadioButton radioButtonBIOS, radioButtonDOS;

    FirebaseStorage storage;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    EnumType typeAssembly;

    private boolean updateImage = false;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assembly_edit);

        Init();

        setClick();
    }

    private void Init() {

        editTextTitle = findViewById(R.id.title);
        editTextContent = findViewById(R.id.content);
        editTextDescription = findViewById(R.id.description);
        editTextNgatInterrupt = findViewById(R.id.ngatInterrupt);
        editTextHamInterrupt = findViewById(R.id.hamInterrupt);
        editTextTypeInterrupt = findViewById(R.id.typeInterrupt);
        radioButtonBIOS = findViewById(R.id.BIOS);
        radioButtonDOS = findViewById(R.id.DOS);
        buttonCancel = findViewById(R.id.cancel);
        buttonSave = findViewById(R.id.save);
        buttonPickImage = findViewById(R.id.pickImage);
        imageView = findViewById(R.id.image);
        checkBoxActive = findViewById(R.id.active);
        linearLayoutInterrupt = findViewById(R.id.inputInterrupt);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        progressDialog = new ProgressDialog(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getParams();

        setUpLayout();
    }

    private void getParams() {
        if (getIntent().getStringExtra("TYPEACTION").equals("CREATE")) {
            typeAssembly = (EnumType) getIntent().getSerializableExtra("TYPE");
            setTitle("Thêm mới " + converttype(typeAssembly));
        } else {
            setTitle("Chỉnh sửa");
            assemblyForm = (AssemblyForm) getIntent().getSerializableExtra("ITEM");
            typeAssembly = assemblyForm.getType();

            editTextDescription.setText(assemblyForm.getDescription());
            checkBoxActive.setChecked(assemblyForm.isActive());
            editTextContent.setText(assemblyForm.getContent());

            if(typeAssembly.equals(EnumType.INTERRUPT)){
                String[] title = assemblyForm.getTitle().split("-");
                editTextNgatInterrupt.setText(title[0].trim());
                editTextHamInterrupt.setText(title[1].trim());

                String[] content = assemblyForm.getTypeInterrupt().split("-");
                if(!content[0].trim().equals("Ngắt BIOS"))  {
                    radioButtonDOS.setChecked(true);
                    radioButtonBIOS.setChecked(false);
                }
                editTextTypeInterrupt.setText(content[1].trim());
            }else {
                editTextTitle.setText(assemblyForm.getTitle());
            }

            if(assemblyForm.getImageLink()!=null)
                if(!assemblyForm.getImageLink().isEmpty())Picasso.get().load(assemblyForm.getImageLink()).into(imageView);
        }
    }

    private void setClick() {
        buttonPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

                if (cm.getActiveNetworkInfo() != null &&
                        cm.getActiveNetworkInfo().isConnectedOrConnecting()){
                    uploadImage();
                }else {
                    Toast.makeText(AssemblyEditActivity.this, "Không có kết nối internet, thử lại sau!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void uploadImage() {

        switch (typeAssembly) {
            case STATEMENT:
            case STRUCT:
            case INTERRUPT: {
                if (filePath != null) {
                    progressDialog.setTitle("Đang lưu...");
                    progressDialog.show();

                    final StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
                    ref.putFile(filePath)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            updateAssembly(uri.toString());
                                        }
                                    });
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(AssemblyEditActivity.this, "Lỗi! Vui lòng thử lại sau.", Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    updateAssembly("");
                }

                break;
            }
            case MACRO: {
                updateAssembly("");
                break;
            }
        }
    }

    private void updateAssembly(String urlImage) {

        if (assemblyForm == null) {
            assemblyForm = new AssemblyForm(
                    UUID.randomUUID().toString(),
                    typeAssembly
            );
        }

        if (typeAssembly.equals(EnumType.INTERRUPT)) {
            assemblyForm.setTitle(editTextNgatInterrupt.getText().toString().trim() + " - " + editTextHamInterrupt.getText().toString().trim());
            String BIOS = radioButtonBIOS.isChecked() ? "Ngắt BIOS" : "Ngắt DOS";
            assemblyForm.setTypeInterrupt(BIOS + " - " + editTextTypeInterrupt.getText().toString().trim());
        } else {
            assemblyForm.setTitle(editTextTitle.getText().toString().trim());
        }

        assemblyForm.setContent(editTextContent.getText().toString().trim());
        assemblyForm.setDescription(editTextDescription.getText().toString().trim());
        assemblyForm.setActive(checkBoxActive.isChecked());
        if (updateImage) assemblyForm.setImageLink(urlImage);

        if(assemblyForm.getTitle().isEmpty()||assemblyForm.getDescription().isEmpty()){
            Toast.makeText(AssemblyEditActivity.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (typeAssembly.equals(EnumType.MACRO)) {
            FirebaseDatabase.getInstance().getReference("usersDictionary")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("macro")
                    .child(assemblyForm.getID())
                    .setValue(assemblyForm).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(AssemblyEditActivity.this, "Lưu thành công!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(AssemblyEditActivity.this, "Lưu thất bại, thử lại!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            FirebaseDatabase.getInstance().getReference("dictionary").child(assemblyForm.getID())
                    .setValue(assemblyForm).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(AssemblyEditActivity.this, "Lưu thành công!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(AssemblyEditActivity.this, "Lưu thất bại, thử lại!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        progressDialog.dismiss();
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    private String converttype(EnumType enumType) {
        switch (enumType) {
            case STATEMENT:
                return "lệnh";
            case STRUCT:
                return "cấu trúc";
            case MACRO:
                return "macro";
            case INTERRUPT:
                return "ngắn";
            default:
                return "lệnh";
        }
    }

    private void setUpLayout() {
        switch (typeAssembly) {
            case STATEMENT: {
                editTextTitle.setHint("Tên lệnh");
                editTextContent.setHint("Cú pháp");
                editTextDescription.setHint("Mô tả");
                linearLayoutInterrupt.setVisibility(View.GONE);
                break;
            }
            case STRUCT: {
                editTextTitle.setHint("Tên cấu trúc");
                editTextContent.setHint("Cú pháp");
                editTextDescription.setHint("Mô tả");
                linearLayoutInterrupt.setVisibility(View.GONE);
                break;
            }
            case INTERRUPT: {
                editTextTitle.setVisibility(View.GONE);
                editTextContent.setHint("Cú pháp");
                editTextDescription.setHint("Mô tả");
                break;
            }
            case MACRO: {
                editTextTitle.setHint("Tên macro");
                editTextContent.setHint("Mô tả");
                editTextDescription.setHint("Danh sách lệnh");
                linearLayoutInterrupt.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
                buttonPickImage.setVisibility(View.GONE);
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
                updateImage = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(AssemblyEditActivity.this)
                .setMessage("Hủy chỉnh sửa?")
                .setCancelable(false)
                .setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("Ở lại", null)
                .show();
    }
}