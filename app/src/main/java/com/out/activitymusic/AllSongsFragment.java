package com.out.activitymusic;

import android.content.ContentUris;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import Service.ServiceMediaPlay;

import static android.content.Context.MODE_PRIVATE;


public class AllSongsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, ItemClickListener {
    private static final String SHARED_PREFERENCES_NAME = "1";
    private ListAdapter mListAdapter;

    private RecyclerView mRecyclerView;

    private ArrayList<Song> arrayList;
    private LinearLayout mLinearLayout;
    TextView title;
    TextView artist;
    ImageView img;
    private SharedPreferences mSharePreferences;
    ArrayList<Song> songs;
    ServiceMediaPlay serviceMediaPlay;
    public void setService(ServiceMediaPlay service){
            this.serviceMediaPlay=service;
    }
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
        Log.d("nhungltk", "onCreateView: ");
        View mInflater = inflater.inflate(R.layout.allsongsfragment, container, false);
        mRecyclerView = mInflater.findViewById(R.id.recycle_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mLinearLayout = mInflater.findViewById(R.id.playMedia);
        LoaderManager.getInstance(this).initLoader(1, null, this);
        title = mInflater.findViewById(R.id.title);
        artist = mInflater.findViewById(R.id.artist);
        img= mInflater.findViewById(R.id.picture);


        return mInflater;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("nhungltk", "onActivityCreated: ");
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
                MediaStore.Audio.Media.ALBUM_ID,
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
        Long file = null;
        String album="";
        String artist = "";
        String duration = "";
        Song song = new Song(id, title, file, album,artist, duration);
        if (data != null && data.getCount() > 0) {
            data.moveToFirst();
            while (data.moveToNext()) {
                id++;
                song.setID(id);
                song.setTitle(data.getString(data.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)));
                song.setFile(data.getLong(data.getColumnIndex(MediaStore.Audio.Media.DATA)));
                song.setAlbum(data.getString(data.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)));
                song.setArtist(data.getString(data.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
                song.setDuration(data.getString(data.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));
                title = song.getTitle();
                file = song.getFile();
                album= song.getAlbum();
                artist = song.getArtist();
                duration = song.getDuration();
                songs.add(new Song(id, title, file,album, artist, duration));
                Log.d("nhungltk", "onLoadFinished: " + title);
                Log.d("nhungltk", "onLoadFinished: " + duration);
                Log.d("Hoang1","onLoad"+file);

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
        mListAdapter = new ListAdapter(getContext(), songs, this);
        mRecyclerView.setAdapter(mListAdapter);
        Log.d("nhungltk", "onLoadFinished: " + songs.size());
        mListAdapter.setService(serviceMediaPlay);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

    @Override
    public void onClick(Song song) {
        if (mLinearLayout.getVisibility() == View.GONE) {
            mLinearLayout.setVisibility(View.VISIBLE);
        }
        title.setText(song.getTitle());
        artist.setText(song.getArtist());
        img.setImageURI(queryAlbumUri(song.getAlbum()));


   }
    public Uri queryAlbumUri(String imgUri) {

        final Uri artworkUri = Uri.parse("content://media/external/audio/albumart");
        return ContentUris.withAppendedId(artworkUri, Long.parseLong(imgUri));//noi them mSrcImageSong vao artworkUri
    }



    
}







