package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentSignIn extends Fragment {
    public static Database database;
    Button logInBtn;
    EditText  username, password;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // create a new database sqlite
        database = new Database(getContext(), "sh.sqlite", null,1);
        // create table account
        database.QueryData("create table if not exists account (accountId integer primary key AUTOINCREMENT, phoneNumber VARCHAR(200) not null, account_type varchar(20) not null, userName varchar(50) not null unique, password varchar(20)  not null) " );
        // create table dish
        database.QueryData("create table if not exists dish (dishId integer primary key AUTOINCREMENT, dishName varchar(200) not null, group_id integer not null, price double, image blob)");//database.QueryData("Insert into  staff_group values (0, 'Staff'), (1, 'Management')");
        // create table group_table
        database.QueryData("create table if not exists group_table(tableId integer primary key autoincrement , chair_number integer not null, status varchar(20) not null )");

        View rootView = inflater.inflate(R.layout.fragment_signin, container, false);
        logInBtn = rootView.findViewById(R.id.loginButton);
        username = (EditText) rootView.findViewById(R.id.usernameEditText);
        password = (EditText) rootView.findViewById(R.id.passwordEditText);


        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToMain = new Intent(getActivity(), MainActivity.class);
                startActivity(intentToMain);
                Cursor dataAccount = database.getData("SELECT * FROM account where userName = '"+username.getText().toString()+"' "); // trả về một cái dãy các account nè

                if(dataAccount.getCount()>0)
                {

                    while (dataAccount.moveToNext()){
                        if(dataAccount.getString(4).equals(password.getText().toString()))
                        {
                            Toast.makeText(getContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            password.setText(null);
                            Toast.makeText(getContext(), "Sai mật khẩu", Toast.LENGTH_SHORT).show();
                        }
                    }

                }else
                {
                    password.setText(null);
                    username.setText(null);
                    Toast.makeText(getContext(), "UserName không tồn tại", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return  rootView;
    }
}
