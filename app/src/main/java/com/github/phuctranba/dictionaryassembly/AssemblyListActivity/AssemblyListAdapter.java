package com.github.phuctranba.dictionaryassembly.AssemblyListActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.github.phuctranba.dictionaryassembly.Objects.AssemblyForm;
import com.github.phuctranba.dictionaryassembly.R;

import java.util.List;

public class AssemblyListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<AssemblyForm> data;

    public AssemblyListAdapter(Context context, List<AssemblyForm> data) {
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
            view = layoutInflater.inflate(R.layout.item_list_assembly, null);
            viewHolder = new ViewHolder();
            viewHolder.textViewDescription = (TextView) view.findViewById(R.id.textViewDescription);
            viewHolder.textViewTitle = (TextView) view.findViewById(R.id.textViewTitle);
            viewHolder.textViewContent = (TextView) view.findViewById(R.id.textViewContent);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }

        AssemblyForm assemblyForm = data.get(i);


        viewHolder.textViewTitle.setText(assemblyForm.getTitle());
        switch (assemblyForm.getType()){
            case STATEMENT: {
                viewHolder.textViewDescription.setText(assemblyForm.getDescription());
                viewHolder.textViewContent.setText(assemblyForm.getContent());
                break;
            }
            case STRUCT: {
                viewHolder.textViewDescription.setText(assemblyForm.getDescription());
                viewHolder.textViewContent.setVisibility(View.GONE);
                break;
            }
            case INTERRUPT:{
                viewHolder.textViewDescription.setText(assemblyForm.getTypeInterrupt());
                viewHolder.textViewContent.setVisibility(View.GONE);
                break;
            }
            case MACRO:{
                viewHolder.textViewDescription.setText(assemblyForm.getContent());
                viewHolder.textViewContent.setVisibility(View.GONE);
                break;
            }

        }

        return view;
    }

}
