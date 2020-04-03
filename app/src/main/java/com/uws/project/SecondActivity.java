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
    ArrayList<String> settingsObject;
    ArrayList<Song> leftSongs;
    ArrayList<Song> rightSongs;
    ArrayList<Song> allSongs;
    Profile currentUser;
    int song_id, array_pos;

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

        ArrayList<Song> updateSongs = new ArrayList<>();
        initialiseSongs(updateSongs);

    }

    private void initialiseSongs(ArrayList<Song> updateSongs) {

        Song song1 = new Song(0,0,"Blinding Lights","Weeknd","blinding_lights",R.raw.blindinglights, fetchComments(0,updateSongs));
        Song song2 = new Song(1,1,"Say So","Doja Cat","say_so",R.raw.sayso, fetchComments(1,updateSongs));
        Song song3 = new Song(2,2,"Don't Start Now","Dua Lipa","dont_start_now",R.raw.dontstartnow, fetchComments(2,updateSongs));
        Song song4 = new Song(3,3,"No Time To Die","Billie Eilish","no_time_to_die",R.raw.notimetodie, fetchComments(3,updateSongs));
        Song song5 = new Song(4,4,"Lonely","Joel Corry","lonely",R.raw.lonely, fetchComments(4,updateSongs));
        Song song6 = new Song(5,5,"Intentions","Justin Bieber","intentions",R.raw.intentions, fetchComments(5,updateSongs));

        Song song7 = new Song(6,0,"She's Casual","The Hunna","shes_casual",R.raw.shescasual, fetchComments(0,updateSongs));
        Song song8 = new Song(7,1,"Someone You Loved","Lewis Capaldi","someone_you_loved",R.raw.someoneyouloved, fetchComments(1,updateSongs));
        Song song9 = new Song(8,2,"Human","The Killers","human",R.raw.human, fetchComments(2,updateSongs));
        Song song10 = new Song(9,3,"Dare","The Hunna","dare",R.raw.dare, fetchComments(3,updateSongs));
        Song song11 = new Song(10,4,"Sex","The 1975","sex",R.raw.sex, fetchComments(4,updateSongs));
        Song song12 = new Song(11,5,"Swim For Your Life","The Pale White","swim_for_your_life",R.raw.swimforyourlife, fetchComments(5,updateSongs));

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
        allSongs.add(song1);
        allSongs.add(song2);
        allSongs.add(song3);
        allSongs.add(song4);
        allSongs.add(song5);
        allSongs.add(song6);
        allSongs.add(song7);
        allSongs.add(song8);
        allSongs.add(song9);
        allSongs.add(song10);
        allSongs.add(song11);
        allSongs.add(song12);

        initialiseAdapter();

    }

    private String[] fetchComments(int pos, ArrayList<Song> updateSongs) {
        if (updateSongs.isEmpty()) {
            return new String[]{};
        } else {
            return updateSongs.get(pos).getComments();
        }
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
            song_id = data.getIntExtra("song_id",1);
            array_pos = data.getIntExtra("array_pos",0);

            initialiseSongs(updateSongs);
        }
    }

}

