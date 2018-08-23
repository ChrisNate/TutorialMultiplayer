package com.mygdx.game.Logica.Objetos;

public class CharacterRecord {

    public final int levelsForHpUpgrade;
    public final int levelsForHpRegenUpgrade;
    public final int levelsForAttackUpgrade;
    public final int levelsForBonusSpawnUpgrade;

    public final String name;

    public CharacterRecord(int levelsForHpUpgrade, int levelsForHpRegenUpgrade, int levelsForAttackUpgrade, int levelsForBonusSpawnUpgrade, String name) {

        this.levelsForHpUpgrade = levelsForHpUpgrade;
        this.levelsForHpRegenUpgrade = levelsForHpRegenUpgrade;
        this.levelsForAttackUpgrade = levelsForAttackUpgrade;
        this.levelsForBonusSpawnUpgrade = levelsForBonusSpawnUpgrade;
        this.name = name;
    }

    public static String CHAR_NAME_HUMAN="Human";
    public static String CHAR_NAME_SPIDER="Spider";
    public static String CHAR_NAME_GHOST="Ghost";
    public static String CHAR_NAME_BAT="Bat";
    public static String CHAR_NAME_SLIME="Slime";
    public static String CHAR_NAME_SKELETON="Skeleton";

    public static CharacterRecord CHARACTERS[]= {

            new CharacterRecord(2,2,4,4,CHAR_NAME_HUMAN),
            new CharacterRecord(3,6,3,3,CHAR_NAME_SPIDER),
            new CharacterRecord(6,12,1,3, CHAR_NAME_SKELETON),
            new CharacterRecord(4,4,2,4, CHAR_NAME_GHOST),
            new CharacterRecord(3,3,4,1,CHAR_NAME_SLIME),
            new CharacterRecord(4, 6,2,2, CHAR_NAME_BAT)
    };
}
