package com.out.activitymusic;


public class Song {

    private int ID;
    private String Title;
    private Long File;
    private String Artist;
    private String Duration;
    private String Album;



    public Song(int ID, String title, Long file, String album, String artist, String duration) {
        this.ID = ID;
        Title = title;
        File = file;
        Artist = artist;
        Duration = duration;
        Album=album;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public long getFile() {
        return File;
    }

    public void setFile(Long file) {
        File = file;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        Album = album;
    }
}



