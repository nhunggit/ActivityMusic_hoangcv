package com.out.activitymusic;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import Service.ServiceMediaPlay;

public class MainActivity extends AppCompatActivity implements ItemClickListener{
    AllSongsFragment allSongsFragment;
    private ServiceMediaPlay player;
    boolean serviceBound = false;
    private Song song;
    private ListAdapter adapter;

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
        allSongsFragment=new AllSongsFragment();
        //final ListView list = findViewById(R.id.list_view);
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            AllSongsFragment allSongsFragment = new AllSongsFragment();
            FragmentManager manager = this.getSupportFragmentManager();

            manager.beginTransaction()
                    .replace(R.id.fragmentSongOne, allSongsFragment)
                    .commit();
        } else {
            AllSongsFragment allSongsFragment = new AllSongsFragment();
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

//play the first audio in the ArrayList



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
    public void onClick(Song song) {

    }

    //   @Override
/*    public void onClick(View view) {
        Intent intent= new Intent(this, ServiceMediaPlay.class);
        Log.d("hoangCv","khoitao");
        switch (view.getId()){
            case R.id.playMedia:
                startService(intent);
                break;
            case R.id.play_pause:
                onDestroy();
        }
    }*/




  /*  @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }*/

}