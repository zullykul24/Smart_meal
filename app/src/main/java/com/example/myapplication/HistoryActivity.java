package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import static com.example.myapplication.FragmentSignIn.database;
public class HistoryActivity extends AppCompatActivity {
    ListView paymentedList;
    ArrayList<HistoryItem> historyItemArrayList;
    HistoryAdapter historyAdapter;
    EditText choosedDate;
    int lastSelectedYear;
    int lastSelectedMonth;
    int lastSelectedDayOfMonth;
    int Exit_Signal = 1111;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);
        Calendar cal = Calendar.getInstance();
        lastSelectedYear = cal.get(Calendar.YEAR);
        lastSelectedDayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        // vì là trong Java thì tháng nó sẽ lấy từ ) tới 11 nên là  phải cộng thêm 1 để lấy đúng với giá trị hiện tại
        lastSelectedMonth = cal.get(Calendar.MONTH);
        historyItemArrayList = new ArrayList<>();
        paymentedList = (ListView) findViewById(R.id.list_history_item);
        choosedDate = (EditText) findViewById(R.id.choose_date_paymented);

        Cursor cursor = database.getData("select * from payments order by paymentId desc");
        while (cursor.moveToNext()) {
            historyItemArrayList.add(new HistoryItem(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getDouble(5),
                    cursor.getLong(6),
                    cursor.getString(7)
            ));
            historyAdapter = new HistoryAdapter(HistoryActivity.this, R.layout.paymented_item, historyItemArrayList);
           choosedDate.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                       @Override
                       public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                           choosedDate.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                           lastSelectedYear = year;
                           lastSelectedMonth = month;
                           lastSelectedDayOfMonth = dayOfMonth;

                       }
                   };
                   DatePickerDialog datePickerDialog;
                   datePickerDialog = new DatePickerDialog(HistoryActivity.this, android.R.style.Theme_Holo_Light_Dialog,
                           dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);
                   datePickerDialog.show();
               }
           });
           paymentedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   Intent intent = new Intent(HistoryActivity.this, ViewPaymentActivity.class );
                   intent.putExtra("paymentId", historyItemArrayList.get(position).getPaymentId());
                   startActivityForResult(intent, Exit_Signal);
               }
           });
            historyAdapter.notifyDataSetChanged();
            paymentedList.setAdapter(historyAdapter);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1111 & resultCode == -1){
            finish();
        }
    }
}
