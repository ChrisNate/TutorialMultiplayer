package com.mygdx.game.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MTutorial;
import com.mygdx.game.Recursos;

public class GameScreen extends DefaultScreen {

    SpriteBatch batch;


    public GameScreen(MTutorial _game) {
        super(_game);
        batch = new SpriteBatch();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(juego.res.ground,0,0);
        batch.draw(juego.res.wall, 0,16);
        batch.end();
    }

    @Override
    public void dispose () {


        batch.dispose();

    }
}
