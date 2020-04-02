package com.uws.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.uws.project.R;
import com.uws.project.SecondActivity;

public class LoginActivity extends AppCompatActivity {

    EditText editUsername;
    String result;
    String username;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText passwordEditText = findViewById(R.id.password);
       Button loginButton = findViewById(R.id.login);
        editUsername = (EditText)findViewById(R.id.username);
        loginButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                goToSecondActivity();
                username = editUsername.getText().toString();
                String usernameWelcome = "Welcome " + username +"!";
                Context context = getApplicationContext();

                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, usernameWelcome, duration);
                toast.show();

            }

        });




    }



    private void goToSecondActivity() {
// TODO PLEASE HELP ME!!!!
        // GET VALUES TO PASS.
        // https://stackoverflow.com/questions/2091465/how-do-i-pass-data-between-activities-in-android-application is where I got info for this.
        // LINES 64 - 65 derived from this code.
        // DOES NOT PASS CORRECTLY, RECEIVE ONLY NULLS
        Intent intent = new Intent(getBaseContext(), SecondActivity.class);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
    }



}


