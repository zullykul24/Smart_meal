package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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
    EditText searchText;
    ListView lvFood, listViewFood;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.choose_foods, container, false);
        //khai bao



        searchText = (EditText)rootView.findViewById(R.id.searchText);

        listViewFood = (ListView)rootView.findViewById(R.id.listViewFoodMenu);
        menuItemArrayList = new ArrayList<>();
        menuFoodItemAdapter =  new MenuFoodItemAdapter(getContext(),R.layout.menu_food_item, menuItemArrayList);
        // đổ dữ liệu trong database ra ne
        listViewFood.setAdapter(menuFoodItemAdapter);
        Cursor cursor = database.getData("SELECT * from dish");
        while (cursor.moveToNext()) {
            menuItemArrayList.add(new MenuFoodItem(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getDouble(3),
                    cursor.getBlob(4)
            ));
        }
//        menuItemArrayList.add(new MenuFoodItem("Lẩu đầu cá",180.000, R.drawable.mango));
//        menuItemArrayList.add(new MenuFoodItem("Lẩu tôm",200.000, R.drawable.mango));
//        menuItemArrayList.add(new MenuFoodItem("Lẩu hải sản",200.000, R.drawable.mango));
//        menuItemArrayList.add(new MenuFoodItem("Gỏi cuốn",30.000, R.drawable.mango));
//        menuItemArrayList.add(new MenuFoodItem("Bún trộn",40.000, R.drawable.mango));
//        menuItemArrayList.add(new MenuFoodItem("Cơm chiên",50.000, R.drawable.mango));
//        menuItemArrayList.add(new MenuFoodItem("Rau xào",30.000, R.drawable.mango));
//        menuItemArrayList.add(new MenuFoodItem("Sương sa",15.000, R.drawable.mango));
//        menuItemArrayList.add(new MenuFoodItem("Chè trái cây",22.000, R.drawable.mango));
//        menuItemArrayList.add(new MenuFoodItem("Trái cây",30.000, R.drawable.mango));
//        menuItemArrayList.add(new MenuFoodItem("Bánh Flan",12.000, R.drawable.mango));
//        menuItemArrayList.add(new MenuFoodItem("Chè đậu xanh",14.000, R.drawable.mango));
//        menuFoodItemAdapter = new MenuFoodItemAdapter(getContext(), R.layout.menu_food_item, menuItemArrayList);

        listViewFood.setTextFilterEnabled(true);

        searchText.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                menuFoodItemAdapter.getFilter().filter(arg0);


            }

            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {

            }

            public void afterTextChanged(Editable arg0) {
                menuFoodItemAdapter.getFilter().filter(arg0);

            }
        });
        //listViewFood.setAdapter(menuFoodItemAdapter);




       /* lvFood = (ListView) rootView.findViewById(R.id.listViewFoodMenu);


        menuItemArrayList = new ArrayList<>();
        menuFoodItemAdapter = new MenuFoodItemAdapter(getContext(), R.layout.menu_food_item, menuItemArrayList);
        lvFood.setAdapter(menuFoodItemAdapter);


        btnAdd = (ImageButton) rootView.findViewById(R.id.buttonAdd);

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
