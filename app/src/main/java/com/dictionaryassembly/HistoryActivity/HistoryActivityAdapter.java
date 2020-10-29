package com.dictionaryassembly.HistoryActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dictionaryassembly.Objects.EnumType;
import com.dictionaryassembly.Objects.History;
import com.dictionaryassembly.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivityAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<History> data;

    public HistoryActivityAdapter(Context context, List<History> data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private static class ViewHolder {
        public TextView textViewType;
        public TextView textViewTitle;
        public TextView textViewContent;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null) {
            view = layoutInflater.inflate(R.layout.item_list_history_search_activity, null);
            viewHolder = new ViewHolder();
            viewHolder.textViewType = (TextView) view.findViewById(R.id.textViewType);
            viewHolder.textViewTitle = (TextView) view.findViewById(R.id.textViewTitle);
            viewHolder.textViewContent = (TextView) view.findViewById(R.id.textViewContent);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }

        History history = data.get(i);

        viewHolder.textViewType.setText(typeFactory(history.getType()));
        viewHolder.textViewTitle.setText(history.getTitle());
        viewHolder.textViewContent.setText(history.getContent());

        return view;
    }

    private String typeFactory(EnumType type){
        switch (type){
            case STATEMENT: return "Lệnh";
            case STRUCT: return "Cấu trúc";
            case INTERRUPT: return "Ngắt";
            case MACRO: return "Macro";
            default: return "Lệnh";
        }
    }
}
