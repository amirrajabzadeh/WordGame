package com.ar.wordgame;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GameFragment extends Fragment {
    private static final String TAG = "GameFragment";
    private Level level;
    private View guessActionContainer, btnAccept, btnCancel;
    private CharacterAdapter guessCharacterAdapter;
    private CharacterAdapter wordsAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        level = getArguments().getParcelable("level");
        Log.i(TAG, "onCreate: ");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        guessActionContainer = view.findViewById(R.id.frame_game_guessActionContainer);
        btnAccept = view.findViewById(R.id.btn_game_acceptAction);
        btnCancel = view.findViewById(R.id.btn_game_cancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guessActionContainer.setVisibility(View.GONE);
                guessCharacterAdapter.clear();
            }
        });

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = guessCharacterAdapter.getWord();
                for (int i = 0; i < level.getWords().size(); i++) {
                    if (word.equalsIgnoreCase(level.getWords().get(i))) {
                        Toast.makeText(getContext(), "حدست درسته : " + word, Toast.LENGTH_SHORT).show();
                        wordsAdapter.makeWordVisible(word);
                        btnCancel.performClick();
                        return;
                    }
                }
                btnCancel.performClick();
                Toast.makeText(getContext(), "صحیح نیست", Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView characterRv = view.findViewById(R.id.rv_game_character);
        characterRv.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        List<Character> uniqueCharacter = GamePlayUtil.extactUniqueCharacter(level.getWords());
        List<CharacterPlaceHolder> characterPlaceHolders = new ArrayList<>();
        for (int i = 0; i < uniqueCharacter.size(); i++) {
            CharacterPlaceHolder characterPlaceHolder = new CharacterPlaceHolder();
            characterPlaceHolder.setVisible(true);
            characterPlaceHolder.setCharacter(uniqueCharacter.get(i));
            characterPlaceHolders.add(characterPlaceHolder);
        }

        CharacterAdapter characterAdapter = new CharacterAdapter(characterPlaceHolders);
        characterRv.setAdapter(characterAdapter);
        characterAdapter.setOnRecyclerViewItemClickListener(new OnRecyclerViewItemClickListener<CharacterPlaceHolder>() {
            @Override
            public void onItemClick(CharacterPlaceHolder item, int position) {
                guessActionContainer.setVisibility(View.VISIBLE);
                guessCharacterAdapter.add(item);
            }
        });

        RecyclerView guessCharacterRv = view.findViewById(R.id.rv_game_guess);
        guessCharacterRv.setLayoutManager(new LinearLayoutManager(getContext(),
                RecyclerView.HORIZONTAL, false));
        guessCharacterAdapter = new CharacterAdapter();
        guessCharacterRv.setAdapter(guessCharacterAdapter);

        int maxLenght = 0;
        for (int i = 0; i < level.getWords().size(); i++) {
            if (level.getWords().get(i).length() > maxLenght)
                maxLenght = level.getWords().get(i).length();
        }
        RecyclerView rvWords = view.findViewById(R.id.rv_game_words);
        rvWords.setLayoutManager(new GridLayoutManager(getContext(), maxLenght,
                RecyclerView.VERTICAL, false));

        List<CharacterPlaceHolder> wordsCharacterPlaceHolder = new ArrayList<>();
        for (int i = 0; i < level.getWords().size(); i++) {
            for (int j = 0; j < maxLenght; j++) {
                CharacterPlaceHolder characterPlaceHolder = new CharacterPlaceHolder();
                if (j < level.getWords().get(i).length()) {
                    characterPlaceHolder.setCharacter(level.getWords().get(i).charAt(j));
                    characterPlaceHolder.setVisible(false);
                    characterPlaceHolder.setNull(false);
                    characterPlaceHolder.setTag(level.getWords().get(i));
                } else characterPlaceHolder.setNull(true);


                wordsCharacterPlaceHolder.add(characterPlaceHolder);
            }
        }
        wordsAdapter = new CharacterAdapter(wordsCharacterPlaceHolder);
        rvWords.setAdapter(wordsAdapter);

    }
}