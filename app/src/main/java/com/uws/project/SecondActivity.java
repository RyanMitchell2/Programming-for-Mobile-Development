package com.uws.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    public static String PACKAGE_NAME;
    RecyclerView leftSideView, rightSideView;
    SongAdapter adapterLeft, adapterRight;
    ArrayList<String> settingsObject;
    ArrayList<Song> leftSongs, rightSongs, allSongs;
    Profile currentUser;
    int song_id, array_pos;
    String[][] comments = new String[][]{{},{},{},{},{},{},{},{},{},{},{},{}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setTitle("Main Page");
        PACKAGE_NAME = getApplicationContext().getPackageName();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            currentUser = extras.getParcelable("user_details");
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

        String[][] comments = new String[][]{{},{},{},{},{},{},{},{},{},{},{},{}};
        initialiseSongs(comments);

    }

    private void initialiseSongs(String[][] comments) {

        Song song1 = new Song(0,0,"Blinding Lights","Weeknd",R.drawable.blinding_lights,R.raw.blindinglights, R.string.blinding_lights, fetchComments(0, comments));
        Song song2 = new Song(1,1,"Say So","Doja Cat",R.drawable.say_so,R.raw.sayso, R.string.say_so, fetchComments(1,comments));
        Song song3 = new Song(2,2,"Don't Start Now","Dua Lipa",R.drawable.dont_start_now,R.raw.dontstartnow, R.string.say_so, fetchComments(2,comments));
        Song song4 = new Song(3,3,"No Time To Die","Billie Eilish",R.drawable.no_time_to_die,R.raw.notimetodie, R.string.no_time_to_die, fetchComments(3,comments));
        Song song5 = new Song(4,4,"Lonely","Joel Corry",R.drawable.lonely,R.raw.lonely, R.string.lonely, fetchComments(4,comments));
        Song song6 = new Song(5,5,"Intentions","Justin Bieber",R.drawable.intentions,R.raw.intentions, R.string.intentions, fetchComments(5,comments));

        Song song7 = new Song(6,0,"She's Casual","The Hunna",R.drawable.shes_casual,R.raw.shescasual, R.string.shes_casual, fetchComments(6,comments));
        Song song8 = new Song(7,1,"Someone You Loved","Lewis Capaldi",R.drawable.someone_you_loved,R.raw.someoneyouloved, R.string.someone_you_loved, fetchComments(7,comments));
        Song song9 = new Song(8,2,"Human","The Killers",R.drawable.human,R.raw.human, R.string.human, fetchComments(8,comments));
        Song song10 = new Song(9,3,"Dare","The Hunna",R.drawable.dare,R.raw.dare, R.string.dare, fetchComments(9,comments));
        Song song11 = new Song(10,4,"Sex","The 1975",R.drawable.sex,R.raw.sex, R.string.sex, fetchComments(10,comments));
        Song song12 = new Song(11,5,"Swim For Your Life","The Pale White",R.drawable.swim_for_your_life,R.raw.swimforyourlife, R.string.swim_for_your_life, fetchComments(11,comments));

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

        allSongs = new ArrayList<>();
        allSongs.addAll(leftSongs);
        allSongs.addAll(rightSongs);

        initialiseAdapter();

    }

    private String[] fetchComments(int index, String[][] comments) {
        if (comments[index] == null) {
            comments[index] = new String[]{String.valueOf(index)};
        }
        return comments[index];
    }

    private void initialiseAdapter() {
        if (settingsObject == null) {
            settingsObject = new ArrayList<>();
            settingsObject.add("Light");
            settingsObject.add("White");
            settingsObject.add("18sp");
            settingsObject.add("1x");
            settingsObject.add("Grey");
            settingsObject.add("Default");
        }

        setProfilePicture();

        leftSideView = findViewById(R.id.leftSideView);
        leftSideView.setLayoutManager(new LinearLayoutManager(this));
        adapterLeft = new SongAdapter(this, leftSongs, settingsObject, currentUser);
        leftSideView.setAdapter(adapterLeft);

        rightSideView = findViewById(R.id.rightSideView);
        rightSideView.setLayoutManager(new LinearLayoutManager(this));
        adapterRight = new SongAdapter(this, rightSongs, settingsObject, currentUser);
        rightSideView.setAdapter(adapterRight);

    }

    public void setProfilePicture() {
        String[] picture_placeholders = {"Default", "Male 2D", "Female 2D", "Outdoors 1", "Outdoors 2", "Outdoors 3"};
        Integer[] picture_values = {R.drawable.account,R.drawable.profile_male,R.drawable.profile_female,R.drawable.profile_outdoors1,R.drawable.profile_outdoors2,R.drawable.profile_outdoors3};
        ImageView profilePicture = findViewById(R.id.profile);

        for (int i=0;i<picture_placeholders.length;i++){
            if (settingsObject.get(5).equals(picture_placeholders[i])){
                profilePicture.setImageResource(picture_values[i]);
            }
        }
    }

    private void goToProfileActivity() {
        Intent intent = new Intent(this, profileActivity.class);
        intent.putExtra("settings", settingsObject);
        intent.putExtra("user_details", currentUser);
        intent.putExtra("songs", allSongs);
        startActivity(intent);
    }

    private void goToSettingsActivity() {
        int resultCode = 2;
        Intent intent = new Intent(this,SettingsActivity.class);
        intent.putExtra("settings", settingsObject);
        intent.putExtra("user_details", currentUser);
        startActivityForResult(intent, resultCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            settingsObject = data.getStringArrayListExtra("settings");
            currentUser = data.getParcelableExtra("user_details");
            initialiseAdapter();
        } else if (requestCode == 3) {
            ArrayList<Song> updateSongs = data.getParcelableArrayListExtra("songs");
            settingsObject = data.getStringArrayListExtra("settings");
            currentUser = data.getParcelableExtra("user_details");
            song_id = data.getIntExtra("song_id",0);
            array_pos = data.getIntExtra("array_pos",0);

            if (updateSongs != null) {
                comments[song_id] = updateSongs.get(array_pos).getComments();
            }
            initialiseSongs(comments);
        }
    }

}

