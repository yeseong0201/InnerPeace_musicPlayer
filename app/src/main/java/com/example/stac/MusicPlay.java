package com.example.stac;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;



import de.hdodenhof.circleimageview.CircleImageView;

public class MusicPlay extends AppCompatActivity {

    LinearLayout linearLayout;

    private double timeElapsed = 0, finalTime = 0;
    private int forwardTime = 2000, backwardTime = 2000;
    private Handler durationHandler = new Handler();

    ImageButton playBtn, pauseBtn, preBtn, nextBtn;
    TextView textTitle, textSinger;
    SeekBar seekBar;

    CircleImageView circleImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play);

        preBtn = findViewById(R.id.prePlay);
        nextBtn = findViewById(R.id.nextPlay);

        linearLayout = findViewById(R.id.container);

        circleImage = findViewById(R.id.circle_image);

        Intent intent = getIntent();

        playBtn = findViewById(R.id.media_play);
        pauseBtn = findViewById(R.id.media_pause);

        playBtn.setVisibility(View.INVISIBLE);
        pauseBtn.setVisibility(View.INVISIBLE);

        textSinger = findViewById(R.id.textsinger);
        textTitle = findViewById(R.id.songName);

        nextBtn.setVisibility(View.INVISIBLE);
        preBtn.setVisibility(View.INVISIBLE);



//        seekBar.setProgress((int) timeElapsed);
//        seekBar.setMax((int) finalTime);
//        seekBar.setClickable(false);

        circleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        playBtn.setVisibility(View.INVISIBLE);

                        //    pauseBtn.setVisibility(View.VISIBLE);
                    }
                }, 2000);
                playBtn.setVisibility(View.VISIBLE);
                //  pauseBtn.setVisibility(View.INVISIBLE);
            }
        });

        textTitle.setText(intent.getStringExtra("Music_Title"));
        textSinger.setText(intent.getStringExtra("Music_Singer"));


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        nextBtn.setVisibility(View.INVISIBLE);
                        preBtn.setVisibility(View.INVISIBLE);

                    }

                }, 2000);  // 2000은 2초를 의미합니다.
                nextBtn.setVisibility(View.VISIBLE);
                preBtn.setVisibility(View.VISIBLE);

            }
        });


    }

//    public void showChange() {
//
//        playBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pauseBtn.setVisibility(View.VISIBLE);
//
//                Intent intent = new Intent(MusicPlay.this, ServiceMusic.class);
//                startService(intent);
//
//            }
//        });
//        pauseBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                playBtn.setVisibility(View.VISIBLE);
//
//                Intent intent = new Intent(MusicPlay.this, ServiceMusic.class);
//                stopService(intent);
//            }
//        });
//    }

}
