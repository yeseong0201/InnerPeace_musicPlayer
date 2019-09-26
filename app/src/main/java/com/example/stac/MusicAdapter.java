package com.example.stac;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ItemViewHolder> {
    public ArrayList<MusicList> mItems;
    int index = -1;

    private SparseBooleanArray mSelectedItems = new SparseBooleanArray(0);

    int selected_position = 0;

    private MediaPlayer mediaPlayer;


    public MusicAdapter(ArrayList<MusicList> items) {
        this.mItems = items;
    }

    // 새로운 뷰 홀더 생성
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_music_list, parent, false);
        return new ItemViewHolder(view);
    }


    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        if (holder != null) {
            holder.drawableID.setImageResource(mItems.get(position).getDrawableId());
            holder.title.setText(mItems.get(position).getTitle());
            holder.singer.setText(mItems.get(position).getSinger());
            holder.imageButton.setImageResource(mItems.get(position).getPlayButton());
        }


        if (isItemSelected(position)) {
            holder.itemView.setBackgroundColor(Color.parseColor("#1CFFFFFF"));

            holder.imageButton.setImageResource(R.drawable.ic_play);
        } else {
            // holder.itemView.setBackgroundColor(Color.WHITE);
        }

    }

    public void setFilter(List<MusicList> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }


    // 데이터 셋의 크기를 리턴해줍니다.
    @Override
    public int getItemCount() {
        return mItems.size();
    }


    // 커스텀 뷰홀더
// item layout 에 존재하는 위젯들을 바인딩합니다.
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView drawableID;
        TextView title;
        TextView singer;
        LinearLayout linearLayout;
        ImageButton imageButton;
        RecyclerView recyclerView;

        public ItemViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    toggleItemSelected(position);
                }
            });

            recyclerView = itemView.findViewById(R.id.recyclerView);

            drawableID = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            singer = itemView.findViewById(R.id.singer);
            imageButton = itemView.findViewById(R.id.playbtn);
            linearLayout = itemView.findViewById(R.id.linearlayout);
        }


    }

    private void toggleItemSelected(int position) {

        if (mSelectedItems.get(position, false) == true) {
            mSelectedItems.delete(position);

            notifyItemChanged(position);
        } else {
            mSelectedItems.put(position, true);
            notifyItemChanged(position);
        }
    }

    private boolean isItemSelected(int position) {
        return mSelectedItems.get(position, false);
    }

    public void clearSelectedItem() {
        int position;

        for (int i = 0; i < mSelectedItems.size(); i++) {
            position = mSelectedItems.keyAt(i);
            mSelectedItems.put(position, false);
            notifyItemChanged(position);
        }

        mSelectedItems.clear();
    }


}