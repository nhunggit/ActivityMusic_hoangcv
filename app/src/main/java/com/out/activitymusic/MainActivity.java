package com.out.activitymusic;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import Service.ServiceMediaPlay;

public class MainActivity extends AppCompatActivity  implements DisplayMediaFragment/*implements ItemClickListener */{
    AllSongsFragment allSongsFragment;
    private ServiceMediaPlay player;
    boolean serviceBound = false;
    private Song song;
    private TextView mTitle,mTime2;
    private ImageView mPictureSmall;
    private ListAdapter adapter;
    private ImageView image;
    private DisplayMediaFragment displayMediaFragment;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            ServiceMediaPlay.LocalBinder binder = (ServiceMediaPlay.LocalBinder) service;
            player = binder.getService();
            serviceBound = true;

            Toast.makeText(MainActivity.this, "Service Bound", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            serviceBound = false;
        }
    };



    private void playAudio(String media) {
        //Check is service is active
        if (!serviceBound) {
            Intent playerIntent = new Intent(this, ServiceMediaPlay.class);
            playerIntent.putExtra("media", media);
            startService(playerIntent);
            bindService(playerIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        } else {
            //Service is active
            //Send media with BroadcastReceiver
        }
    }
  // Gọi playAudio()hàm từ phương thức Activitys onCreate()và tham chiếu tệp âm thanh.
 //   playAudio("https://upload.wikimedia.org/wikipedia/commons/6/6c/Grieg_Lyric_Pieces_Kobold.ogg");
  @Override
  public void onSaveInstanceState(Bundle savedInstanceState) {
      savedInstanceState.putBoolean("ServiceState", serviceBound);
      super.onSaveInstanceState(savedInstanceState);
  }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        serviceBound = savedInstanceState.getBoolean("ServiceState");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (serviceBound) {
            unbindService(serviceConnection);
            //service is active
            player.stopSelf();
        }
    }

    public void setSong(Song songs){
        this.song=songs;
    }

    public void FileSong(Song song){
         song.getFile();
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        allSongsFragment=new AllSongsFragment(this);
        mTitle= findViewById(R.id.song1);
        mTime2 = findViewById(R.id.Time2);
        mPictureSmall= findViewById(R.id.picture_small);

        //final ListView list = findViewById(R.id.list_view);
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            AllSongsFragment allSongsFragment = new AllSongsFragment(this);
            FragmentManager manager = this.getSupportFragmentManager();

            manager.beginTransaction()
                    .replace(R.id.fragmentSongOne, allSongsFragment)
                    .commit();
        } else {
            AllSongsFragment allSongsFragment = new AllSongsFragment(this);
            FragmentManager manager = this.getSupportFragmentManager();

            manager.beginTransaction()
                    .replace(R.id.fragmentSongOne, allSongsFragment)
                    .commit();

            MediaPlaybackFragment mediaPlaybackFragment = new MediaPlaybackFragment();
            FragmentManager manager1 = this.getSupportFragmentManager();

            manager1.beginTransaction()
                    .replace(R.id.fragmentMediaTwo, mediaPlaybackFragment)

                    .commit();
        }
       /* Log.d("HoangCV", "onCreateView: "+song.getTitle());*/

    /*    Intent intent= new Intent(this, ServiceMediaPlay.class);
        bindService(intent,serviceConection,BIND_AUTO_CREATE);
        if(savedInstanceState!=null) {
            allSongsFragment.setService((ServiceMediaPlay) serviceConection);*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu_bar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onclick() {
        MediaPlaybackFragment mediaPlaybackFragment = new MediaPlaybackFragment();
        FragmentManager manager1 = this.getSupportFragmentManager();

        manager1.beginTransaction()
                .replace(R.id.fragmentSongOne, mediaPlaybackFragment)

                .commit();
 /*   mTitle.setText(song.getTitle());
    mTime2.setText(song.getDuration());
    mPictureSmall.setImageURI(queryAlbumUri(song.getAlbum()));
     Log.d("HoangCV", "onclick: ogd"+ song.getTitle());*/
    }
    public Uri queryAlbumUri(String imgUri) {

        final Uri artworkUri = Uri.parse("content://media/external/audio/albumart");
        return ContentUris.withAppendedId(artworkUri, Long.parseLong(imgUri));//noi them mSrcImageSong vao artworkUri
    }

 /*   @Override
    public void onClick(Song song) {

    }

*/


  /*  @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }*/

}