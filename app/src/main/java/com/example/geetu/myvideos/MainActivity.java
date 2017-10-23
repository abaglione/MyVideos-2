package com.example.geetu.myvideos;

import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
import android.net.Uri;

public class MainActivity extends AppCompatActivity {
    private final int COUNT=3;
    private int index =1;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);



        videoView = (VideoView) findViewById(R.id.videoView);
        Button button = (Button) findViewById(R.id.button);
        videoView.requestFocus();
        videoView.setVideoURI(getPath(index));
        index++;
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
               //code to repeat the video
                 mediaPlayer.setLooping(true);

            }
        });
        //code to play next video after completion of the video
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                //videos count +1 since we started with 1
                if (index == COUNT + 1) index = 1;
                videoView.setVideoURI(getPath(index));
                index++;
            }
        });


        videoView.setMediaController(new MediaController(this){public void hide() {}});

        videoView.start();

    }
    private Uri getPath(int id) {
        return Uri.parse("android.resource://" + getPackageName() + "/raw/video" + id);
    }
}
