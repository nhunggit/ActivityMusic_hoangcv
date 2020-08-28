package com.out.activitymusic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{
    private LinkedList<String> mListView;
    private LayoutInflater mInflater;

    public ListAdapter(Context context, LinkedList<String> ListView) {
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
        String mCurrent =mListView.get(position);
        holder.mListItemView.setText(mCurrent);

    }

    @Override
    public int getItemCount() {
        return mListView.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public final TextView mListItemView;
        final ListAdapter mAdapter;


        public ViewHolder(@NonNull View itemView, ListAdapter adapter) {
            super(itemView);
            this.mAdapter = adapter;
            mListItemView = itemView.findViewById(R.id.list_view);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            int mPosition = getLayoutPosition();
// Use that to access the affected item in mWordList.
            String element = mListView.get(mPosition);
// Change the word in the mWordList.
            mListView.set(mPosition, "Clicked! " + element);
// Notify the adapter, that the data has changed so it can
// update the RecyclerView to display the data.
            mAdapter.notifyDataSetChanged();
        }


    }}



