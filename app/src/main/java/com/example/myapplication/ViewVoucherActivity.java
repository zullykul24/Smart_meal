package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.myapplication.FragmentSignIn.database;

public class ViewVoucherActivity extends AppCompatActivity {
    ListView listVoucher;
    VoucherItemAdapter  voucherItemAdapter;
    ArrayList<VoucherItem> itemArrayList;
    int voucherId;
    long millis=System.currentTimeMillis();
    java.sql.Date date=new java.sql.Date(millis);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_voucher);
        listVoucher  = (ListView) findViewById(R.id.listViewVoucher);
        // hien nhung voucher dang trong thoi gian su dung
        Cursor cursor = database.getData("select * from vouchers where startdate <= "+millis+" and enddate >= "+millis+" ");// where startdate <= "+millis+" and enddate >= "+millis+" ORDER by startdate DESC
        itemArrayList = new ArrayList<>();
        while(cursor.moveToNext()){
            voucherId = cursor.getInt(0);
            Cursor cursor1 = database.getData("select * from conditions where voucherid = "+ voucherId);
            ArrayList<String> conditions = new ArrayList<>();
            while(cursor1.moveToNext()){
                conditions.add(cursor1.getString(1));
            }
            itemArrayList.add( new VoucherItem(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getInt(3),
                            cursor.getString(2),
                            cursor.getLong(4),
                            cursor.getLong(5),
                           conditions
                    )
            );
            Log.d("check", "curent " + millis + " start date" + cursor.getLong(4) + " end date " + cursor.getLong(5) + "ngayf" + date);
        }
        voucherItemAdapter = new VoucherItemAdapter(ViewVoucherActivity.this, R.layout.voucher_item, itemArrayList);
        voucherItemAdapter.notifyDataSetChanged();
        listVoucher.setAdapter(voucherItemAdapter);
    }
}