package com.mygdx.game.Logica.Objetos;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Graficos.SizeEvaluator;
import com.mygdx.game.Recursos;

public class Player extends Sprite {

    private int campoX;
    private int campoY;
    private int vidas;
    private final int max_lives;

    public Player(int fx, int fy, Recursos res, int vidas){

        campoX=fx;
        campoY=fy;
        set(res.player);
        this.vidas=vidas;
        max_lives=vidas;

    }

    public int getVidas(){

        return vidas;
    }

    public void recibirDamage(int valor){

        this.vidas-=valor;
        if(vidas<0)vidas=0;
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

        setPosition(sz.getBaseScreenX(campoX), sz.getBaseScreenY(campoY));
        super.draw(batch);
    }
}
