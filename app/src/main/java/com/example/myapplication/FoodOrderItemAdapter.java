package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FoodOrderItemAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<FoodOrderItem> foodOrderItem;

    public FoodOrderItemAdapter(Context context, int layout, List<FoodOrderItem> foodOrderItem) {
        this.context = context;
        this.layout = layout;
        this.foodOrderItem = foodOrderItem;
    }

    @Override
    public int getCount() {
        return foodOrderItem.size();
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
        ImageView image;
        TextView name;
        TextView price;
        TextView number;
        ImageButton plusBtn, minusBtn;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            //ánh xạ view
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.textViewNameFoodOrderItem);
            holder.price = (TextView) convertView.findViewById(R.id.textViewPriceFoodOrderItem);
            holder.image = (ImageView) convertView.findViewById(R.id.imageViewFoodOrderItem);
            holder.plusBtn= (ImageButton)convertView.findViewById(R.id.plus_btn);
            holder.number = (TextView) convertView.findViewById(R.id.number);
            holder.minusBtn = (ImageButton) convertView.findViewById(R.id.minus_btn);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        FoodOrderItem menu = foodOrderItem.get(position); // lay tung cai mot ra
        holder.name.setText(menu.getDish_name());
        holder.price.setText(menu.getPrice().toString());
        holder.number.setText(Integer.toString(menu.getNumber()));
        holder.plusBtn.setImageResource(R.drawable.plus);
        holder.minusBtn.setImageResource(R.drawable.minus);

      /*  byte[] hinhanh = menu.getImage();
        // muon lay hinh anh ra thi phai chuyen tu byte[] sang bitmap
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
        holder.image.setImageBitmap(bitmap);
        */
        holder.image.setImageResource(menu.getHinhAnh());

        return convertView;
    }
}