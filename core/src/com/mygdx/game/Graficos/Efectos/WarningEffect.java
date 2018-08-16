package com.mygdx.game.Graficos.Efectos;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.game.Graficos.SizeEvaluator;
import com.mygdx.game.Recursos;

public class WarningEffect extends Effect {

    private static final float WARNING_TIME =2.0f ;
    private int campoX, campoY;
    private SizeEvaluator sizeEvaluator;
    private Recursos res;

    public static WarningEffect create(int fx, int fy, EffectsEngine parent, SizeEvaluator sizeEvaluator, Recursos res){

        WarningEffect efecto=warningPool.obtain();
        efecto.init(fx, fy, parent, sizeEvaluator, res);
        return efecto;
    }

    public WarningEffect() {
    }

    public void init(int fx, int fy, EffectsEngine parent, SizeEvaluator sizeEvaluator, Recursos res){

        campoX=fx;
        campoY=fy;
        this.sizeEvaluator=sizeEvaluator;
        this.res=res;
        super.init(parent);
    }

    @Override
    public void draw(SpriteBatch batch) {

        batch.begin();
        batch.draw(res.warning, sizeEvaluator.getBaseScreenX(campoX), sizeEvaluator.getBaseScreenY(campoY));
        batch.end();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if(timeAlive>WARNING_TIME){

            isAlive=false;
        }
    }

    @Override
    public void release() {

        warningPool.free(this);
    }

    static Pool<WarningEffect> warningPool= new Pool<WarningEffect>(){


        @Override
        protected WarningEffect newObject() {
            return new WarningEffect();
        }
    };
}
