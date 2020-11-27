package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import static com.example.myapplication.FragmentSignIn.database;

public class OrderActivity extends AppCompatActivity {
    TextView tableName;
    Button themMonBtn;
    SwipeMenuListView listViewChosenFood;
    Button btn_ok ;
    Button btn_cancel ;
    ArrayList<FoodOrderItem> arrayListChosenFood;
    EditText note ;
    FoodOrderItemAdapter adapterChosenFood;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);

        tableName = (TextView)findViewById(R.id.nameOfIntentedTable);
        themMonBtn = (Button)findViewById(R.id.themMonBtn);


        // là cái thanh cuộn các món ở dưới.
        listViewChosenFood = (SwipeMenuListView) findViewById(R.id.listViewChosenFood);
        View footer = getLayoutInflater().inflate(R.layout.footer, null);
        // add thêm cái footer ghi chú và button OK
        listViewChosenFood.addFooterView(footer);

        btn_ok = (Button)findViewById(R.id.btn_ok_order);
        btn_cancel = (Button) findViewById(R.id.btn_cancel_order);
        note = (EditText) findViewById(R.id.noteFooter);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 int banId = getIntent().getIntExtra("Bàn id", 0);
                 String banStatus = getIntent().getStringExtra("Bàn Status");
                 // Nếu chưa order bao giờ thì thêm orders vào trong cơ sở dữ liệu
                 if(banStatus.equals("Empty") || banStatus.equals("Booked")){
                     if(!arrayListChosenFood.isEmpty()){
                         database.QueryData("insert into orders values(null,"+banId+","+note+" )");
                         // set trạng thái của bàn là not empty
                         database.QueryData("update group_table set status = 'Not Empty'  where tableId = " + banId + ";");
                     }
                }
                Cursor cursor =  database.getData("Select * from orders where tableId = " + banId + " order by orderId DESC Limit 1 ; ");
                int orderId =0;
                String no = "";
                while (cursor.moveToNext()){
                    orderId = cursor.getInt(0);
                    no = cursor.getString(2);
                }
                Log.d("note", "" + no + banId);

                for( FoodOrderItem food : arrayListChosenFood){
                        Cursor cursor1 = database.getData("SELECT * from orderdetails where orderId = " + orderId + " and dishId = " + food.getDish_id());
                       // Nếu chưa từng đặt món này
                        if (cursor1.getCount() == 0){
                            database.QueryData("insert into orderdetails values ("+orderId+", "+food.getDish_id()+", "+getIntent().getIntExtra("AccountID", 0)+", "+food.getNumber()+" )");
                        }
                        else
                        {
                            int quantityOrdered = 0;
                            while (cursor1.moveToNext()){
                                quantityOrdered = cursor1.getInt(3);
                            }
                            quantityOrdered += food.getNumber();
                            database.QueryData("update orderdetails set quantityOrder = " + quantityOrdered + " where dishId = " + food.getDish_id());
                        }
              }
                // please help me on this
               // String notes = note.getText().toString();
               //Log.d("123", notes);
                finish();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(OrderActivity.this, note.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });
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
                arrayListChosenFood.add(new FoodOrderItem(foodItem.getDish_id(), foodItem.getDish_name(),foodItem.getGroup_id(),  foodItem.getPrice(), foodItem.getImage()));
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
