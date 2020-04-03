package com.uws.project;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class DetailsActivity extends AppCompatActivity {

    TextView textTitle, textArtist, textLyrics;
    ImageView imageAlbum;
    ArrayList<String> settingsObject, comments_body;

    MediaPlayer player;
    Context context;
    Button playButton, pauseButton, stopButton;

    RecyclerView commentsRecycler;
    CommentAdapter commentAdapter;
    EditText editComment;

    ArrayList<Song> songs;
    Profile currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        context= this;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            songs = extras.getParcelable("songs");
            settingsObject = extras.getStringArrayList("settings");
            currentUser = extras.getParcelable("user_details");
            int song_id = extras.getInt("song_id");
            String title = extras.getString("title");
            String artist = extras.getString("artist");
            String artwork = extras.getString("artwork");
            Integer audio = extras.getInt("audio");
            String[] comments = extras.getStringArray("comments");

            setTitle(title + " by " + artist);

            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            CharSequence announcement = "details toast:" + " " + settingsObject;
            Toast toast = Toast.makeText(context, announcement, duration);
            toast.show();

            playButton = findViewById(R.id.playButton);
            pauseButton = findViewById(R.id.pauseButton);
            stopButton = findViewById(R.id.stopButton);

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
            initialiseComments();

        }

        // Comment button
        Button postButton = findViewById(R.id.postButton);
        postButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                postComment();
            }
        });

    }

    public void initialiseComments() {

        comments_body = new ArrayList<>();
        comments_body.add("1 - comment text would be here ahaha haha i hope this works yo");
        comments_body.add("2 - comment text would be here ahaha haha i hope this works yo");

        commentsRecycler = findViewById(R.id.commentsRecycler);
        commentsRecycler.setLayoutManager(new LinearLayoutManager(this));
        commentAdapter = new CommentAdapter(this,comments_body);
        commentsRecycler.setAdapter(commentAdapter);
    }

    public void postComment() {
        editComment = findViewById(R.id.editComment);
        String current_text = editComment.getText().toString();
        if (!current_text.equals("") && comments_body != null) {
            comments_body.add(current_text);
        }
        commentsRecycler = findViewById(R.id.commentsRecycler);
        commentsRecycler.setLayoutManager(new LinearLayoutManager(this));
        commentAdapter = new CommentAdapter(this,comments_body);
        commentsRecycler.setAdapter(commentAdapter);
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

        // SET PLAY TO WHITE, PAUSE AND STOP TO GREY
        playButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
        playButton.setTextColor(Color.parseColor("#303030"));
        pauseButton.setBackgroundColor(Color.parseColor("#303030"));
        pauseButton.setTextColor(Color.parseColor("#FFFFFF"));
        stopButton.setBackgroundColor(Color.parseColor("#303030"));
        stopButton.setTextColor(Color.parseColor("#FFFFFF"));
    }
    public void pause (View v){
        if (player != null) {
            player.pause();

            // SET PAUSE TO WHITE, PLAY AND STOP TO GREY
            pauseButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
            pauseButton.setTextColor(Color.parseColor("#303030"));
            playButton.setBackgroundColor(Color.parseColor("#303030"));
            playButton.setTextColor(Color.parseColor("#FFFFFF"));
            stopButton.setBackgroundColor(Color.parseColor("#303030"));
            stopButton.setTextColor(Color.parseColor("#FFFFFF"));
        }
    }

    public void stop (View v) {
        stopPlayer();
    }

    private void stopPlayer() {
        if (player != null){
            player.release();
            player = null;

            // SET STOP TO WHITE, PLAY AND PAUSE TO GREY
            stopButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
            stopButton.setTextColor(Color.parseColor("#303030"));
            playButton.setBackgroundColor(Color.parseColor("#303030"));
            playButton.setTextColor(Color.parseColor("#FFFFFF"));
            pauseButton.setBackgroundColor(Color.parseColor("#303030"));
            pauseButton.setTextColor(Color.parseColor("#FFFFFF"));
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

        String[] colour_placeholders = {"White","Black","Grey","Red","Orange","Yellow","Green","Blue","Indigo", "Violet"};
        String[] colour_values = {"#FFFFFF","#000000","#303030","#FF0000","#FF7F00","#FFFF00","#00FF00","#0000FF","#4B0082","#9400D3"};
        selection = getSavedSetting(colour_placeholders, 1);
        textLyrics.setTextColor(Color.parseColor(colour_values[selection]));

        String[] size_placeholders = {"16sp", "18sp", "20sp", "22sp", "24sp"};
        int[] size_values = {16,18,20,22,24};
        selection = getSavedSetting(size_placeholders, 2);
        textLyrics.setTextSize(size_values[selection]);

//        Double[] speed_placeholders = {0.5,0.75,1.0,1.25,1.5};
//        Double[] speed_values = {0.5,0.75,1.0,1.25,1.5};

        String[] background_placeholders = {"White","Black","Grey","Red","Orange","Yellow","Green","Blue","Indigo", "Violet"};
        String[] background_values = {"#FFFFFF","#000000","#303030","#FF0000","#FF7F00","#FFFF00","#00FF00","#0000FF","#4B0082","#9400D3"};
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
