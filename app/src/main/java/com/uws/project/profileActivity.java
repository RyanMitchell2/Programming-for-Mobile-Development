package com.uws.project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

public class profileActivity extends AppCompatActivity {

    // This is all hardcoded for testing purposes and would be dynamic instead

    TextView usernameText, biographyText;
    TextView[] comments = new TextView[3];
    TextView[] titles = new TextView[3];
    TextView[] artists = new TextView[3];
    ImageView[] artworks = new ImageView[3];
    Profile currentUser;
    ArrayList<String> settingsObject;
    ArrayList<Song> songs;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("My Profile");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            settingsObject = extras.getStringArrayList("settings");
            currentUser = extras.getParcelable("user_details");
            songs = extras.getParcelableArrayList("songs");
        }

        usernameText = findViewById(R.id.usernameText);
        usernameText.setText(currentUser.getUsername());

        biographyText = findViewById(R.id.bioText);
        biographyText.setText(currentUser.getBiography());

        comments[0] = findViewById(R.id.commentText1);
        comments[0].setText(currentUser.getComments()[currentUser.getComments().length-1]);

        comments[1] = findViewById(R.id.commentText2);
        comments[1].setText(currentUser.getComments()[currentUser.getComments().length-2]);

        comments[2] = findViewById(R.id.commentText3);
        comments[2].setText(currentUser.getComments()[currentUser.getComments().length-3]);

        int[] current_liked = currentUser.getSongs();

        titles[0] = findViewById(R.id.detailTitle1);
        artists[0] = findViewById(R.id.detailArtist1);
        artworks[0] = findViewById(R.id.detailArtwork1);

        titles[1] = findViewById(R.id.detailTitle2);
        artists[1] = findViewById(R.id.detailArtist2);
        artworks[1] = findViewById(R.id.detailArtwork2);

        titles[2] = findViewById(R.id.detailTitle3);
        artists[2] = findViewById(R.id.detailArtist3);
        artworks[2] = findViewById(R.id.detailArtwork3);

        for (int index=0;index<3;index++) {
            titles[index].setText(songs.get(current_liked[index]).getTitle());
            artists[index].setText(songs.get(current_liked[index]).getArtist());
            artworks[index].setImageResource(songs.get(current_liked[index]).getArtwork());
        }

        setProfilePicture();

    }

    public void setProfilePicture() {
        String[] picture_placeholders = {"Default", "Male 2D", "Female 2D", "Outdoors 1", "Outdoors 2", "Outdoors 3"};
        Integer[] picture_values = {R.drawable.account,R.drawable.profile_male,R.drawable.profile_female,R.drawable.profile_outdoors1,R.drawable.profile_outdoors2,R.drawable.profile_outdoors3};
        ImageView profilePicture = findViewById(R.id.profilePicture);

        for (int i=0;i<picture_placeholders.length;i++){
            if (settingsObject.get(5).equals(picture_placeholders[i])){
                profilePicture.setImageResource(picture_values[i]);
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(2, intent);
        finish();
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}


