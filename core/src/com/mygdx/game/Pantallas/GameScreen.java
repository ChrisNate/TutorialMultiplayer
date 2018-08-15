package com.mygdx.game.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.Graficos.Fondo;
import com.mygdx.game.Graficos.SizeEvaluator;
import com.mygdx.game.MTutorial;
import com.mygdx.game.Recursos;

public class GameScreen extends DefaultScreen {

    SpriteBatch batch;

    // ancho =12
    // alto=8

    public static final int SCREEN_ANCHO= 12* Recursos.TILE_SIZE; // 192
    public static final int SCREEN_ALTO= 8* Recursos.TILE_SIZE; // 128

    private Stage gameStage;
    private Fondo fondo;
    private SizeEvaluator sizeEvaluator;
    public static final int MAX_BASE_X=3;
    public static final int MAX_BASE_Y=3;



    public GameScreen(MTutorial _game) {
        super(_game);
        batch = new SpriteBatch();
        ExtendViewport viewport= new ExtendViewport(SCREEN_ANCHO, SCREEN_ALTO);
        gameStage= new Stage(viewport, batch);
        fondo=new Fondo();
        sizeEvaluator=new SizeEvaluator(gameStage, juego.res, MAX_BASE_X, MAX_BASE_Y);

    }

    public void update(float delta){

        gameStage.act(delta);
    }

    public void drawBases(){

        batch.begin();

        for(int x=0;x<= MAX_BASE_X; x++){

            for(int y=0; y<=MAX_BASE_Y; y++){

                batch.draw(juego.res.base, sizeEvaluator.getBaseScreenX(x), sizeEvaluator.getBaseScreenY(y) );
            }
        }

        batch.draw(juego.res.player, sizeEvaluator.getBaseScreenX(1), sizeEvaluator.getBaseScreenY(1)+ SizeEvaluator.BASE_MARGIN);
        batch.end();
    }

    @Override
    public void render(float delta) {

        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        fondo.draw(gameStage, juego.res);
        drawBases();
        gameStage.draw();

    }

    @Override
    public void dispose () {

        super.dispose();
        batch.dispose();

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        gameStage.getViewport().update(width, height, true);

    }
}
