package com.example.myapplication;
import android.database.Cursor;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.myapplication.FragmentSignIn.database;

public class FragmentHomePage extends Fragment {
    int accountId;
    public void getInfor(int accountId) {
        this.accountId = accountId;
    }
    public  int getAccountId(){
        return this.accountId;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_homepage, container, false);

        Button thanhToan = (Button)rootView.findViewById(R.id.thanh_toan);
        thanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToPayment = new Intent(getActivity().getApplicationContext(),PaymentActivity.class);
                intentToPayment.putExtra("accountId", getAccountId());
                startActivityForResult(intentToPayment,65);
            }
        });

            RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.recycle_view_hot_items);
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(decoration);
            ArrayList<MenuFoodItem> hotArrayList = new ArrayList<>();
        Cursor cursor = database.getData("SELECT * from dish where dishId in (Select dishId from orderdetails group by dishId order by sum(orderdetails.quantityOrder) desc) limit  5 ");
        while(cursor.moveToNext()){
            hotArrayList.add(new MenuFoodItem(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getDouble(3),
                    cursor.getBlob(4)));
        }
            RecycleItemAdapter adapter = new RecycleItemAdapter(hotArrayList, getActivity().getApplicationContext());
            recyclerView.setAdapter(adapter);

        return rootView;
    }

}
