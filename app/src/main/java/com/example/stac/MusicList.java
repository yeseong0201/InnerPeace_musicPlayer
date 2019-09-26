package com.example.stac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MusicList {

    private int drawableId;
    private String title;
    private String singer;
    private int playButton;
    private int music;
    private String musicId;


    public int getPlayButton() {
        return playButton;
    }

    public int getDrawableId() {
        return drawableId;
    }


    public String getTitle() {
        return title;
    }


    public String getSinger() {
        return singer;
    }

    public int getMusic() {
        return music;
    }

    public String getMusicId() {
        return musicId;
    }


//    public int getPlayButton() {
//        return playButton;
//    }


    public MusicList(int drawableId, String title, String singer, int playButton, int music) {
        this.drawableId = drawableId;
        this.title = title;
        this.singer = singer;
        this.playButton = playButton;
        this.music = music;

    }
}