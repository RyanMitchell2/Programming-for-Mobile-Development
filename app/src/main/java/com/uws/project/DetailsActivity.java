package com.uws.project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    TextView textTitle;
    TextView textArtist;
    ImageView imageAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String title = extras.getString("title");
            String artist = extras.getString("artist");
            String artwork = extras.getString("artwork");

            Context context = getApplicationContext();
            CharSequence text = title + " by " + artist;
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            textTitle = findViewById(R.id.detailTitle);
            textTitle.setText(title);

            textArtist = findViewById(R.id.detailArtist);
            textArtist.setText(artist);

            int resourceId = getResources().getIdentifier(artwork,"drawable",getPackageName());
            imageAlbum = findViewById(R.id.detailArtwork);
            imageAlbum.setImageResource(resourceId);

        }

    }
}
