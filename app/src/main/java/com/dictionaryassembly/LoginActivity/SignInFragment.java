package com.dictionaryassembly.LoginActivity;

import android.content.Context;
import android.content.SharedPreferences;
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

import com.dictionaryassembly.Objects.User;
import com.dictionaryassembly.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInFragment extends Fragment {

    Button buttonSignIn, buttonSignUp;
    EditText editTextEmail, editTextPass;
    FirebaseAuth mauth;
    TextView textViewForget;
    SharedPreferences sharedPreferences;
    public SignInFragment() {
        // Required empty public constructor
    }

    public static SignInFragment newInstance() {
        SignInFragment fragment = new SignInFragment();
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
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
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

        textViewForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoginActivity) getActivity()).GoToForget();
            }
        });

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignIn();
            }
        });
    }

    private void Init(View view) {
        buttonSignIn = view.findViewById(R.id.btnSignin);
        buttonSignUp = view.findViewById(R.id.btnSignup);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextPass = view.findViewById(R.id.editTextPass);
        textViewForget = view.findViewById(R.id.textForget);
        sharedPreferences = getActivity().getSharedPreferences("userinfor", Context.MODE_PRIVATE);

        mauth = FirebaseAuth.getInstance();
    }

    private void SignIn() {

        final String email = editTextEmail.getText().toString().trim();
        String pass = editTextPass.getText().toString().trim();


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
            }
        }

        mauth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    FirebaseDatabase.getInstance().getReference("users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    User user = snapshot.getValue(User.class);

                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("email", user.getEmail());
                                    editor.putString("lastname", user.getLastName());
                                    editor.putString("firstname", user.getFirstName());

                                    editor.apply();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    System.out.println("The read failed: " + error.getCode());
                                }
                            });

                    ((LoginActivity) getActivity()).GoToMainActitity();
                }else {
                    Toast.makeText(getActivity(), "Email hoặc mật khẩu không chính xác!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}