package com.example.musicplayer;

import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class AndroidMediaPlayerExample extends Activity {

    private MediaPlayer mediaPlayer;
    public TextView songName, duration;
    private double timeElapsed = 0, finalTime = 0;
    private int forwardTime = 2000, backwardTime = 2000;
    private Handler durationHandler = new Handler();
    private SeekBar seekbar;

    private ImageButton play_btn, pause_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set the layout of the Activity
        setContentView(R.layout.activity_main);

        songName = findViewById(R.id.songName);
        mediaPlayer = MediaPlayer.create(this, R.raw.ncs_music1);
        finalTime = mediaPlayer.getDuration();
        duration = findViewById(R.id.songDuration);
        seekbar = findViewById(R.id.seekBar);
        songName.setText("Sample_Song.mp3");

        seekbar.setMax((int) finalTime);
        seekbar.setClickable(false);

        play_btn = findViewById(R.id.media_play);
        pause_btn = findViewById(R.id.media_pause);

        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                timeElapsed = mediaPlayer.getCurrentPosition();
                seekbar.setProgress((int) timeElapsed);
                durationHandler.postDelayed(updateSeekBarTime, 100);
            }
        });

        pause_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });

        //initialize views
        //initializeViews();
    }

//    public void initializeViews() {
//        songName = (TextView) findViewById(R.id.songName);
//        mediaPlayer = MediaPlayer.create(this, R.raw.ncs_music1);
//        finalTime = mediaPlayer.getDuration();
//        duration = (TextView) findViewById(R.id.songDuration);
//        seekbar = findViewById(R.id.seekBar);
//        songName.setText("Sample_Song.mp3");
//
//        seekbar.setMax((int) finalTime);
//        seekbar.setClickable(false);
//    }

    // play mp3 song
//    public void play(View view) {
//        mediaPlayer.start();
//        timeElapsed = mediaPlayer.getCurrentPosition();
//        seekbar.setProgress((int) timeElapsed);
//        durationHandler.postDelayed(updateSeekBarTime, 100);
//    }

    //handler to change seekBarTime
    private Runnable updateSeekBarTime = new Runnable() {
        public void run() {
            //get current position
            timeElapsed = mediaPlayer.getCurrentPosition();
            //set seekbar progress
            seekbar.setProgress((int) timeElapsed);
            //set time remaing
            double timeRemaining = finalTime - timeElapsed;
            duration.setText(String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining), TimeUnit.MILLISECONDS.toSeconds((long) timeRemaining) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining))));

            //repeat yourself that again in 100 miliseconds
            durationHandler.postDelayed(this, 100);
        }
    };

    // pause mp3 song
//    public void pause(View view) {
//        mediaPlayer.pause();
//    }

    // go forward at forwardTime seconds
    public void forward(View view) {
        //check if we can go forward at forwardTime seconds before song endes
//        if ((timeElapsed + forwardTime) 0){
//            timeElapsed = timeElapsed - backwardTime;
//
//            //seek to the exact second of the track
//            mediaPlayer.seekTo((int) timeElapsed);
//        }

        if ((timeElapsed + forwardTime) == 0) {
            timeElapsed = timeElapsed - backwardTime;

            //seek to the exact second of the track
            mediaPlayer.seekTo((int) timeElapsed);
        }
    }

}