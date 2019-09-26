package com.example.stac.fragments;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stac.FeelRecommend;
import com.example.stac.MusicAdapter;
import com.example.stac.MusicList;
import com.example.stac.MusicMainActivity;
import com.example.stac.MusicPlay;
import com.example.stac.R;
import com.example.stac.RecyclerItemClickListener;
import com.example.stac.controller.SwipeController;
import com.example.stac.controller.SwipeControllerActions;

import java.util.ArrayList;

public class MusicFragment extends Fragment implements View.OnClickListener {
    RecyclerView recyclerView;
    int currentPosition;
    RecyclerView.LayoutManager layoutManager;

    TextView backdrop_Title, backdrop_Singer, textView, textView2;

    double timeElapsed = 0, finalTime = 0;
    int forwardTime = 2000, backwardTime = 2000;
    Handler durationHandler = new Handler();


    MediaPlayer mediaPlayer;

    LinearLayout backdrop;

    Button changeBtn, feel_btn, feelbtn1, feelbtn2, feelbtn3, feelbtn4, feelbtn5, feelbtn6, feelbtn7, feelbtn8, feelbtn9, feelbtn10;

    MusicMainActivity musicmainActivity;

    public static Boolean isPlayed = false;

    ConstraintLayout recommendView;
    LinearLayout allOfView;

    Intent song_intent;

    int click_cnt = 1;

    int touch_cnt = 0;

    int music_cnt = 0;

    int music[];


    SearchView searchView;

    ImageView backdrop_Image;

    ImageButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, feeling_image_btn;

    ImageButton backdrop_previous_btn, backdrop_pause_btn, backdrop_next_btn, backdrop_play_btn;

    private ArrayList<MusicList> MusicInfoLIst = new ArrayList<>();

    SwipeController swipeController = null;

    Boolean swiped;

    int swipeCnt = 0;

    private int pausePosition;

    MusicAdapter musicAdapter = new MusicAdapter(MusicInfoLIst);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public MusicFragment newInstance() {
        MusicFragment fragment = new MusicFragment();
        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_music, container, false);

        changeBtn = v.findViewById(R.id.change_btn);
        feel_btn = v.findViewById(R.id.feel_btn);
        feeling_image_btn = v.findViewById(R.id.feeling_image_btn);

        btn1 = v.findViewById(R.id.btn1);
        btn2 = v.findViewById(R.id.btn2);
        btn3 = v.findViewById(R.id.btn3);
        btn4 = v.findViewById(R.id.btn4);
        btn5 = v.findViewById(R.id.btn5);
        btn6 = v.findViewById(R.id.btn6);
        btn7 = v.findViewById(R.id.btn7);
        btn8 = v.findViewById(R.id.btn8);
        btn9 = v.findViewById(R.id.btn9);
        btn10 = v.findViewById(R.id.btn10);

        feelbtn1 = v.findViewById(R.id.feel_btn1);
        feelbtn2 = v.findViewById(R.id.feel_btn2);
        feelbtn3 = v.findViewById(R.id.feel_btn3);
        feelbtn4 = v.findViewById(R.id.feel_btn4);
        feelbtn5 = v.findViewById(R.id.feel_btn5);
        feelbtn6 = v.findViewById(R.id.feel_btn6);
        feelbtn7 = v.findViewById(R.id.feel_btn7);
        feelbtn8 = v.findViewById(R.id.feel_btn8);
        feelbtn9 = v.findViewById(R.id.feel_btn9);
        feelbtn10 = v.findViewById(R.id.feel_btn10);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn10.setOnClickListener(this);

        feelbtn1.setOnClickListener(this);
        feelbtn2.setOnClickListener(this);
        feelbtn3.setOnClickListener(this);
        feelbtn4.setOnClickListener(this);
        feelbtn5.setOnClickListener(this);
        feelbtn6.setOnClickListener(this);
        feelbtn7.setOnClickListener(this);
        feelbtn8.setOnClickListener(this);
        feelbtn9.setOnClickListener(this);
        feelbtn10.setOnClickListener(this);


        recommendView = v.findViewById(R.id.recommendView);

        allOfView = v.findViewById(R.id.linearlayout);

        textView = v.findViewById(R.id.textview);
        textView = v.findViewById(R.id.textview2);


        backdrop = v.findViewById(R.id.backdrop);
        backdrop_previous_btn = v.findViewById(R.id.backdrop_previous_btn);
        backdrop_pause_btn = v.findViewById(R.id.backdrop_pause_btn);
        backdrop_next_btn = v.findViewById(R.id.backdrop_next_btn);
        backdrop_play_btn = v.findViewById(R.id.backdrop_play_btn);

        backdrop_Singer = v.findViewById(R.id.backdrop_singer);
        backdrop_Title = v.findViewById(R.id.badkdrop_title);
        backdrop_Image = v.findViewById(R.id.image);

        musicmainActivity = (MusicMainActivity) getActivity();

        searchView = v.findViewById(R.id.searchView);

        recyclerView = v.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(musicmainActivity);

        recyclerView.setLayoutManager(layoutManager);

