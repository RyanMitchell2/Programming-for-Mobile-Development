package com.uws.project;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DetailsActivity extends AppCompatActivity {

    // My code
    TextView textTitle, textArtist, textLyrics;
    ImageView imageAlbum;
    ArrayList<String> settingsObject, comments_body;

    MediaPlayer player;
    Context context;
    Button playButton, pauseButton, stopButton, likeButton;

    RecyclerView commentsRecycler;
    CommentAdapter commentAdapter;
    EditText editComment;

    ArrayList<Song> songs;
    Profile currentUser;
    int[] current_liked, new_liked;
    int song_id, array_pos, song1, song2, song3;
    String title, artist;
    Integer artwork, audio, lyrics;
    String[] comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        context= this;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            songs = extras.getParcelableArrayList("songs");
            settingsObject = extras.getStringArrayList("settings");
            currentUser = extras.getParcelable("user_details");
            song_id = extras.getInt("song_id");
            array_pos = extras.getInt("array_pos");
            title = extras.getString("title");
            artist = extras.getString("artist");
            artwork = extras.getInt("artwork");
            audio = extras.getInt("audio");
            lyrics = extras.getInt("lyrics");
            comments = extras.getStringArray("comments");
        }

        setTitle(title + " by " + artist);

        playButton = findViewById(R.id.playButton);
        pauseButton = findViewById(R.id.pauseButton);
        stopButton = findViewById(R.id.stopButton);
        likeButton = findViewById(R.id.likeButton);

        current_liked = currentUser.getSongs();
        song1 = current_liked[0];
        song2 = current_liked[1];
        song3 = current_liked[2];

        textTitle = findViewById(R.id.detailTitle);
        textTitle.setText(title);

        textArtist = findViewById(R.id.detailArtist);
        textArtist.setText(artist);

        imageAlbum = findViewById(R.id.detailArtwork);
        imageAlbum.setImageResource(artwork);

        textLyrics = findViewById(R.id.detailLyrics);
        textLyrics.setText(lyrics);

        lyricsAppearance();
        initialiseComments();

        // Comment button
        Button postButton = findViewById(R.id.postButton);
        postButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                postComment();
            }
        });

        // Like stuff
        if (song_id == song1) {
            new_liked = new int[]{song1, song2, song3};
            likeButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
            likeButton.setTextColor(Color.parseColor("#303030"));
            likeButton.setText(R.string.liked);
        } else if (song_id == song2) {
            new_liked = new int[]{song2, song1, song3};
            likeButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
            likeButton.setTextColor(Color.parseColor("#303030"));
            likeButton.setText(R.string.liked);
        } else if (song_id == song3) {
            new_liked = new int[]{song3, song1, song2};
            likeButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
            likeButton.setTextColor(Color.parseColor("#303030"));
            likeButton.setText(R.string.liked);
        } else {
            new_liked = new int[]{song_id, song1, song2};
        }

        // Like button
        likeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                likeSong();
            }
        });
    }

    public void initialiseComments() {
        comments_body = new ArrayList<>();
        comments_body.addAll(Arrays.asList(comments));

        if (comments_body.isEmpty()) {
            comments_body.add("No comments yet");
        }

        commentsRecycler = findViewById(R.id.commentsRecycler);
        commentsRecycler.setLayoutManager(new LinearLayoutManager(this));
        commentAdapter = new CommentAdapter(this,comments_body, currentUser.getUsername());
        commentsRecycler.setAdapter(commentAdapter);
    }

    public void postComment() {
        editComment = findViewById(R.id.editComment);
        String current_text = editComment.getText().toString();
        if (!current_text.equals("") && comments_body != null) {
            comments_body.add(current_text);
            List<String> comments_list = new ArrayList<>(Arrays.asList(comments));
            comments_list.add(current_text);
            comments = comments_list.toArray(new String[]{});
            songs.get(array_pos).setComments(comments);

            String[] user_comments = currentUser.getComments();
            List<String> user_list = new ArrayList<>(Arrays.asList(user_comments));
            user_list.add(current_text);
            String[] test = user_list.toArray(new String[]{});
            currentUser.setComments(test);
        }

        commentsRecycler = findViewById(R.id.commentsRecycler);
        commentsRecycler.setLayoutManager(new LinearLayoutManager(this));
        commentAdapter = new CommentAdapter(this,comments_body, currentUser.getUsername());
        commentsRecycler.setAdapter(commentAdapter);
    }

    public void likeSong() {
        likeButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
        likeButton.setTextColor(Color.parseColor("#303030"));
        likeButton.setText(R.string.liked);
        currentUser.setSongs(new_liked);
    }

    public void play (View v){
        if (player == null) {
            player = MediaPlayer.create(this, audio);
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

        String[] style_placeholders = {"Light", "Regular", "Semi-bold", "Bold", "Black", "Comic Sans"};
        int[] style_values = {R.font.source_sans_pro_light,R.font.source_sans_pro,R.font.source_sans_pro_semibold,R.font.source_sans_pro_bold,R.font.source_sans_pro_black,R.font.comic_sans};
        selection = getSavedSetting(style_placeholders,0);
        Typeface typeface = ResourcesCompat.getFont(context, style_values[selection]);
        textLyrics.setTypeface(typeface);

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
        intent.putExtra("songs", songs);
        intent.putExtra("settings", settingsObject);
        intent.putExtra("user_details", currentUser);
        intent.putExtra("song_id", songs.get(array_pos).getSong_id());
        intent.putExtra("array_pos", songs.get(array_pos).getArray_pos());
        setResult(3,intent);
        finish();
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
