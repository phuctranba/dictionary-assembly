package com.dictionaryassembly.MainActivity;

import android.content.Intent;
import android.os.Bundle;

import com.dictionaryassembly.LoginActivity.SignInFragment;
import com.dictionaryassembly.R;
import com.dictionaryassembly.SearchActivity.SearchActivity;
import com.dictionaryassembly.UserActivity.UserActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fabSearch, fabUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();

        fabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchActivity = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(searchActivity);
            }
        });

        fabUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchActivity = new Intent(MainActivity.this, UserActivity.class);
                startActivity(searchActivity);
            }
        });
    }

    private void Init() {
        fabSearch = findViewById(R.id.fabSearch);
        fabUser = findViewById(R.id.fabUser);

//        fragmentManagerOptions =
//        FragmentTransaction fragmentTransaction =
//        fragmentTransaction.replace(R.id.framelayoutOptions, new FragmentOptions()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayoutOptions, new FragmentOptions()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayoutHistory, new FragmentHistory()).commit();
    }
}