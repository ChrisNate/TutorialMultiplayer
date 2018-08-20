package com.mygdx.game.Logica;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Graficos.Efectos.EffectsEngine;
import com.mygdx.game.Graficos.Efectos.WarningEffect;
import com.mygdx.game.Logica.Objetos.Enemigo;
import com.mygdx.game.Logica.Objetos.Player;
import com.mygdx.game.MTutorial;

public class GameLogic implements Enemigo.EnemyAttackListener, WarningEffect.WarningEffectListener {

    public static final int MAX_BASE_X=3;
    public static final int MAX_BASE_Y=3;
    private static final int DEFAULT_PLAYER_LIVES = 3;

    Player player;
    Enemigo enemigo;
    EffectsEngine effectEngine;
    MTutorial juego;

    public GameLogic(MTutorial game){

        juego=game;
        player=new Player(MathUtils.random(MAX_BASE_X), MathUtils.random(MAX_BASE_Y), juego.res, DEFAULT_PLAYER_LIVES);
        enemigo= new Enemigo(juego.res, this);
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
        enemigo.update(delta);

    }

    public EffectsEngine getEffectEngine(){

        return effectEngine;
    }


    @Override
    public void onAttack(boolean[][] tiles) {

        for(int x=0; x<tiles.length; x++){

            for(int y=0; y<tiles[x].length; y++){

                if(tiles[x][y]) WarningEffect.create(x,y, effectEngine, juego.res, this);
            }
        }
    }

    @Override
    public void onEffectOver(WarningEffect efecto) {

        if(efecto.getCampoX()== player.getCampoX() && efecto.getCampoY()==player.getCampoY()){

            player.recibirDamage(1);


        }

    }
}
