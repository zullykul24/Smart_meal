package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    TextView tableName;
    Button themMonBtn;
    SwipeMenuListView listViewChosenFood;

    ArrayList<FoodOrderItem> arrayListChosenFood;

    FoodOrderItemAdapter adapterChosenFood;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);



        tableName = (TextView)findViewById(R.id.nameOfIntentedTable);
        themMonBtn = (Button)findViewById(R.id.themMonBtn);
        listViewChosenFood = (SwipeMenuListView) findViewById(R.id.listViewChosenFood);
        View footer = getLayoutInflater().inflate(R.layout.footer, null);
        listViewChosenFood.addFooterView(footer);

        Intent intent = getIntent();
        String nameOfTableSelected = intent.getStringExtra("Tên bàn");
        tableName.setText(nameOfTableSelected);

        MenuFoodItem foodItem = (MenuFoodItem) intent.getSerializableExtra("abc");

        arrayListChosenFood = new ArrayList<>();

        themMonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToChooseFood = new Intent(OrderActivity.this, ChooseFoodActivity.class);
                startActivityForResult(intentToChooseFood, 999);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999) {
            if (resultCode == -1) {
                MenuFoodItem foodItem = (MenuFoodItem) data.getSerializableExtra("abc");
                arrayListChosenFood.add(new FoodOrderItem(foodItem.getDish_name(), foodItem.getPrice(), foodItem.getHinhAnh()));
                adapterChosenFood = new FoodOrderItemAdapter(OrderActivity.this, R.layout.food_order_item, arrayListChosenFood);
                listViewChosenFood.setAdapter(adapterChosenFood);
                // Thêm trượt để xoá https://github.com/baoyongzhang/SwipeMenuListView
                SwipeMenuCreator creator = new SwipeMenuCreator() {

                    @Override
                    public void create(SwipeMenu menu) {
                        // create "delete" item
                        SwipeMenuItem deleteItem = new SwipeMenuItem(
                                getApplicationContext());
                        // set item background

                        deleteItem.setBackground(new ColorDrawable(Color.rgb(0xe6, 0x00,
                                0x00)));
                        // set item width
                        deleteItem.setWidth(170);
                        // set item title
                        deleteItem.setTitle("Xoá");
                        // set item title fontsize
                        deleteItem.setTitleSize(18);
                        // set item title font color
                        deleteItem.setTitleColor(Color.WHITE);
                        // add to menu
                        menu.addMenuItem(deleteItem);


                        //có thể setIcon


                    }
                };
                listViewChosenFood.setMenuCreator(creator);
                /// set click cho item ở menu trượt
                listViewChosenFood.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                        Toast.makeText(getApplicationContext(),arrayListChosenFood.get(position).getDish_name(),Toast.LENGTH_SHORT).show();
                       arrayListChosenFood.remove(position);
                       adapterChosenFood.notifyDataSetChanged();
                        // false : close the menu; true : not close the menu
                        return false;
                    }
                });

            }
        }
    }
}
