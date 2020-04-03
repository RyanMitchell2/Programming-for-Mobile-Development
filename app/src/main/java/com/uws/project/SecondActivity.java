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

import java.util.ArrayList;
import java.util.Arrays;

public class SecondActivity extends AppCompatActivity {

    public static String PACKAGE_NAME;
    RecyclerView leftSideView, rightSideView;
    SongAdapter adapterLeft, adapterRight;
    ArrayList<String> settingsObject;
    ArrayList<Song> leftSongs, rightSongs;
    Profile currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setTitle("Main Page");
        PACKAGE_NAME = getApplicationContext().getPackageName();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            currentUser = extras.getParcelable("user_details");

            Context context = getApplicationContext();
            CharSequence announcement = "second toast:" + " " + " " + currentUser.getUsername();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, announcement, duration);
            toast.show();
        }

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

        initialiseAdapter();

    }

    private void initialiseAdapter() {

        if (settingsObject == null) {
            settingsObject = new ArrayList<>();
            settingsObject.add("Light");
            settingsObject.add("White");
            settingsObject.add("18sp");
            settingsObject.add("1x");
            settingsObject.add("Grey");
        }

        Song song1 = new Song(1,"Blinding Lights","Weeknd","blinding_lights",R.raw.blindinglights, new String[]{"Weeknd rocks", "comment2"});
        Song song2 = new Song(2,"Say So","Doja Cat","say_so",R.raw.sayso, new String[]{"Doja rocks", "comment2"});
        Song song3 = new Song(3,"Don't Start Now","Dua Lipa","dont_start_now",R.raw.dontstartnow, new String[]{"Dua rocks", "comment2"});
        Song song4 = new Song(4,"No Time To Die","Billie Eilish","no_time_to_die",R.raw.notimetodie, new String[]{"Billie rocks", "comment2"});
        Song song5 = new Song(5,"Lonely","Joel Corry","lonely",R.raw.lonely, new String[]{"Lonely rocks", "comment2"});
        Song song6 = new Song(6,"Intentions","Justin Bieber","intentions",R.raw.intentions, new String[]{"Justin rocks", "comment2"});

        Song song7 = new Song(7,"She's Casual","The Hunna","shes_casual",R.raw.shescasual, new String[]{"Hunna rocks", "comment2"});
        Song song8 = new Song(8,"Someone You Loved","Lewis Capaldi","someone_you_loved",R.raw.someoneyouloved, new String[]{"Lewis rocks", "comment2"});
        Song song9 = new Song(9,"Human","The Killers","human",R.raw.human, new String[]{"Killers rocks", "comment2"});
        Song song10 = new Song(10,"Dare","The Hunna","dare",R.raw.dare, new String[]{"Hunna rocks", "comment2"});
        Song song11 = new Song(11,"Sex","The 1975","sex",R.raw.sex, new String[]{"1975 rocks", "comment2"});
        Song song12 = new Song(12,"Swim For Your Life","The Pale White","swim_for_your_life",R.raw.swimforyourlife, new String[]{"Pale rocks", "comment2"});

        leftSongs = new ArrayList<>();
        leftSongs.add(song1);
        leftSongs.add(song2);
        leftSongs.add(song3);
        leftSongs.add(song4);
        leftSongs.add(song5);
        leftSongs.add(song6);

        rightSongs = new ArrayList<>();
        rightSongs.add(song7);
        rightSongs.add(song8);
        rightSongs.add(song9);
        rightSongs.add(song10);
        rightSongs.add(song11);
        rightSongs.add(song12);

        leftSideView = findViewById(R.id.leftSideView);
        leftSideView.setLayoutManager(new LinearLayoutManager(this));
        adapterLeft = new SongAdapter(this, leftSongs, settingsObject, currentUser);
        leftSideView.setAdapter(adapterLeft);

        rightSideView = findViewById(R.id.rightSideView);
        rightSideView.setLayoutManager(new LinearLayoutManager(this));
        adapterRight = new SongAdapter(this, rightSongs, settingsObject, currentUser);
        rightSideView.setAdapter(adapterRight);

    }

    private void goToProfileActivity() {
        Intent intent = new Intent(this, profileActivity.class);
        intent.putExtra("user_details", currentUser);
        startActivity(intent);
    }

    private void goToSettingsActivity() {
        int resultCode = 2;
        Intent intent = new Intent(this,SettingsActivity.class);
        intent.putExtra("user_details", currentUser);
        intent.putExtra("settings", settingsObject);
        startActivityForResult(intent, resultCode); // suppose resultCode == 2
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            currentUser = data.getParcelableExtra("user_details");
            settingsObject = data.getStringArrayListExtra("settings");
            initialiseAdapter();

            Context context = getApplicationContext();
            CharSequence announcement = "second toast:" + " " + settingsObject + " " + currentUser.getUsername();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, announcement, duration);
            toast.show();
        } else {
            initialiseAdapter();
            Context context = getApplicationContext();
            CharSequence announcement = "second toast: bad result code";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, announcement, duration);
            toast.show();
        }
    }


    }

