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
        database = new Database(getContext(), "Smart_Meal_1.sqlite", null,1);

        // create group account : phân chia xem bên nào là quản lý, bên nào là nhân viên.
        // , FOREIGN KEY (accountGro) REFERENCES  staff_group (groupID)
       // database.QueryData("create table if not exists staff_group(groupID integer primary key, groupName VARCHAR(20) not null ) " );
       // create account :
        database.QueryData("create table if not exists account (accountId integer primary key AUTOINCREMENT, phoneNumber VARCHAR(200) not null, account_type varchar(20) not null, userName varchar(50) not null unique, password varchar(20)  not null) " );

        //database.QueryData("Insert into  staff_group values (0, 'Staff'), (1, 'Management')");


        View rootView = inflater.inflate(R.layout.fragment_signin, container, false);
        logInBtn = rootView.findViewById(R.id.loginButton);
        username = (EditText) rootView.findViewById(R.id.usernameEditText);
        password = (EditText) rootView.findViewById(R.id.passwordEditText);
        Cursor data = database.getData("SELECT * from account");
        while (data.moveToNext())
        {
            Log.d("fafafasf", data.getString(0)+ "  " +  data.getString(1));
        }
        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//and password = '"+password.getText().toString()+"'
                Cursor dataAccount = database.getData("SELECT * FROM account where userName = '"+username.getText().toString()+"' "); // trả về một cái dãy các account nè

                if(dataAccount.getCount()>0)
                {

                    while (dataAccount.moveToNext()){
                        if(dataAccount.getString(4).equals(password.getText().toString()))
                        {
                            Toast.makeText(getContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intentToMain = new Intent(getActivity(), MainActivity.class);
                            startActivity(intentToMain);
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
