package com.dictionaryassembly.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dictionaryassembly.AssemblyEditActivity.AssemblyEditActivity;
import com.dictionaryassembly.Objects.EnumType;
import com.dictionaryassembly.R;
import com.dictionaryassembly.AssemblyListActivity.AssemblyListActivity;

public class FragmentOptions extends Fragment {

    private LinearLayout buttonStatement, buttonStruct, buttonInterrupt, buttonMacro;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_options, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Init(view);

        buttonStatement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AssemblyListActivity.class);
                intent.putExtra("TYPE", EnumType.STATEMENT);
                startActivity(intent);
            }
        });

        buttonStruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AssemblyListActivity.class);
                intent.putExtra("TYPE", EnumType.STRUCT);
                startActivity(intent);
            }
        });

        buttonInterrupt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AssemblyListActivity.class);
                intent.putExtra("TYPE", EnumType.INTERRUPT);
                startActivity(intent);
            }
        });

        buttonMacro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AssemblyListActivity.class);
                intent.putExtra("TYPE", EnumType.MACRO);
                startActivity(intent);
            }
        });

    }

    private void Init(View view){
        buttonStatement = view.findViewById(R.id.buttonStatement);
        buttonStruct = view.findViewById(R.id.buttonStruct);
        buttonInterrupt = view.findViewById(R.id.buttonInterrupt);
        buttonMacro = view.findViewById(R.id.buttonMacro);
    }
}