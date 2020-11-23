package com.example.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import static com.example.myapplication.FragmentSignIn.database;

public class FragmentTableOrder extends Fragment {

    GridView gridViewTable;
    TableItemAdapter tableItemAdapter;
    ImageButton btnAdd;
    ArrayList<TableItem> tableItemArrayList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tableorder, container,false);

      /*  btnAdd= (ImageButton) rootView.findViewById(R.id.buttonThemBan);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInsertTable();


            }
        });

       */

        gridViewTable = (GridView) rootView.findViewById(R.id.gridViewTable);
        tableItemArrayList = new ArrayList<>();
         /////Edit
        tableItemArrayList.add(new TableItem("Bàn số 1"));
        tableItemArrayList.add(new TableItem("Bàn số 2"));
        tableItemArrayList.add(new TableItem("Bàn số 3"));
        tableItemArrayList.add(new TableItem("Bàn số 4"));
        tableItemArrayList.add(new TableItem("Bàn số 5", "Booked"));
        tableItemArrayList.add(new TableItem("Bàn số 6"));
        tableItemArrayList.add(new TableItem("Bàn số 7"));
        tableItemArrayList.add(new TableItem("Bàn số 8", "Not Empty"));
        tableItemArrayList.add(new TableItem("Bàn số 9"));
        tableItemArrayList.add(new TableItem("Bàn số 10"));
        tableItemArrayList.add(new TableItem("Bàn số 11"));
        tableItemArrayList.add(new TableItem("Bàn số 12"));
        tableItemArrayList.add(new TableItem("Bàn số 13"));
        tableItemArrayList.add(new TableItem("Bàn số 14"));
        tableItemArrayList.add(new TableItem("Bàn số 15"));
        tableItemArrayList.add(new TableItem("Bàn số 16"));
        tableItemArrayList.add(new TableItem("Bàn số 17"));
        tableItemArrayList.add(new TableItem("Bàn số 18"));
        ////
        tableItemAdapter = new TableItemAdapter(getContext(), R.layout.table_item,tableItemArrayList );


        Cursor cursor = database.getData("SELECT * FROM group_table");
        while (cursor.moveToNext()){
            // khởi tạo đối tượng đầu vào bao gồm thuộc tính tên,số lượng thui;
            tableItemArrayList.add(new TableItem(
                    "Bàn số "+ cursor.getInt(0),
                    cursor.getInt(1)
            ));
        }
        tableItemAdapter.notifyDataSetChanged();
        gridViewTable.setAdapter(tableItemAdapter);


        for(TableItem i:tableItemArrayList){
            if(i.getStatus()=="Not Empty"){
                i.setColor("#959523");

            }
            if(i.getStatus()=="Booked"){
                i.setColor("#E64875");
            }
        }
        registerForContextMenu(gridViewTable);

        gridViewTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), tableItemArrayList.get(position).getName(), Toast.LENGTH_SHORT).show();
                String status = tableItemArrayList.get(position).getStatus();
                if(status.equals("Empty")){
                    Intent intentToOrder = new Intent(getActivity(), OrderActivity.class);
                    intentToOrder.putExtra("Tên bàn", tableItemArrayList.get(position).getName() );
                    startActivity(intentToOrder);
                }

            }
        });
        return rootView;
    }

    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {

        getActivity().getMenuInflater().inflate(R.menu.set_table_status, menu);
        menu.setHeaderTitle("Đặt trạng thái");

        super.onCreateContextMenu(menu, v, menuInfo);
    }
    public boolean onContextItemSelected(MenuItem item) {
        // below variable info contains clicked item info and it can be null; scroll down to see a fix for it
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()){
            case R.id.status_booked:

                tableItemAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity().getApplicationContext(),"Booked",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.status_empty:
                tableItemAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity().getApplicationContext(),"empty",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


    private  void DialogInsertTable(){
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.add_table);
        // Ánh xạ xong r nè

        Button add = (Button) dialog.findViewById(R.id.button_ok);
        Button cancel = (Button) dialog.findViewById(R.id.button_cancel);
        final EditText number_chair = (EditText) dialog.findViewById(R.id.edit_chair_number);

        // sử dụng các button
        /*
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

         */
    }
}
