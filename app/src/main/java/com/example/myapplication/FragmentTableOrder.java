package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

        btnAdd= (ImageButton) rootView.findViewById(R.id.buttonThemBan);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.QueryData("insert into group_table values (null, 4, 'Empty')");
            }
        });

        gridViewTable = (GridView) rootView.findViewById(R.id.gridViewTable);
        tableItemArrayList = new ArrayList<>();
        tableItemAdapter = new TableItemAdapter(getContext(), R.layout.table_item,tableItemArrayList );


        Cursor cursor = database.getData("SELECT * FROM group_table");
        while (cursor.moveToNext()){
            // khởi tạo đối tượng đầu vào bao gồm thuộc tính tên, status và số lượng thui;
            tableItemArrayList.add(new TableItem(
                    "Bàn số "+ cursor.getInt(0),
                    cursor.getString(2),
                    cursor.getInt(1)
            ));
        }
        tableItemAdapter.notifyDataSetChanged();
        gridViewTable.setAdapter(tableItemAdapter);


        for(TableItem i:tableItemArrayList){
            if(i.getStatus()=="Có khách"){
                i.setColor("#959523");

            }
            if(i.getStatus()=="Đã đặt"){
                i.setColor("#E64875");

            }
        }


        gridViewTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), tableItemArrayList.get(position).getName(), Toast.LENGTH_SHORT).show();

            }
        });
        return rootView;
    }
}
