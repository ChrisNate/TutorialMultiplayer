package com.mygdx.game.Logica.Objetos;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Graficos.SizeEvaluator;
import com.mygdx.game.Logica.GameLogic;
import com.mygdx.game.Recursos;

public class Enemigo extends Character {

    private static final float BASE_ATTACK_TIME=3.0f;
    private static final int DEFAULT_ENEMY_LIVES=10;

    private float timeSinceAttack, nextAttackTime;
    private boolean targetTiles[][];


    public interface EnemyAttackListener{

        void onAttack(boolean [][] tiles);
    }

    private EnemyAttackListener attackListener;

    public Enemigo(Recursos res, EnemyAttackListener listener){

        super(DEFAULT_ENEMY_LIVES);
        set(res.enemy);
        resetAttackTime();
        attackListener=listener;
        targetTiles=new boolean[GameLogic.MAX_BASE_X+1][];
        for(int i=0; i<GameLogic.MAX_BASE_Y+1;i++){

            targetTiles[i]=new boolean[GameLogic.MAX_BASE_Y+1];
        }
    }

    public void draw(SpriteBatch batch, SizeEvaluator sz){

        preDraw();
        setPosition(sz.getEnemyX(this), sz.getEnemyY(this));
        super.draw(batch);
        postDraw();
    }



    public void resetAttackTime(){

        timeSinceAttack=0;
        nextAttackTime=BASE_ATTACK_TIME + MathUtils.random(2);
    }
    @Override
    public void update(float delta){

        super.update(delta);
        timeSinceAttack+=delta;
        if(timeSinceAttack> nextAttackTime){

            int col1=MathUtils.random(GameLogic.MAX_BASE_X);
            int col2=0;
            do{
                col2=MathUtils.random(GameLogic.MAX_BASE_X);
            }while(col1==col2);

            for(int x=0; x<GameLogic.MAX_BASE_X+1; x++){

                for(int y=0; y<GameLogic.MAX_BASE_Y+1; y++){

                    targetTiles[x][y]=(x==col1 || x== col2);               }
            }
            attackListener.onAttack(targetTiles);
            resetAttackTime();
        }
    }
}
