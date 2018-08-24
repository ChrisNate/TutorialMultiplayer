package com.mygdx.game.Logica.Objetos;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.SoundManager;

public class Character extends Sprite {

    protected int vidas;
    private float timeOfDmgTaken;
    protected float timeAlive;

    public static final float BLINK_TIME_AFTER_DMG=0.25f;

    public Character(int _vidas){

        this.vidas=_vidas;
        timeAlive=0;
        timeOfDmgTaken=-1;
    }

    public int getVidas(){

        return vidas;
    }

    public void recibirDamage(int valor){

        SoundManager.PlaySwingSound();
        timeOfDmgTaken=timeAlive;
        this.vidas-=valor;
        if(vidas<0)vidas=0;
    }

    public void update(float delta){

        timeAlive+=delta;

    }

    public void preDraw(){

        if(timeAlive<timeOfDmgTaken + BLINK_TIME_AFTER_DMG){

            float t=(timeAlive-timeOfDmgTaken)/ BLINK_TIME_AFTER_DMG;
            t=t*t;
            setColor(1,0,0,t);

        }
    }

    public void postDraw(){

            setColor(1,1,1,1);
    }

    public float getTimeOfDmgTaken() {
        return timeOfDmgTaken;
    }

    public float getTimeAlive() {
        return timeAlive;
    }
}
