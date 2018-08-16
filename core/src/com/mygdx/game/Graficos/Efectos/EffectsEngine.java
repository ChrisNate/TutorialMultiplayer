package com.mygdx.game.Graficos.Efectos;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class EffectsEngine {

    List<Effect> efectos;
    public EffectsEngine() {

        efectos=new ArrayList<Effect>();
    }

    public void add(Effect effect){

        efectos.add(effect);
    }

    public void update(float delta){

        int i=0;
        while(i<efectos.size()){

            efectos.get(i).update(delta);
            if(efectos.get(i).isAlive()){
                i++;
            }else{

                efectos.get(i).release();
                efectos.remove(i);

            }
        }
    }

    public void draw(SpriteBatch batch){

        for(Effect efecto: efectos){

            efecto.draw(batch);
        }
    }

    public void clear(){

        while(efectos.size()>0){

            efectos.get(0).release();
            efectos.remove(0);
        }
    }
}
