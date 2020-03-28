package com.uws.project;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<String> titles;
    private List<String> artists;
    private List<String> artworks;

    SongAdapter(Context context, List<String> titles, List<String> artists, List<String> artworks) {
        this.layoutInflater = LayoutInflater.from(context);
        this.titles = titles;
        this.artists = artists;
        this.artworks = artworks;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.song_card,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        String title = titles.get(i);
        viewHolder.textTitle.setText(title);

        String artist = artists.get(i);
        viewHolder.textArtist.setText(artist);

        String artwork = artworks.get(i);
        int resourceId = context.getResources().getIdentifier(artwork,"drawable", SecondActivity.PACKAGE_NAME);
        viewHolder.imageAlbum.setImageResource(resourceId);

    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textTitle, textArtist;
        ImageView imageAlbum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), DetailsActivity.class);
                    i.putExtra("title",titles.get(getAdapterPosition()));
                    i.putExtra("artist",artists.get(getAdapterPosition()));
                    i.putExtra("artwork",artworks.get(getAdapterPosition()));
                    v.getContext().startActivity(i);
                }
            });
            textTitle = itemView.findViewById(R.id.textTitle);
            textArtist = itemView.findViewById(R.id.textArtist);
            imageAlbum = itemView.findViewById(R.id.imageAlbum);
        }

    }
}
