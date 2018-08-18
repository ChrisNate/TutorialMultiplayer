package com.mygdx.game.Graficos.Efectos;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.game.Graficos.SizeEvaluator;

public abstract class Effect  implements Pool.Poolable{

    protected boolean isAlive;
    protected float timeAlive;

    public Effect(){

        isAlive=false;
        timeAlive =0;
    }

    @Override
    public void reset() {

    }

    public void init(EffectsEngine parent){

        isAlive=true;
        timeAlive =0;
        parent.add(this);
    }

    public void update(float delta){

        timeAlive+=delta;
    }

    public abstract void draw(SpriteBatch batch, SizeEvaluator sizeEvaluator);

    public boolean isAlive(){

        return isAlive;
    }


    public abstract void release();
}
