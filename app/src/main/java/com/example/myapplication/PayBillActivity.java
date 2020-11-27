package com.example.myapplication;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PayBillActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paybill);
        TextView totalPriceText;

        ListView listViewPayBill;
        ArrayList<PayBillItem> payBillItemArrayList;
        PayBillItemAdapter payBillItemAdapter;

        totalPriceText = (TextView)findViewById(R.id.total_price);



        listViewPayBill = (ListView)findViewById(R.id.listview_pay_bill);
        payBillItemArrayList = new ArrayList<>();
        payBillItemArrayList.add(new PayBillItem(1, "xoài non", 3,"20000"));
        payBillItemArrayList.add(new PayBillItem(1, "xoài non", 3,"20000"));
        payBillItemArrayList.add(new PayBillItem(1, "xoài non", 3,"20000"));
        payBillItemArrayList.add(new PayBillItem(1, "xoài non", 3,"20000"));
        payBillItemArrayList.add(new PayBillItem(1, "xoài non", 3,"20000"));
        payBillItemArrayList.add(new PayBillItem(1, "xoài non", 3,"20000"));
        int total = 0;
        for(int i=0; i< payBillItemArrayList.size();i++){

            //đánh lại STT
        payBillItemArrayList.get(i).setSTT(i);

        // tính tiền cho anh
        total = total + Integer.parseInt(payBillItemArrayList.get(i).getPriceTotal());
        }
        /// định dạng tiền 1500000 -> 1,500,000

        DecimalFormat df= new DecimalFormat("###,###,###");
        String priceString = String.valueOf(df.format(Double.valueOf(total)));
        totalPriceText.setText(priceString);



        payBillItemAdapter = new PayBillItemAdapter(PayBillActivity.this, R.layout.bill_item, payBillItemArrayList);
        listViewPayBill.setAdapter(payBillItemAdapter);
    }
}
