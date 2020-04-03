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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText passwordEditText = findViewById(R.id.password);
        editUsername = findViewById(R.id.username);

        Button loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                username = editUsername.getText().toString();
                goToSecondActivity();
                String usernameWelcome = "Welcome " + username + "!";
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, usernameWelcome, duration);
                toast.show();

            }

        });

    }

    private void goToSecondActivity() {

        String password = "temp password";
        String biography = "Placeholder biography";
        String[] comments = {"comment 1","comment 2","comment 3"};
        int[] songs = {0,1,2};

        currentUser = new Profile(0,0, username, password, biography, comments, songs);

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("user_details", currentUser);
        startActivity(intent);
    }



}


