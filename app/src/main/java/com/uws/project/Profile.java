package com.uws.project;

import android.os.Parcel;
import android.os.Parcelable;

public class Profile implements Parcelable {

    private int user_id, picture;
    private String username, password, biography;
    private String[] comments;
    private int[] songs;

    Profile(int user_id, int picture, String username, String password, String biography, String[] comments, int[] songs) {
        this.user_id = user_id;
        this.picture = picture;
        this.username = username;
        this.password = password;
        this.biography = biography;
        this.comments = comments;
        this.songs = songs;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getPicture() {
        return picture;
    }

    String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String changePassword() {
        String newPassword;
        newPassword = "placeholder";
        password = newPassword;
        return password;
    }

    String getBiography() {
        return biography;
    }

    void setBiography(String biography) {
        this.biography = biography;
    }

    String[] getComments() {
        return comments;
    }

    void setComments(String[] comments) {
        this.comments = comments;
    }

    int[] getSongs() {
        return songs;
    }

    void setSongs(int[] songs) {
        this.songs = songs;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(user_id);
        dest.writeInt(picture);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(biography);
        dest.writeStringArray(comments);
        dest.writeIntArray(songs);
    }

    private Profile(Parcel parcel) {
        user_id = parcel.readInt();
        picture = parcel.readInt();
        username = parcel.readString();
        password = parcel.readString();
        biography = parcel.readString();
        comments = parcel.createStringArray();
        songs = parcel.createIntArray();
    }

    public static final Parcelable.Creator<Profile> CREATOR = new Parcelable.Creator<Profile>() {

        @Override
        public Profile createFromParcel(Parcel parcel) {
            return new Profile(parcel);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

}
