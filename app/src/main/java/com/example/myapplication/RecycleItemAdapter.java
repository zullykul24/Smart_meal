package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class RecycleItemAdapter extends RecyclerView.Adapter<RecycleItemAdapter.MyViewHolder> {
    ArrayList<MenuFoodItem> hotItemsArray;
    Context context;

    public RecycleItemAdapter(ArrayList<MenuFoodItem> hotItemsArray, Context context) {
        this.hotItemsArray = hotItemsArray;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, price;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_hot);
            price = itemView.findViewById(R.id.price_hot);
            image = itemView.findViewById(R.id.img_hot);
        }
    }

    @NonNull
    @Override
    public RecycleItemAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.hot_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleItemAdapter.MyViewHolder holder, int position) {
        holder.name.setText(hotItemsArray.get(position).getDish_name());
        holder.price.setText(hotItemsArray.get(position).getPrice().toString());
        holder.image.setImageResource(hotItemsArray.get(position).getHinhAnh());

    }

    @Override
    public int getItemCount() {
        return hotItemsArray.size();
    }
}

