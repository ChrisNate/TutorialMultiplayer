package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Recursos {

    TextureAtlas gameSprites;

    public TextureRegion ground;
    public TextureRegion wall;
    public Sprite player, enemy;
    public TextureRegion base;
    public TextureRegion warning;
    public BitmapFont gameFont;
    public Sprite attackBonus, healthBonus;




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
        enemy=new Sprite(gameSprites.findRegion("spider"));
        base=gameSprites.findRegion("base");
    }

    public void dispose(){

        gameSprites.dispose();
    }
}
