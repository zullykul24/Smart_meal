package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FragmentMenu extends Fragment {

    GridView gridViewMenu;
    MenuFoodItemAdapter menuFoodItemAdapter;
    ArrayList<MenuFoodItem> menuItemArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        gridViewMenu = (GridView) rootView.findViewById(R.id.gridViewFoodMenu);
        menuItemArrayList = new ArrayList<>();
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));
        menuItemArrayList.add(new MenuFoodItem("Bò sốt tiêu đen + Bánh bao","20.000", R.drawable.mango));


        gridViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), "Haha", Toast.LENGTH_SHORT).show();

            }
        });


        menuFoodItemAdapter = new MenuFoodItemAdapter(getActivity(), R.layout.menu_food_item, menuItemArrayList);
        gridViewMenu.setAdapter(menuFoodItemAdapter);

        return rootView;
    }
}
