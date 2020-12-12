package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<HistoryItem> historyItemList;
    private List<HistoryItem> displayedList;

    public HistoryAdapter(Context context, int layout, List<HistoryItem> historyItemList) {
        this.context = context;
        this.layout = layout;
        this.historyItemList = historyItemList;
    }

    @Override
    public int getCount() {
        return historyItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView nameTable;
        TextView paymentDate;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final  ViewHolder holder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder = new ViewHolder();
            holder.nameTable = (TextView) convertView.findViewById(R.id.tenban);
            holder.paymentDate = (TextView) convertView.findViewById(R.id.datePaymented);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        final  HistoryItem historyItem  = historyItemList.get(position);
        holder.nameTable.setText("Bàn "+ historyItem.getTableId());
        java.sql.Timestamp date=new java.sql.Timestamp(historyItem.getDate());
        holder.paymentDate.setText(""+ date);
        return convertView;
    }
}
