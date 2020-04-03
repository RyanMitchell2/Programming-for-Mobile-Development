package com.uws.project;

import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable {

    private int song_id;
    private int array_pos;
    private String title;
    private String artist;
    private String artwork;
    private Integer audio;
    private String[] comments;

    Song(int song_id, int array_pos, String username, String artist, String artwork, Integer audio, String[] comments) {
        this.song_id = song_id;
        this.array_pos = array_pos;
        this.title = username;
        this.artist = artist;
        this.artwork = artwork;
        this.audio = audio;
        this.comments = comments;
    }

    public int getSong_id() {
        return song_id;
    }

    public int getArray_pos() {
        return array_pos;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getArtwork() {
        return artwork;
    }

    public Integer getAudio() {
        return audio;
    }

    public String[] getComments() {
        return comments;
    }

    public void setComments(String[] comments) {
        this.comments = comments;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(song_id);
        dest.writeInt(array_pos);
        dest.writeString(title);
        dest.writeString(artist);
        dest.writeString(artwork);
        dest.writeInt(audio);
        dest.writeStringArray(comments);
    }

    private Song(Parcel parcel) {
        song_id = parcel.readInt();
        array_pos = parcel.readInt();
        title = parcel.readString();
        artist = parcel.readString();
        artwork = parcel.readString();
        audio = parcel.readInt();
        comments = parcel.createStringArray();
    }

    public static final Parcelable.Creator<Song> CREATOR = new Parcelable.Creator<Song>() {

        @Override
        public Song createFromParcel(Parcel parcel) {
            return new Song(parcel);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

}
