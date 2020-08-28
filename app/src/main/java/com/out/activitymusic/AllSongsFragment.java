package com.out.activitymusic;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class AllSongsFragment extends Fragment {
    private ListAdapter mListAdapter;
    private RecyclerView mRecyclerView;
    private ListView mListView;
    View mInflater;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflater=inflater.inflate(R.layout.allsongsfragment,container,false);
        mRecyclerView=mInflater.findViewById(R.id.recycle_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mListView= new ListView();
        mListAdapter = new ListAdapter(getContext(),mListView.createListView());
        mRecyclerView.setAdapter(mListAdapter);

        return mInflater;
    }
}






