package com.out.activitymusic;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class AllSongsFragment extends Fragment {
    private ListAdapter mListAdapter1;

    private RecyclerView mRecyclerView1;

    private ArrayList<MusicClass> arrayList;
    View mInflater;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflater=inflater.inflate(R.layout.allsongsfragment,container,false);
        mRecyclerView1=mInflater.findViewById(R.id.recycle_view);
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));

        arrayList = Music.setListMusic();

        mListAdapter1 = new ListAdapter(getContext(),arrayList);

        mRecyclerView1.setAdapter(mListAdapter1);


        return mInflater;
    }

    }







