package com.uws.project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity implements Serializable {

    public static String PACKAGE_NAME;
    RecyclerView leftSideView, rightSideView;
    SongAdapter adapterLeft, adapterRight;
    ArrayList<String> songsLeft, artistsLeft, artworkLeft, songsRight, artistsRight, artworkRight;
    ArrayList<String> settingsObject;
    ArrayList<Integer> audioLeft, audioRight;

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

        initialiseAdapater();



    }

    private void initialiseAdapater() {

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
    // SET UP LEFT AUDIO
        audioLeft = new ArrayList<>();
        audioLeft.add(R.raw.blindinglights);
        audioLeft.add(R.raw.sayso);
        audioLeft.add(R.raw.dontstartnow);
        audioLeft.add(R.raw.notimetodie);
        audioLeft.add(R.raw.lonely);
        audioLeft.add(R.raw.intentions);

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
        // SET UP RIGHT AUDIO
        audioRight = new ArrayList<>();
        audioRight.add(R.raw.shescasual);
        audioRight.add(R.raw.someoneyouloved);
        audioRight.add(R.raw.human);
        audioRight.add(R.raw.dare);
        audioRight.add(R.raw.sex);
        audioRight.add(R.raw.swimforyourlife);



        if (settingsObject == null) {
            settingsObject = new ArrayList<>();
            settingsObject.add("Light");
            settingsObject.add("White");
            settingsObject.add("18sp");
            settingsObject.add("1x");
            settingsObject.add("Grey");
        }

        leftSideView = findViewById(R.id.leftSideView);
        leftSideView.setLayoutManager(new LinearLayoutManager(this));
        adapterLeft = new SongAdapter(this,songsLeft,artistsLeft,artworkLeft,settingsObject, audioLeft);
        leftSideView.setAdapter(adapterLeft);

        rightSideView = findViewById(R.id.rightSideView);
        rightSideView.setLayoutManager(new LinearLayoutManager(this));
        adapterRight = new SongAdapter(this,songsRight,artistsRight,artworkRight,settingsObject, audioRight);
        rightSideView.setAdapter(adapterRight);

    }

    private void goToProfileActivity() {
        Intent intent = new Intent(this, profileActivity.class);
        startActivity(intent);
    }

    private void goToSettingsActivity() {
        int resultCode = 2;
        Intent intent = new Intent(this,SettingsActivity.class);
        intent.putExtra("settings", settingsObject);
        startActivityForResult(intent, resultCode); // suppose resultCode == 2
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            settingsObject = data.getStringArrayListExtra("settings");
            initialiseAdapater();

            Context context = getApplicationContext();
            CharSequence announcement = "second toast:" + " " + settingsObject;
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, announcement, duration);
            toast.show();
        } else {
            initialiseAdapater();
            Context context = getApplicationContext();
            CharSequence announcement = "second toast: bad result code";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, announcement, duration);
            toast.show();
        }
    }


    }

