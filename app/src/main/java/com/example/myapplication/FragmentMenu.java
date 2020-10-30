package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import static com.example.myapplication.FragmentSignIn.database;

public class FragmentMenu extends Fragment {


    MenuFoodItemAdapter menuFoodItemAdapter;
    ArrayList<MenuFoodItem> menuItemArrayList;
    ImageButton btnAdd;
    ListView lvFood, listViewFood;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        //khai bao
        lvFood = (ListView) rootView.findViewById(R.id.list_menu_food);


        menuItemArrayList = new ArrayList<>();
        menuFoodItemAdapter = new MenuFoodItemAdapter(getContext(), R.layout.menu_food_item, menuItemArrayList);
        lvFood.setAdapter(menuFoodItemAdapter);


       /* btnAdd = (ImageButton) rootView.findViewById(R.id.buttonAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), AddFood.class));
            }
        });


        // đổ dữ liệu trong database ra ne
        Cursor cursor = database.getData("SELECT * from dish");
        while (cursor.moveToNext()){
            menuItemArrayList.add(new MenuFoodItem(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getDouble(3),
                    cursor.getBlob(4)

            ));
        }*/
        menuFoodItemAdapter.notifyDataSetChanged();
        return rootView;
    }
}
