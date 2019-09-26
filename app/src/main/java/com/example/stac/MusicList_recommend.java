package com.example.stac;

public class MusicList_recommend {
    private String title_r;
    private String singer_r;
    private int music_r;
    private int drawableId_r;
    private int playbutton_r;


    public String getTitle_r() {
        return title_r;
    }

    public String getSinger_r() {
        return singer_r;
    }

    public int getPlaybutton_r() {
        return playbutton_r;
    }

    public int getMusic_r() {
        return music_r;
    }

    public int getDrawableId_r() {
        return drawableId_r;
    }

    public MusicList_recommend(String title_r, String singer_r, int drawableId_r, int music_r) {
        this.title_r = title_r;
        this.singer_r = singer_r;
        this.drawableId_r = drawableId_r;
        this.music_r = music_r;

    }
}
