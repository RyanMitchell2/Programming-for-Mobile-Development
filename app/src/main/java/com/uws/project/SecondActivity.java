package com.uws.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    RecyclerView leftSideView;
    RecyclerView rightSideView;

    SongAdapter adapterLeft;
    SongAdapter adapterRight;

    ArrayList<String> songsLeft;
    ArrayList<String> artistsLeft;
    ArrayList<String> artworkLeft;

    ArrayList<String> songsRight;
    ArrayList<String> artistsRight;
    ArrayList<String> artworkRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setTitle("Main Page");

        // Profile button
        ImageButton button = (ImageButton) findViewById(R.id.profile);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToProfileActivity();
            }
        });


        // Left side

        songsLeft = new ArrayList<>();
        songsLeft.add("Blinding Lights");
        songsLeft.add("Roses");
        songsLeft.add("Don't Start Now");
        songsLeft.add("No Time To Die");
        songsLeft.add("Lonely");
        songsLeft.add("Physical");

        artistsLeft = new ArrayList<>();
        artistsLeft.add("Weeknd");
        artistsLeft.add("Saint Jhn");
        artistsLeft.add("Dua Lipa");
        artistsLeft.add("Billie Eilish");
        artistsLeft.add("Joel Corry");
        artistsLeft.add("Dua Lipa");

        artworkLeft = new ArrayList<>();
        artworkLeft.add("hunna1");
        artworkLeft.add("hunna2");
        artworkLeft.add("killers1");
        artworkLeft.add("hunna1");
        artworkLeft.add("hunna2");
        artworkLeft.add("killers1");

        // Right side

        songsRight = new ArrayList<>();
        songsRight.add("Say So");
        songsRight.add("Someone You Loved");
        songsRight.add("Intentions");
        songsRight.add("You Should Be Sad");
        songsRight.add("Godzilla");
        songsRight.add("Life Is Good");

        artistsRight = new ArrayList<>();
        artistsRight.add("Doja Cat");
        artistsRight.add("Lewis Capaldi");
        artistsRight.add("Justin Bieber");
        artistsRight.add("Halsey");
        artistsRight.add("Eminem");
        artistsRight.add("Future");

        artworkRight = new ArrayList<>();
        artworkRight.add("hunna1");
        artworkRight.add("hunna2");
        artworkRight.add("killers1");
        artworkRight.add("hunna2");
        artworkRight.add("hunna1");
        artworkRight.add("killers1");

        leftSideView = findViewById(R.id.leftSideView);
        leftSideView.setLayoutManager(new LinearLayoutManager(this));
        adapterLeft = new SongAdapter(this,songsLeft,artistsLeft,artworkLeft);
        leftSideView.setAdapter(adapterLeft);

        rightSideView = findViewById(R.id.rightSideView);
        rightSideView.setLayoutManager(new LinearLayoutManager(this));
        adapterRight = new SongAdapter(this,songsRight,artistsRight,artworkRight);
        rightSideView.setAdapter(adapterRight);

    }

    private void goToProfileActivity() {

        Intent intent = new Intent(this, profileActivity.class);
        startActivity(intent);

    }
    }

