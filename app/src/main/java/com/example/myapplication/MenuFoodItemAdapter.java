package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    private List<MenuFoodItem> menuFoodItems;

    public MenuFoodItemAdapter(Context context, int layout, List<MenuFoodItem> menuFoodItems) {
        this.context = context;
        this.layout = layout;
        this.menuFoodItems = menuFoodItems;
    }

    @Override
    public int getCount() {
        return menuFoodItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /// tạo ra view holder để ko phải ánh xạ lại những items đã lướt qua khi mình lướt lại
    private class ViewHolder{
        ImageView image;
        TextView name;
        TextView price;
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
        MenuFoodItem menu = menuFoodItems.get(position); // lay tung cai mot ra
        holder.name.setText(menu.getDish_name());
        holder.price.setText(menu.getPrice().toString());
        byte[] hinhanh = menu.getImage();
        // muon lay hinh anh ra thi phai chuyen tu byte[] sang bitmap
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
        holder.image.setImageBitmap(bitmap);

        return convertView;
    }
}
