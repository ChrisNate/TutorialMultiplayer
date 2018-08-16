package com.mygdx.game.Logica.Objetos;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends Sprite {

    private int campoX;
    private int campoY;

    public Player(int fx, int fy){

        campoX=fx;
        campoY=fy;

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
}
