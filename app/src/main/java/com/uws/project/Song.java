package com.uws.project;

import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable {

    private int song_id, array_pos;
    private String title, artist;
    private Integer artwork, audio, lyrics;
    private String[] comments;

    Song(int song_id, int array_pos, String username, String artist, Integer artwork, Integer audio, Integer lyrics, String[] comments) {
        this.song_id = song_id;
        this.array_pos = array_pos;
        this.title = username;
        this.artist = artist;
        this.artwork = artwork;
        this.audio = audio;
        this.lyrics = lyrics;
        this.comments = comments;
    }

    int getSong_id() {
        return song_id;
    }

    void setSong_id(int song_id) {
        this.song_id = song_id;
    }

    int getArray_pos() {
        return array_pos;
    }

    void setArray_pos(int array_pos) {
        this.array_pos = array_pos;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getArtist() {
        return artist;
    }

    void setArtist(String artist) {
        this.artist = artist;
    }

    Integer getArtwork() {
        return artwork;
    }

    void setArtwork(Integer artwork) {
        this.artwork = artwork;
    }

    Integer getAudio() {
        return audio;
    }

    void setAudio(Integer audio) {
        this.audio = audio;
    }

    Integer getLyrics() {
        return lyrics;
    }

    void setLyrics(Integer lyrics) {
        this.lyrics = lyrics;
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
        dest.writeInt(artwork);
        dest.writeInt(audio);
        dest.writeInt(lyrics);
        dest.writeStringArray(comments);
    }

    private Song(Parcel parcel) {
        song_id = parcel.readInt();
        array_pos = parcel.readInt();
        title = parcel.readString();
        artist = parcel.readString();
        artwork = parcel.readInt();
        audio = parcel.readInt();
        lyrics = parcel.readInt();
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
