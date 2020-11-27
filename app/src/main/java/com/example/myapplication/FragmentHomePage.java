package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
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

public class FragmentHomePage extends Fragment {
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_homepage, container, false);
      /*  rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });*/

        Button thanhToan = (Button)rootView.findViewById(R.id.thanh_toan);
        thanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToPayment = new Intent(getActivity().getApplicationContext(),PaymentActivity.class);
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
//            hotArrayList.add(new MenuFoodItem("Món 1", 20.000));
//            hotArrayList.add(new MenuFoodItem("Món 2", 25.000));
//        hotArrayList.add(new MenuFoodItem("Món 2", 25.000));
//        hotArrayList.add(new MenuFoodItem("Món 2", 25.000));
//        hotArrayList.add(new MenuFoodItem("Món 2", 25.000));
//        hotArrayList.add(new MenuFoodItem("Món 2", 25.000));
            RecycleItemAdapter adapter = new RecycleItemAdapter(hotArrayList, getActivity().getApplicationContext());
            recyclerView.setAdapter(adapter);

        return rootView;
    }

}
