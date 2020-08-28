package com.out.activitymusic;

import java.util.LinkedList;

public class ListView {
    private LinkedList<String> mListView;
    public LinkedList<String> createListView() {
        mListView = new LinkedList<>();
        for (int i = 0; i < 20; i++)
            mListView.addLast("Music" + i);
        return mListView;
    }



}
