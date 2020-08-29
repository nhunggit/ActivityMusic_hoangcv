package com.out.activitymusic;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{
    private ArrayList<MusicClass> mListView;
    private LayoutInflater mInflater;

    public ListAdapter(Context context, ArrayList<MusicClass> ListView) {
        mInflater=LayoutInflater.from(context);
        this.mListView = ListView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.list_view, parent, false);
        return new ViewHolder(mItemView,this);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MusicClass mCurrent =mListView.get(position);
//        String mCurrent1=mListSTT.get(position);
        holder.mStt.setText((position+1)+"");
        holder.mTitle.setText(mCurrent.getTitle());
        Log.i("main",mCurrent.getTimeSong());
        holder.mTime.setText(mCurrent.getTimeSong());
//        holder.mListItemView2.setText(mCurrent1);

    }

    @Override
    public int getItemCount() {
        return mListView.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mStt;
        public TextView mTitle;
        public TextView mTime;
        final ListAdapter mAdapter;


        public ViewHolder(@NonNull View itemView, ListAdapter adapter) {
            super(itemView);
            this.mAdapter = adapter;
            mStt = itemView.findViewById(R.id.STT);
            mTitle = itemView.findViewById(R.id.music);
            mTime = itemView.findViewById(R.id.tvTime);

//            mListItemView2 = itemView.findViewById(R.id.STT);


        }




    }}



