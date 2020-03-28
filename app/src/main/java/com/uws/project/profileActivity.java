package com.uws.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class profileActivity extends AppCompatActivity {

    // This is all hardcoded for testing purposes and would be dynamic instead

    String username = "AlanB73";
    TextView usernameText, biographyText, commentText1, commentText2, commentText3;
    TextView titleText1, artistText1;
    TextView titleText2, artistText2;
    TextView titleText3, artistText3;
    ImageView artworkImage1, artworkImage2, artworkImage3;
    int resourceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(username);

        // Placeholder code for testing

        usernameText = findViewById(R.id.usernameText);
        usernameText.setText(username);

        biographyText = findViewById(R.id.bioText);
        biographyText.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Praesent sapien libero, vulputate euismod elit ullamcorper, iaculis suscipit dolor. " +
                "Morbi dignissim sit amet est quis suscipit. Curabitur venenatis eu ipsum eu molestie.");

        commentText1 = findViewById(R.id.commentText1);
        commentText1.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");

        commentText2 = findViewById(R.id.commentText2);
        commentText2.setText("Praesent sapien libero, vulputate euismod elit ullamcorper, iaculis suscipit dolor. .");

        commentText3 = findViewById(R.id.commentText3);
        commentText3.setText("Morbi dignissim sit amet est quis suscipit. Curabitur venenatis eu ipsum eu molestie.");

        titleText1 = findViewById(R.id.detailTitle1);
        titleText1.setText("Blinding Lights");
        artistText1 = findViewById(R.id.detailArtist1);
        artistText1.setText("Weeknd");
        resourceId = getResources().getIdentifier("blinding_lights","drawable",getPackageName());
        artworkImage1 = findViewById(R.id.detailArtwork1);
        artworkImage1.setImageResource(resourceId);

        titleText2 = findViewById(R.id.detailTitle2);
        titleText2.setText("Human");
        artistText2 = findViewById(R.id.detailArtist2);
        artistText2.setText("The Killers");
        resourceId = getResources().getIdentifier("human","drawable",getPackageName());
        artworkImage2 = findViewById(R.id.detailArtwork2);
        artworkImage2.setImageResource(resourceId);

        titleText3 = findViewById(R.id.detailTitle3);
        titleText3.setText("She's Casual");
        artistText3 = findViewById(R.id.detailArtist3);
        artistText3.setText("The Hunna");
        resourceId = getResources().getIdentifier("shes_casual","drawable",getPackageName());
        artworkImage3 = findViewById(R.id.detailArtwork3);
        artworkImage3.setImageResource(resourceId);

    }
}
