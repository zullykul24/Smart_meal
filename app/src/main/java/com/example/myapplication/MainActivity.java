package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import static com.example.myapplication.FragmentSignIn.database;

public class MainActivity extends AppCompatActivity {

    // dau  tien la phai show dc danh sach cac mon
    BottomNavigationView navbar;
    EditText foodName;
    TextView displayName;
    TextView roleName;

    //public static Database database;

    protected void AnhXa(){
        navbar = (BottomNavigationView) findViewById(R.id.navbar);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        AnhXa();

        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        final FragmentHomePage fragment1 = new FragmentHomePage();
        final FragmentTableOrder fragment2 = new FragmentTableOrder();
        final FragmentMenu fragment3 = new FragmentMenu();
        final FragmentAccount fragment4 = new FragmentAccount();
        fragment4.getInfor(intent.getStringExtra("name"), intent.getStringExtra("account_type"));

        fragmentManager.beginTransaction().add(R.id.rela, fragment1, "1").commit();
        navbar.setOnNavigationItemSelectedListener (new BottomNavigationView.OnNavigationItemSelectedListener(){
            Fragment active = fragment1;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                //// đang bị lỗi item Trang chủ ko sáng khi chuyển đổi lại
                switch (menuItem.getItemId()){
                    case R.id.nav_1:
                        fragmentManager.beginTransaction()
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                //.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                               // .hide(active)
                                .replace(R.id.rela,fragment1)
                                .addToBackStack(null)
                               // .show(fragment1)
                                .commit();
                        active = fragment1;

                        return true;

                    case R.id.nav_2:
                        fragmentManager.beginTransaction()
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                               // .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                              //  .hide(active)
                                .replace(R.id.rela, fragment2)
                                .addToBackStack(null)
                              // .show(fragment2)
                                .commit();
                        active = fragment2;
                        return true;
                    case R.id.nav_3:
                        fragmentManager.beginTransaction()
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                //.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                              //  .hide(active)
                                .replace(R.id.rela,fragment3)
                                .addToBackStack(null)
                            // .show(fragment3)
                                .commit();
                        active = fragment3;
                        return true;
                    case R.id.nav_4:
                        fragmentManager.beginTransaction()
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                               // .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                              //  .hide(active)
                                .replace(R.id.rela,fragment4)
                                .addToBackStack(null)
                              //  .show(fragment4)
                                .commit();
                        active = fragment4;
                        return true;
                }

                return false;
            }
        });

        ///đổ dữ liệu vào menu




    }
}