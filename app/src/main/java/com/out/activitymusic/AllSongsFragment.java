package com.out.activitymusic;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;


public class AllSongsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String SHARED_PREFERENCES_NAME = "1";
    private ListAdapter mListAdapter;

    private RecyclerView mRecyclerView;

    private ArrayList<Song> arrayList;
    private LinearLayout mLinearLayout;
    private SharedPreferences mSharePreferences;
    ArrayList<Song> songs;

    /*   public boolean isIsplaying() {
           return isplaying;
       }

       public void setIsplaying(boolean isplaying) {
           this.isplaying = isplaying;
       }

       private boolean isplaying;
       View mInflater;

       @Override
       public void onAttach(@NonNull Context context) {
           super.onAttach(context);
       }
   */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mInflater = inflater.inflate(R.layout.allsongsfragment, container, false);
        mRecyclerView = mInflater.findViewById(R.id.recycle_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mLinearLayout = mInflater.findViewById(R.id.playMedia);
        LoaderManager.getInstance(this).initLoader(1, null, this);
        return mInflater;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        Log.d("nhungltk", "onCreateLoader: ");
        String[] projection = {MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION};
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        CursorLoader cursorLoader = new CursorLoader(getContext(), MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, selection, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        Log.d("nhungltk", "onLoadFinished: ");
        mSharePreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        songs = new ArrayList<>();
        boolean isCreate = mSharePreferences.getBoolean("create_db", false);
        int id = 0;
        String title = "";
        String file = "";
        String artist = "";
        int duration=0;
        Song song = new Song(id,title,file,artist,duration);
        if (data != null && data.getCount() > 0) {
            data.moveToFirst();
            while (data.moveToNext()) {
                id++;
                song.setId(id);
               // Song.setTitle(data.getString(data.getColumnIndex(MediaStore.Audio.Media.TITLE)));
                Song.setTitle(data.getString(data.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)));
                song.setFile(data.getString(data.getColumnIndex(MediaStore.Audio.Media.DATA)));
                song.setArtist(data.getString(data.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
                song.setDuration(data.getInt(data.getColumnIndex(MediaStore.Audio.Media.DURATION)));
                title = song.getTitle();
                file = song.getFile();
                artist = song.getArtist();
                duration = song.getDuration();
                songs.add(new Song(id,title,file,artist,duration));
                Log.d("nhungltk", "onLoadFinished: "+title);
                Log.d("nhungltk", "onLoadFinished: "+duration);
               /* if (isCreate == false) {
                    ContentValues values = new ContentValues();
                    values.put(FavoriteSongsProvider.ID_PROVIDER, id);
                    values.put(FavoriteSongsProvider.FAVORITE, 0);
                    values.put(FavoriteSongsProvider.COUNT, 0);
                    Uri uri = getActivity().getContentResolver().insert(Uri.parse(FavoriteSongsProvider.CONTENT_URI), values);
                    mSharePreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = mSharePreferences.edit();
                    editor.putBoolean("create_db", true);
                    editor.commit();
                }*/
            }
        }
        Log.d("nhungltk", "onLoadFinished: "+songs.size());
        mListAdapter = new ListAdapter(getContext(), songs);
        mRecyclerView.setAdapter(mListAdapter);








    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}







