package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class Recursos {

    TextureAtlas gameSprites;

    public TextureRegion ground;
    public TextureRegion wall;
    public Sprite player;
    public HashMap<Integer, Sprite> enemySprite;
    public TextureRegion base;
    public TextureRegion warning;
    public BitmapFont gameFont;
    public Sprite attackBonus, healthBonus;
    public static final int ENEMY_VERTICAL=0; // spider
    public static final int ENEMY_HORIZONTAL=1; // ghost
    public static final int ENEMY_DIAGONAL=2; // bat
    public static final int ENEMY_RANDOM=3; // slime
    public static final int ENEMY_UNIVERSAL=4; // skeleton

    public static final int TILE_SIZE=16;

    public Recursos(){

        gameFont= new BitmapFont(Gdx.files.internal("Mortal Kombat.fnt"), Gdx.files.internal("Mortal Kombat.png"), false);
        gameFont.getData().setScale(0.65f);

        gameSprites=new TextureAtlas(Gdx.files.internal("packed/game.atlas"));
        ground=gameSprites.findRegion("ground");
        wall=gameSprites.findRegion("wall");
        warning=gameSprites.findRegion("warning");
        attackBonus=new Sprite(gameSprites.findRegion("attack"));
        healthBonus=new Sprite(gameSprites.findRegion("health"));

        player=new Sprite(gameSprites.findRegion("player"));
        enemySprite= new HashMap<Integer, Sprite>();
        enemySprite.put(ENEMY_VERTICAL, gameSprites.createSprite("spider"));
        enemySprite.put(ENEMY_HORIZONTAL, gameSprites.createSprite("ghost"));
        enemySprite.put(ENEMY_DIAGONAL, gameSprites.createSprite("bat"));
        enemySprite.put(ENEMY_RANDOM, gameSprites.createSprite("slime"));
        enemySprite.put(ENEMY_UNIVERSAL, gameSprites.createSprite("skeleton"));
        base=gameSprites.findRegion("base");
    }

    public void dispose(){

        gameSprites.dispose();
    }
}
