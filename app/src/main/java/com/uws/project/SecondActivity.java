package com.uws.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.uws.project.ui.login.LoginActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setTitle("Main Page");
        ImageButton button = (ImageButton) findViewById(R.id.profile);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                goToProfileActivity();

            }

        });
    }
    private void goToProfileActivity() {

        Intent intent = new Intent(this, profileActivity.class);

        startActivity(intent);

    }
    }

