package com.media.playerlib.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.media.playerlib.R;
import com.media.playerlib.cover.ControllerCover;
import com.media.playerlib.model.VideoPlayVo;

import java.util.List;

/**
 * creator huangyong
 * createTime 2019/4/3 下午1:26
 * path com.huangyong.playerlib.adapter
 * description:
 */
public class PlayListAdapter extends RecyclerView.Adapter<PlayHolder> {


    private VideoPlayVo infos;
    private Context context;
    private ControllerCover.OnItemClickedListener clickedListener;

    public PlayListAdapter(VideoPlayVo mPlayList, Context activity, ControllerCover.OnItemClickedListener clickedListener) {
        this.infos = mPlayList;
        this.context = activity;
        this.clickedListener = clickedListener;
    }


    @NonNull
    @Override
    public PlayHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.play_list_item, null);
        return new PlayHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final PlayHolder holder, final int position) {
        holder.index.setText((position + 1) + "");
        holder.index.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(infos.getSeriUrls().get(position))) {
                    clickedListener.clicked(infos.getSeriUrls().get(position),position);
                    holder.index.setSelected(true);
                    ControllerCover.CurrentIndex = position;
                    for (int i = 0; i < infos.getSeriUrls().size(); i++) {
                        if (i == position) {
                        } else {
                            holder.index.setSelected(false);
                        }
                    }
                }

                notifyDataSetChanged();
            }
        });

        if (ControllerCover.CurrentIndex == position) {
            holder.index.setSelected(true);
        } else {
            holder.index.setSelected(false);
        }
    }

    @Override
    public int getItemCount() {
        return infos.getSeriUrls().size() > 0 ? infos.getSeriUrls().size() : 0;
    }

}
