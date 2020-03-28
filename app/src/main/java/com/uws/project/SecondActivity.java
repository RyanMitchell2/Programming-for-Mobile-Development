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

    public static String PACKAGE_NAME;
    RecyclerView leftSideView, rightSideView;
    SongAdapter adapterLeft, adapterRight;
    ArrayList<String> songsLeft, artistsLeft, artworkLeft, songsRight, artistsRight, artworkRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setTitle("Main Page");
        PACKAGE_NAME = getApplicationContext().getPackageName();

        // Profile button
        ImageButton profile_button = findViewById(R.id.profile);
        profile_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToProfileActivity();
            }
        });

        // Profile button
        ImageButton settings_button = findViewById(R.id.settings);
        settings_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToSettingsActivity();
            }
        });


        // Left side

        songsLeft = new ArrayList<>();
        songsLeft.add("Blinding Lights");
        songsLeft.add("Say So");
        songsLeft.add("Don't Start Now");
        songsLeft.add("No Time To Die");
        songsLeft.add("Lonely");
        songsLeft.add("Intentions");

        artistsLeft = new ArrayList<>();
        artistsLeft.add("Weeknd");
        artistsLeft.add("Doja Cat");
        artistsLeft.add("Dua Lipa");
        artistsLeft.add("Billie Eilish");
        artistsLeft.add("Joel Corry");
        artistsLeft.add("Justin Bieber");

        artworkLeft = new ArrayList<>();
        artworkLeft.add("blinding_lights");
        artworkLeft.add("say_so");
        artworkLeft.add("dont_start_now");
        artworkLeft.add("no_time_to_die");
        artworkLeft.add("lonely");
        artworkLeft.add("intentions");

        // Right side

        songsRight = new ArrayList<>();
        songsRight.add("She's Casual");
        songsRight.add("Someone You Loved");
        songsRight.add("Human");
        songsRight.add("Dare");
        songsRight.add("Sex");
        songsRight.add("Swim For Your Life");

        artistsRight = new ArrayList<>();
        artistsRight.add("The Hunna");
        artistsRight.add("Lewis Capaldi");
        artistsRight.add("The Killers");
        artistsRight.add("The Hunna");
        artistsRight.add("The 1975");
        artistsRight.add("The Pale White");

        artworkRight = new ArrayList<>();
        artworkRight.add("shes_casual");
        artworkRight.add("someone_you_loved");
        artworkRight.add("human");
        artworkRight.add("dare");
        artworkRight.add("sex");
        artworkRight.add("swim_for_your_life");

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

    private void goToSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    }

