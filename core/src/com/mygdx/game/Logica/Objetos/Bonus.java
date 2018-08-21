package com.mygdx.game.Logica.Objetos;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.game.Graficos.SizeEvaluator;
import com.mygdx.game.Recursos;


public class Bonus extends Sprite implements Pool.Poolable{

    public static byte BONUS_TYPE_ATTACK=0;
    public static byte BONUS_TYPE_HEALTH=1;

    private int campoX, campoY;
    private byte bonusType;

    public Bonus(){

    }

    public void setup(int fx, int fy, byte bType, Recursos res ){

        campoX=fx;
        campoY=fy;
        bonusType=bType;
        if(bType==BONUS_TYPE_ATTACK){
            set(res.attackBonus);
        }else if(bType==BONUS_TYPE_HEALTH){

            set(res.healthBonus);
        }
    }

    @Override
    public void reset() {

    }

    static final Pool<Bonus> bonusPool=new Pool<Bonus>(){


        @Override
        protected Bonus newObject() {
            return new Bonus();
        }
    };

    public void release(){

        bonusPool.free(this);
    }

    public static Bonus Create(int fx, int fy, byte bType, Recursos res){

        Bonus bonus=bonusPool.obtain();
        bonus.setup(fx, fy, bType, res);
        return bonus;
    }

    public void draw(SpriteBatch batch, SizeEvaluator sz){

        setPosition(sz.getBaseScreenX(campoX), sz.getBaseScreenY(campoY));
        super.draw(batch);
    }

    public int getCampoX() {
        return campoX;
    }

    public byte getBonusType() {
        return bonusType;
    }

    public int getCampoY() {
        return campoY;
    }


}
