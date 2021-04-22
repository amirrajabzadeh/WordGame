package com.ar.wordgame;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView=(RecyclerView) view;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        LevelAdapter levelAdapter=new LevelAdapter(GamePlayUtil.createLevels(), new OnRecyclerViewItemClickListener<Level>() {
            @Override
            public void onItemClick(Level item, int position) {
                Bundle bundle=new Bundle();
                bundle.putParcelable("level",item);
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_gameFragment,bundle);
            }
        });
        recyclerView.setAdapter(levelAdapter);
    }
}