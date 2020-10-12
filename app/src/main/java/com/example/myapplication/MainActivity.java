package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.GridView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navbar;
    EditText foodName;


    protected void AnhXa(){
        navbar = (BottomNavigationView) findViewById(R.id.navbar);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();



        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        final FragmentHomePage fragment1 = new FragmentHomePage();
        final FragmentTableOrder fragment2 = new FragmentTableOrder();
        final FragmentMenu fragment3 = new FragmentMenu();
        final FragmentAccount fragment4 = new FragmentAccount();

        fragmentManager.beginTransaction().add(R.id.rela, fragment4, "4").hide(fragment4).commit();
        fragmentManager.beginTransaction().add(R.id.rela, fragment3, "3").hide(fragment3).commit();
        fragmentManager.beginTransaction().add(R.id.rela, fragment2, "2").hide(fragment2).commit();
        fragmentManager.beginTransaction().add(R.id.rela, fragment1, "1").commit();
        navbar.setOnNavigationItemSelectedListener (new BottomNavigationView.OnNavigationItemSelectedListener(){
            Fragment active = fragment1;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                //// đang bị lỗi, khi mở lên thì tất cả các fragment đều active
                switch (menuItem.getItemId()){
                    case R.id.nav_1:
                        fragmentManager.beginTransaction().hide(active).show(fragment1).commit();
                        active = fragment1;
                        return true;
                    case R.id.nav_2:
                        fragmentManager.beginTransaction().hide(active).show(fragment2).commit();
                        active = fragment2;
                        return true;
                    case R.id.nav_3:
                        fragmentManager.beginTransaction().hide(active).show(fragment3).commit();
                        active = fragment3;
                        return true;
                    case R.id.nav_4:
                        fragmentManager.beginTransaction().hide(active).show(fragment4).commit();
                        active = fragment4;
                        return true;
                }

                return false;
            }
        });

        ///đổ dữ liệu vào menu




    }
}