package com.uws.project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText editUsername;
    String username;
    Profile currentUser;
    EditText editPassword;
    String password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editPassword = findViewById(R.id.password);
        editUsername = findViewById(R.id.username);

        Button loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                password = editPassword.getText().toString();
                username = editUsername.getText().toString();
                if (username.equals("") || password.equals("")) {
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, "Username and Password must have a value", duration);
                    toast.show();
                } else {
                    goToSecondActivity();
                    String usernameWelcome = "Welcome " + username + "!";
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, usernameWelcome, duration);
                    toast.show();
                }
            }

        });
    }

    private void goToSecondActivity() {
        String biography = "Hi, my name is " + username + "! Welcome to my profile! Just got the app recently, looking forward to using it!";
        String[] comments = {"Good music taste!", "Had the pleasure of meeting AlanB73 at a charity do once. They were surprisingly down to earth, and VERY funny.","Don't really like your music taste."};
        int[] songs = {0,6,10};

        currentUser = new Profile(0,R.drawable.account, username, password, biography, comments, songs);

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("user_details", currentUser);
        startActivity(intent);
    }



}


