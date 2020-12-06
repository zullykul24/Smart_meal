package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import static com.example.myapplication.FragmentSignIn.database;

public class PaymentActivity extends AppCompatActivity {
    int REQUEST_EXIT = 2818;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_activity);

        final ListView listViewPaymentTable;
        ImageButton backToHomeBtn;
        final ArrayList<PaymentTableItem> paymentTableItemArrayList;
        PaymentTableItemAdapter paymentTableItemAdapter;
        listViewPaymentTable = findViewById(R.id.list_payment_item);

        paymentTableItemArrayList = new ArrayList<>();
        // lấy tên các bàn mà đang được sử dụng
        Cursor cursor = database.getData("select * from group_table where status = 'Not Empty';");
        while (cursor.moveToNext()){
            paymentTableItemArrayList.add(new PaymentTableItem( cursor.getInt(0)));
        }

        paymentTableItemAdapter = new PaymentTableItemAdapter(PaymentActivity.this,R.layout.payment_item,paymentTableItemArrayList);
        listViewPaymentTable.setAdapter(paymentTableItemAdapter);

        backToHomeBtn = (ImageButton)findViewById(R.id.payment_backtohome_btn);
        backToHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToHome = new Intent(PaymentActivity.this, MainActivity.class);
                setResult(65, intentToHome);
                finish();
            }
        });
        listViewPaymentTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentToPayBill = new Intent(PaymentActivity.this, PayBillActivity.class);
                intentToPayBill.putExtra("BanTen", paymentTableItemArrayList.get(position).getTableName());
                intentToPayBill.putExtra("accountId", getIntent().getIntExtra("accountId", 1));
               // Log.d("check",""+ getIntent().getIntExtra("accountId", 1) );
                startActivityForResult(intentToPayBill, REQUEST_EXIT);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_EXIT) {
            if (resultCode == RESULT_OK) {
                this.finish();

            }
        }
    }
}
