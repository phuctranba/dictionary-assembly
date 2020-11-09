package com.dictionaryassembly.AssemblyListActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dictionaryassembly.AssemblyDetailActivity.AssemblyDetailActivity;
import com.dictionaryassembly.AssemblyEditActivity.AssemblyEditActivity;
import com.dictionaryassembly.Objects.AssemblyForm;
import com.dictionaryassembly.Objects.DatabaseHelper;
import com.dictionaryassembly.Objects.EnumType;
import com.dictionaryassembly.R;
import com.dictionaryassembly.SearchActivity.SearchActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AssemblyListActivity extends AppCompatActivity {

    private AssemblyListAdapter assemblyListAdapter;
    private List<AssemblyForm> assemblyFormArrayList;
    private ListView listViewAssemblyForm;
    EnumType enumType;
    DatabaseHelper databaseHelper;
    SharedPreferences sharedPreferences;
    MenuItem itemAdd;
    int permission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assembly_list);

        Init();

        setClick();
    }

    private void Init() {
        sharedPreferences = this.getSharedPreferences("userinfor", Context.MODE_PRIVATE);
        enumType = (EnumType) getIntent().getSerializableExtra("TYPE");
        databaseHelper = new DatabaseHelper(this);
        assemblyFormArrayList = databaseHelper.getByTypeAssembly(enumType);


        setTitle(converttype(enumType));
        listViewAssemblyForm = findViewById(R.id.listAssembly);
        listViewAssemblyForm.setEmptyView(findViewById(R.id.emptyElement));
        assemblyListAdapter = new AssemblyListAdapter(this, assemblyFormArrayList);

        permission = sharedPreferences.getInt("permission", 1);
    }

    private void setClick() {
        listViewAssemblyForm.setAdapter(assemblyListAdapter);

        listViewAssemblyForm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(AssemblyListActivity.this, AssemblyDetailActivity.class);
                intent.putExtra("ITEM", assemblyFormArrayList.get(i));
                startActivity(intent);
            }
        });

        listViewAssemblyForm.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (!enumType.equals(EnumType.MACRO)) {

                    if (permission == 0) {
                        Intent intent = new Intent(AssemblyListActivity.this, AssemblyEditActivity.class);
                        intent.putExtra("TYPEACTION", "EDIT");
                        intent.putExtra("ITEM", assemblyFormArrayList.get(i));
                        startActivityForResult(intent, 1);
                        return true;
                    } else return false;

                } else {

                    Intent intent = new Intent(AssemblyListActivity.this, AssemblyEditActivity.class);
                    intent.putExtra("TYPEACTION", "EDIT");
                    intent.putExtra("ITEM", assemblyFormArrayList.get(i));
                    startActivityForResult(intent, 1);
                    return true;

                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_open_search, menu);

        itemAdd = menu.findItem(R.id.action_add);
        if (!enumType.equals(EnumType.MACRO)) itemAdd.setVisible(permission == 0);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.action_search:
                startActivity(new Intent(AssemblyListActivity.this, SearchActivity.class));
                return true;
            case R.id.action_add:
                Intent intent = new Intent(AssemblyListActivity.this, AssemblyEditActivity.class);
                intent.putExtra("TYPEACTION", "CREATE");
                intent.putExtra("TYPE", getIntent().getSerializableExtra("TYPE"));
                startActivityForResult(intent, 1);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String converttype(EnumType enumType) {
        switch (enumType) {
            case STATEMENT:
                return "Lệnh";
            case STRUCT:
                return "Cấu trúc";
            case MACRO:
                return "Macro";
            case INTERRUPT:
                return "Ngắt";
            default:
                return "Lệnh";
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        assemblyFormArrayList.clear();
        assemblyFormArrayList.addAll(databaseHelper.getByTypeAssembly(enumType));
        assemblyListAdapter.notifyDataSetChanged();
    }
}