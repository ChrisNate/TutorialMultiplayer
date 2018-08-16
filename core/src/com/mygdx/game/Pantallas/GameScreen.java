package com.mygdx.game.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.Graficos.Efectos.WarningEffect;
import com.mygdx.game.Graficos.Fondo;
import com.mygdx.game.Graficos.SizeEvaluator;
import com.mygdx.game.Logica.GameLogic;
import com.mygdx.game.Logica.Objetos.Player;
import com.mygdx.game.MTutorial;
import com.mygdx.game.Recursos;

import static com.mygdx.game.Logica.GameLogic.MAX_BASE_X;
import static com.mygdx.game.Logica.GameLogic.MAX_BASE_Y;

public class GameScreen extends DefaultScreen implements InputProcessor {

    SpriteBatch batch;

    // ancho =12
    // alto=8

    public static final int SCREEN_ANCHO= 12* Recursos.TILE_SIZE; // 192
    public static final int SCREEN_ALTO= 8* Recursos.TILE_SIZE; // 128

    private Stage gameStage;
    private Fondo fondo;
    private SizeEvaluator sizeEvaluator;
    private GameLogic logica;
    private Player jugador;




    public GameScreen(MTutorial _game) {
        super(_game);
        batch = new SpriteBatch();
        ExtendViewport viewport= new ExtendViewport(SCREEN_ANCHO, SCREEN_ALTO);
        gameStage= new Stage(viewport, batch);
        fondo=new Fondo();
        sizeEvaluator=new SizeEvaluator(gameStage, juego.res, MAX_BASE_X, MAX_BASE_Y);
        logica=new GameLogic();
        jugador=logica.getPlayer();

        jugador.set(juego.res.player);
        refrescarJugador();
        Gdx.input.setInputProcessor(this);
        WarningEffect.create(0,0, logica.getEffectEngine(), sizeEvaluator, juego.res);


    }

    public void update(float delta){

        gameStage.act(delta);
        logica.update(delta);
    }

    public void drawBases(){

        batch.begin();

        for(int x=0;x<= MAX_BASE_X; x++){

            for(int y=0; y<=MAX_BASE_Y; y++){

                batch.draw(juego.res.base, sizeEvaluator.getBaseScreenX(x), sizeEvaluator.getBaseScreenY(y) );
            }
        }


        batch.end();
    }

    @Override
    public void render(float delta) {

        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        fondo.draw(gameStage, juego.res);
        drawBases();
        logica.getEffectEngine().draw(batch);
        batch.begin();
        jugador.draw(batch);
        batch.end();

        gameStage.draw();

    }

    @Override
    public void dispose () {

        super.dispose();
        batch.dispose();
        Gdx.input.setInputProcessor(null);

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        gameStage.getViewport().update(width, height, true);
        refrescarJugador();

    }

    public void refrescarJugador(){

        jugador.setPosition(sizeEvaluator.getBaseScreenX(jugador.getCampoX()), sizeEvaluator.getBaseScreenY(jugador.getCampoY()));
    }

    public void intentarMover(int dx, int dy){

        if(logica.canMove(jugador.getCampoX()+dx, jugador.getCampoY()+dy)){

            logica.asignarPosicionJugador(jugador.getCampoX()+dx, jugador.getCampoY()+dy);
            refrescarJugador();

        }
    }

    @Override
    public boolean keyDown(int keycode) {

        switch(keycode){

            case Input.Keys.UP:
                intentarMover(0,1);
                break;
            case Input.Keys.DOWN:
                intentarMover(0, -1);
                break;
            case Input.Keys.LEFT:
                intentarMover(-1,0);
                break;
            case Input.Keys.RIGHT:
                intentarMover(1,0);
                break;


        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
