package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    TextView tableName;
    Button themMonBtn;
    ListView listViewChosenFood;
    ArrayList<MenuFoodItem> arrayListChosenFood;
    MenuFoodItemAdapter adapterChosenFood;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);

        tableName = (TextView)findViewById(R.id.nameOfIntentedTable);
        themMonBtn = (Button)findViewById(R.id.themMonBtn);
        listViewChosenFood = (ListView)findViewById(R.id.listViewChosenFood);

        Intent intent = getIntent();
        String nameOfTableSelected = intent.getStringExtra("Tên bàn");
        tableName.setText(nameOfTableSelected);

        MenuFoodItem foodItem = (MenuFoodItem) intent.getSerializableExtra("abc");

        arrayListChosenFood = new ArrayList<>();
/// lỗi ở đây, data nhận được bị null
       /* arrayListChosenFood.add(new MenuFoodItem(foodItem.getDish_name(),foodItem.getPrice(), foodItem.getHinhAnh()));
        adapterChosenFood = new MenuFoodItemAdapter(OrderActivity.this,R.layout.menu_food_item,arrayListChosenFood);
        listViewChosenFood.setAdapter(adapterChosenFood);*/

        themMonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToChooseFood = new Intent(OrderActivity.this, ChooseFoodActivity.class);
                startActivity(intentToChooseFood);
            }
        });





    }
}
