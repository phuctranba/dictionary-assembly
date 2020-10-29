package com.dictionaryassembly.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.dictionaryassembly.AssemblyDetailActivity.AssemblyDetailActivity;
import com.dictionaryassembly.AssemblyEditActivity.AssemblyEditActivity;
import com.dictionaryassembly.AssemblyListActivity.AssemblyListActivity;
import com.dictionaryassembly.HistoryActivity.HistoryActivity;
import com.dictionaryassembly.LoginActivity.LoginActivity;
import com.dictionaryassembly.LoginActivity.SignInFragment;
import com.dictionaryassembly.Objects.AssemblyForm;
import com.dictionaryassembly.Objects.DatabaseHelper;
import com.dictionaryassembly.Objects.EnumType;
import com.dictionaryassembly.Objects.History;
import com.dictionaryassembly.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FragmentHistory extends Fragment {

    private HistoryAdapter historyAdapter;
    private ListView listViewHistory;
    private ImageView expandImage;
    List<History> historyList;
    DatabaseHelper databaseHelper;

    public FragmentHistory() {
        // Required empty public constructor
    }

    public static FragmentHistory newInstance() {
        FragmentHistory fragment = new FragmentHistory();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("phuc", "Init: 2");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("phuc", "Init: 1");
        Init(view);

        expandImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HistoryActivity.class));
            }
        });


        listViewHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AssemblyForm assemblyForm = databaseHelper.getByIDTypeAssembly(historyList.get(i).getType(),historyList.get(i).getID());
                if(assemblyForm!=null){
                    Intent intent = new Intent(getActivity(), AssemblyDetailActivity.class);
                    intent.putExtra("ITEM", assemblyForm);
                    startActivity(intent);
                }else {
                    Toast.makeText(getActivity(), "Nội dung không còn tồn tại!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Init(View view) {
        listViewHistory = view.findViewById(R.id.listviewHistory);
        expandImage = view.findViewById(R.id.imageExpand);

        databaseHelper = new DatabaseHelper(getActivity());
        historyList = new ArrayList<>();

        loadHistory();

        listViewHistory.setAdapter(historyAdapter);
    }

    private void loadHistory(){

        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting()) {
            historyAdapter = new HistoryAdapter(getActivity(),historyList);

            FirebaseDatabase.getInstance().getReference("users")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("history")
                    .addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            History history = snapshot.getValue(History.class);
                            if (historyList.size() < 50) historyList.add(history);
                            databaseHelper.addHistory(history);
                            historyAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                            History history = snapshot.getValue(History.class);
                            historyList.remove(history);
                            databaseHelper.deleteHistory(history);
                            historyAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }else {
            historyList = new ArrayList<>(databaseHelper.getAllHistory());
            historyAdapter = new HistoryAdapter(getActivity(),historyList);
        }
    }


}