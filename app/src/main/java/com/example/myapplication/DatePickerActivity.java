package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import static com.example.myapplication.FragmentSignIn.database;

public class DatePickerActivity extends AppCompatActivity {

    EditText startDate;
    EditText endDate;
    EditText codeVoucher;
    Button btn_ok;
    Button btn_cancel;
    int lastSelectedYear;
    int lastSelectedDayOfMonth;
    int lastSelectedMonth;
    int voucherID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        startDate = (EditText) findViewById(R.id.startDate);
        endDate = (EditText) findViewById(R.id.endDate);
        codeVoucher = (EditText) findViewById(R.id.add_code_voucher);
        btn_ok = (Button) findViewById(R.id.btn_oke_date);
        btn_cancel = (Button) findViewById(R.id.btn_cancel_date);
        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        startDate.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        lastSelectedYear = year;
                        lastSelectedMonth = month;
                        lastSelectedDayOfMonth = dayOfMonth;
                    }
                };
                DatePickerDialog datePickerDialog;
                datePickerDialog = new DatePickerDialog(DatePickerActivity.this,
                        dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);
                datePickerDialog.show();
            }
        });
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        endDate.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        lastSelectedYear = year;
                        lastSelectedMonth = month;
                        lastSelectedDayOfMonth = dayOfMonth;
                    }
                };
                DatePickerDialog datePickerDialog;
                datePickerDialog = new DatePickerDialog(DatePickerActivity.this,
                        dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);
                datePickerDialog.show();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK, null);
               finish();
            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check ddax
                if(isEmpty(codeVoucher) == true || isEmpty(startDate) == true || isEmpty(endDate) == true)
                {
                    Toast.makeText(DatePickerActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else{
                    voucherID = getIntent().getIntExtra("voucherID", 0);
                    // insert nốt dữ liệu vào db
                   database.QueryData("update vouchers set vouchercode = "+codeVoucher.getText().toString()+",  startdate ="+startDate.getText().toString()+" , enddate = "+endDate.getText().toString()+" WHERE voucherid = "+voucherID+";");
                    // chuyển về trạng thái ban đầu
                    setResult(RESULT_OK, null);
                    finish();
                }

            }
        });
    }
    private boolean isEmpty(EditText editText){
        if(editText.getText().toString().trim().length() > 0){
            return false;
        }
        return true;
    }
}