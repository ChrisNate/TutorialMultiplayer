package com.mygdx.game.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.MTutorial;
import com.mygdx.game.Recursos;

public class CharacterSelectionScreen extends DefaultScreen {

    Stage uiStage;
    int currentCharacter;
    public CharacterSelectionScreen(MTutorial _game) {
        super(_game);
        currentCharacter=0;
        FitViewport viewport=new FitViewport(160,120);
        uiStage= new Stage(viewport);
        Gdx.input.setInputProcessor(uiStage);
        prepareUI();

    }

    public void prepareUI(){

        TextButton.TextButtonStyle buttonStyle=new TextButton.TextButtonStyle();
        buttonStyle.font= juego.res.gameFont;
        buttonStyle.font.getData().setScale(0.4f);

        buttonStyle.fontColor= Color.WHITE;

        TextButton startButton=new TextButton("START", buttonStyle);

        startButton.setPosition((uiStage.getWidth()-startButton.getWidth())/2, uiStage.getHeight()/6);
        startButton.addListener(new ClickListener()
                                {
                                    @Override
                                    public void touchUp(InputEvent event, float x, float y, int pointer, int button ){

                                        dispose();
                                        juego.setScreen(new GameScreen(juego));
                                    }

                                }
        );
        uiStage.addActor(startButton);
        Image heroSprite= new Image(juego.res.player);
        heroSprite.setPosition((uiStage.getWidth()-heroSprite.getWidth())/2, (uiStage.getHeight()-heroSprite.getHeight())/2 );
        uiStage.addActor(heroSprite);

        TextButton nextButton=new TextButton(">>>", buttonStyle);
        nextButton.setPosition(uiStage.getWidth()*5/6 - nextButton.getWidth()/2, uiStage.getHeight()/2);
        uiStage.addActor(nextButton);

        TextButton prevButton=new TextButton("<<<", buttonStyle);
        prevButton.setPosition(uiStage.getWidth()/6, uiStage.getHeight()/2);
        uiStage.addActor(prevButton);

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        uiStage.act(delta);
        uiStage.draw();

    }

    @Override
    public void dispose() {

        Gdx.input.setInputProcessor(null);
        uiStage.dispose();
        super.dispose();
    }
}
