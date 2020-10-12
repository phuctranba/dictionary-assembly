package com.dictionaryassembly.SearchActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.dictionaryassembly.Objects.Search;
import com.dictionaryassembly.Objects.Search;
import com.dictionaryassembly.R;
import com.dictionaryassembly.Objects.Statement;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private SearchView searchView;
    private SearchListAdapter searchListAdapter;
    private ArrayList<Search> searchsListSource, searchsListType ,searchsListSearch;
    private ListView listViewResult;
    private Button buttonAll, buttonStatement, buttonStruct, buttonInterrupt, buttonMacro;
    private String valueSearch="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        init();

        listViewResult.setAdapter(searchListAdapter);

        setClick();
    }

    private void init() {
        searchsListSource = new ArrayList<>();
        searchsListSource.add(new Search(1, "Mov", " [Toán hạng đích], [Toán hạng nguồn]", "STATEMENT"));
        searchsListSource.add(new Search(2, "Inc", "[Toán hạng đích]", "STRUCT"));
        searchsListSource.add(new Search(3, "Loop", "<Nhãn đích>", "INTERRUPT"));
        searchsListSource.add(new Search(4, "LEA", "[Toán hạng đích],[Toán hạng nguồn]","STATEMENT"));
        searchsListSource.add(new Search(5, "Cmp", "[Toán hạng đích], [Toán hạng nguồn]", "MACRO"));
        searchsListSource.add(new Search(6, "Cmp", "[Toán hạng đích], [Toán hạng nguồn]", "MACRO"));
        searchsListSource.add(new Search(7, "LEA", "[Toán hạng đích],[Toán hạng nguồn]", "STATEMENT"));
        searchsListSource.add(new Search(9, "Mov", " [Toán hạng đích], [Toán hạng nguồn]", "STATEMENT"));
        searchsListSource.add(new Search(10, "Inc", "[Toán hạng đích]", "STRUCT"));
        searchsListSource.add(new Search(11, "Loop", "<Nhãn đích>",  "INTERRUPT"));
        searchsListSource.add(new Search(12, "LEA", "[Toán hạng đích],[Toán hạng nguồn]", "STATEMENT"));
        searchsListSource.add(new Search(13, "Cmp", "[Toán hạng đích], [Toán hạng nguồn]", "MACRO"));
        searchsListSource.add(new Search(14, "Cmp", "[Toán hạng đích], [Toán hạng nguồn]", "MACRO"));
        searchsListSource.add(new Search(15, "LEA", "[Toán hạng đích],[Toán hạng nguồn]", "STATEMENT"));
        searchsListSource.add(new Search(16, "Loop", "<Nhãn đích>",  "INTERRUPT"));
        searchsListSource.add(new Search(17, "Mov", " [Toán hạng đích], [Toán hạng nguồn]", "STATEMENT"));
        searchsListSource.add(new Search(18, "Inc", "[Toán hạng đích]",  "STRUCT"));
        searchsListSource.add(new Search(19, "Loop", "<Nhãn đích>",  "INTERRUPT"));
        searchsListSource.add(new Search(20, "LEA", "[Toán hạng đích],[Toán hạng nguồn]", "STATEMENT"));
        searchsListSource.add(new Search(21, "Cmp", "[Toán hạng đích], [Toán hạng nguồn]", "MACRO"));
        searchsListSource.add(new Search(22, "Cmp", "[Toán hạng đích], [Toán hạng nguồn]", "MACRO"));
        searchsListSource.add(new Search(23, "LEA", "[Toán hạng đích],[Toán hạng nguồn]", "STATEMENT"));
        searchsListSource.add(new Search(24, "Loop", "<Nhãn đích>",  "INTERRUPT"));

        searchsListType = (ArrayList<Search>) searchsListSource.clone();
        searchsListSearch = (ArrayList<Search>) searchsListSource.clone();


        buttonAll = findViewById(R.id.buttonAll);
        buttonStatement = findViewById(R.id.buttonStatement);
        buttonStruct = findViewById(R.id.buttonStruct);
        buttonInterrupt = findViewById(R.id.buttonInterrupt);
        buttonMacro = findViewById(R.id.buttonMacro);
        searchView = findViewById(R.id.search_bar);
        listViewResult = findViewById(R.id.listResultSearch);
        searchListAdapter = new SearchListAdapter(this, searchsListSearch);
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

        buttonAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setType("ALL");
            }
        });

        buttonStatement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setType("STATEMENT");
            }
        });

        buttonStruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setType("STRUCT");
            }
        });

        buttonInterrupt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setType("INTERRUPT");
            }
        });

        buttonMacro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setType("MACRO");
            }
        });
    }

    protected void searchWithValue(String value) {
        searchsListSearch.clear();

        for (Search search : searchsListType) {
            if (search.getTitle().toLowerCase().contains(value.toLowerCase())) {
                searchsListSearch.add(search);
            }
        }

        valueSearch = value;
        searchListAdapter.notifyDataSetChanged();
    }

    protected void setType(String type) {
        searchsListType.clear();

        switch (type){
            case "ALL": searchsListType = (ArrayList<Search>) searchsListSource.clone(); break;
            default: {
                for (Search search : searchsListSource) {
                    if (search.getType().equals(type)) {
                        searchsListType.add(search);
                    }
                }
            }
        }

        searchWithValue(valueSearch);
    }
}