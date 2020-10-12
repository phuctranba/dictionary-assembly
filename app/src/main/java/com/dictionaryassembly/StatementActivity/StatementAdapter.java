package com.dictionaryassembly.StatementActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dictionaryassembly.HistoryActivity.HistoryActivityAdapter;
import com.dictionaryassembly.Objects.History;
import com.dictionaryassembly.Objects.Statement;
import com.dictionaryassembly.R;

import java.util.ArrayList;

public class StatementAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Statement> data;

    public StatementAdapter(Context context, ArrayList<Statement> data) {
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
        public TextView textViewDescription;
        public TextView textViewTitle;
        public TextView textViewContent;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null) {
            view = layoutInflater.inflate(R.layout.item_list_statement, null);
            viewHolder = new ViewHolder();
            viewHolder.textViewDescription = (TextView) view.findViewById(R.id.textViewDescription);
            viewHolder.textViewTitle = (TextView) view.findViewById(R.id.textViewTitle);
            viewHolder.textViewContent = (TextView) view.findViewById(R.id.textViewContent);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }

        Statement statement = data.get(i);

        viewHolder.textViewDescription.setText(statement.getDescription());
        viewHolder.textViewTitle.setText(statement.getStatement());
        viewHolder.textViewContent.setText(statement.getContent());

        return view;
    }

}
