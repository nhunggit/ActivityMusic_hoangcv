package com.out.activitymusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //final ListView list = findViewById(R.id.list_view);

     /*   AllSongsFragment allSongsFragment=new AllSongsFragment();
        fragmentTransaction.replace(R.id.frame_Layout1,allSongsFragment);
        fragmentTransaction.commit();
        FragmentManager fragmentManager1=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction1=fragmentManager1.beginTransaction();
        MediaPlaybackFragment mediaPlaybackFragment= new MediaPlaybackFragment();
        fragmentTransaction1.replace(R.id.frame_Layout2,mediaPlaybackFragment);
        fragmentTransaction1.commit();*/
/*
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);*/
/*
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        AllSongsFragment allSongsFragment = new AllSongsFragment();
        if (findViewById(R.id.frame_Layout) != null) {
            // Found the ID of only one Fragment ==> Portrait mode
            // Remove the existing fragment before add new one
            if (savedInstanceState != null) {
                getSupportFragmentManager().executePendingTransactions();
                Fragment fragmentById = getSupportFragmentManager().findFragmentById(R.id.frame_Layout);
                if (fragmentById != null) {
                    getSupportFragmentManager().beginTransaction().remove(fragmentById).commit();
                }
            }

            // Add new one
            getSupportFragmentManager().beginTransaction().add(R.id.frame_Layout, allSongsFragment).commit();
        } else {
            // Landscape mode
            // Remove the existing fragments before add new one
            if (savedInstanceState != null) {
                getSupportFragmentManager().executePendingTransactions();
                Fragment firstFragmentById = getSupportFragmentManager().findFragmentById(R.id.frame_Layout1);
                if (firstFragmentById != null) {
                    getSupportFragmentManager().beginTransaction().remove(firstFragmentById).commit();
                }
                Fragment secondFragmentById = getSupportFragmentManager().findFragmentById(R.id.frame_Layout2);
                if (secondFragmentById != null) {
                    getSupportFragmentManager().beginTransaction().remove(secondFragmentById).commit();
                }
            }

            // Add new one
            getSupportFragmentManager().beginTransaction().add(R.id.frame_Layout1, allSongsFragment).commit();
        }



    }
    @Override
    public void onItemPressed(String content) {
        @Override
        MediaPlaybackFragment mediaPlaybackFragment = MediaPlaybackFragment.newInstance(content);
        public boolean onCreateOptionsMenu(Menu menu)
        {
            if (findViewById(R.id.frame_Layout2) != null) {
                getMenuInflater().inflate(R.menu.menu_seach, menu);
                // Found the ID of only one Fragment ==> Portrait mode    return true;
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_Layout, mediaPlaybackFragment);
            }
        }  }*/
}