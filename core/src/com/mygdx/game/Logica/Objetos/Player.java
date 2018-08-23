package com.mygdx.game.Logica.Objetos;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Graficos.SizeEvaluator;
import com.mygdx.game.Logica.GameProgress;
import com.mygdx.game.Recursos;

public class Player extends Character {

    private int campoX;
    private int campoY;
    private final int max_lives;
    private boolean winning=false;
    private float winTime=0;
    public static final float APPROACH_TIME=1.5f;

    public Player(int fx, int fy, Recursos res, int vidas){

        super(vidas);
        campoX=fx;
        campoY=fy;
        set(res.playerSprites.get(CharacterRecord.CHARACTERS[GameProgress.currentCharacter].name));
        this.vidas=vidas;
        max_lives= GameProgress.maxPlayerLive;

    }





    public int getCampoX() {
        return campoX;
    }

    public void setCampoX(int campoX) {
        this.campoX = campoX;
    }

    public int getCampoY() {
        return campoY;
    }

    public void setCampoY(int campoY) {
        this.campoY = campoY;
    }


    public void addVidas(int total){

        vidas+=total;
        if(vidas>max_lives)vidas=max_lives;

    }

    public void draw(SpriteBatch batch, SizeEvaluator sz) {

        preDraw();
        if(timeAlive<APPROACH_TIME) {

            float t = timeAlive / APPROACH_TIME;
            t = t * t;
            setPosition(t * sz.getBaseScreenX(campoX), sz.getBaseScreenY(campoY));

        }else if(winning){

            float t=1;
            if(timeAlive-winTime<APPROACH_TIME){

                t=(timeAlive-winTime)/APPROACH_TIME;
                t=t*t;
            }

            float fx=sz.getBaseScreenX(campoX);
            setPosition(fx + t * (sz.getRightSideX()-fx), sz.getBaseScreenY(campoY));

        }else{

            setPosition(sz.getBaseScreenX(campoX), sz.getBaseScreenY(campoY));

        }

        super.draw(batch);
        postDraw();
    }

    public void markVictoria(){

        winning=true;
        winTime=timeAlive;
    }
}
