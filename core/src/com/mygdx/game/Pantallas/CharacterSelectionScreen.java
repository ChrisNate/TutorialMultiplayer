package com.mygdx.game.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Logica.GameProgress;
import com.mygdx.game.Logica.Objetos.CharacterRecord;
import com.mygdx.game.MTutorial;
import com.mygdx.game.Recursos;

public class CharacterSelectionScreen extends DefaultScreen {

    Stage uiStage;

    public CharacterSelectionScreen(MTutorial _game) {
        super(_game);
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

        uiStage.clear();

        Label.LabelStyle labelStyle=new Label.LabelStyle(juego.res.gameFont, Color.WHITE);
        labelStyle.font.getData().setScale(0.4f);

        if(GameProgress.levels[GameProgress.currentCharacter]==0){ // char is locked

            TextButton unlockButton=new TextButton("UNLOCK(1000 GOLD)", buttonStyle);
            unlockButton.setPosition((uiStage.getWidth()-unlockButton.getWidth())/2, uiStage.getHeight()*5/6);
            unlockButton.addListener(new ClickListener()
                                    {
                                        @Override
                                        public void touchUp(InputEvent event, float x, float y, int pointer, int button ){

                                            if(GameProgress.currentGold>=GameProgress.CHARACTER_PRICE){

                                                GameProgress.currentGold-=GameProgress.CHARACTER_PRICE;
                                                GameProgress.levels[GameProgress.currentCharacter]=1;
                                                prepareUI();
                                            }

                                        }

                                    }
            );
            uiStage.addActor(unlockButton);

        }else{

            TextButton upgradeButton=new TextButton("LvlUp(" + GameProgress.getNextUpgradeCost(GameProgress.currentCharacter)+")", buttonStyle);
            upgradeButton.setPosition((uiStage.getWidth()-upgradeButton.getWidth())/2, uiStage.getHeight()/6);
            upgradeButton.addListener(new ClickListener()
                                      {
                                          @Override
                                          public void touchUp(InputEvent event, float x, float y, int pointer, int button ){

                                              if(GameProgress.currentGold>=GameProgress.getNextUpgradeCost(GameProgress.currentCharacter)){

                                                  GameProgress.currentGold-=GameProgress.getNextUpgradeCost(GameProgress.currentCharacter);
                                                  GameProgress.levels[GameProgress.currentCharacter]+=1;
                                                  prepareUI();
                                              }

                                          }

                                      }
            );
            uiStage.addActor(upgradeButton);

            TextButton startButton=new TextButton("START", buttonStyle);

            startButton.setPosition((uiStage.getWidth()-startButton.getWidth())/2, uiStage.getHeight()*5/6);
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

        }


        Image heroSprite= new Image(juego.res.playerSprites.get(CharacterRecord.CHARACTERS[GameProgress.currentCharacter].name));
        heroSprite.setPosition((uiStage.getWidth()-heroSprite.getWidth())/2, (uiStage.getHeight()-heroSprite.getHeight())/2 );
        uiStage.addActor(heroSprite);

        int lvl=GameProgress.levels[GameProgress.currentCharacter];
        Label statusText=new Label(lvl>0 ? "LVL: " + lvl : "LOCKED", labelStyle);
        statusText.setPosition(heroSprite.getX()+(heroSprite.getWidth()-statusText.getWidth())/2, heroSprite.getY()-statusText.getHeight()-5 );
        uiStage.addActor(statusText);

        TextButton nextButton=new TextButton(">>>", buttonStyle);
        nextButton.setPosition(uiStage.getWidth()*5/6 - nextButton.getWidth()/2, uiStage.getHeight()/2);
        nextButton.addListener(new ClickListener()
                                {
                                    @Override
                                    public void touchUp(InputEvent event, float x, float y, int pointer, int button ){

                                        GameProgress.currentCharacter++;
                                        if(GameProgress.currentCharacter==CharacterRecord.CHARACTERS.length)GameProgress.currentCharacter=0;
                                        prepareUI();
                                    }

                                }
        );

        uiStage.addActor(nextButton);

        TextButton prevButton=new TextButton("<<<", buttonStyle);
        prevButton.setPosition(uiStage.getWidth()/6, uiStage.getHeight()/2);
        prevButton.addListener(new ClickListener()
                               {
                                   @Override
                                   public void touchUp(InputEvent event, float x, float y, int pointer, int button ){

                                       GameProgress.currentCharacter--;
                                       if(GameProgress.currentCharacter<0)GameProgress.currentCharacter=CharacterRecord.CHARACTERS.length-1;
                                       prepareUI();
                                   }

                               }
        );
        uiStage.addActor(prevButton);

        Image coinImage=new Image(juego.res.coinBonus);
        coinImage.setPosition(1,1);
        uiStage.addActor(coinImage);


        Label coinAmountLabel=new Label(""+GameProgress.currentGold, labelStyle);
        coinAmountLabel.setPosition(coinImage.getX()+coinImage.getWidth()+3, coinImage.getY()+(coinImage.getHeight()-coinAmountLabel.getHeight())/2);
        uiStage.addActor(coinAmountLabel);


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

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        uiStage.getViewport().update(width, height, true);
    }
}
