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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import Service.ServiceMediaPlay;

import static android.content.Context.MODE_PRIVATE;


public class AllSongsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, ItemClickListener,DataFragment/*, PopupMenu.OnMenuItemClickListener*/ {
    private static final String SHARED_PREFERENCES_NAME = "1";
    private ListAdapter mListAdapter;

    private RecyclerView mRecyclerView;
private Song song;
    private ArrayList<Song> arrayList;
    private RelativeLayout mLinearLayout,mBottom;
    TextView title,mTitle,mTime;
    TextView artist;
    ImageView img,mImageSmall;
    private SharedPreferences mSharePreferences;
    ArrayList<Song> songs;
    ServiceMediaPlay serviceMediaPlay;
    private ImageView image;

    private DisplayMediaFragment displayMediaFragment;


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
    public AllSongsFragment(DisplayMediaFragment displayMediaFragment){
        this.displayMediaFragment=displayMediaFragment;
    }
    public AllSongsFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("nhungltk", "onCreateView: ");
        View mInflater = inflater.inflate(R.layout.allsongsfragment, container, false);
        mRecyclerView = mInflater.findViewById(R.id.recycle_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mLinearLayout = mInflater.findViewById(R.id.bottom);

        LoaderManager.getInstance(this).initLoader(1, null, this);
        title = mInflater.findViewById(R.id.title);
        artist = mInflater.findViewById(R.id.artist);
        img= mInflater.findViewById(R.id.picture);
        mTitle= mInflater.findViewById(R.id.song1);
        mTime=mInflater.findViewById(R.id.Time2);
        mImageSmall=mInflater.findViewById(R.id.picture_small);
        //Bkav Nhungltk: doan nay nghia la sao
        ((MainActivity)getActivity()).setiConnectActivityAndBaseSong(new MainActivity.IConnectActivityAndBaseSong() {
                                                                         @Override
                                                                         public void connectActivityAndBaseSong() {
                                                                             if (((MainActivity) getActivity()).player != null) {
                                                                                 Log.d("nhungltk", "onCreateView: " + "not null");
                                                                                 setService((((MainActivity) getActivity()).player));
                                                                             }
                                                                         }
                                                                     });

        /*   image= (ImageView) mInflater.findViewById(R.id.menu_pop);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getContext(),v);
                popup.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) container);
                popup.inflate(R.menu.poupup_menu);
                popup.show();
            }
        });

*/      mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayMediaFragment.onclick(song);
            }
        });
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
        String file = "";
        String album="";
        String artist = "";
        String duration = "";
        Song song = new Song(id, title, file, album,artist, duration);
        if (data != null && data.getCount() > 0) {
            data.moveToFirst();
            do {
                id++;
                song.setID(id);
                song.setTitle(data.getString(data.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)));
                song.setFile(data.getString(data.getColumnIndex(MediaStore.Audio.Media.DATA)));
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
            }while (data.moveToNext());
        }
        mListAdapter = new ListAdapter(getContext(), songs, this);
        mRecyclerView.setAdapter(mListAdapter);
        Log.d("nhungltk", "onLoadFinished: " + songs.size());
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

    @Override
    public void onClick(Song song) {
        this.song = song;
        Log.d("HoangCV", "onClick: 123");
        if (mLinearLayout.getVisibility() == View.GONE) {
            mLinearLayout.setVisibility(View.VISIBLE);
        }
        if(serviceMediaPlay!=null){
            Log.d("nhungltk", "onClick: "+"playMusic");
            try {
                serviceMediaPlay.playMedia(song);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        title.setText(song.getTitle());
        artist.setText(song.getArtist());
        img.setImageURI(queryAlbumUri(song.getAlbum()));


   }
    public Uri queryAlbumUri(String imgUri) {

        final Uri artworkUri = Uri.parse("content://media/external/audio/albumart");
        return ContentUris.withAppendedId(artworkUri, Long.parseLong(imgUri));//noi them mSrcImageSong vao artworkUri
    }
   /* @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        Toast.makeText(getContext(), "Selected Item: " +menuItem.getTitle(), Toast.LENGTH_SHORT).show();
        switch (menuItem.getItemId()) {
            case R.id.search_item:
                // do your code
                return true;
            case R.id.upload_item:
                // do your code
                return true;
            case R.id.copy_item:
                // do your code
                return true;
            case R.id.print_item:
                // do your code
                return true;
            case R.id.share_item:
                // do your code
                return true;
            case R.id.bookmark_item:
                // do your code
                return true;
            default:
                return false;
        }
    }
*/


    @Override
    public void onclick(Song song) {
        mTitle.setText(song.getTitle());
        mTime.setText(song.getArtist());
        mImageSmall.setImageURI(queryAlbumUri(song.getAlbum()));
    }

    @Override
    public void onResume() {
        super.onResume();
     // mListAdapter.setService(serviceMediaPlay);
    }
}









