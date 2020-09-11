package com.out.activitymusic;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.ComponentName;
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
import android.widget.Toast;

import Service.ServiceMediaPlay;

public class MainActivity extends AppCompatActivity{
    AllSongsFragment allSongsFragment;
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
        ServiceConnection serviceConection= new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
        Intent intent= new Intent(this, ServiceMediaPlay.class);
        bindService(intent,serviceConection,BIND_AUTO_CREATE);
        if(savedInstanceState!=null) {
            allSongsFragment.setService((ServiceMediaPlay) serviceConection);
        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu_bar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.three_dots : Toast.makeText(this, "Item1 selected", Toast.LENGTH_SHORT).show();
            case R.id.three_dots1 : Toast.makeText(this, "Item2 selected", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
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