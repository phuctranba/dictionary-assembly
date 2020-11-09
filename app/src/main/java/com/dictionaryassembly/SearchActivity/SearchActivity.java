package com.dictionaryassembly.SearchActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.dictionaryassembly.AssemblyDetailActivity.AssemblyDetailActivity;
import com.dictionaryassembly.Objects.AssemblyForm;
import com.dictionaryassembly.Objects.DatabaseHelper;
import com.dictionaryassembly.Objects.EnumType;
import com.dictionaryassembly.Objects.History;
import com.dictionaryassembly.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SearchActivity extends AppCompatActivity {

    private SearchView searchView;
    private SearchListAdapter searchListAdapter;
    private List<AssemblyForm> searchsListSource, searchsListType ,searchsListSearch;
    private ListView listViewResult;
    private Button buttonAll, buttonStatement, buttonStruct, buttonInterrupt, buttonMacro;
    private String valueSearch="";
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        init();

        listViewResult.setAdapter(searchListAdapter);

        setClick();
    }

    private void init() {

        databaseHelper = new DatabaseHelper(this);

        searchsListSource = databaseHelper.getAllAssembly();

        searchsListType = new ArrayList<>(searchsListSource);
        searchsListSearch = new ArrayList<>(searchsListSource);

        buttonAll = findViewById(R.id.buttonAll);
        buttonStatement = findViewById(R.id.buttonStatement);
        buttonStruct = findViewById(R.id.buttonStruct);
        buttonInterrupt = findViewById(R.id.buttonInterrupt);
        buttonMacro = findViewById(R.id.buttonMacro);
        searchView = findViewById(R.id.search_bar);
        listViewResult = findViewById(R.id.listResultSearch);
        listViewResult.setEmptyView(findViewById(R.id.emptyElement));
        searchListAdapter = new SearchListAdapter(this, searchsListSearch);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setClick(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchWithValue(s);
                return false;
            }
        });

        listViewResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                saveHistory(searchsListSearch.get(i));
                Intent intent = new Intent(SearchActivity.this, AssemblyDetailActivity.class);
                intent.putExtra("ITEM", searchsListSearch.get(i));
                startActivity(intent);
            }
        });

        buttonAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setType(EnumType.ALL);
                setColorButton();
                buttonAll.setBackgroundResource(R.drawable.item_options_selected);
                buttonAll.setTextColor(getResources().getColor(R.color.white));
            }
        });

        buttonStatement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setType(EnumType.STATEMENT);
                setColorButton();
                buttonStatement.setBackgroundResource(R.drawable.item_options_selected);
                buttonStatement.setTextColor(getResources().getColor(R.color.white));
            }
        });

        buttonStruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setType(EnumType.STRUCT);
                setColorButton();
                buttonStruct.setBackgroundResource(R.drawable.item_options_selected);
                buttonStruct.setTextColor(getResources().getColor(R.color.white));
            }
        });

        buttonInterrupt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setType(EnumType.INTERRUPT);
                setColorButton();
                buttonInterrupt.setBackgroundResource(R.drawable.item_options_selected);
                buttonInterrupt.setTextColor(getResources().getColor(R.color.white));
            }
        });

        buttonMacro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setType(EnumType.MACRO);
                setColorButton();
                buttonMacro.setBackgroundResource(R.drawable.item_options_selected);
                buttonMacro.setTextColor(getResources().getColor(R.color.white));
            }
        });
    }

    private void setColorButton(){
        buttonAll.setBackgroundResource(R.drawable.item_options);
        buttonStatement.setBackgroundResource(R.drawable.item_options);
        buttonStruct.setBackgroundResource(R.drawable.item_options);
        buttonInterrupt.setBackgroundResource(R.drawable.item_options);
        buttonMacro.setBackgroundResource(R.drawable.item_options);

        buttonAll.setTextColor(getResources().getColor(R.color.colorPrimary));
        buttonStatement.setTextColor(getResources().getColor(R.color.colorPrimary));
        buttonStruct.setTextColor(getResources().getColor(R.color.colorPrimary));
        buttonInterrupt.setTextColor(getResources().getColor(R.color.colorPrimary));
        buttonMacro.setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    private void saveHistory(AssemblyForm assemblyForm){

        History history = new History(assemblyForm, UUID.randomUUID().toString());

        FirebaseDatabase.getInstance().getReference("usersDictionary")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("history")
                .child(history.getHistoryID())
                .setValue(history).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }

    protected void searchWithValue(String value) {
        searchsListSearch.clear();

        for (AssemblyForm assemblyForm : searchsListType) {
            if (assemblyForm.getTitle().toLowerCase().contains(value.toLowerCase())) {
                searchsListSearch.add(assemblyForm);
            }
        }

        valueSearch = value;
        searchListAdapter.notifyDataSetChanged();
    }

    protected void setType(EnumType type) {
        searchsListType.clear();

        switch (type){
            case ALL: searchsListType = new ArrayList<>(searchsListSource); break;
            default: {
                for (AssemblyForm assemblyForm : searchsListSource) {
                    if (assemblyForm.getType().equals(type)) {
                        searchsListType.add(assemblyForm);
                    }
                }
            }
        }

        searchWithValue(valueSearch);
    }
}