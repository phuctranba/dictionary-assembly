package com.dictionaryassembly.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.dictionaryassembly.R;
import com.dictionaryassembly.StatementActivity.StatementActivity;

public class FragmentOptions extends Fragment {

    private LinearLayout buttonStatement;

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
                startActivity(new Intent(getActivity(), StatementActivity.class));
            }
        });

    }

    private void Init(View view){
        buttonStatement = view.findViewById(R.id.buttonStatement);
    }
}