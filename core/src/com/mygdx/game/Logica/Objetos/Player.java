package com.mygdx.game.Logica.Objetos;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Graficos.SizeEvaluator;
import com.mygdx.game.Recursos;

public class Player extends Sprite {

    private int campoX;
    private int campoY;

    public Player(int fx, int fy, Recursos res){

        campoX=fx;
        campoY=fy;
        set(res.player);
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

    public void draw(SpriteBatch batch, SizeEvaluator sz){

        setPosition(sz.getBaseScreenX(campoX), sz.getBaseScreenY(campoY));
        super.draw(batch);
    }
}
