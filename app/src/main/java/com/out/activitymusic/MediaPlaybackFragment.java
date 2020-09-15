package com.out.activitymusic;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MediaPlaybackFragment extends Fragment {
    TextView tv,time;
    ImageView img,imgex;
    RelativeLayout imgBig;

    public MediaPlaybackFragment newInstance(Song song) {
        MediaPlaybackFragment fragment = new MediaPlaybackFragment();
        Bundle bundle = new Bundle();
        bundle.putString("song", song.getTitle());
        bundle.putString("song1", getDurationTime(song.getDuration()));
        bundle.putString("song2", String.valueOf(queryAlbumUri(song.getAlbum())));
       // bundle.putString("song3", String.valueOf(queryAlbumUri(song.getAlbum())));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mediaplaybackfragment, container, false);
        tv = view.findViewById(R.id.song1);
        time= view.findViewById(R.id.Time2);
        img= view.findViewById(R.id.picture_small);
        imgBig=view.findViewById(R.id.picture_big);
        if (getArguments()!=null){
            setText(getArguments());
        }
        return view;
    }

    public void setText(Bundle bundle) {
        tv.setText(bundle.getString("song"));
        time.setText(bundle.getString("song1"));
        img.setImageURI(Uri.parse(bundle.getString("song2")));
        //imgex.setImageURI(Uri.parse(bundle.getString("song3")));

    }
    private String getDurationTime(String str) {
        int mili = Integer.parseInt(str) / 1000;
        int phut = mili / 60;
        int giay = mili % 60;
        String duration = String.valueOf(phut) + ":" + String.valueOf(giay);
        return duration;
    }
    public Uri queryAlbumUri(String imgUri) {

        final Uri artworkUri = Uri.parse("content://media/external/audio/albumart");
        return ContentUris.withAppendedId(artworkUri, Long.parseLong(imgUri));//noi them mSrcImageSong vao artworkUri
    }
}

