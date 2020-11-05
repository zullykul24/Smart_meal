package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class ChooseFoodActivity extends AppCompatActivity {
    ListView listViewFood;
    MenuFoodItemAdapter menuFoodItemAdapter;
    ArrayList<MenuFoodItem> menuItemArrayList;
    EditText searchText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_foods);
        searchText = (EditText)findViewById(R.id.searchText);

        listViewFood = (ListView)findViewById(R.id.listViewFoodMenu);
        menuItemArrayList = new ArrayList<>();
        menuItemArrayList.add(new MenuFoodItem("Món 1",20.000, R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Món 2",20.000, R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Món 3",20.000, R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Món 4",20.000, R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Món 5",20.000, R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Món 6",20.000, R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Món 7",20.000, R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Món 8",20.000, R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Món 9",20.000, R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Món 10",20.000, R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Món 11",20.000, R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Món 12",20.000, R.drawable.mango));
        menuFoodItemAdapter = new MenuFoodItemAdapter(ChooseFoodActivity.this, R.layout.menu_food_item, menuItemArrayList);

        listViewFood.setTextFilterEnabled(true);

        searchText.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                ChooseFoodActivity.this.menuFoodItemAdapter.getFilter().filter(arg0);


            }

            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {

            }

            public void afterTextChanged(Editable arg0) {
                ChooseFoodActivity.this.menuFoodItemAdapter.getFilter().filter(arg0);

            }
        });

        listViewFood.setAdapter(menuFoodItemAdapter);

        listViewFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentSendFoodToOrderActivity = new Intent(ChooseFoodActivity.this, OrderActivity.class);
                MenuFoodItem item = new MenuFoodItem(menuItemArrayList.get(position).getDish_name(), menuItemArrayList.get(position).getPrice(), menuItemArrayList.get(position).getHinhAnh());

                Toast.makeText(ChooseFoodActivity.this, item.getDish_name()+""+item.getPrice().toString(),Toast.LENGTH_SHORT).show();
                intentSendFoodToOrderActivity.putExtra("abc", (Serializable) item);
                setResult(Activity.RESULT_OK, intentSendFoodToOrderActivity);
                finish();
            }
        });


    }
}
