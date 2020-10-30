package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_foods);

        listViewFood = (ListView)findViewById(R.id.listViewFoodMenu);
        menuItemArrayList = new ArrayList<>();
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao",20.000, R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao",20.000, R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao",20.000, R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao",20.000, R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao",20.000, R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao",20.000, R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao",20.000, R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao",20.000, R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao",20.000, R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao",20.000, R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao",20.000, R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao",20.000, R.drawable.mango));
        menuFoodItemAdapter = new MenuFoodItemAdapter(ChooseFoodActivity.this, R.layout.menu_food_item, menuItemArrayList);
        listViewFood.setAdapter(menuFoodItemAdapter);

        listViewFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentSendFoodToOrderActivity = new Intent(ChooseFoodActivity.this, OrderActivity.class);
              //  MenuFoodItem item = new MenuFoodItem(menuItemArrayList.get(position).getDish_name(), menuItemArrayList.get(position).getPrice(), menuItemArrayList.get(position).getHinhAnh());
               MenuFoodItem item = new MenuFoodItem("Bò sốt tiêu đen + Bánh bao",20.000, R.drawable.mango);
                Toast.makeText(ChooseFoodActivity.this, item.getDish_name()+""+item.getPrice().toString(),Toast.LENGTH_SHORT).show();
                intentSendFoodToOrderActivity.putExtra("abc", (Serializable) item);
                setResult(Activity.RESULT_OK, intentSendFoodToOrderActivity);
                finish();
            }
        });


    }
}
