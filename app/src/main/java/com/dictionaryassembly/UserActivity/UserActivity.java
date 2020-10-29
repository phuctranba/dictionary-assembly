package com.dictionaryassembly.UserActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.dictionaryassembly.LoginActivity.LoginActivity;
import com.dictionaryassembly.Objects.DatabaseHelper;
import com.dictionaryassembly.R;
import com.google.firebase.auth.FirebaseAuth;

public class UserActivity extends AppCompatActivity {

    FirebaseAuth mauth;
    private TextView textViewName, textViewEmail;
    SharedPreferences sharedPreferences;
    DatabaseHelper databaseHelper;

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

        sharedPreferences = this.getSharedPreferences("userinfor", Context.MODE_PRIVATE);
        textViewName = findViewById(R.id.textName);
        textViewEmail = findViewById(R.id.textEmail);
    }

    private void getUser() {

        String email = sharedPreferences.getString("email", "Trống");
        String lastname = sharedPreferences.getString("lastname", "Trống");
        String firstname = sharedPreferences.getString("firstname", "");

        textViewName.setText(lastname + " " + firstname);
        textViewEmail.setText(email);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_signout) {

            new AlertDialog.Builder(this)
                    .setMessage("Bạn chắc chắn muốn đăng xuất?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            mauth.signOut();
                            databaseHelper.deleteAllHistory();
                            databaseHelper.deleteMacroAssembly();
                            startActivity(new Intent(UserActivity.this, LoginActivity.class));
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}