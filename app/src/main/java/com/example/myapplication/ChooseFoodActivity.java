package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
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

import static com.example.myapplication.FragmentSignIn.database;

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
        Cursor cursor = database.getData("SELECT * from dish");
        while (cursor.moveToNext()){
            menuItemArrayList.add(new MenuFoodItem(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getDouble(3),
                    cursor.getBlob(4)

            ));
        }
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
