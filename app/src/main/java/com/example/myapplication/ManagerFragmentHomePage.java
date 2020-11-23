package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ManagerFragmentHomePage extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.manager_fragment_homepage, container, false);

        RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.manager_recycle_view_hot_items);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(decoration);
        ArrayList<MenuFoodItem> hotArrayList = new ArrayList<>();
        hotArrayList.add(new MenuFoodItem("Món 1", 20.000));
        hotArrayList.add(new MenuFoodItem("Món 2", 25.000));
        hotArrayList.add(new MenuFoodItem("Món 2", 25.000));
        hotArrayList.add(new MenuFoodItem("Món 2", 25.000));
        hotArrayList.add(new MenuFoodItem("Món 2", 25.000));
        hotArrayList.add(new MenuFoodItem("Món 2", 25.000));
        RecycleItemAdapter adapter = new RecycleItemAdapter(hotArrayList, getActivity().getApplicationContext());
        recyclerView.setAdapter(adapter);

        return rootView;
    }

}