        MusicInfoLIst.add(new MusicList(R.drawable.music_profile, "힐링_1", "#힐링", R.drawable.ic_play_white, R.raw.test_sound));
        MusicInfoLIst.add(new MusicList(R.drawable.image1, "힐링_2", "#힐링", R.drawable.ic_play_white, R.raw.ncs_music1));
        MusicInfoLIst.add(new MusicList(R.drawable.image2, "집중_1", "#집중", R.drawable.ic_play_white, R.raw.song1));
        MusicInfoLIst.add(new MusicList(R.drawable.image3, "집중_2", "#집중", R.drawable.ic_play_white, R.raw.ncs_music1));
        MusicInfoLIst.add(new MusicList(R.drawable.image4, "열정_1", "#열정", R.drawable.ic_play_white, R.raw.song3));
        MusicInfoLIst.add(new MusicList(R.drawable.music_profile, "열정_2", "#열정", R.drawable.ic_play_white, R.raw.song4));
        MusicInfoLIst.add(new MusicList(R.drawable.music_profile, "안정_1", "#안정", R.drawable.ic_play_white, R.raw.ncs_music1));
        MusicInfoLIst.add(new MusicList(R.drawable.music_profile, "안정_2", "#안정", R.drawable.ic_play_white, R.raw.song3));
        MusicInfoLIst.add(new MusicList(R.drawable.music_profile, "경쾌_1", "#경쾌", R.drawable.ic_play_white, R.raw.ncs_music1));
        MusicInfoLIst.add(new MusicList(R.drawable.music_profile, "경쾌_2", "#경쾌", R.drawable.ic_play_white, R.raw.song1));

        musicAdapter.notifyDataSetChanged();

        recyclerView.setAdapter(musicAdapter);

