package com.out.activitymusic;

import java.util.ArrayList;

public class Music {
    MusicClass musicClass;
    public ArrayList<MusicClass> arrayList = new ArrayList<>();
    public static ArrayList<MusicClass> setListMusic(){
        ArrayList<MusicClass> arrayList = new ArrayList<>();
        arrayList.add(new MusicClass("track1","3:50"));
        arrayList.add(new MusicClass("track2","4:35"));
        arrayList.add(new MusicClass("track3","1:12"));
        arrayList.add(new MusicClass("track3","9:45"));
        arrayList.add(new MusicClass("track3","10:23"));
        return arrayList;
    }

}
