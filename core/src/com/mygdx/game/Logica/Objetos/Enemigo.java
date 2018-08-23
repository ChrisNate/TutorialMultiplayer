package com.mygdx.game.Logica.Objetos;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Graficos.SizeEvaluator;
import com.mygdx.game.Logica.GameLogic;
import com.mygdx.game.Logica.GameProgress;
import com.mygdx.game.Recursos;

import sun.awt.AWTAccessor;

public class Enemigo extends Character {

    private static final float BASE_ATTACK_TIME=1.0f;
    private static final float WARM_UP_TIME = 2.0f ;
    private static float SCALE_TIME=1.5f;

    private float timeSinceAttack, nextAttackTime;
    private boolean targetTiles[][];


    public interface EnemyAttackListener{

        void onAttack(boolean [][] tiles);
    }

    private EnemyAttackListener attackListener;
    private int tipo;

    public Enemigo(Recursos res, EnemyAttackListener listener, int tipo){

        super(GameProgress.getEnemyLives());
        this.tipo=tipo;
        set(res.enemySprite.get(tipo));
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
        if(timeAlive<SCALE_TIME){

            float t=timeAlive/ SCALE_TIME;
            t=t*t;
            setScale(t);

        }else{

            setScale(1);
        }
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
        if(timeAlive>WARM_UP_TIME &&timeSinceAttack> nextAttackTime){

            switch(tipo){

                case Recursos.ENEMY_VERTICAL:
                    performVerticalLineAttack();
                    break;
                case Recursos.ENEMY_HORIZONTAL:
                    performHorizontalLineAttack();
                    break;
                case Recursos.ENEMY_DIAGONAL:
                    performDiagonalAttack();
                    break;
                case Recursos.ENEMY_RANDOM:
                    performRandomAttack();
                    break;
                default:
                    performUniversalAttack();
                    break;
            }
            attackListener.onAttack(targetTiles);
            resetAttackTime();
        }
    }

    private void performVerticalLineAttack(){

        int col1=MathUtils.random(GameLogic.MAX_BASE_X);
        int col2=0;
        do{
            col2=MathUtils.random(GameLogic.MAX_BASE_X);
        }while(col1==col2);

        for(int x=0; x<GameLogic.MAX_BASE_X+1; x++){

            for(int y=0; y<GameLogic.MAX_BASE_Y+1; y++){

                targetTiles[x][y]=(x==col1 || x== col2);               }
        }
    }

    private void performHorizontalLineAttack(){

        int row1=MathUtils.random(GameLogic.MAX_BASE_Y);
        int row2=0;
        do{
            row2=MathUtils.random(GameLogic.MAX_BASE_Y);
        }while(row1==row2);

        for(int x=0; x<GameLogic.MAX_BASE_X+1; x++){

            for(int y=0; y<GameLogic.MAX_BASE_Y+1; y++){

                targetTiles[x][y]=(y==row1 || y== row2);               }
        }

    }

    private void fillDiagonal(int xstart, int dx){

        for(int i=0; i<=GameLogic.MAX_BASE_Y; i++){

            int nx= xstart + i*dx;
            if(nx>GameLogic.MAX_BASE_X)nx=nx-GameLogic.MAX_BASE_X-1;
            if(nx<0)nx=nx+GameLogic.MAX_BASE_X+1;
            targetTiles[nx][i]=true;

        }
    }

    private void performDiagonalAttack(){

        int dx1=-1 + MathUtils.random(1)*2; // el rango estará entre -1 y 1
        int dx2=-1 + MathUtils.random(1)*2; // el rango estará entre -1 y 1

        int col1=MathUtils.random(GameLogic.MAX_BASE_X);
        int col2=0;
        do{
            col2=MathUtils.random(GameLogic.MAX_BASE_X);
        }while(col1==col2);

        for(int x=0; x<GameLogic.MAX_BASE_X+1; x++){

            for(int y=0; y<GameLogic.MAX_BASE_Y+1; y++){

                targetTiles[x][y]=false;               }
        }
        fillDiagonal(col1, dx1);
        fillDiagonal(col2, dx2);
    }

    private void performRandomAttack() {

        for (int x = 0; x < GameLogic.MAX_BASE_X + 1; x++) {

            for (int y = 0; y < GameLogic.MAX_BASE_Y + 1; y++) {

                targetTiles[x][y] = false;
            }
        }

        for(int i=0; i<10;i++){

            int nx=MathUtils.random(GameLogic.MAX_BASE_X);
            int ny=MathUtils.random(GameLogic.MAX_BASE_Y);
            targetTiles[nx][ny]=true;

        }
    }

    private void performUniversalAttack(){

        int rnd=MathUtils.random(3);
        switch(rnd){

            case 0:
                performVerticalLineAttack();
                break;
            case 1:
                performHorizontalLineAttack();
                break;
            case 2:
                performDiagonalAttack();
                break;
            case 3:
                performRandomAttack();
                break;
            default:
                performRandomAttack();
                break;
        }
    }


}
