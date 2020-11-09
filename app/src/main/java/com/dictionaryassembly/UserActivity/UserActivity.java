package com.dictionaryassembly.UserActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dictionaryassembly.AssemblyEditActivity.AssemblyEditActivity;
import com.dictionaryassembly.LoginActivity.LoginActivity;
import com.dictionaryassembly.Objects.DatabaseHelper;
import com.dictionaryassembly.Objects.User;
import com.dictionaryassembly.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class UserActivity extends AppCompatActivity {

    FirebaseAuth mauth;
    private TextView textViewName, textViewEmail, textViewGender, textViewAge, textViewTypeEducation, textViewAssemblyIntro;
    SharedPreferences sharedPreferences;
    DatabaseHelper databaseHelper;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Init();

        getUser();

    }

    private void Init() {
        mauth = FirebaseAuth.getInstance();
        databaseHelper = new DatabaseHelper(this);
        user = new User();

        sharedPreferences = this.getSharedPreferences("userinfor", Context.MODE_PRIVATE);
        textViewName = findViewById(R.id.textName);
        textViewEmail = findViewById(R.id.textEmail);
        textViewAge = findViewById(R.id.textAge);
        textViewGender = findViewById(R.id.textGender);
        textViewTypeEducation = findViewById(R.id.textTypeEducation);
        textViewAssemblyIntro = findViewById(R.id.assemblyIntro);

        textViewAssemblyIntro.setText(
"Trong lập trình máy tính, Hợp ngữ (hay assembly) thường được viết tắt là asm là bất kỳ ngôn ngữ lập trình cấp thấp nào có sự tương ứng rất mạnh giữa các tập lệnh trong ngôn ngữ và tập lệnh mã máy của kiến trúc. Bởi vì hợp ngữ phụ thuộc vào tập lệnh mã máy, mỗi trình biên dịch có hợp ngữ riêng được thiết kế cho chính xác một kiến trúc máy tính cụ thể. Hợp ngữ cũng có thể được gọi là mã máy tượng trưng (symbolic machine code).\n" +
        "\n" +
        "Mã hợp ngữ được chuyển đổi thành mã máy thực thi bằng một chương trình được gọi là assembler. Quá trình chuyển đổi được gọi là assembling. Hợp ngữ thường có một câu lệnh trên một lệnh máy (1:1), nhưng các comment và các câu lệnh là chỉ thị trình biên dịch, macros, và các nhãn chương trình và địa chỉ bộ nhớ cũng được hỗ trợ\n" +
        "\n" +
        "Mỗi một hợp ngữ là dành riêng cho một kiến trúc máy tính cụ thể và đôi khi cho một hệ điều hành. Tuy nhiên, một số hợp ngữ không cung cấp cú pháp riêng cho lời gọi hệ điều hành, và hầu hết các hợp ngữ có thể được sử dụng phổ biến với bất kỳ hệ điều hành nào, vì ngôn ngữ này cung cấp quyền truy cập vào tất cả các khả năng thực sự của bộ xử lý, theo đó tất cả các cơ chế gọi hệ thống đều dừng lại. Trái ngược với hợp ngữ, hầu hết các ngôn ngữ lập trình bậc cao thường có khả năng di động trên nhiều kiến trúc nhưng yêu cầu thông dịch hoặc biên dịch, một công việc phức tạp hơn nhiều so với assembling.\n" +
        "\n" +
        "Hợp ngữ đã từng được dùng rộng rãi trong tất cả các khía cạnh lập trình, nhưng ngày nay nó có xu hướng chỉ được dùng trong một số lãnh vực hẹp, chủ yếu để giao tiếp trực tiếp với phần cứng hoặc xử lý các vấn đề liên quan đến tốc độ cao điển hình như các trình điều khiển thiết bị, các hệ thống nhúng cấp thấp và các ứng dụng thời gian thực."
        );
    }

    private void getUser() {

        String email = sharedPreferences.getString("email", "Trống");
        String lastname = sharedPreferences.getString("lastname", "");
        String firstname = sharedPreferences.getString("firstname", "");
        String gender = sharedPreferences.getString("gender", "Trống");
        String age = sharedPreferences.getString("age", "Trống");
        String typeEducation = sharedPreferences.getString("typeEducation", "Trống");

        user.setEmail(email);
        user.setLastName(lastname);
        user.setFirstName(firstname);
        user.setGender(gender);
        user.setAge(age);
        user.setTypeEducation(typeEducation);

        textViewName.setText((lastname+firstname).length()==0?"Trống":lastname+" "+firstname);
        textViewEmail.setText(email);
        textViewAge.setText(age);
        textViewGender.setText(gender);
        textViewTypeEducation.setText(typeEducation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_signout:{
                new AlertDialog.Builder(this)
                        .setMessage("Bạn chắc chắn muốn đăng xuất?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mauth.signOut();
                                databaseHelper.deleteAllHistory();
                                databaseHelper.deleteMacroAssembly();

                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.clear().apply();

                                startActivity(new Intent(UserActivity.this, LoginActivity.class));
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

                return true;
            }
            case R.id.action_edit: displayEditDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    public void displayEditDialog() {
        final LayoutInflater inflater = getLayoutInflater();
        final View dialogLayout = inflater.inflate(R.layout.dialog_edit_user, null);
        final EditText editLastName = (EditText) dialogLayout.findViewById(R.id.editLastName);
        final EditText editFirstName = (EditText) dialogLayout.findViewById(R.id.editFirstName);
        final EditText editAge = (EditText) dialogLayout.findViewById(R.id.editAge);
        final RadioGroup radioGender = (RadioGroup) dialogLayout.findViewById(R.id.radioGroupGender);
        final RadioGroup radioTypeEducation = (RadioGroup) dialogLayout.findViewById(R.id.radioGroupTypeEducation);
        final RadioButton radioNam = (RadioButton) dialogLayout.findViewById(R.id.nam);
        final RadioButton radioNu = (RadioButton) dialogLayout.findViewById(R.id.nu);
        final RadioButton radioTHCS = (RadioButton) dialogLayout.findViewById(R.id.THCS);
        final RadioButton radioTHPT = (RadioButton) dialogLayout.findViewById(R.id.THPT);
        final RadioButton radioUNI = (RadioButton) dialogLayout.findViewById(R.id.UNI);

        editLastName.setText(user.getLastName());
        editFirstName.setText(user.getFirstName());
        editAge.setText(user.getAge());

        if ("Nữ".equals(user.getGender())) {
            radioNu.setChecked(true);
        } else {
            radioNam.setChecked(true);
        }

        switch (user.getTypeEducation()){
            case "THPT": radioTHPT.setChecked(true); break;
            case "Đại học": radioUNI.setChecked(true); break;
            default: radioTHCS.setChecked(true);
        }


        final AlertDialog.Builder editDialog = new AlertDialog.Builder(this);
        editDialog.setView(dialogLayout);
        editDialog.setCancelable(false);
        editDialog.setNegativeButton("Hủy", null);

        editDialog.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

                if (cm.getActiveNetworkInfo() != null &&
                        cm.getActiveNetworkInfo().isConnectedOrConnecting()) {
                    String lastName = editLastName.getText().toString().trim();
                    String firstName = editFirstName.getText().toString().trim();
                    String age = editAge.getText().toString().trim();
                    String gender = ((RadioButton) dialogLayout.findViewById(radioGender.getCheckedRadioButtonId())).getText().toString();
                    String typeEducation = ((RadioButton) dialogLayout.findViewById(radioTypeEducation.getCheckedRadioButtonId())).getText().toString();

                    user.setLastName(lastName);
                    user.setFirstName(firstName);
                    user.setGender(gender);
                    user.setAge(age);
                    user.setTypeEducation(typeEducation);

                    FirebaseDatabase.getInstance().getReference("users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                if (user.getEmail() != null)
                                    editor.putString("email", user.getEmail());
                                if (user.getLastName() != null)
                                    editor.putString("lastname", user.getLastName());
                                if (user.getFirstName() != null)
                                    editor.putString("firstname", user.getFirstName());
                                if (user.getAge() != null) editor.putString("age", user.getAge());
                                if (user.getTypeEducation() != null)
                                    editor.putString("typeEducation", user.getTypeEducation());
                                if (user.getGender() != null)
                                    editor.putString("gender", user.getGender());

                                editor.apply();

                                textViewName.setText((user.getLastName() + user.getFirstName()).length() == 0 ? "Trống" : user.getLastName() + " " + user.getFirstName());
                                textViewEmail.setText(user.getEmail());
                                textViewAge.setText(user.getAge());
                                textViewGender.setText(user.getGender());
                                textViewTypeEducation.setText(user.getTypeEducation());

                                Toast.makeText(UserActivity.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(UserActivity.this, "Cập nhật thất bại, thử lại sau!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(UserActivity.this, "Không có kết nối internet, thử lại sau!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog dialog = editDialog.create();
        dialog.show();
    }

}