package com.out.activitymusic;

import android.app.Service;
import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;


import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import javax.xml.datatype.Duration;

import Service.ServiceMediaPlay;
/*class RecyclerViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
    public TextView music;
    private ItemClickListener itemClickListener;
    LinearLayout clickItem;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        clickItem = (LinearLayout) itemView.findViewById(R.id.clickItem);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }


    public void setOnClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);

    }

    @Override
    public boolean onLongClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), true);
        return true;
    }*/

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private ArrayList<Song> mListSong;
    // private Context context;
    private LayoutInflater mInflater;
    private View playMediaSong;
    LinearLayout mLinearLayout;
    AllSongsFragment allSongsFragment;
    private Object Context;
    private ItemClickListener listener;
    private ListAdapter mListAdapter;
    private RecyclerView mRecyclerView;
    private ServiceMediaPlay serviceMediaPlay;
    ArrayList<Song> songs;

    public ListAdapter(Context context, ArrayList<Song> ListView,ItemClickListener itemClickListener) {
        mInflater = LayoutInflater.from(context);
        this.listener = itemClickListener;
        this.mListSong = ListView;
    }
    public void setService(ServiceMediaPlay service){
        this.serviceMediaPlay=service;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.list_view, parent, false);
        playMediaSong = mInflater.inflate(R.layout.allsongsfragment, parent, false);
        mLinearLayout = playMediaSong.findViewById(R.id.playMedia);
        allSongsFragment = new AllSongsFragment();

        return new ViewHolder(mItemView, this);

    }



    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {


        final Song mCurrent = mListSong.get(position);


//        String mCurrent1=mListSTT.get(position);
        holder.mId.setText((position + 1) + "");
        holder.mTitle.setText(mCurrent.getTitle());
        holder.mDuration.setText(getDurationTime(mCurrent.getDuration()));
        Log.d("HoangCV", "onBindViewHolder: " + mCurrent.getTitle());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(mCurrent);
                try {
                    serviceMediaPlay.pause(mCurrent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private String getDurationTime(String str) {
        int mili = Integer.parseInt(str) / 1000;
        int phut = mili / 60;
        int giay = mili % 60;
        String duration = String.valueOf(phut) + ":" + String.valueOf(giay);
        return duration;
    }

    @Override
    public int getItemCount() {
        return mListSong.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mId;
        public TextView mTitle;
        public TextView mDuration;
        final ListAdapter mAdapter;


        public ViewHolder(@NonNull View itemView, ListAdapter adapter) {
            super(itemView);
            this.mAdapter = adapter;
            mId = itemView.findViewById(R.id.STT);
            mTitle = itemView.findViewById(R.id.music);
            mDuration = itemView.findViewById(R.id.tvTime);


        }


    }

}


