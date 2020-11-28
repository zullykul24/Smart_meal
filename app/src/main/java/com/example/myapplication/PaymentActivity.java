package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_activity);

        ListView listViewPaymentTable;
        ImageButton backToHomeBtn;
        ArrayList<PaymentTableItem> paymentTableItemArrayList;
        PaymentTableItemAdapter paymentTableItemAdapter;
        listViewPaymentTable = findViewById(R.id.list_payment_item);

        paymentTableItemArrayList = new ArrayList<>();
        paymentTableItemArrayList.add(new PaymentTableItem("Bàn 1"));
        paymentTableItemArrayList.add(new PaymentTableItem("Bàn 2"));
        paymentTableItemArrayList.add(new PaymentTableItem("Bàn 3"));
        paymentTableItemArrayList.add(new PaymentTableItem("Bàn 4"));
        paymentTableItemArrayList.add(new PaymentTableItem("Bàn 5"));

        paymentTableItemAdapter = new PaymentTableItemAdapter(PaymentActivity.this,R.layout.payment_item,paymentTableItemArrayList);
        listViewPaymentTable.setAdapter(paymentTableItemAdapter);

        backToHomeBtn = (ImageButton)findViewById(R.id.payment_backtohome_btn);
        backToHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToHome = new Intent(PaymentActivity.this, MainActivity.class);
                setResult(RESULT_OK, intentToHome);
                finish();
            }
        });
        listViewPaymentTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentToPayBill = new Intent(PaymentActivity.this, PayBillActivity.class);
                startActivity(intentToPayBill);
            }
        });
    }
}
