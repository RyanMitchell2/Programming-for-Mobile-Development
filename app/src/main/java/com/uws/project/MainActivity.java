package com.uws.project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;

import com.uws.project.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Welcome!");
        Button button = (Button) findViewById(R.id.Button);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                goToLoginActivity();

            }

        });
    }
    private void goToLoginActivity() {

        Intent intent = new Intent(this, LoginActivity.class);

        startActivity(intent);

    }
}
