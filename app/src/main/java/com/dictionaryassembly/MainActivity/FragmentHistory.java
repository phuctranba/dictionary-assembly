package com.dictionaryassembly.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.dictionaryassembly.HistoryActivity.HistoryActivity;
import com.dictionaryassembly.LoginActivity.SignInFragment;
import com.dictionaryassembly.Objects.History;
import com.dictionaryassembly.R;

import java.util.ArrayList;

public class FragmentHistory extends Fragment {

    private HistoryAdapter historyAdapter;
    private ArrayList<History> historyArrayList;
    private ListView listViewHistory;
    private ImageView expandImage;

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

        listViewHistory.setAdapter(historyAdapter);

        expandImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HistoryActivity.class));
            }
        });
    }

    private void Init(View view) {
        historyArrayList = new ArrayList<>();
//        historyArrayList.add(new History(1, "Mov", " [Toán hạng đích], [Toán hạng nguồn]", "Lấy nội dung (giá trị) của [Toán hạng nguồn] đặt vào [Toán hạng đích]. Nội dung của [Toán hạng nguồn] không bị thay đổi.", "STATEMENT"));
//        historyArrayList.add(new History(2, "Inc", "[Toán hạng đích]", "Lệnh Inc (Increment): làm tăng giá trị của [Toán hạng đích] lên 1 đơn vị.", "STRUCT"));
//        historyArrayList.add(new History(3, "Loop", "<Nhãn đích>", "Khi gặp lệnh này chương trình sẽ lặp lại việc thực hiện các lệnh sau <Nhãn lệnh> đủ n lần, với n được đặt trước trong thanh ghi CX. Sau mỗi lần lặp CX tự động giảm 1 đơn vị (Cx = Cx – 1) và lệnh lặp sẽ dừng khi Cx = 0.", "INTERRUPT"));
//        historyArrayList.add(new History(4, "LEA", "[Toán hạng đích],[Toán hạng nguồn]", "[Toán hạng đích]: Là các thanh ghi 16 bít. [Toán hạng nguồn]: Là địa chỉ của một vùng nhớ hay tên của một biến.", "STATEMENT"));
//        historyArrayList.add(new History(5, "Cmp", "[Toán hạng đích], [Toán hạng nguồn]", "Lệnh Cmp (Compare) được sử dụng để so sánh giá trị/nội dung của [Toán hạng đích] so với [Toán hạng nguồn]. Tương tự như lệnh Sub, nó lấy [Toán hạng đích] trừ đi [Toán hạng nguồn] nhưng kết quả không làm thay đổi [Toán hạng đích] mà chỉ làm thay đổi giá trị của một số cờ hiệu: CF, ZF, OF,…", "MACRO"));
//        historyArrayList.add(new History(6, "Cmp", "[Toán hạng đích], [Toán hạng nguồn]", "Lệnh Cmp (Compare) được sử dụng để so sánh giá trị/nội dung của [Toán hạng đích] so với [Toán hạng nguồn]. Tương tự như lệnh Sub, nó lấy [Toán hạng đích] trừ đi [Toán hạng nguồn] nhưng kết quả không làm thay đổi [Toán hạng đích] mà chỉ làm thay đổi giá trị của một số cờ hiệu: CF, ZF, OF,…", "MACRO"));
//        historyArrayList.add(new History(7, "LEA", "[Toán hạng đích],[Toán hạng nguồn]", "[Toán hạng đích]: Là các thanh ghi 16 bít. [Toán hạng nguồn]: Là địa chỉ của một vùng nhớ hay tên của một biến.", "STATEMENT"));
//        historyArrayList.add(new History(9, "Mov", " [Toán hạng đích], [Toán hạng nguồn]", "Lấy nội dung (giá trị) của [Toán hạng nguồn] đặt vào [Toán hạng đích]. Nội dung của [Toán hạng nguồn] không bị thay đổi.", "STATEMENT"));
//        historyArrayList.add(new History(10, "Inc", "[Toán hạng đích]", "Lệnh Inc (Increment): làm tăng giá trị của [Toán hạng đích] lên 1 đơn vị.", "STRUCT"));
//        historyArrayList.add(new History(11, "Loop", "<Nhãn đích>", "Khi gặp lệnh này chương trình sẽ lặp lại việc thực hiện các lệnh sau <Nhãn lệnh> đủ n lần, với n được đặt trước trong thanh ghi CX. Sau mỗi lần lặp CX tự động giảm 1 đơn vị (Cx = Cx – 1) và lệnh lặp sẽ dừng khi Cx = 0.", "INTERRUPT"));
//        historyArrayList.add(new History(12, "LEA", "[Toán hạng đích],[Toán hạng nguồn]", "[Toán hạng đích]: Là các thanh ghi 16 bít. [Toán hạng nguồn]: Là địa chỉ của một vùng nhớ hay tên của một biến.", "STATEMENT"));
//        historyArrayList.add(new History(13, "Cmp", "[Toán hạng đích], [Toán hạng nguồn]", "Lệnh Cmp (Compare) được sử dụng để so sánh giá trị/nội dung của [Toán hạng đích] so với [Toán hạng nguồn]. Tương tự như lệnh Sub, nó lấy [Toán hạng đích] trừ đi [Toán hạng nguồn] nhưng kết quả không làm thay đổi [Toán hạng đích] mà chỉ làm thay đổi giá trị của một số cờ hiệu: CF, ZF, OF,…", "MACRO"));
//        historyArrayList.add(new History(14, "Cmp", "[Toán hạng đích], [Toán hạng nguồn]", "Lệnh Cmp (Compare) được sử dụng để so sánh giá trị/nội dung của [Toán hạng đích] so với [Toán hạng nguồn]. Tương tự như lệnh Sub, nó lấy [Toán hạng đích] trừ đi [Toán hạng nguồn] nhưng kết quả không làm thay đổi [Toán hạng đích] mà chỉ làm thay đổi giá trị của một số cờ hiệu: CF, ZF, OF,…", "MACRO"));
//        historyArrayList.add(new History(15, "LEA", "[Toán hạng đích],[Toán hạng nguồn]", "[Toán hạng đích]: Là các thanh ghi 16 bít. [Toán hạng nguồn]: Là địa chỉ của một vùng nhớ hay tên của một biến.", "STATEMENT"));
//        historyArrayList.add(new History(16, "Loop", "<Nhãn đích>", "Khi gặp lệnh này chương trình sẽ lặp lại việc thực hiện các lệnh sau <Nhãn lệnh> đủ n lần, với n được đặt trước trong thanh ghi CX. Sau mỗi lần lặp CX tự động giảm 1 đơn vị (Cx = Cx – 1) và lệnh lặp sẽ dừng khi Cx = 0.", "INTERRUPT"));
//        historyArrayList.add(new History(17, "Mov", " [Toán hạng đích], [Toán hạng nguồn]", "Lấy nội dung (giá trị) của [Toán hạng nguồn] đặt vào [Toán hạng đích]. Nội dung của [Toán hạng nguồn] không bị thay đổi.", "STATEMENT"));
//        historyArrayList.add(new History(18, "Inc", "[Toán hạng đích]", "Lệnh Inc (Increment): làm tăng giá trị của [Toán hạng đích] lên 1 đơn vị.", "STRUCT"));
//        historyArrayList.add(new History(19, "Loop", "<Nhãn đích>", "Khi gặp lệnh này chương trình sẽ lặp lại việc thực hiện các lệnh sau <Nhãn lệnh> đủ n lần, với n được đặt trước trong thanh ghi CX. Sau mỗi lần lặp CX tự động giảm 1 đơn vị (Cx = Cx – 1) và lệnh lặp sẽ dừng khi Cx = 0.", "INTERRUPT"));
//        historyArrayList.add(new History(20, "LEA", "[Toán hạng đích],[Toán hạng nguồn]", "[Toán hạng đích]: Là các thanh ghi 16 bít. [Toán hạng nguồn]: Là địa chỉ của một vùng nhớ hay tên của một biến.", "STATEMENT"));
//        historyArrayList.add(new History(21, "Cmp", "[Toán hạng đích], [Toán hạng nguồn]", "Lệnh Cmp (Compare) được sử dụng để so sánh giá trị/nội dung của [Toán hạng đích] so với [Toán hạng nguồn]. Tương tự như lệnh Sub, nó lấy [Toán hạng đích] trừ đi [Toán hạng nguồn] nhưng kết quả không làm thay đổi [Toán hạng đích] mà chỉ làm thay đổi giá trị của một số cờ hiệu: CF, ZF, OF,…", "MACRO"));
//        historyArrayList.add(new History(22, "Cmp", "[Toán hạng đích], [Toán hạng nguồn]", "Lệnh Cmp (Compare) được sử dụng để so sánh giá trị/nội dung của [Toán hạng đích] so với [Toán hạng nguồn]. Tương tự như lệnh Sub, nó lấy [Toán hạng đích] trừ đi [Toán hạng nguồn] nhưng kết quả không làm thay đổi [Toán hạng đích] mà chỉ làm thay đổi giá trị của một số cờ hiệu: CF, ZF, OF,…", "MACRO"));
//        historyArrayList.add(new History(23, "LEA", "[Toán hạng đích],[Toán hạng nguồn]", "[Toán hạng đích]: Là các thanh ghi 16 bít. [Toán hạng nguồn]: Là địa chỉ của một vùng nhớ hay tên của một biến.", "STATEMENT"));
//        historyArrayList.add(new History(24, "Loop", "<Nhãn đích>", "Khi gặp lệnh này chương trình sẽ lặp lại việc thực hiện các lệnh sau <Nhãn lệnh> đủ n lần, với n được đặt trước trong thanh ghi CX. Sau mỗi lần lặp CX tự động giảm 1 đơn vị (Cx = Cx – 1) và lệnh lặp sẽ dừng khi Cx = 0.", "INTERRUPT"));

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

        listViewHistory = view.findViewById(R.id.listviewHistory);
        expandImage = view.findViewById(R.id.imageExpand);
        historyAdapter = new HistoryAdapter(getActivity(), historyArrayList);
    }


}