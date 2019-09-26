package com.example.stac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FeelRecommend extends AppCompatActivity {

    RecyclerView recyclerView_recommend;
    RecyclerView.LayoutManager layoutManager;

    ImageView imageView;

    TextView feeltext;

    FeelRecommend feelActivity;

    ArrayList<MusicList_recommend> MusicInfoLIst_r = new ArrayList<>();

    MusicAdapterRecommend musicAdapter_recommend;

    Intent in1, in2, in3, in4, in5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feel_recommend);

        imageView = findViewById(R.id.imageView);

        feeltext = findViewById(R.id.feel_text);

        recyclerView_recommend = findViewById(R.id.recyclerview_recommend);
        recyclerView_recommend.setHasFixedSize(true);

        musicAdapter_recommend = new MusicAdapterRecommend(MusicInfoLIst_r);

        layoutManager = new LinearLayoutManager(feelActivity);

        recyclerView_recommend.setLayoutManager(new LinearLayoutManager(this));


        musicAdapter_recommend.notifyDataSetChanged();

        recyclerView_recommend.setAdapter(musicAdapter_recommend);

        in1 = getIntent();
        in2 = getIntent();
        in3 = getIntent();
        in4 = getIntent();
        in5 = getIntent();


        int res1 = in1.getIntExtra("1", 0);
        int res2 = in2.getIntExtra("2", 0);
        int res3 = in3.getIntExtra("3", 0);
        int res4 = in4.getIntExtra("4", 0);
        int res5 = in5.getIntExtra("5", 0);

        if (res1 == 1) {
            imageView.setImageResource(R.drawable.image2);
            feeltext.setText("힐링");

            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));
            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));
            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));
            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));
            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));
            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));


        } else if (res2 == 2) {
            imageView.setImageResource(R.drawable.image3);
            feeltext.setText("집중");

            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));
            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));
            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));
            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));
            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));
            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));


        } else if (res3 == 3) {
            imageView.setImageResource(R.drawable.image4);
            feeltext.setText("열정");

            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));
            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));
            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));
            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));
            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));
            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));


        } else if (res4 == 4) {
            imageView.setImageResource(R.drawable.image1);
            feeltext.setText("안정");

            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));
            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));
            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));
            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));
            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));
            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));


        } else if (res5 == 5) {
            imageView.setImageResource(R.drawable.music_profile);
            feeltext.setText("경쾌");

            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));
            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));
            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));
            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));
            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));
            MusicInfoLIst_r.add(new MusicList_recommend("sadf", "asdf", R.drawable.music_profile, R.raw.ncs_music1));


        }


    }
}