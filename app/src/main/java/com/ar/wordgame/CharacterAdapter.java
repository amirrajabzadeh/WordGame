package com.ar.wordgame;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private List<CharacterPlaceHolder> characterPlaceHolders = new ArrayList<>();
    private OnRecyclerViewItemClickListener<CharacterPlaceHolder> onRecyclerViewItemClickListener;

    public CharacterAdapter() {
    }

    public CharacterAdapter(List<CharacterPlaceHolder> characterPlaceHolders) {
        this.characterPlaceHolders = characterPlaceHolders;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CharacterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_char, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        holder.bindCharacter(characterPlaceHolders.get(position));
    }

    public void add(CharacterPlaceHolder characterPlaceHolder) {
        this.characterPlaceHolders.add(characterPlaceHolder);
        notifyItemInserted(characterPlaceHolders.size() - 1);
    }

    public void clear() {
        characterPlaceHolders.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return characterPlaceHolders.size();
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener<CharacterPlaceHolder> onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }


    public class CharacterViewHolder extends RecyclerView.ViewHolder {
        private TextView tvChar;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChar = itemView.findViewById(R.id.tv_char);
        }

        public void bindCharacter(CharacterPlaceHolder characterPlaceHolder) {
            if (characterPlaceHolder.isVisible()) {
                tvChar.setText(characterPlaceHolder.getCharacter().toString());
                tvChar.setVisibility(View.VISIBLE);
            } else {
                tvChar.setVisibility(View.INVISIBLE);
            }

            if (characterPlaceHolder.isNull())
                itemView.setBackground(null);
            else
                itemView.setBackgroundResource(R.drawable.background_rv_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onRecyclerViewItemClickListener != null) {
                        onRecyclerViewItemClickListener.onItemClick(characterPlaceHolder, getAdapterPosition());
                    }
                }
            });
        }
    }
}
