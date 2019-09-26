//package com.example.musicplayer;
//
//import android.app.NotificationManager;
//import android.app.Service;
//import android.content.Context;
//import android.content.Intent;
//import android.media.MediaPlayer;
//import android.os.Handler;
//import android.os.IBinder;
//import android.provider.Settings;
//import android.support.v4.app.NotificationCompat;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.SeekBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.concurrent.TimeUnit;
//
//public class BackgroundPlayer extends Service {
//
//    private MediaPlayer mediaPlayer;
//
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//
//
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//
//
//        if (intent == null) {
//            return Service.START_STICKY; //서비스가 종료되어도 자동으로 다시 실행시켜줘!
//        } else {
//            mediaPlayer = MediaPlayer.create(this, R.raw.ncs_music1);
//            mediaPlayer.start();
//        }
//
//        return super.onStartCommand(intent, flags, startId);
//
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mediaPlayer.stop();
//    }
//
//}
