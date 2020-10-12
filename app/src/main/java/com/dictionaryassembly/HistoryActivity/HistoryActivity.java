package com.dictionaryassembly.HistoryActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.dictionaryassembly.MainActivity.HistoryAdapter;
import com.dictionaryassembly.Objects.History;
import com.dictionaryassembly.R;

import java.util.ArrayList;
import java.util.Collections;

public class HistoryActivity extends AppCompatActivity {

    private HistoryActivityAdapter historyAdapter;
    private ArrayList<History> historyArrayList;
    private ListView listViewHistory;
    private LinearLayout buttonSortDate, buttonSortTitle;
    private ImageView imageViewSortDate, imageViewSortTitle;
    private Boolean sortDate, sortTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Init();
        imageViewSortDate.setVisibility(View.GONE);
        Collections.sort(historyArrayList,History.sortTitleDecrease);
        listViewHistory.setAdapter(historyAdapter);

        buttonSortDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(historyArrayList, sortDate?History.sortDateIncrease:History.sortDateDecrease);
                imageViewSortDate.setImageResource(sortDate?R.drawable.icon_arrow_up:R.drawable.icon_arrow_down);
                sortDate = !sortDate;
                imageViewSortTitle.setVisibility(View.GONE);
                imageViewSortDate.setVisibility(View.VISIBLE);
                historyAdapter.notifyDataSetChanged();
            }
        });

        buttonSortTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(historyArrayList, sortTitle?History.sortTitleIncrease:History.sortTitleDecrease);
                imageViewSortTitle.setImageResource(sortTitle?R.drawable.icon_arrow_up:R.drawable.icon_arrow_down);
                sortTitle = !sortTitle;
                imageViewSortDate.setVisibility(View.GONE);
                imageViewSortTitle.setVisibility(View.VISIBLE);
                historyAdapter.notifyDataSetChanged();
            }
        });
    }

    private void Init(){
        historyArrayList = new ArrayList<>();

        historyArrayList.add(new History(1, "Mov", " [Toán hạng đích], [Toán hạng nguồn]", "STATEMENT"));
        historyArrayList.add(new History(2, "Inc", "[Toán hạng đích]", "STRUCT"));
        historyArrayList.add(new History(3, "Loop", "<Nhãn đích>", "INTERRUPT"));
        historyArrayList.add(new History(4, "LEA", "[Toán hạng đích],[Toán hạng nguồn]","STATEMENT"));
        historyArrayList.add(new History(5, "Cmp", "[Toán hạng đích], [Toán hạng nguồn]", "MACRO"));
        historyArrayList.add(new History(6, "Cmp", "[Toán hạng đích], [Toán hạng nguồn]", "MACRO"));
        historyArrayList.add(new History(7, "LEA", "[Toán hạng đích],[Toán hạng nguồn]", "STATEMENT"));
        historyArrayList.add(new History(9, "Mov", " [Toán hạng đích], [Toán hạng nguồn]", "STATEMENT"));
        historyArrayList.add(new History(10, "Inc", "[Toán hạng đích]", "STRUCT"));
        historyArrayList.add(new History(11, "Loop", "<Nhãn đích>",  "INTERRUPT"));
        historyArrayList.add(new History(12, "LEA", "[Toán hạng đích],[Toán hạng nguồn]", "STATEMENT"));
        historyArrayList.add(new History(13, "Cmp", "[Toán hạng đích], [Toán hạng nguồn]", "MACRO"));
        historyArrayList.add(new History(14, "Cmp", "[Toán hạng đích], [Toán hạng nguồn]", "MACRO"));
        historyArrayList.add(new History(15, "LEA", "[Toán hạng đích],[Toán hạng nguồn]", "STATEMENT"));
        historyArrayList.add(new History(16, "Loop", "<Nhãn đích>",  "INTERRUPT"));
        historyArrayList.add(new History(17, "Mov", " [Toán hạng đích], [Toán hạng nguồn]", "STATEMENT"));
        historyArrayList.add(new History(18, "Inc", "[Toán hạng đích]",  "STRUCT"));
        historyArrayList.add(new History(19, "Loop", "<Nhãn đích>",  "INTERRUPT"));
        historyArrayList.add(new History(20, "LEA", "[Toán hạng đích],[Toán hạng nguồn]", "STATEMENT"));
        historyArrayList.add(new History(21, "Cmp", "[Toán hạng đích], [Toán hạng nguồn]", "MACRO"));
        historyArrayList.add(new History(22, "Cmp", "[Toán hạng đích], [Toán hạng nguồn]", "MACRO"));
        historyArrayList.add(new History(23, "LEA", "[Toán hạng đích],[Toán hạng nguồn]", "STATEMENT"));
        historyArrayList.add(new History(24, "Loop", "<Nhãn đích>",  "INTERRUPT"));


        sortDate=true;
        sortTitle=true;
        buttonSortDate = findViewById(R.id.buttonSortDate);
        buttonSortTitle = findViewById(R.id.buttonSortTitle);
        imageViewSortDate = findViewById(R.id.imageViewSortDate);
        imageViewSortTitle = findViewById(R.id.imageViewSortTitle);
        listViewHistory = findViewById(R.id.listHistory);
        listViewHistory = findViewById(R.id.listHistory);
        historyAdapter = new HistoryActivityAdapter(this, historyArrayList);
    }
}