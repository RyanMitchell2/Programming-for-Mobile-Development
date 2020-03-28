package com.uws.project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class profileActivity extends AppCompatActivity {

    // This is all hardcoded for testing purposes and would be dynamic instead

    String username = "AlanB73";
    TextView usernameText, biographyText;
    TextView[] comments = new TextView[3];
    TextView[] titles = new TextView[3];
    TextView[] artists = new TextView[3];
    ImageView[] artworks = new ImageView[3];
    int resourceId;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle(username);

        // Placeholder code for testing

        usernameText = findViewById(R.id.usernameText);
        usernameText.setText(username);

        biographyText = findViewById(R.id.bioText);
        biographyText.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Praesent sapien libero, vulputate euismod elit ullamcorper, iaculis suscipit dolor. " +
                "Morbi dignissim sit amet est quis suscipit. Curabitur venenatis eu ipsum eu molestie.");

        comments[0] = findViewById(R.id.commentText1);
        comments[0].setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");

        comments[1] = findViewById(R.id.commentText2);
        comments[1].setText("Praesent sapien libero, vulputate euismod elit ullamcorper, iaculis suscipit dolor. .");

        comments[2] = findViewById(R.id.commentText3);
        comments[2].setText("Morbi dignissim sit amet est quis suscipit. Curabitur venenatis eu ipsum eu molestie.");

        titles[0] = findViewById(R.id.detailTitle1);
        titles[0].setText("Blinding Lights");
        artists[0] = findViewById(R.id.detailArtist1);
        artists[0].setText("Weeknd");
        resourceId = getResources().getIdentifier("blinding_lights","drawable",getPackageName());
        artworks[0] = findViewById(R.id.detailArtwork1);
        artworks[0].setImageResource(resourceId);

        titles[1] = findViewById(R.id.detailTitle2);
        titles[1].setText("Human");
        artists[1] = findViewById(R.id.detailArtist2);
        artists[1].setText("The Killers");
        resourceId = getResources().getIdentifier("human","drawable",getPackageName());
        artworks[1] = findViewById(R.id.detailArtwork2);
        artworks[1].setImageResource(resourceId);

        titles[2] = findViewById(R.id.detailTitle3);
        titles[2].setText("She's Casual");
        artists[2] = findViewById(R.id.detailArtist3);
        artists[2].setText("The Hunna");
        resourceId = getResources().getIdentifier("shes_casual","drawable",getPackageName());
        artworks[2] = findViewById(R.id.detailArtwork3);
        artworks[2].setImageResource(resourceId);

    }
}
