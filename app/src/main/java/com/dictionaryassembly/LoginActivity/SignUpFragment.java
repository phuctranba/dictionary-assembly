package com.dictionaryassembly.LoginActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dictionaryassembly.R;
import com.dictionaryassembly.Objects.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpFragment extends Fragment {

    Button buttonSignIn, buttonSignUp;
    EditText editTextLastName, editTextFirstNam, editTextEmail, editTextPass, editTextRePass;
    FirebaseAuth mauth;
    TextView textViewForget;

    public SignUpFragment() {
        // Required empty public constructor
    }

    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Init(view);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoginActivity) getActivity()).GoToSignIn();
            }
        });

        textViewForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoginActivity) getActivity()).GoToForget();
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUp();
            }
        });
    }

    private void Init(View view) {
        buttonSignIn = view.findViewById(R.id.btnSignin);
        buttonSignUp = view.findViewById(R.id.btnSignup);
        editTextLastName = view.findViewById(R.id.editTextLastName);
        editTextFirstNam = view.findViewById(R.id.editTextFirstName);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextPass = view.findViewById(R.id.editTextPass);
        editTextRePass = view.findViewById(R.id.editTextRePass);
        textViewForget = view.findViewById(R.id.textForget);

        mauth = FirebaseAuth.getInstance();
    }

    private void SignUp() {

        final String lastName = editTextLastName.getText().toString().trim();
        final String firstName = editTextFirstNam.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        String pass = editTextPass.getText().toString().trim();
        String rePass = editTextRePass.getText().toString().trim();

        if (lastName.isEmpty()) {
            editTextLastName.setError("Họ tên đệm trống!");
            editTextLastName.requestFocus();
            return;
        }

        if (firstName.isEmpty()) {
            editTextFirstNam.setError("Tên trống!");
            editTextFirstNam.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Email trống!");
            editTextEmail.requestFocus();
            return;
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                editTextEmail.setError("Email không đúng định dạng!");
                editTextEmail.requestFocus();
                return;
            }
        }

        if (pass.isEmpty()) {
            editTextPass.setError("Mật khẩu trống!");
            editTextPass.requestFocus();
            return;
        } else {

            if (pass.length() < 6) {
                editTextPass.setError("Mật khẩu phải hơn 6 kí tự!");
                editTextPass.requestFocus();
                return;
            } else {
                if (rePass.isEmpty()) {
                    editTextRePass.setError("Nhập lại mật khẩu!");
                    editTextRePass.requestFocus();
                    return;
                } else {
                    if (!pass.equals(rePass)) {
                        editTextRePass.setError("Mật khẩu nhập lại không khớp!");
                        editTextRePass.requestFocus();
                        return;
                    }
                }
            }
        }


        mauth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    User user = new User(email, lastName, firstName);

                    FirebaseDatabase.getInstance().getReference("users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Log.e("Phuc",task.toString());
                            if (task.isSuccessful()) {
                                Toast.makeText(getActivity(), "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                                ((LoginActivity) getActivity()).GoToMainActitity();
                            } else {
                                Toast.makeText(getActivity(), "Đăng ký thất bại, thử lại!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}