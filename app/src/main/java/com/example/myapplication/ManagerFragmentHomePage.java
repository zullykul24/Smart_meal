package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import static com.example.myapplication.FragmentSignIn.database;

public class ManagerFragmentHomePage extends Fragment {
    @Nullable

    Button monmoi;
    Button addFood;
    Button addTable;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.manager_fragment_homepage, container, false);
        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        final FragmentManager fragmentManager = getFragmentManager();
       addFood = (Button) rootView.findViewById(R.id.manager_add_food_btn);
        addTable = (Button) rootView.findViewById(R.id.manager_add_table_btn);
        monmoi = (Button) rootView.findViewById(R.id.manage_new_food);

        monmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityNewFood.class);
                startActivity(intent);
            }
        });
         addFood.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intentFood = new Intent(getActivity(), AddFood.class );
                 startActivity(intentFood);
             }
         });
        addTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInsertTable();
            }
        });

        RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.manager_recycle_view_hot_items);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(decoration);
        ArrayList<MenuFoodItem> hotArrayList = new ArrayList<>();
        // edit list hot food
        // món hot sẽ là món được đặt với số lượng nhiều nhất trong nhà hàng
        //         database.QueryData("create table if not exists dish (dishId integer primary key AUTOINCREMENT, dishName varchar(200) not null, group_id integer not null, price double, image varchar(200))");//database.QueryData("Insert into  staff_group values (0, 'Staff'), (1, 'Management')");
        Cursor  cursor = database.getData("SELECT * from dish where dishId in (Select dishId from orderdetails group by dishId order by sum(orderdetails.quantityOrder) desc) limit  5 ");
        while(cursor.moveToNext()){
            hotArrayList.add(new MenuFoodItem(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getDouble(3),
                    cursor.getBlob(4)));
        }
//        hotArrayList.add(new MenuFoodItem("Món 1", 20.000));
//        hotArrayList.add(new MenuFoodItem("Món 2", 25.000));
//        hotArrayList.add(new MenuFoodItem("Món 2", 25.000));
//        hotArrayList.add(new MenuFoodItem("Món 2", 25.000));
//        hotArrayList.add(new MenuFoodItem("Món 2", 25.000));
//        hotArrayList.add(new MenuFoodItem("Món 2", 25.000));
        RecycleItemAdapter adapter = new RecycleItemAdapter(hotArrayList, getActivity().getApplicationContext());
        recyclerView.setAdapter(adapter);
        return rootView;
    }
    private  void DialogInsertTable(){
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.add_table);
        // Ánh xạ xong r nè

        Button add = (Button) dialog.findViewById(R.id.button_ok);
        Button cancel = (Button) dialog.findViewById(R.id.button_cancel);
        final EditText number_chair = (EditText) dialog.findViewById(R.id.edit_chair_number);

        // sử dụng các button

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!number_chair.getText().toString().equals(""))
                {
                    int num = Integer.parseInt(number_chair.getText().toString()); // lay du lieu trong cai nay nay
                    if(num<=0 || num > 100)
                    {
                        Toast.makeText(getContext(), "Không hợp lệ", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        database.QueryData("insert into group_table values (null, "+num+", 'Empty')");
                        dialog.dismiss();
                    }

                }
                else {
                    Toast.makeText(getContext(), "Vui lòng điền số lượng ghế", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

}
