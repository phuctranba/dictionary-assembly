package com.github.phuctranba.dictionaryassembly.SplashActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.phuctranba.dictionaryassembly.MainActivity.MainActivity;
import com.github.phuctranba.dictionaryassembly.LoginActivity.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        } else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        }
        finish();
    }
}