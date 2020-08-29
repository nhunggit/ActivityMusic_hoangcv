package com.out.activitymusic;

public class MusicClass {
    private String title;
    private String timeSong;

    public MusicClass(String title, String timeSong) {
        this.title = title;
        this.timeSong = timeSong;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimeSong() {
        return timeSong;
    }

    public void setTimeSong(String timeSong) {
        this.timeSong = timeSong;
    }
}
