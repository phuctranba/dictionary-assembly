package com.github.phuctranba.dictionaryassembly.LoginActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.phuctranba.dictionaryassembly.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassFragment extends Fragment {

    Button buttonReset, buttonSignUp;
    EditText editTextEmail;
    FirebaseAuth mauth;
    TextView textViewSignin;

    public ResetPassFragment() {
        // Required empty public constructor
    }

    public static ResetPassFragment newInstance(String param1, String param2) {
        ResetPassFragment fragment = new ResetPassFragment();
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
        return inflater.inflate(R.layout.fragment_reset_pass, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Init(view);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoginActivity) getActivity()).GoToSignUp();
            }
        });

        textViewSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoginActivity) getActivity()).GoToSignIn();
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Reset();
            }
        });
    }

    private void Init(View view) {
        buttonReset = view.findViewById(R.id.btnReset);
        buttonSignUp = view.findViewById(R.id.btnSignup);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        textViewSignin = view.findViewById(R.id.textSignin);

        mauth = FirebaseAuth.getInstance();
    }

    private void Reset() {

        final String email = editTextEmail.getText().toString().trim();


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

        mauth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Kiểm tra email để đặt lại mật khẩu!", Toast.LENGTH_SHORT).show();
                            ((LoginActivity) getActivity()).GoToSignIn();
                        }else {
                            Toast.makeText(getActivity(), "Xảy ra lỗi, xin thử lại!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}