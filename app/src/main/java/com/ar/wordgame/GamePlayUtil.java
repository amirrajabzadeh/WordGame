package com.ar.wordgame;

import java.util.ArrayList;
import java.util.List;

public class GamePlayUtil {
    public static List<Level> createLevels(){
        List<Level> levels=new ArrayList<>();

        Level level1=new Level();
        level1.setId(1);
        level1.getWords().add("آتا");
        level1.getWords().add("آتنا");
        levels.add(level1);


        Level level2=new Level();
        level2.setId(2);
        level2.getWords().add("آیسا");
        level2.getWords().add("آیسان");
        level2.getWords().add("آیسانا");
        levels.add(level2);


        Level level3=new Level();
        level3.setId(3);
        level3.getWords().add("بهار");
        level3.getWords().add("بهاران");
        level3.getWords().add("بهاره");
        level3.getWords().add("بهارک");
        levels.add(level3);


        Level level4=new Level();
        level4.setId(4);
        level4.getWords().add("پرینوش");
        level4.getWords().add("پریوش");
        level4.getWords().add("پرینا");
        level4.getWords().add("پگاه");
        level4.getWords().add("پوران");
        levels.add(level4);


        Level level5=new Level();
        level5.setId(5);
        level5.getWords().add("رامش");
        level5.getWords().add("رامینا");
        level5.getWords().add("رامینه");
        level5.getWords().add("رانیا");
        level5.getWords().add("رانیکا");
        level5.getWords().add("رایا");
        levels.add(level5);


        Level level6=new Level();
        level6.setId(6);
        level6.getWords().add("سنا");
        level6.getWords().add("سنبل");
        level6.getWords().add("سنبله");
        level6.getWords().add("سندس");
        level6.getWords().add("سنیه");
        level6.getWords().add("سودا");
        level6.getWords().add("سودابه");
        levels.add(level6);




        return levels;
    }

    public static List<Character> extactUniqueCharacter(List<String> words){
        List<Character> characters=new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            for (int j = 0; j < words.get(i).length(); j++) {
                if (!characters.contains(words.get(i).charAt(j))){
                    characters.add(words.get(i).charAt(j));
                }
            }
        }
        return characters;
    }
}
