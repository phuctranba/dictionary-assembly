package com.dictionaryassembly.MainActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dictionaryassembly.Objects.EnumType;
import com.dictionaryassembly.Objects.History;
import com.dictionaryassembly.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoryAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<History> data;

    public HistoryAdapter(Context context, List<History> data) {
        Collections.sort(data,History.sortDateDecrease);
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
        public TextView textViewTitle;
        public TextView textViewContent;
        public ImageView imageView;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null) {
            view = layoutInflater.inflate(R.layout.item_list_history_main, null);
            viewHolder = new ViewHolder();
            viewHolder.textViewTitle = (TextView) view.findViewById(R.id.textViewTitle);
            viewHolder.textViewContent = (TextView) view.findViewById(R.id.textViewContent);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.imageType);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }

        History history = data.get(i);

        Picasso.get().load(iconTypeFactory(history.getType())).into(viewHolder.imageView);
        viewHolder.textViewTitle.setText(history.getTitle());
        viewHolder.textViewContent.setText(history.getContent());

        return view;
    }

    private int iconTypeFactory(EnumType type){
        switch (type){
            case STATEMENT: return R.drawable.icon_lenh;
            case STRUCT: return R.drawable.icon_structure;
            case INTERRUPT: return R.drawable.icon_ngat;
            case MACRO: return R.drawable.icon_macro;
            default: return R.drawable.icon_lenh;
        }
    }
}
