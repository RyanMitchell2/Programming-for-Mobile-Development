package com.uws.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    public static String PACKAGE_NAME;
    RecyclerView leftSideView, rightSideView;
    SongAdapter adapterLeft, adapterRight;
    ArrayList<String> settingsObject;
    ArrayList<Song> leftSongs, rightSongs, allSongs;
    Profile currentUser;
    int song_id, array_pos;
    String[][] comments = new String[][]{{},{},{},{},{},{},{},{},{},{},{},{}};
    List<Integer> random_list;
    String checker = "no";
    SearchView main_search;

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

        // Generate random list of 10 unique numbers 0-9
        random_list = new ArrayList<>();
        for (int index = 0; index < 12; index++)
            random_list.add(index);
        Collections.shuffle(random_list);

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

        // Search bar stuff
        main_search = findViewById(R.id.mainSearch);
        main_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                goToSearchFunction();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                goToSearchFunction();
                return false;
            }
        });

        checker = "no";
        String[][] comments = new String[][]{{},{},{},{},{},{},{},{},{},{},{},{}};
        initialiseSongs(comments);

    }

    public void goToSearchFunction() {
        String search_query = main_search.getQuery().toString();
        boolean found = false;
        
        if (allSongs.size() == 12 && leftSongs.size() == 6 && rightSongs.size() == 6) {
            for (int index=0;index<allSongs.size();index++) {
                if (allSongs.get(index).getTitle().equalsIgnoreCase(search_query) || allSongs.get(index).getArtist().equalsIgnoreCase(search_query)) {
                    leftSongs.clear();
                    rightSongs.clear();
                    leftSongs.add(allSongs.get(index));
                    found = true;
                    checker = "yes";
                    initialiseAdapter();
                    Toast toast = Toast.makeText(getApplicationContext(), "Found song: " + search_query, Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    checker = "no";
                    found = false;
                }
            }
            if (!found) {
                checker = "no";
            }
        } else {
            String[][] comments = new String[][]{{},{},{},{},{},{},{},{},{},{},{},{}};
            initialiseSongs(comments);
            goToSearchFunction();
        }
    }

    private void initialiseSongs(String[][] comments) {

        String[] song_titles = {"Blinding Lights","Say So","Don't Start Now","No Time To Die","Lonely","Intentions","She's Casual","Someone You Loved","Human","Dare","Sex","Swim For Your Life"};
        String[] song_artists = {"Weeknd","Doja Cat","Dua Lipa","Billie Eilish","Joel Corry","Justin Bieber","The Hunna","Lewis Capaldi","The Killers","The Hunna","The 1975","The Pale White"};
        int[] song_artworks = {R.drawable.blinding_lights,R.drawable.say_so,R.drawable.dont_start_now,R.drawable.no_time_to_die,R.drawable.lonely,R.drawable.intentions,R.drawable.shes_casual,R.drawable.someone_you_loved,R.drawable.human,R.drawable.dare,R.drawable.sex,R.drawable.swim_for_your_life};
        int[] song_audios = {R.raw.blindinglights,R.raw.sayso,R.raw.dontstartnow,R.raw.notimetodie,R.raw.lonely,R.raw.intentions,R.raw.shescasual,R.raw.someoneyouloved,R.raw.human,R.raw.dare,R.raw.sex,R.raw.swimforyourlife};
        int[] song_lyrics = {R.string.blinding_lights,R.string.say_so,R.string.dont_start_now,R.string.no_time_to_die,R.string.lonely,R.string.intentions,R.string.shes_casual,R.string.someone_you_loved,R.string.human,R.string.dare,R.string.sex,R.string.swim_for_your_life};
        int count = song_titles.length;
        Song[] songs_array = new Song[count];
        int index;

        for (index=0;index<count;index++) {
            songs_array[index] = new Song(index,index,song_titles[index],song_artists[index],song_artworks[index],song_audios[index],song_lyrics[index],fetchComments(index,comments));
        }

        leftSongs = new ArrayList<>();
        rightSongs = new ArrayList<>();
        allSongs = new ArrayList<>();

        for (index = 0; index < count; index++) {
            allSongs.add(songs_array[index]);
            Song current_song = songs_array[random_list.get(index)];
            if (leftSongs.size() < 6) {
                current_song.setArray_pos(index);
                leftSongs.add(current_song);
            } else {
                current_song.setArray_pos(index - 6);
                rightSongs.add(current_song);
            }
        }

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
        adapterLeft = new SongAdapter(this, leftSongs, settingsObject, currentUser, checker);
        leftSideView.setAdapter(adapterLeft);

        rightSideView = findViewById(R.id.rightSideView);
        rightSideView.setLayoutManager(new LinearLayoutManager(this));
        adapterRight = new SongAdapter(this, rightSongs, settingsObject, currentUser, checker);
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
            checker = "no";
            main_search.setQuery("",false);
            main_search.clearFocus();
            initialiseSongs(comments);
        }
    }

}

