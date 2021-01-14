package com.github.phuctranba.dictionaryassembly.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.github.phuctranba.dictionaryassembly.MainActivity.MainActivity;
import com.github.phuctranba.dictionaryassembly.R;

public class LoginActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, new SignInFragment()).commit();

        progressDialog = new ProgressDialog(this);
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

    void showLoad(String mess){
        progressDialog.setTitle(mess);
        progressDialog.show();
    }

    void hideLoad(){
        progressDialog.dismiss();
    }
}