package com.uws.project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

public class profileActivity extends AppCompatActivity {

    // This is all hardcoded for testing purposes and would be dynamic instead

    ArrayList<String> profileObject;
    TextView usernameText, biographyText;
    TextView[] comments = new TextView[3];
    TextView[] titles = new TextView[3];
    TextView[] artists = new TextView[3];
    ImageView[] artworks = new ImageView[3];
    int resourceId;
    Profile currentUser;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("My Profile");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            currentUser = extras.getParcelable("user_details");
        }

        // Placeholder code for testing

        usernameText = findViewById(R.id.usernameText);
        usernameText.setText(currentUser.getUsername());


        biographyText = findViewById(R.id.bioText);
        biographyText.setText(currentUser.getBiography());

        comments[0] = findViewById(R.id.commentText1);
        comments[0].setText(currentUser.getComments()[0]);

        comments[1] = findViewById(R.id.commentText2);
        comments[1].setText(currentUser.getComments()[1]);

        comments[2] = findViewById(R.id.commentText3);
        comments[2].setText(currentUser.getComments()[2]);


// CHANGE PASSWORD BUTTON
//        Button changePasswordButton = findViewById(R.id.changePassword);
//        changePasswordButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                currentUser.changePassword();
//                Context context = getApplicationContext();
//                int duration = Toast.LENGTH_SHORT;
//                Toast toast = Toast.makeText(context, currentUser.changePassword(), duration);
//                toast.show();
//            }
//            });


//        titles[0] = findViewById(R.id.detailTitle1);
//        titles[0].setText(currentUser.getTitles()[0]);
//        artists[0] = findViewById(R.id.detailArtist1);
//        artists[0].setText(currentUser.getArtists()[0]);
//        resourceId = getResources().getIdentifier(currentUser.getArtworks()[0], "drawable", getPackageName());
//        artworks[0] = findViewById(R.id.detailArtwork1);
//        artworks[0].setImageResource(resourceId);
//
//        titles[1] = findViewById(R.id.detailTitle2);
//        titles[1].setText(currentUser.getTitles()[1]);
//        artists[1] = findViewById(R.id.detailArtist2);
//        artists[1].setText(currentUser.getArtists()[1]);
//        resourceId = getResources().getIdentifier(currentUser.getArtworks()[1], "drawable", getPackageName());
//        artworks[1] = findViewById(R.id.detailArtwork2);
//        artworks[1].setImageResource(resourceId);
//
//        titles[2] = findViewById(R.id.detailTitle3);
//        titles[2].setText(currentUser.getTitles()[2]);
//        artists[2] = findViewById(R.id.detailArtist3);
//        artists[2].setText(currentUser.getArtists()[2]);
//        resourceId = getResources().getIdentifier(currentUser.getArtworks()[2], "drawable", getPackageName());
//        artworks[2] = findViewById(R.id.detailArtwork3);
//        artworks[2].setImageResource(resourceId);

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


