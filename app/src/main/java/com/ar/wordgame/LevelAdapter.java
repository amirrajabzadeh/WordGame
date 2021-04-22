package com.ar.wordgame;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.LevelViewHolder> {
    private List<Level> levels = new ArrayList<>();
    private OnRecyclerViewItemClickListener<Level> onRecyclerViewItemClickListener;

    public LevelAdapter(List<Level> levels,OnRecyclerViewItemClickListener<Level> onRecyclerViewItemClickListener) {
        this.levels = levels;
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    @NonNull
    @Override
    public LevelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LevelViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_level,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LevelViewHolder holder, int position) {
        holder.bindLevel(levels.get(position));
    }

    @Override
    public int getItemCount() {
        return levels.size();
    }

    public class LevelViewHolder extends RecyclerView.ViewHolder {
        private TextView tvLevel;

        public LevelViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLevel = itemView.findViewById(R.id.tv_level_num);
        }

        public void bindLevel(Level level) {
            tvLevel.setText(String.valueOf(level.getId()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecyclerViewItemClickListener.onItemClick(level,getAdapterPosition());
                }
            });
        }
    }
}
