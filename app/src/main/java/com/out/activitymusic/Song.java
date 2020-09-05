package com.out.activitymusic;

public class Song {

    private int ID;
    private static String Title;
    private String File;
    private  String Artist;
    private int Duration;



    public int getID() {
        return ID;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }

    public void setId(int ID) {
        this.ID = ID;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int duration) {
        Duration = duration;
    }

    public String getFile() {
        return File;
    }

    public void setFile(String file) {
        File = file;
    }

    public String getTitle() {
        return Title;
    }

    public static void setTitle(String title) {
        Title = title;
    }

    public Song(int ID, String Title , String File , String Artist, int Duration){
        this.ID= ID;
        this.Title= Title;
        this.File= File;
        this.Artist= Artist;
        this.Duration= Duration;

        }
}



