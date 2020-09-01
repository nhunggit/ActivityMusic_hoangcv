package com.out.activitymusic;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MediaPlaybackFragment extends Fragment {
    View mInflater;

    public static MediaPlaybackFragment newInstance(String content) {
        return MediaPlaybackFragment.newInstance(content);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflater = inflater.inflate(R.layout.mediaplaybackfragment, container, false);
        return mInflater;
    }
}
