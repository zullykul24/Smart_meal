package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button loginBtn;
    Button signInTextBtn, signUpTextBtn;
    ConstraintLayout signInLayout, signUpLayout;


    protected void AnhXa(){
        username = (EditText) findViewById(R.id.usernameEditText);
        password = (EditText) findViewById(R.id.passwordEditText);
        loginBtn = (Button) findViewById(R.id.loginButton);
        signInTextBtn = (Button) findViewById(R.id.signInButton);
        signUpTextBtn = (Button) findViewById(R.id.signUpButton);
        signInLayout = (ConstraintLayout) findViewById(R.id.signInLayout);
        signUpLayout = (ConstraintLayout) findViewById(R.id.signUpLayout);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        AnhXa();

        final FragmentManager fragmentManager = getSupportFragmentManager();

        final FragmentSignIn fragmentSignIn = new FragmentSignIn();
        final FragmentSignUp fragmentSignUp= new FragmentSignUp();

        fragmentManager.beginTransaction().add(R.id.frameSign,fragmentSignUp, "signup");

        fragmentManager.beginTransaction().add(R.id.frameSign, fragmentSignIn, "signin").commit();








        signUpTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.frameSign,fragmentSignUp).addToBackStack(null).commit();
               // fragmentManager.beginTransaction().hide(fragmentSignIn).show(fragmentSignUp).commit();



                signUpTextBtn.setTextColor(Color.parseColor("#000000"));
                signInTextBtn.setTextColor(Color.parseColor("#786464"));
            }
        });

        signInTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.frameSign,fragmentSignIn).addToBackStack(null).commit();
                //fragmentManager.beginTransaction().hide(fragmentSignUp).show(fragmentSignIn).commit();




                signInTextBtn.setTextColor(Color.parseColor("#000000"));
                signUpTextBtn.setTextColor(Color.parseColor("#786464"));
            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToMain = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intentToMain);
            }
        });
    }
}