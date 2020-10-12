package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FragmentTableOrder extends Fragment {

    GridView gridViewTable;
    TableItemAdapter tableItemAdapter;
    ArrayList<TableItem> tableItemArrayList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tableorder, container,false);
        gridViewTable = rootView.findViewById(R.id.gridViewTable);
        tableItemArrayList = new ArrayList<>();
        tableItemArrayList.add(new TableItem("Bàn số 1"));
        tableItemArrayList.add(new TableItem("Bàn số 2"));
        tableItemArrayList.add(new TableItem("Bàn số 3"));
        tableItemArrayList.add(new TableItem("Bàn số 4"));
        tableItemArrayList.add(new TableItem("Bàn số 5"));
        tableItemArrayList.add(new TableItem("Bàn số 6"));
        tableItemArrayList.add(new TableItem("Bàn số 7"));
        tableItemArrayList.add(new TableItem("Bàn số 8"));
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
        tableItemArrayList.add(new TableItem("Bàn số 19"));
        tableItemArrayList.add(new TableItem("Bàn số 20"));
        tableItemArrayList.get(1).setStatus("Có khách");
        tableItemArrayList.get(2).setStatus("Có khách");
        tableItemArrayList.get(7).setStatus("Có khách");
        tableItemArrayList.get(8).setStatus("Có khách");
        tableItemArrayList.get(3).setStatus("Đã đặt");
        tableItemArrayList.get(0).setStatus("Đã đặt");
        tableItemArrayList.get(6).setStatus("Đã đặt");
        tableItemArrayList.get(5).setStatus("Đã đặt");
        for(TableItem i:tableItemArrayList){
            if(i.getStatus()=="Có khách"){
                i.setColor("#959523");

            }
            if(i.getStatus()=="Đã đặt"){
                i.setColor("#E64875");

            }
        }
        tableItemAdapter = new TableItemAdapter(getActivity(), R.layout.table_item, tableItemArrayList);
        gridViewTable.setAdapter(tableItemAdapter);
        return rootView;
    }
}
