package com.github.phuctranba.dictionaryassembly.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.github.phuctranba.dictionaryassembly.Objects.AssemblyForm;
import com.github.phuctranba.dictionaryassembly.Objects.DatabaseHelper;
import com.github.phuctranba.dictionaryassembly.R;
import com.github.phuctranba.dictionaryassembly.SearchActivity.SearchActivity;
import com.github.phuctranba.dictionaryassembly.UserActivity.UserActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fabSearch, fabUser;
    DatabaseHelper databaseHelper;

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

        loadData();
    }

    private void Init() {
        fabSearch = findViewById(R.id.fabSearch);
        fabUser = findViewById(R.id.fabUser);

        databaseHelper = new DatabaseHelper(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.framelayoutOptions, new FragmentOptions()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayoutHistory, new FragmentHistory()).commit();
    }

    private void loadData() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting()) {

            databaseHelper.deleteAllAssembly();

            FirebaseDatabase.getInstance().getReference("dictionary")
                    .addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            AssemblyForm assemblyForm = snapshot.getValue(AssemblyForm.class);
                            databaseHelper.addAssembly(assemblyForm);
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            AssemblyForm assemblyForm = snapshot.getValue(AssemblyForm.class);
                            databaseHelper.updateAssembly(assemblyForm);
                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            AssemblyForm assemblyForm = snapshot.getValue(AssemblyForm.class);
                            databaseHelper.deleteAssembly(assemblyForm);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

            FirebaseDatabase.getInstance().getReference("usersDictionary")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("macro")
                    .addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            AssemblyForm assemblyForm = snapshot.getValue(AssemblyForm.class);
                            databaseHelper.addAssembly(assemblyForm);
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            AssemblyForm assemblyForm = snapshot.getValue(AssemblyForm.class);
                            databaseHelper.updateAssembly(assemblyForm);
                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                            AssemblyForm assemblyForm = snapshot.getValue(AssemblyForm.class);
                            databaseHelper.deleteAssembly(assemblyForm);
                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }
    }
}