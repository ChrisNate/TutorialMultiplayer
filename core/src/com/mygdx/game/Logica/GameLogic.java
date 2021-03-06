package com.mygdx.game.Logica;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Graficos.Efectos.EffectsEngine;
import com.mygdx.game.Graficos.Efectos.WarningEffect;
import com.mygdx.game.Logica.Objetos.Bonus;
import com.mygdx.game.Logica.Objetos.Enemigo;
import com.mygdx.game.Logica.Objetos.Player;
import com.mygdx.game.MTutorial;
import com.mygdx.game.Recursos;

import java.util.ArrayList;

public class GameLogic implements Enemigo.EnemyAttackListener, WarningEffect.WarningEffectListener {

    public static final int MAX_BASE_X=3;
    public static final int MAX_BASE_Y=3;
    private final float BONUS_SPAWN_INTERVAL;
    private static final int MAX_BONUS_ON_FIELD = 3;

    public interface GameEventListener{

        void omGameEnd(final boolean playerWon);
        void onBonusPickup(byte bonusType);
    }



    Player player;
    Enemigo enemigo;
    EffectsEngine effectEngine;
    MTutorial juego;
    ArrayList<Bonus> bonus;
    float gameTime, lastBonusSpawnTime;
    GameEventListener listener;

    public GameLogic(MTutorial game, GameEventListener _listener){

        BONUS_SPAWN_INTERVAL=2.0f * (1-GameProgress.getPlayerBonusreduction());
        juego=game;
        player=new Player(MathUtils.random(MAX_BASE_X), MathUtils.random(MAX_BASE_Y), juego.res, GameProgress.playerLives);
        enemigo= new Enemigo(juego.res, this, MathUtils.random(Recursos.ENEMY_UNIVERSAL) );
        effectEngine=new EffectsEngine();
        bonus=new ArrayList<Bonus>();
        gameTime=0;
        lastBonusSpawnTime=0;
        listener=_listener;


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

        for(int i=bonus.size()-1; i>=0; i--){

            Bonus bonusActual=bonus.get(i);
            if(bonusActual.getCampoX()==fx && bonusActual.getCampoY()==fy){

                listener.onBonusPickup(bonusActual.getBonusType());
                if(bonusActual.getBonusType()==Bonus.BONUS_TYPE_HEALTH){

                    player.addVidas(GameProgress.getPlayerHealthRestored());

                }else if(bonusActual.getBonusType()==Bonus.BONUS_TYPE_ATTACK){

                    enemigo.recibirDamage(GameProgress.getPlayerDamage());
                    if(enemigo.getVidas()<=0){

                        GameProgress.increaseStage();
                        GameProgress.playerLives=player.getVidas();
                        player.markVictoria();
                        listener.omGameEnd(true);

                    }

                }else if(bonusActual.getBonusType()==Bonus.BONUS_TYPE_COIN){

                    GameProgress.currentGold++;


                }

                bonusActual.release();
                bonus.remove(i);
                break;
            }

        }

    }

    private void spawnRandomBonus(){

        int fx=0;
        int fy=0;
        boolean targetNonEmpty=true;
        do{

            fx=MathUtils.random(MAX_BASE_X);
            fy=MathUtils.random(MAX_BASE_Y);
            targetNonEmpty=player.getCampoX()==fx || fy==player.getCampoY();
            for(int i=0; i<bonus.size() && !targetNonEmpty; i++){

                if(bonus.get(i).getCampoX()==fx && bonus.get(i).getCampoY()==fy){
                    targetNonEmpty=true;
                }
            }


        }while(targetNonEmpty);

        byte tipo=Bonus.BONUS_TYPE_ATTACK;
        int random=MathUtils.random(7);
        if(random>6){
            tipo=Bonus.BONUS_TYPE_HEALTH;
        }else if(random>4){
            tipo=Bonus.BONUS_TYPE_COIN;
        }
        bonus.add(Bonus.Create(fx,fy, tipo, juego.res));
        lastBonusSpawnTime=gameTime;

    }

    public void update(float delta){

        gameTime+=delta;

        player.update(delta);

        if(player.getVidas()>0 && enemigo.getVidas()>0) {

            effectEngine.update(delta);
            enemigo.update(delta);

            if (lastBonusSpawnTime + BONUS_SPAWN_INTERVAL < gameTime && bonus.size() < MAX_BONUS_ON_FIELD) {

                spawnRandomBonus();

            }
        }


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

            player.recibirDamage(GameProgress.getEnemyDamage());
            if(player.getVidas()<=0){
                listener.omGameEnd(false);
                GameProgress.Reset(true);
            }


        }

    }

    public ArrayList<Bonus> getBonus() {
        return bonus;
    }
}
