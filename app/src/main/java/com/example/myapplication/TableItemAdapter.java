package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.view.menu.MenuView;

import java.util.List;

public class TableItemAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<TableItem> tableItemList;


    /// tạo ra view holder để ko phải ánh xạ lại những items đã lướt qua khi mình lướt lại
    private class ViewHolder{
        ImageButton image;
        TextView name, status;

    }

    public TableItemAdapter(Context context, int layout, List<TableItem> tableItemList) {
        this.context = context;
        this.layout = layout;
        this.tableItemList = tableItemList;
    }

    @Override
    public int getCount() {
        // very important
        return tableItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            //ánh xạ view
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.table_name);
            holder.status = (TextView) convertView.findViewById(R.id.table_status);
            holder.image = (ImageButton) convertView.findViewById(R.id.table_img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }



        //gán giá trị

        TableItem tableItem = tableItemList.get(position);
        holder.name.setText(tableItem.getName());
        holder.status.setText(tableItem.getStatus());
        holder.image.setImageResource(tableItem.getImage());
        holder.image.setBackgroundColor(Color.parseColor(tableItem.getColor()));

        return convertView;
    }
}
