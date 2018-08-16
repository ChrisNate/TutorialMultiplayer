package com.mygdx.game.Logica;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Logica.Objetos.Player;

public class GameLogic {

    public static final int MAX_BASE_X=3;
    public static final int MAX_BASE_Y=3;

    Player player;

    public GameLogic(){

        player=new Player(MathUtils.random(MAX_BASE_X), MathUtils.random(MAX_BASE_Y));
    }

    public Player getPlayer() {
        return player;
    }

    public boolean canMove(int fx, int fy){
        return (fx>=0 && fx<=MAX_BASE_X ) && (fy>=0 && fy<=MAX_BASE_Y);
    }

    public void asignarPosicionJugador(int fx, int fy){

        player.setCampoX(fx);
        player.setCampoY(fy);

    }


}
