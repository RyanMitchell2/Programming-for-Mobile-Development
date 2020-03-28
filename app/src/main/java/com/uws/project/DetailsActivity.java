package com.uws.project;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    TextView textTitle;
    TextView textArtist;
    ImageView imageAlbum;

    TextView textLyrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String title = extras.getString("title");
            String artist = extras.getString("artist");
            String artwork = extras.getString("artwork");

            Context context = getApplicationContext();
            CharSequence announcement = title + " by " + artist;
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, announcement, duration);
            toast.show();
            setTitle(announcement);

            textTitle = findViewById(R.id.detailTitle);
            textTitle.setText(title);

            textArtist = findViewById(R.id.detailArtist);
            textArtist.setText(artist);

            int resourceId = getResources().getIdentifier(artwork,"drawable",getPackageName());
            imageAlbum = findViewById(R.id.detailArtwork);
            imageAlbum.setImageResource(resourceId);

        }

        // testing stuff

        String testLyrics1 = "Yeah\n\n" +
                "I been tryna call\n" +
                "I been on my own for long enough\n" +
                "Maybe you can show me how to love, maybe\n" +
                "I'm going through withdrawals\n" +
                "You don't even have to do too much\n" +
                "You can turn me on with just a touch, baby\n\n" +
                "I look around and Sin City's cold and empty (oh)\n" +
                "No one's around to judge me (oh)\n" +
                "I can't see clearly when you're gone\n\n" +
                "I said, ooh, I'm blinded by the lights\n" +
                "No, I can't sleep until I feel your touch\n" +
                "I said, ooh, I'm drowning in the night\n" +
                "Oh, when I'm like this, you're the one I trust\n" +
                "Hey, hey, hey\n\n" +
                "I'm running out of time\n" +
                "'Cause I can see the sun light up the sky\n" +
                "So I hit the road in overdrive, baby\n\n" +
                "Oh, the city's cold and empty (oh)\n" +
                "No one's around to judge me (oh)\n" +
                "I can't see clearly when you're gone\n\n" +
                "I said, ooh, I'm blinded by the lights\n" +
                "No, I can't sleep until I feel your touch\n" +
                "I said, ooh, I'm drowning in the night\n" +
                "Oh, when I'm like this, you're the one I trust";

        textLyrics = findViewById(R.id.detailLyrics);
        textLyrics.setText(testLyrics1);

    }
}
