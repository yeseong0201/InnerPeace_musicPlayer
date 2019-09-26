package com.example.stac;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.stac.fragments.DiaryFragment;
//import com.example.stac.fragments.FragementBeat;
import com.example.stac.fragments.HomeFragment;
import com.example.stac.fragments.MusicFragment;
import com.example.stac.fragments.MusicFragment;
import com.example.stac.fragments.PlaylistFragment;
import com.example.stac.fragments.ProfileFragment;

import java.util.ArrayList;

public class MusicMainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    ArrayList<Fragment> fragments;
    FragmentUtils fragmentUtils;
    ProfileFragment profileFragment;
    DiaryFragment diaryFragment;
    HomeFragment homeFragment;
    MusicFragment musicFragment;
    PlaylistFragment playlistFragment;
    //FragementBeat fragementBeat;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_main);
        setUp();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void setUp() {
        Window window = getWindow();
        //   Logger.addLogAdapter(new AndroidLogAdapter());
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#121319"));
        //  backPressCloseHandler = new BackPressCloseHandler(this);
        fragments = new ArrayList<>();
        profileFragment = new ProfileFragment();
        diaryFragment = new DiaryFragment();
        homeFragment = new HomeFragment();
        musicFragment = new MusicFragment();
        playlistFragment = new PlaylistFragment();
        //  fragementBeat = new FragementBeat();

        fragments.add(profileFragment);
        fragments.add(diaryFragment);
        fragments.add(homeFragment);
        fragments.add(musicFragment);
        fragments.add(playlistFragment);
        //  fragments.add(fragementBeat);

        fragmentUtils = new FragmentUtils(R.id.framelayout, fragments);
        fragmentUtils.setCurrentFragmentByPosition(getSupportFragmentManager(), 0, new Bundle());
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.bottom_profile:
                        fragmentUtils.setCurrentFragmentByPosition(getSupportFragmentManager(), 0, new Bundle());
                        return true;
                    case R.id.bottom_diary:
                        fragmentUtils.setCurrentFragmentByPosition(getSupportFragmentManager(), 1, new Bundle());
                        return true;
                    case R.id.bottom_home:
                        fragmentUtils.setCurrentFragmentByPosition(getSupportFragmentManager(), 2, new Bundle());
                        return true;
                    case R.id.bottom_music:
                        fragmentUtils.setCurrentFragmentByPosition(getSupportFragmentManager(), 3, new Bundle());
                        return true;
                    case R.id.bottom_playlist:
                        fragmentUtils.setCurrentFragmentByPosition(getSupportFragmentManager(), 4, new Bundle());
                        return true;
                }
                return false;
            }
        });
    }

}

