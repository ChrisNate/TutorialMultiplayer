package com.mygdx.game.Logica.Objetos;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Graficos.SizeEvaluator;
import com.mygdx.game.Recursos;

public class Enemigo extends Sprite {

    public Enemigo(Recursos res){

        set(res.enemy);
    }

    public void draw(SpriteBatch batch, SizeEvaluator sz){

        setPosition(sz.getEnemyX(this), sz.getEnemyY(this));
        super.draw(batch);

    }
}
