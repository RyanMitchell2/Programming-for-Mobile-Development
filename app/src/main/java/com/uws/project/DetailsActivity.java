package com.uws.project;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

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

    private int getSavedSetting(String[] placeholders, int position){
        int index = 0;
        for (int i=0;i<placeholders.length;i++){
            if (settingsObject.get(position).equals(placeholders[i])){
                index = i;
            }
        }
        return index;
    }

}
