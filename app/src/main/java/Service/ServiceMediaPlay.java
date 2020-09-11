package Service;

import android.app.Service;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.out.activitymusic.Song;

import java.io.IOException;

public class ServiceMediaPlay extends Service {

//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        Toast.makeText(this,"onCreate",Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        //mediaPlayer=MediaPlayer.create(this,song.getFile());
//        Toast.makeText(this,"onStartCommand",Toast.LENGTH_SHORT).show();
//        return super.onStartCommand(intent, flags, startId);
//    }
//
//    @Override
//    public void onDestroy() {
//        Toast.makeText(this,"onDestroy",Toast.LENGTH_SHORT).show();
//        super.onDestroy();
//    }
//}
//class BoundMediaPlay extends Service{
    MediaPlayer mediaPlayer;
    private Song song;

    @Override
    public void onCreate() {
        Toast.makeText(this,"onCreate",Toast.LENGTH_SHORT).show();

        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this,"onUnbind",Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"onDestroy",Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
    public void setSong(Song songs){
        this.song=songs;
    }
    public void pause(Song song) throws IOException {
        Uri contentUri = ContentUris.withAppendedId(
                android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, song.getFile());
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(getApplicationContext(),contentUri );
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.prepare();
        mediaPlayer.start();
    }
}
