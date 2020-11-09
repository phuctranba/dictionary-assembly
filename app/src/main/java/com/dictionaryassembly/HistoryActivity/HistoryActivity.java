package com.dictionaryassembly.HistoryActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.dictionaryassembly.AssemblyDetailActivity.AssemblyDetailActivity;
import com.dictionaryassembly.MainActivity.HistoryAdapter;
import com.dictionaryassembly.Objects.AssemblyForm;
import com.dictionaryassembly.Objects.DatabaseHelper;
import com.dictionaryassembly.Objects.EnumType;
import com.dictionaryassembly.Objects.History;
import com.dictionaryassembly.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private HistoryActivityAdapter historyAdapter;
    private List<History> historyArrayList;
    private ListView listViewHistory;
    private LinearLayout buttonSortDate, buttonSortTitle;
    private ImageView imageViewSortDate, imageViewSortTitle;
    private Boolean sortDate, sortTitle;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Init();
        imageViewSortTitle.setVisibility(View.GONE);
        Collections.sort(historyArrayList,History.sortDateIncrease);
        listViewHistory.setAdapter(historyAdapter);

        buttonSortDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(historyArrayList, sortDate?History.sortDateDecrease:History.sortDateIncrease);
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

        listViewHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AssemblyForm assemblyForm = databaseHelper.getByIDTypeAssembly(historyArrayList.get(i).getType(),historyArrayList.get(i).getID());
                if(assemblyForm!=null){
                    Intent intent = new Intent(HistoryActivity.this, AssemblyDetailActivity.class);
                    intent.putExtra("ITEM", assemblyForm);
                    startActivity(intent);
                }else {
                    Toast.makeText(HistoryActivity.this, "Nội dung không còn tồn tại!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Init(){
        historyArrayList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(this);

        historyArrayList = databaseHelper.getAllHistory();

        sortDate=true;
        sortTitle=true;
        buttonSortDate = findViewById(R.id.buttonSortDate);
        buttonSortTitle = findViewById(R.id.buttonSortTitle);
        imageViewSortDate = findViewById(R.id.imageViewSortDate);
        imageViewSortTitle = findViewById(R.id.imageViewSortTitle);
        listViewHistory = findViewById(R.id.listHistory);
        listViewHistory.setEmptyView(findViewById(R.id.emptyElement));
        historyAdapter = new HistoryActivityAdapter(this, historyArrayList);
    }
}