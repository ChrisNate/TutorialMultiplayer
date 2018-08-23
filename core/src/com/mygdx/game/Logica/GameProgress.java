package com.mygdx.game.Logica;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GameProgress {


    public static int playerLives=3;
    public static int maxPlayerLive=3;
    public static int playerDamage=1;
    public static int currentLevel=0;
    public static int currentCharacter=0;
    public static int currentGold=0;

    private static final String PROGRESS_SAVE_NAME="progress";

    private static final String SAVE_KEY_LIVES="lives";
    private static final String SAVE_KEY_LIVES_MAX="livemax";
    private static final String SAVE_KEY_CURRENT_LEVEL="currentlevel";
    private static final String SAVE_KEY_PLAYER_DAMAGE="playerdamage";
    private static final String SAVE_KEY_GOLD="currentgold";
    private static final String SAVE_KEY_CURRENT_CHARACTER = "currentcharacter";

    public static int getEnemyLives(){

        return 3 + currentLevel *2;
    }

    public static void Save(){

        Preferences prefs= Gdx.app.getPreferences(PROGRESS_SAVE_NAME);
        prefs.putInteger(SAVE_KEY_LIVES, playerLives);
        prefs.putInteger(SAVE_KEY_LIVES_MAX, maxPlayerLive);
        prefs.putInteger(SAVE_KEY_CURRENT_LEVEL, currentLevel);
        prefs.putInteger(SAVE_KEY_PLAYER_DAMAGE, playerDamage);
        prefs.putInteger(SAVE_KEY_GOLD, currentGold);
        prefs.putInteger(SAVE_KEY_CURRENT_CHARACTER, currentCharacter);
        prefs.flush();

    }

    public static void Load(){

        Preferences prefs= Gdx.app.getPreferences(PROGRESS_SAVE_NAME);
        playerLives=prefs.getInteger(SAVE_KEY_LIVES, 3);
        maxPlayerLive=prefs.getInteger(SAVE_KEY_LIVES_MAX, 3);
        playerDamage=prefs.getInteger(SAVE_KEY_PLAYER_DAMAGE, 1);
        currentLevel=prefs.getInteger(SAVE_KEY_CURRENT_LEVEL, 0);
        currentCharacter=prefs.getInteger(SAVE_KEY_CURRENT_CHARACTER, 0);
        currentGold=prefs.getInteger(SAVE_KEY_GOLD, 0);
    }


    public static void Reset() {

        playerLives=3;
        maxPlayerLive=3;
        currentLevel=0;
        playerDamage=1;
        currentGold=0;
        currentCharacter=0;
    }
}
