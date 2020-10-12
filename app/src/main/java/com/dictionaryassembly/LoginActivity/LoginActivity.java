package com.dictionaryassembly.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.dictionaryassembly.MainActivity.MainActivity;
import com.dictionaryassembly.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, new SignInFragment()).commit();
    }

    @SuppressLint("CommitTransaction")
    void GoToSignUp() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.framelayout,new SignUpFragment()).commit();
    }

    @SuppressLint("CommitTransaction")
    void GoToForget() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.framelayout,new ResetPassFragment()).commit();
    }

    @SuppressLint("CommitTransaction")
    void GoToSignIn() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.framelayout,new SignInFragment()).commit();
    }

    @SuppressLint("CommitTransaction")
    void GoToMainActitity() {
        Intent searchActivity = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(searchActivity);
    }
}