package com.uws.project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Song> songs;
    private List<String> settings;
    private Profile currentUser;
    private Song currentSong;

    SongAdapter(Context context, ArrayList<Song> songs, List<String> settings, Profile currentUser) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.songs = songs;
        this.settings = settings;
        this.currentUser = currentUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.song_card,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        currentSong = songs.get(i);

        String title = currentSong.getTitle() + " " + currentSong.getSong_id();
        viewHolder.textTitle.setText(title);

        String artist = currentSong.getArtist() + " " + currentSong.getArray_pos();
        viewHolder.textArtist.setText(artist);

        Integer artwork = currentSong.getArtwork();
        viewHolder.imageAlbum.setImageResource(artwork);

    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textTitle, textArtist;
        ImageView imageAlbum;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentSong = songs.get(getAdapterPosition());
                    Intent i = new Intent(context, DetailsActivity.class);
                    i.putExtra("songs", songs);
                    i.putExtra("settings", (Serializable) settings);
                    i.putExtra("user_details", currentUser);
                    i.putExtra("song_id",currentSong.getSong_id());
                    i.putExtra("array_pos",currentSong.getArray_pos());
                    i.putExtra("title",currentSong.getTitle());
                    i.putExtra("artist",currentSong.getArtist());
                    i.putExtra("artwork",currentSong.getArtwork());
                    i.putExtra("audio", currentSong.getAudio());
                    i.putExtra("lyrics", currentSong.getLyrics());
                    i.putExtra("comments", currentSong.getComments());
                    int resultCode = 3;
                    ((Activity) context).startActivityForResult(i, resultCode);
                }
            });
            textTitle = itemView.findViewById(R.id.textTitle);
            textArtist = itemView.findViewById(R.id.textArtist);
            imageAlbum = itemView.findViewById(R.id.imageAlbum);

        }

    }

}
