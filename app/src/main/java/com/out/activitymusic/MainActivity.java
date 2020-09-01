package com.out.activitymusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements AllSongsFragment.itemSelected {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    }
}