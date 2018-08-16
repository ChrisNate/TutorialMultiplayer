package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Recursos {

    TextureAtlas gameSprites;

    public TextureRegion ground;
    public TextureRegion wall;
    public Sprite player;
    public TextureRegion base;
    public TextureRegion warning;

    public static final int TILE_SIZE=16;

    public Recursos(){

        gameSprites=new TextureAtlas(Gdx.files.internal("packed/game.atlas"));
        ground=gameSprites.findRegion("ground");
        wall=gameSprites.findRegion("wall");
        warning=gameSprites.findRegion("warning");

        player=new Sprite(gameSprites.findRegion("player"));
        base=gameSprites.findRegion("base");
    }

    public void dispose(){

        gameSprites.dispose();
    }
}
