package com.uws.project;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

public class DetailsActivity extends AppCompatActivity {

    TextView textTitle, textArtist, textLyrics;
    ImageView imageAlbum;
    ArrayList<String> settingsObject;
    MediaPlayer player;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        context= this;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String title = extras.getString("title");
            String artist = extras.getString("artist");
            String artwork = extras.getString("artwork");
            settingsObject = extras.getStringArrayList("settings");

            Context context = getApplicationContext();
            CharSequence announcement = title + " by " + artist;
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, announcement, duration);
            toast.show();
            setTitle(announcement);

            announcement = "details toast:" + " " + settingsObject;
            duration = Toast.LENGTH_SHORT;
            toast = Toast.makeText(context, announcement, duration);
            toast.show();

            textTitle = findViewById(R.id.detailTitle);
            textTitle.setText(title);

            textArtist = findViewById(R.id.detailArtist);
            textArtist.setText(artist);

            int albumID = getResources().getIdentifier(artwork,"drawable",getPackageName());
            imageAlbum = findViewById(R.id.detailArtwork);
            imageAlbum.setImageResource(albumID);



            int lyricID = getResources().getIdentifier(artwork,"string",getPackageName());
            textLyrics = findViewById(R.id.detailLyrics);
            textLyrics.setText(lyricID);
            lyricsAppearance();





        }

    }

    public void play (View v){
        // PLACEHOLDER ARRAY UNTIL PASSING WORKS
        Bundle extras = getIntent().getExtras();
        Integer audio = null;
        if (extras != null) {
           audio = extras.getInt("audio");
        }
        int audioID = getResources().getIdentifier(String.valueOf(audio), "Integer", getPackageName());
        if (player == null) {
            player = MediaPlayer.create(this, audioID);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }
        player.start();
    }
    public void pause (View v){
        if (player != null) {
            player.pause();
        }
    }
    public void stop (View v) {
        stopPlayer();
    }
    private void stopPlayer() {
        if (player != null){
            player.release();
            player = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }

    public void lyricsAppearance() {

        int selection;

//        String[] style_placeholders = {"Light", "Regular", "Semi-bold", "Bold", "Black"};
//        String[] style_values = {"Light", "Regular", "Semi-bold", "Bold", "Black"};

        String[] colour_placeholders = {"White","Red","Blue","Green","Yellow"};
        String[] colour_values = {"#FFFFFF","#ff0000","#0048ff","#00ff1e","#fffb00"};
        selection = getSavedSetting(colour_placeholders, 1);
        textLyrics.setTextColor(Color.parseColor(colour_values[selection]));

        String[] size_placeholders = {"16sp", "18sp", "20sp", "22sp", "24sp"};
        int[] size_values = {16,18,20,22,24};
        selection = getSavedSetting(size_placeholders, 2);
        textLyrics.setTextSize(size_values[selection]);

//        Double[] speed_placeholders = {0.5,0.75,1.0,1.25,1.5};
//        Double[] speed_values = {0.5,0.75,1.0,1.25,1.5};

        String[] background_placeholders = {"White","Black","Grey","Blue","Red","Yellow"};
        String[] background_values = {"#FFFFFF","#000000","#303030","#0048ff","#00ff1e","#fffb00"};
        selection = getSavedSetting(background_placeholders, 4);
        textLyrics.setBackgroundColor(Color.parseColor(background_values[selection]));

    }
// GETTING THE SETTINGS THAT HAVE BEEN SAVED PREVIOUSLY
    private int getSavedSetting(String[] placeholders, int position){
        int index = 0;
        for (int i=0;i<placeholders.length;i++){
            if (settingsObject.get(position).equals(placeholders[i])){
                index = i;
            }
        }
        return index;
    }
    // BACK BUTTON CODE
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(2,intent);
        finish();
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
