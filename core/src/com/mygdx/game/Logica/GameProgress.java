package com.mygdx.game.Logica;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.game.Logica.Objetos.CharacterRecord;

public class GameProgress {


    public static int playerLives=3;
    public static int currentLevel=0;
    public static int currentCharacter=0;
    public static int currentGold=0;

    private static final String PROGRESS_SAVE_NAME="progress";

    private static final String SAVE_KEY_LIVES="lives";
    private static final String SAVE_KEY_CURRENT_LEVEL="currentlevel";
    private static final String SAVE_KEY_GOLD="currentgold";
    private static final String SAVE_KEY_CURRENT_CHARACTER = "currentcharacter";
    private static final String SAVE_KEY_PLAYER_LEVEL="playerlevel";

    public static final int CHARACTER_PRICE=1000;
    public static int levels[]; // level of each character, 0= locked


    public static int getEnemyLives(){

        return 3 + currentLevel *2;
    }

    public static void Save(){

        Preferences prefs= Gdx.app.getPreferences(PROGRESS_SAVE_NAME);
        prefs.putInteger(SAVE_KEY_LIVES, playerLives);
        prefs.putInteger(SAVE_KEY_CURRENT_LEVEL, currentLevel);
        prefs.putInteger(SAVE_KEY_GOLD, currentGold);
        prefs.putInteger(SAVE_KEY_CURRENT_CHARACTER, currentCharacter);
        for(int i=0; i<levels.length; i++ ){
            prefs.putInteger(SAVE_KEY_PLAYER_LEVEL + i, levels[i] );
        }

        prefs.flush();

    }

    public static void Load(){

        levels=new int[CharacterRecord.CHARACTERS.length];

        Preferences prefs= Gdx.app.getPreferences(PROGRESS_SAVE_NAME);
        playerLives=prefs.getInteger(SAVE_KEY_LIVES, 3);
        currentLevel=prefs.getInteger(SAVE_KEY_CURRENT_LEVEL, 0);
        currentCharacter=prefs.getInteger(SAVE_KEY_CURRENT_CHARACTER, 0);
        currentGold=prefs.getInteger(SAVE_KEY_GOLD, 0);
        for(int i=0; i<CharacterRecord.CHARACTERS.length; i++ ){

            levels[i]=prefs.getInteger(SAVE_KEY_PLAYER_LEVEL +i, i==0 ? 1:0);
        }

    }


    public static void Reset() {

        playerLives=3;
        currentLevel=0;
        currentCharacter=0;
    }

    public static int getNextUpgradeCost(int currentCharacter) {
        return levels[currentCharacter]*2;
    }

    public static int getPlayerMaxHp(){

        CharacterRecord currentChar=CharacterRecord.CHARACTERS[currentCharacter];
        return currentChar.getMaxHp(levels[currentCharacter]);
    }

    public static int getPlayerDamage() {

        CharacterRecord currentChar=CharacterRecord.CHARACTERS[currentCharacter];
        return currentChar.getDamage(levels[currentCharacter]);
    }

    public static int getPlayerHealthRestored() {

        CharacterRecord currentChar=CharacterRecord.CHARACTERS[currentCharacter];
        return currentChar.getHpRestored(levels[currentCharacter]);
    }

    public static float getPlayerBonusreduction() {

        CharacterRecord currentChar=CharacterRecord.CHARACTERS[currentCharacter];
        return currentChar.getBonusSpawnReduction(levels[currentCharacter]);
    }

    public static int getPlayerBonusreductionValue(){

        CharacterRecord currentChar=CharacterRecord.CHARACTERS[currentCharacter];
        return levels[currentCharacter] / currentChar.levelsForBonusSpawnUpgrade;
    }
}
