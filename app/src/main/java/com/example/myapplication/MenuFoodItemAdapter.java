package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.view.menu.MenuView;

import java.util.List;

public class MenuFoodItemAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<MenuFoodItem> menuFoodItemList;


    /// tạo ra view holder để ko phải ánh xạ lại những items đã lướt qua khi mình lướt lại
    private class ViewHolder{
        ImageView image;
        TextView name, price;
    }

    public MenuFoodItemAdapter(Context context, int layout, List<MenuFoodItem> menuFoodItemList) {
        this.context = context;
        this.layout = layout;
        this.menuFoodItemList = menuFoodItemList;
    }

    @Override
    public int getCount() {
        // very important
        return menuFoodItemList.size();
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
            holder.name = (TextView) convertView.findViewById(R.id.textViewNameMenuFoodItem);
            holder.price = (TextView) convertView.findViewById(R.id.textViewPriceMenuFoodItem);
            holder.image = (ImageView) convertView.findViewById(R.id.imageViewMenuFoodItem);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }



        //gán giá trị

        MenuFoodItem menuFoodItem = menuFoodItemList.get(position);
        holder.name.setText(menuFoodItem.getName());
        holder.price.setText(menuFoodItem.getPrice());
        holder.image.setImageResource(menuFoodItem.getImage());

        return convertView;
    }
}
