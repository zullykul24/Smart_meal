package com.example.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
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
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

import static com.example.myapplication.FragmentSignIn.database;

public class FragmentTableOrder extends Fragment {

    GridView gridViewTable;
    TableItemAdapter tableItemAdapter;

    ArrayList<TableItem> tableItemArrayList;

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
                database.QueryData("update group_table set status = 'Booked'  where tableId = " + tableItemArrayList.get(info.position).getId() + ";");
                Toast.makeText(getActivity().getApplicationContext(),"Booked",Toast.LENGTH_SHORT).show();
                tableItemAdapter.notifyDataSetChanged();
                return true;
            case R.id.status_empty:
                database.QueryData("update group_table set status = 'Empty'  where tableId = " + tableItemArrayList.get(info.position).getId() + ";");
                tableItemAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity().getApplicationContext(),"Empty",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tableorder, container,false);

        gridViewTable = (GridView) rootView.findViewById(R.id.gridViewTable);
        tableItemArrayList = new ArrayList<>();
         /////Edit
        Cursor  cursor = database.getData("SELECT * from group_table");
        while  (cursor.moveToNext()){
            tableItemArrayList.add( new TableItem(
                    cursor.getInt(0),
                    "Bàn số "+cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2)
                    )
            );
        }

        for(TableItem i:tableItemArrayList){
            if(i.getStatus().equals("Not Empty")){
                i.setColor("#959523");
            }
            else if(i.getStatus().equals("Booked")){
                i.setColor("#E64875");
            }
            else
            {
                i.setColor("#4EC33A");
            }
        }
    //    tableItemArrayList.add(new TableItem("Bàn số 10", "Empty"));
//        tableItemArrayList.add(new TableItem("Bàn số 2"));
//        tableItemArrayList.add(new TableItem("Bàn số 3"));
//        tableItemArrayList.add(new TableItem("Bàn số 4"));
//        tableItemArrayList.add(new TableItem("Bàn số 5", "Booked"));
//        tableItemArrayList.add(new TableItem("Bàn số 6"));
//        tableItemArrayList.add(new TableItem("Bàn số 7"));
//        tableItemArrayList.add(new TableItem("Bàn số 8", "Not Empty"));
//        tableItemArrayList.add(new TableItem("Bàn số 9"));
//        tableItemArrayList.add(new TableItem("Bàn số 10"));
//        tableItemArrayList.add(new TableItem("Bàn số 11"));
//        tableItemArrayList.add(new TableItem("Bàn số 12"));
//        tableItemArrayList.add(new TableItem("Bàn số 13"));
//        tableItemArrayList.add(new TableItem("Bàn số 14"));
//        tableItemArrayList.add(new TableItem("Bàn số 15"));
//        tableItemArrayList.add(new TableItem("Bàn số 16"));
//        tableItemArrayList.add(new TableItem("Bàn số 17"));
//        tableItemArrayList.add(new TableItem("Bàn số 18"));
        ////
        tableItemAdapter = new TableItemAdapter(getContext(), R.layout.table_item,tableItemArrayList );

        tableItemAdapter.notifyDataSetChanged();
        gridViewTable.setAdapter(tableItemAdapter);





        gridViewTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), tableItemArrayList.get(position).getName(), Toast.LENGTH_SHORT).show();
                String status = tableItemArrayList.get(position).getStatus();
                if(status.equals("Empty")) {
                    Intent intentToOrder = new Intent(getActivity(), OrderActivity.class);
                    intentToOrder.putExtra("Tên bàn", tableItemArrayList.get(position).getName());
                    startActivity(intentToOrder);
                }
            }
        });
        registerForContextMenu(gridViewTable);


        return rootView;
    }




}
