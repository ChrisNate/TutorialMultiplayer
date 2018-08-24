package com.mygdx.game.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.Graficos.Efectos.WarningEffect;
import com.mygdx.game.Graficos.Fondo;
import com.mygdx.game.Graficos.SizeEvaluator;
import com.mygdx.game.Logica.GameLogic;
import com.mygdx.game.Logica.GameProgress;
import com.mygdx.game.Logica.Objetos.Bonus;
import com.mygdx.game.Logica.Objetos.Player;
import com.mygdx.game.MTutorial;
import com.mygdx.game.Recursos;
import com.mygdx.game.SoundManager;

import static com.mygdx.game.Logica.GameLogic.MAX_BASE_X;
import static com.mygdx.game.Logica.GameLogic.MAX_BASE_Y;

public class GameScreen extends DefaultScreen implements InputProcessor, GameLogic.GameEventListener {

    SpriteBatch batch;

    // ancho =12
    // alto=8

    public static final int SCREEN_ANCHO= 12* Recursos.TILE_SIZE; // 192
    public static final int SCREEN_ALTO= 8* Recursos.TILE_SIZE; // 128
    public static final float SHAKE_TIME_ON_DAMAGE=0.3f;
    public static final float SHAKE_DIST=4.0f;
    public static final float GAME_END_FADEOUT=1.5f;
    public static final float GAME_START_FADEIN=1.5f;

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
        sizeEvaluator=new SizeEvaluator(gameStage, juego.res, MAX_BASE_X, MAX_BASE_Y, gameStage.getWidth());
        logica=new GameLogic(juego, this);
        jugador=logica.getPlayer();
        SoundManager.PlayBattleMusic();



        Gdx.input.setInputProcessor(this);
        gameStage.addAction(
            new Action(){

                float time=0;
                @Override
                public boolean act(float delta) {
                    time+=delta;
                    float t=time/GAME_START_FADEIN;
                    t=t*t;
                    if(t>1.0f)t=1.0f;
                    batch.setColor(1,1,1, t);
                    return time>=GAME_START_FADEIN;

                }
            }
        );



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

    private void mostrarResultadoJuego(String str){

        drawShadowed(str, 0, gameStage.getViewport().getScreenY() + gameStage.getHeight() / 2, gameStage.getWidth(), Align.center, Color.RED);

    }

    private void drawUI(){

        batch.begin();
        drawShadowed("VIDAS: "+ jugador.getVidas(), 5,  gameStage.getHeight(), gameStage.getWidth(), Align.left, Color.WHITE );
        drawShadowed("ENEMIGO: "+ logica.getEnemigo().getVidas(), 0,  gameStage.getHeight(), gameStage.getWidth()-5, Align.right, Color.WHITE );

        batch.draw(juego.res.coinBonus, gameStage.getViewport().getScreenX()+2, gameStage.getViewport().getScreenY()+5);
        drawShadowed(""+ GameProgress.currentGold, gameStage.getViewport().getScreenX()+ juego.res.coinBonus.getWidth()+4,
                gameStage.getViewport().getScreenY()+10+ juego.res.coinBonus.getHeight()/2, gameStage.getWidth()-4, Align.left, Color.WHITE);
        if(jugador.getVidas()<=0) {

          mostrarResultadoJuego("DERROTA");

        }else if(logica.getEnemigo().getVidas()<=0){

           mostrarResultadoJuego("VICTORIA");
        }
        batch.end();
    }

    private void drawShadowed(String str, float x, float y, float width, int align, Color color){

        juego.res.gameFont.setColor(Color.BLACK);

        for(int i=-1; i<2;i++){

            for(int j=-1;j<2;j++){

                juego.res.gameFont.draw(batch, str, x+i, y+j, width, align, false);
            }
        }

        juego.res.gameFont.setColor(color);
        juego.res.gameFont.draw(batch, str, x, y, width, align, false);
        juego.res.gameFont.setColor(Color.WHITE);
    }

    @Override
    public void render(float delta) {

        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        fondo.draw(gameStage, juego.res);
        drawBases();
        batch.begin();
        for(Bonus bonus: logica.getBonus()){

            bonus.draw(batch, sizeEvaluator);
        }
        jugador.draw(batch, sizeEvaluator);
        logica.getEnemigo().draw(batch, sizeEvaluator);
        batch.end();
        logica.getEffectEngine().draw(batch, sizeEvaluator);
        gameStage.getCamera().position.set(gameStage.getWidth()/2, gameStage.getHeight()/2, 0);
        if(jugador.getVidas()>0 && jugador.getTimeAlive()-jugador.getTimeOfDmgTaken()<SHAKE_TIME_ON_DAMAGE){
            gameStage.getCamera().translate(-(SHAKE_DIST/2)+ MathUtils.random(SHAKE_DIST), -(SHAKE_DIST/2)+ MathUtils.random(SHAKE_DIST), 0);
        }
        gameStage.getCamera().update();
        drawUI();
        gameStage.draw();

    }

    @Override
    public void dispose () {

        super.dispose();
        batch.dispose();
        SoundManager.StopBattleMusic();
        Gdx.input.setInputProcessor(null);

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        gameStage.getViewport().update(width, height, true);
        sizeEvaluator.setRightSideX(gameStage.getWidth());

    }



    public void intentarMover(int dx, int dy){

        if(jugador.getVidas()>0 && logica.getEnemigo().getVidas()>0 && logica.canMove(jugador.getCampoX()+dx, jugador.getCampoY()+dy)){

            SoundManager.PlayWalkSound();
            logica.asignarPosicionJugador(jugador.getCampoX()+dx, jugador.getCampoY()+dy);


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

    @Override
    public void omGameEnd(final boolean playerWon) {

        gameStage.addAction(
                Actions.sequence(
                    new Action(){

                        float time=0;
                        @Override
                        public boolean act(float delta) {
                            time+=delta;
                            float t=time/GAME_END_FADEOUT;
                            t=t*t;
                            batch.setColor(1,1,1,1-t);
                            return time>=GAME_END_FADEOUT;
                        }
                    },
                    new Action(){

                        @Override
                        public boolean act(float delta) {
                            dispose();
                            if(playerWon){
                                juego.setScreen(new GameScreen(juego));
                            }else{
                                juego.setScreen(new CharacterSelectionScreen(juego));
                            }

                            return true;
                        }
                    }
                )
        );
    }

    @Override
    public void onBonusPickup(byte bonusType) {

        if (bonusType==Bonus.BONUS_TYPE_COIN){

            SoundManager.PlayCoinSound();
        }else if(bonusType==Bonus.BONUS_TYPE_HEALTH){

            SoundManager.PlayHealSound();
        }
    }
}