        MusicInfoLIst.size();
        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_cnt++;
                if (click_cnt % 2 == 0) {
                    allOfView.setVisibility(View.VISIBLE);
                    recommendView.setVisibility(View.INVISIBLE);
                    changeBtn.setText("추천비트 보기");
                } else {
                    allOfView.setVisibility(View.INVISIBLE);
                    recommendView.setVisibility(View.VISIBLE);
                    changeBtn.setText("음악 전체보기");
                }

            }
        });


        feeling_image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feel_btn.setVisibility(View.VISIBLE);
            }
        });

        feel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feel_btn.setVisibility(View.INVISIBLE);

            }
        });

        Spannable span = (Spannable) textView.getText();

        span.setSpan(new ForegroundColorSpan(Color.parseColor("#996ad7")), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new RelativeSizeSpan(1.2f), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        backdrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = backdrop_Title.getText().toString();
                String singer = backdrop_Singer.getText().toString();

                Intent intent = new Intent(musicmainActivity, MusicPlay.class);
                intent.putExtra("singer", singer);
                intent.putExtra("title", title);

                if (singer == null) {
                    Toast.makeText(musicmainActivity, "먼저 음악을 선택해주세요.", Toast.LENGTH_SHORT).show();
                } else if (singer != null) {
                }
            }
        });

        backdrop_play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                backdrop_play_btn.setVisibility(View.GONE);
                backdrop_pause_btn.setVisibility(View.VISIBLE);

                mediaPlayer.start();
                mediaPlayer.seekTo(pausePosition);

            }
        });
        backdrop_pause_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                backdrop_play_btn.setVisibility(View.VISIBLE);
                backdrop_pause_btn.setVisibility(View.GONE);

                mediaPlayer.pause();
                pausePosition = mediaPlayer.getCurrentPosition();


            }
        });

        backdrop_next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int size = MusicInfoLIst.size();
                int res = MusicInfoLIst.get(currentPosition).getMusic();
                Log.d("current is ", String.valueOf(currentPosition));

                if (isPlayed) {
                    mediaPlayer.stop();
                    isPlayed = false;
                    touch_cnt = 0;
                }

                mediaPlayer = MediaPlayer.create(musicmainActivity, MusicInfoLIst.get(currentPosition + 1).getMusic());
                backdrop_Title.setText(MusicInfoLIst.get(currentPosition + 1).getTitle());
                backdrop_Singer.setText(MusicInfoLIst.get(currentPosition + 1).getSinger());
                backdrop_Image.setImageResource(MusicInfoLIst.get(currentPosition + 1).getDrawableId());
                currentPosition = currentPosition + 1;
                isPlayed = true;
                if (currentPosition > 8) {
                    currentPosition = 0;
                    mediaPlayer = MediaPlayer.create(musicmainActivity, MusicInfoLIst.get(currentPosition).getMusic());
                }

                mediaPlayer.start();


            }
        });


        backdrop_previous_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("-- current is ", String.valueOf(currentPosition));

                if (isPlayed) {
                    mediaPlayer.stop();
                    isPlayed = false;
                    touch_cnt = 0;
                }

                mediaPlayer = MediaPlayer.create(musicmainActivity, MusicInfoLIst.get(currentPosition - 1).getMusic());
                backdrop_Title.setText(MusicInfoLIst.get(currentPosition + 1).getTitle());
                backdrop_Singer.setText(MusicInfoLIst.get(currentPosition + 1).getSinger());
                backdrop_Image.setImageResource(MusicInfoLIst.get(currentPosition + 1).getDrawableId());
                currentPosition = currentPosition - 1;
                isPlayed = true;
                if (currentPosition < 1) {
                    currentPosition = 8;
                    mediaPlayer = MediaPlayer.create(musicmainActivity, MusicInfoLIst.get(currentPosition).getMusic());
                }

                mediaPlayer.start();

            }
        });


        swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {


            }

            @Override
            public void onLeftClicked(int position) {


            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent) {
                swipeController.onDraw(c);
            }
        });


        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            //
            @Override
            public void onItemClick(View view, final int position) {
                currentPosition = position;
                if (isPlayed) {
                    mediaPlayer.stop();
                    isPlayed = false;
                    touch_cnt = 0;
                }
                musicAdapter.clearSelectedItem();

                swiped = false;
                swipeCnt++;
                touch_cnt++;

                MusicInfoLIst.get(position).getMusic();

                int music = MusicInfoLIst.get(position).getMusic();


                if (isPlayed == false && touch_cnt == 1) {
                    mediaPlayer = MediaPlayer.create(musicmainActivity, MusicInfoLIst.get(position).getMusic());
                    mediaPlayer.start();

                    backdrop_Title.setText(MusicInfoLIst.get(position).getTitle());
                    backdrop_Singer.setText(MusicInfoLIst.get(position).getSinger());
                    backdrop_Image.setImageResource(MusicInfoLIst.get(position).getDrawableId());

                    isPlayed = true;
                } else if (isPlayed) {
                    mediaPlayer.stop();
                    isPlayed = false;
                    touch_cnt = 0;
                }


                if (!isPlayed) {

                    isPlayed = true;
                } else {

                }

                if (swiped == false && swipeCnt % 2 > 0) {
                    swipeCnt = 0;

                }
            }

            @Override
            public void onLongItemClick(View view, int position) {

                swiped = true;
                swipeCnt++;
            }
        }));

        return v;
    }

    public float dpToPx(float valueIndp) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueIndp, metrics);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn1:
                feelbtn1.setVisibility(View.VISIBLE);
                break;
            case R.id.btn2:
                feelbtn2.setVisibility(View.VISIBLE);
                break;
            case R.id.btn3:
                feelbtn3.setVisibility(View.VISIBLE);
                break;
            case R.id.btn4:
                feelbtn4.setVisibility(View.VISIBLE);
                break;
            case R.id.btn5:
                feelbtn5.setVisibility(View.VISIBLE);
                break;
            case R.id.btn6:
                feelbtn6.setVisibility(View.VISIBLE);
                break;
            case R.id.btn7:
                feelbtn7.setVisibility(View.VISIBLE);
                break;
            case R.id.btn8:
                feelbtn8.setVisibility(View.VISIBLE);
                break;
            case R.id.btn9:
                feelbtn9.setVisibility(View.VISIBLE);
                break;
            case R.id.btn10:
                feelbtn10.setVisibility(View.VISIBLE);
                break;

            // feelRecommend 로 넘어가버렸
            case R.id.feel_btn1:
                Intent intent1 = new Intent(musicmainActivity, FeelRecommend.class);
                intent1.putExtra("1", 1);
                startActivity(intent1);
                feelbtn1.setVisibility(View.INVISIBLE);
                break;
            case R.id.feel_btn2:
                Intent intent2 = new Intent(musicmainActivity, FeelRecommend.class);
                intent2.putExtra("2", 2);
                startActivity(intent2);
                feelbtn2.setVisibility(View.INVISIBLE);

                break;
            case R.id.feel_btn3:
                Intent intent3 = new Intent(musicmainActivity, FeelRecommend.class);
                intent3.putExtra("3", 3);

                startActivity(intent3);
                feelbtn3.setVisibility(View.INVISIBLE);
                break;
            case R.id.feel_btn4:
                Intent intent4 = new Intent(musicmainActivity, FeelRecommend.class);
                intent4.putExtra("4", 4);
                startActivity(intent4);
                feelbtn4.setVisibility(View.INVISIBLE);
                break;
            case R.id.feel_btn5:
                Intent intent5 = new Intent(musicmainActivity, FeelRecommend.class);
                intent5.putExtra("5", 5);
                startActivity(intent5);
                feelbtn5.setVisibility(View.INVISIBLE);
                break;
            case R.id.feel_btn6:
                feelbtn6.setVisibility(View.INVISIBLE);
                break;
            case R.id.feel_btn7:
                feelbtn7.setVisibility(View.INVISIBLE);
                break;
            case R.id.feel_btn8:
                feelbtn8.setVisibility(View.INVISIBLE);
                break;
            case R.id.feel_btn9:
                feelbtn9.setVisibility(View.INVISIBLE);
                break;
            case R.id.feel_btn10:
                feelbtn10.setVisibility(View.INVISIBLE);
                break;


        }

    }
}


