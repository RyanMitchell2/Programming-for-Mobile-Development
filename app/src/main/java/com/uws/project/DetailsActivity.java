package com.uws.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    TextView textTitle;
    TextView textArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();

        String title = i.getStringExtra("title");
        textTitle.findViewById(R.id.detailTitle);
        textTitle.setText(title);

        String artist = i.getStringExtra("artist");
        textArtist.findViewById(R.id.detailTitle);
        textArtist.setText(artist);

    }
}
