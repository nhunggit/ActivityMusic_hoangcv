package com.out.activitymusic;

import java.util.ArrayList;

public class Music {
    Song song;
    public ArrayList<Song> arrayList = new ArrayList<>();
    public static ArrayList<Song> setListMusic(){
        ArrayList<Song> arrayList = new ArrayList<>();
        arrayList.add(new Song(1,"music1","src1","picture1",1));
        arrayList.add(new Song(2,"music1","src1","picture1",2));
        arrayList.add(new Song(3,"music1","src1","picture1",3));
        arrayList.add(new Song(1,"music1","src1","picture1",3));
        arrayList.add(new Song(1,"music1","src1","picture1",2));
        arrayList.add(new Song(1,"music1","src1","picture1",3));

        return arrayList;
    }

}
