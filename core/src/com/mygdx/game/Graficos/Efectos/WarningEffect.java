package com.mygdx.game.Graficos.Efectos;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.game.Graficos.SizeEvaluator;
import com.mygdx.game.Recursos;

public class WarningEffect extends Effect {

    private static final float WARNING_TIME =2.0f ;
    private int campoX, campoY;
    private Recursos res;

    public interface WarningEffectListener{

        public void onEffectOver(WarningEffect efecto);
    };

    private WarningEffectListener listener;

    public static WarningEffect create(int fx, int fy, EffectsEngine parent,  Recursos res, WarningEffectListener _listener){

        WarningEffect efecto=warningPool.obtain();
        efecto.init(fx, fy, parent, res, _listener);
        return efecto;
    }



    public WarningEffect() {
    }

    public void init(int fx, int fy, EffectsEngine parent, Recursos res, WarningEffectListener listener){

        campoX=fx;
        campoY=fy;
        this.listener=listener;
        this.res=res;
        super.init(parent);
    }

    @Override
    public void draw(SpriteBatch batch, SizeEvaluator sizeEvaluator) {

        batch.begin();
        batch.draw(res.warning, sizeEvaluator.getBaseScreenX(campoX), sizeEvaluator.getBaseScreenY(campoY));
        batch.end();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if(timeAlive>WARNING_TIME && isAlive()){

            isAlive=false;
            if(listener!=null){

                listener.onEffectOver(this);
            }
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

    public int getCampoX(){

        return campoX;
    }

    public int getCampoY(){
        return campoY;
    }
}
