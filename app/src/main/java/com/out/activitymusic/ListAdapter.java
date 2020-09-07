package com.out.activitymusic;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
class RecyclerViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
    public TextView music;
    private ItemClickListener itemClickListener;
    LinearLayout clickItem;
    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        clickItem = (LinearLayout) itemView.findViewById(R.id.clickItem);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }



    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);

    }

    @Override
    public boolean onLongClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),true);
        return true;
    }
}
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>  {
    private ArrayList<Song> mListSong;
   // private Context context;
    private LayoutInflater mInflater;
    private View playMediaSong;
    LinearLayout mLinearLayout;
    AllSongsFragment allSongsFragment;



    public ListAdapter(Context context, ArrayList<Song> ListView) {
        mInflater = LayoutInflater.from(context);
        this.mListSong = ListView;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.list_view, parent, false);
         playMediaSong=mInflater.inflate(R.layout.allsongsfragment,parent,false);
         mLinearLayout=playMediaSong.findViewById(R.id.playMedia);
         allSongsFragment=new AllSongsFragment();
        return new ViewHolder(mItemView, this);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Song mCurrent = mListSong.get(position);
//        String mCurrent1=mListSTT.get(position);
        holder.mId.setText((position + 1) + "");
        holder.mTitle.setText(mCurrent.getTitle());
        holder.mDuration.setText(mCurrent.getDuration()+"");

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                MediaPlaybackFragment mediaPlaybackFragment = new MediaPlaybackFragment();
                FragmentManager manager1 = this.getSupportFragmentManager();

                manager1.beginTransaction()
                        .replace(R.id.fragmentMediaTwo, mediaPlaybackFragment)

                        .commit();
            }
        });




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

//            mListItemView2 = itemView.findViewById(R.id.STT);


        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
        }
    }
}


