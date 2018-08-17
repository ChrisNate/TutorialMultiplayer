package com.mygdx.game.Logica;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Graficos.Efectos.EffectsEngine;
import com.mygdx.game.Logica.Objetos.Enemigo;
import com.mygdx.game.Logica.Objetos.Player;
import com.mygdx.game.MTutorial;

public class GameLogic {

    public static final int MAX_BASE_X=3;
    public static final int MAX_BASE_Y=3;

    Player player;
    Enemigo enemigo;
    EffectsEngine effectEngine;
    MTutorial juego;

    public GameLogic(MTutorial game){

        juego=game;
        player=new Player(MathUtils.random(MAX_BASE_X), MathUtils.random(MAX_BASE_Y), juego.res);
        enemigo= new Enemigo(juego.res);
        effectEngine=new EffectsEngine();

    }

    public Player getPlayer() {
        return player;
    }

    public Enemigo getEnemigo(){

        return enemigo;
    }

    public boolean canMove(int fx, int fy){
        return (fx>=0 && fx<=MAX_BASE_X ) && (fy>=0 && fy<=MAX_BASE_Y);
    }

    public void asignarPosicionJugador(int fx, int fy){

        player.setCampoX(fx);
        player.setCampoY(fy);

    }

    public void update(float delta){

        effectEngine.update(delta);

    }

    public EffectsEngine getEffectEngine(){

        return effectEngine;
    }


}
