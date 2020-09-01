package com.out.activitymusic;
import android.content.Context;
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
    private ListAdapter mListAdapter;

    private RecyclerView mRecyclerView;

    private ArrayList<MusicClass> arrayList;
    View mInflater;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflater=inflater.inflate(R.layout.allsongsfragment,container,false);
        mRecyclerView=mInflater.findViewById(R.id.recycle_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        arrayList = Music.setListMusic();

        mListAdapter = new ListAdapter(getContext(),arrayList);

        mRecyclerView.setAdapter(mListAdapter);


        return mInflater;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public interface itemSelected {
    }
}







