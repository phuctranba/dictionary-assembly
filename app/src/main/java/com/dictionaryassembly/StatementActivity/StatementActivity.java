package com.dictionaryassembly.StatementActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.dictionaryassembly.LoginActivity.LoginActivity;
import com.dictionaryassembly.Objects.Statement;
import com.dictionaryassembly.R;
import com.dictionaryassembly.SearchActivity.SearchActivity;
import com.dictionaryassembly.UserActivity.UserActivity;

import java.util.ArrayList;

public class StatementActivity extends AppCompatActivity {

    private StatementAdapter statementAdapter;
    private ArrayList<Statement> statementArrayList;
    private ListView listViewStatement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement);

        Init();


        listViewStatement.setAdapter(statementAdapter);
    }

    private void Init(){
        statementArrayList = new ArrayList<>();
        statementArrayList.add(new Statement(1, "Mov", " [Toán hạng đích], [Toán hạng nguồn]", "Lấy nội dung (giá trị) của [Toán hạng nguồn] đặt vào [Toán hạng đích]. Nội dung của [Toán hạng nguồn] không bị thay đổi."));
        statementArrayList.add(new Statement(2, "Inc", "[Toán hạng đích]", "Lệnh Inc (Increment): làm tăng giá trị của [Toán hạng đích] lên 1 đơn vị."));
        statementArrayList.add(new Statement(3, "Loop", "<Nhãn đích>", "Khi gặp lệnh này chương trình sẽ lặp lại việc thực hiện các lệnh sau <Nhãn lệnh> đủ n lần, với n được đặt trước trong thanh ghi CX. Sau mỗi lần lặp CX tự động giảm 1 đơn vị (Cx = Cx – 1) và lệnh lặp sẽ dừng khi Cx = 0."));
        statementArrayList.add(new Statement(4, "LEA", "[Toán hạng đích],[Toán hạng nguồn]", "[Toán hạng đích]: Là các thanh ghi 16 bít. [Toán hạng nguồn]: Là địa chỉ của một vùng nhớ hay tên của một biến."));
        statementArrayList.add(new Statement(5, "Cmp", "[Toán hạng đích], [Toán hạng nguồn]", "Lệnh Cmp (Compare) được sử dụng để so sánh giá trị/nội dung của [Toán hạng đích] so với [Toán hạng nguồn]. Tương tự như lệnh Sub, nó lấy [Toán hạng đích] trừ đi [Toán hạng nguồn] nhưng kết quả không làm thay đổi [Toán hạng đích] mà chỉ làm thay đổi giá trị của một số cờ hiệu: CF, ZF, OF,…"));
        statementArrayList.add(new Statement(6, "Cmp", "[Toán hạng đích], [Toán hạng nguồn]", "Lệnh Cmp (Compare) được sử dụng để so sánh giá trị/nội dung của [Toán hạng đích] so với [Toán hạng nguồn]. Tương tự như lệnh Sub, nó lấy [Toán hạng đích] trừ đi [Toán hạng nguồn] nhưng kết quả không làm thay đổi [Toán hạng đích] mà chỉ làm thay đổi giá trị của một số cờ hiệu: CF, ZF, OF,…"));
        statementArrayList.add(new Statement(7, "LEA", "[Toán hạng đích],[Toán hạng nguồn]", "[Toán hạng đích]: Là các thanh ghi 16 bít. [Toán hạng nguồn]: Là địa chỉ của một vùng nhớ hay tên của một biến."));
        statementArrayList.add(new Statement(9, "Mov", " [Toán hạng đích], [Toán hạng nguồn]", "Lấy nội dung (giá trị) của [Toán hạng nguồn] đặt vào [Toán hạng đích]. Nội dung của [Toán hạng nguồn] không bị thay đổi."));
        statementArrayList.add(new Statement(10, "Inc", "[Toán hạng đích]", "Lệnh Inc (Increment): làm tăng giá trị của [Toán hạng đích] lên 1 đơn vị."));
        statementArrayList.add(new Statement(11, "Loop", "<Nhãn đích>", "Khi gặp lệnh này chương trình sẽ lặp lại việc thực hiện các lệnh sau <Nhãn lệnh> đủ n lần, với n được đặt trước trong thanh ghi CX. Sau mỗi lần lặp CX tự động giảm 1 đơn vị (Cx = Cx – 1) và lệnh lặp sẽ dừng khi Cx = 0."));
        statementArrayList.add(new Statement(12, "LEA", "[Toán hạng đích],[Toán hạng nguồn]", "[Toán hạng đích]: Là các thanh ghi 16 bít. [Toán hạng nguồn]: Là địa chỉ của một vùng nhớ hay tên của một biến."));
        statementArrayList.add(new Statement(13, "Cmp", "[Toán hạng đích], [Toán hạng nguồn]", "Lệnh Cmp (Compare) được sử dụng để so sánh giá trị/nội dung của [Toán hạng đích] so với [Toán hạng nguồn]. Tương tự như lệnh Sub, nó lấy [Toán hạng đích] trừ đi [Toán hạng nguồn] nhưng kết quả không làm thay đổi [Toán hạng đích] mà chỉ làm thay đổi giá trị của một số cờ hiệu: CF, ZF, OF,…"));
        statementArrayList.add(new Statement(14, "Cmp", "[Toán hạng đích], [Toán hạng nguồn]", "Lệnh Cmp (Compare) được sử dụng để so sánh giá trị/nội dung của [Toán hạng đích] so với [Toán hạng nguồn]. Tương tự như lệnh Sub, nó lấy [Toán hạng đích] trừ đi [Toán hạng nguồn] nhưng kết quả không làm thay đổi [Toán hạng đích] mà chỉ làm thay đổi giá trị của một số cờ hiệu: CF, ZF, OF,…"));
        statementArrayList.add(new Statement(15, "LEA", "[Toán hạng đích],[Toán hạng nguồn]", "[Toán hạng đích]: Là các thanh ghi 16 bít. [Toán hạng nguồn]: Là địa chỉ của một vùng nhớ hay tên của một biến."));
        statementArrayList.add(new Statement(16, "Loop", "<Nhãn đích>", "Khi gặp lệnh này chương trình sẽ lặp lại việc thực hiện các lệnh sau <Nhãn lệnh> đủ n lần, với n được đặt trước trong thanh ghi CX. Sau mỗi lần lặp CX tự động giảm 1 đơn vị (Cx = Cx – 1) và lệnh lặp sẽ dừng khi Cx = 0."));
        statementArrayList.add(new Statement(17, "Mov", " [Toán hạng đích], [Toán hạng nguồn]", "Lấy nội dung (giá trị) của [Toán hạng nguồn] đặt vào [Toán hạng đích]. Nội dung của [Toán hạng nguồn] không bị thay đổi."));
        statementArrayList.add(new Statement(18, "Inc", "[Toán hạng đích]", "Lệnh Inc (Increment): làm tăng giá trị của [Toán hạng đích] lên 1 đơn vị."));
        statementArrayList.add(new Statement(19, "Loop", "<Nhãn đích>", "Khi gặp lệnh này chương trình sẽ lặp lại việc thực hiện các lệnh sau <Nhãn lệnh> đủ n lần, với n được đặt trước trong thanh ghi CX. Sau mỗi lần lặp CX tự động giảm 1 đơn vị (Cx = Cx – 1) và lệnh lặp sẽ dừng khi Cx = 0."));
        statementArrayList.add(new Statement(20, "LEA", "[Toán hạng đích],[Toán hạng nguồn]", "[Toán hạng đích]: Là các thanh ghi 16 bít. [Toán hạng nguồn]: Là địa chỉ của một vùng nhớ hay tên của một biến."));
        statementArrayList.add(new Statement(21, "Cmp", "[Toán hạng đích], [Toán hạng nguồn]", "Lệnh Cmp (Compare) được sử dụng để so sánh giá trị/nội dung của [Toán hạng đích] so với [Toán hạng nguồn]. Tương tự như lệnh Sub, nó lấy [Toán hạng đích] trừ đi [Toán hạng nguồn] nhưng kết quả không làm thay đổi [Toán hạng đích] mà chỉ làm thay đổi giá trị của một số cờ hiệu: CF, ZF, OF,…"));
        statementArrayList.add(new Statement(22, "Cmp", "[Toán hạng đích], [Toán hạng nguồn]", "Lệnh Cmp (Compare) được sử dụng để so sánh giá trị/nội dung của [Toán hạng đích] so với [Toán hạng nguồn]. Tương tự như lệnh Sub, nó lấy [Toán hạng đích] trừ đi [Toán hạng nguồn] nhưng kết quả không làm thay đổi [Toán hạng đích] mà chỉ làm thay đổi giá trị của một số cờ hiệu: CF, ZF, OF,…"));
        statementArrayList.add(new Statement(23, "LEA", "[Toán hạng đích],[Toán hạng nguồn]", "[Toán hạng đích]: Là các thanh ghi 16 bít. [Toán hạng nguồn]: Là địa chỉ của một vùng nhớ hay tên của một biến."));
        statementArrayList.add(new Statement(24, "Loop", "<Nhãn đích>", "Khi gặp lệnh này chương trình sẽ lặp lại việc thực hiện các lệnh sau <Nhãn lệnh> đủ n lần, với n được đặt trước trong thanh ghi CX. Sau mỗi lần lặp CX tự động giảm 1 đơn vị (Cx = Cx – 1) và lệnh lặp sẽ dừng khi Cx = 0."));


        listViewStatement = findViewById(R.id.listStatement);
        statementAdapter = new StatementAdapter(this, statementArrayList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_open_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {

            startActivity(new Intent(StatementActivity.this, SearchActivity.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}