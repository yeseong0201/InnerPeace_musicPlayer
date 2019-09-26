package com.example.stac;

import android.graphics.Color;
import android.support.annotation.NonNull;
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

public class MusicAdapterRecommend extends RecyclerView.Adapter<MusicAdapterRecommend.ItemViewHolder2> {

    private ArrayList<MusicList_recommend> mItems_r;

    private SparseBooleanArray mSelectedItems_r = new SparseBooleanArray(0);

    public MusicAdapterRecommend(ArrayList<MusicList_recommend> items_r) {
        this.mItems_r = items_r;
    }

    @NonNull
    @Override
    public ItemViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_music_list_recommend, parent, false);
        return new ItemViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder2 holder2, final int position) {
        holder2.title.setText(mItems_r.get(position).getTitle_r());
        holder2.singer.setText(mItems_r.get(position).getSinger_r());
        holder2.imageButton.setImageResource(mItems_r.get(position).getPlaybutton_r());
        holder2.drawableID.setImageResource(mItems_r.get(position).getDrawableId_r());

        if (isItemSelected(position)) {
            holder2.itemView.setBackgroundColor(Color.parseColor("#1CFFFFFF"));

            holder2.imageButton.setImageResource(R.drawable.ic_play);


        } else {

        }


    }

    @Override
    public int getItemCount() {
        return mItems_r.size();
    }

    // item layout 에 존재하는 위젯들을 바인딩합니다.
    public class ItemViewHolder2 extends RecyclerView.ViewHolder {
        ImageView drawableID;
        TextView title;
        TextView singer;
        LinearLayout linearLayout;
        ImageButton imageButton;
        RecyclerView recyclerView_recommend;


        public ItemViewHolder2(View itemView2) {
            super(itemView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    toggleItemSelected_r(position);
                }
            });

            // title 여기서부터
            title = itemView2.findViewById(R.id.title_r);
            singer = itemView2.findViewById(R.id.singer_r);
            imageButton = itemView2.findViewById(R.id.playbtn_r);
            drawableID = itemView2.findViewById(R.id.image_r);
            recyclerView_recommend = itemView2.findViewById(R.id.recyclerview_recommend);


        }

    }

    private void toggleItemSelected_r(int position) {

        if (mSelectedItems_r.get(position, false) == true) {
            mSelectedItems_r.delete(position);

            notifyItemChanged(position);
        } else {
            mSelectedItems_r.put(position, true);
            notifyItemChanged(position);
        }
    }

    private boolean isItemSelected(int position) {
        return mSelectedItems_r.get(position, false);
    }

    public void clearSelectedItem() {
        int position;

        for (int i = 0; i < mSelectedItems_r.size(); i++) {
            position = mSelectedItems_r.keyAt(i);
            mSelectedItems_r.put(position, false);
            notifyItemChanged(position);
        }

        mSelectedItems_r.clear();
    }

//    private void toggleItemSelected(int position) {
//
//        if (mSelectedItems.get(position, false) == true) {
//            mSelectedItems.delete(position);
//
//            notifyItemChanged(position);
//        } else {
//            mSelectedItems.put(position, true);
//            notifyItemChanged(position);
//        }
//    }
//
//    private boolean isItemSelected(int position) {
//        return mSelectedItems.get(position, false);
//    }
//
//    public void clearSelectedItem() {
//        int position;
//
//        for (int i = 0; i < mSelectedItems.size(); i++) {
//            position = mSelectedItems.keyAt(i);
//            mSelectedItems.put(position, false);
//            notifyItemChanged(position);
//        }
//
//        mSelectedItems.clear();
//    }
}
