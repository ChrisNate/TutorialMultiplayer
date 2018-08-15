package com.mygdx.game.Graficos;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Recursos;

public class Fondo {

    public Fondo(){


    }

    public void draw(Stage stage, Recursos res){

        stage.getBatch().begin();

        for(int y=0; y<stage.getHeight(); y+=Recursos.TILE_SIZE){

            for(int x=0; x<stage.getWidth(); x+=Recursos.TILE_SIZE)
            {
                stage.getBatch().draw(res.ground,
                        x, y,
                        0,0,
                        Recursos.TILE_SIZE, Recursos.TILE_SIZE,
                        1.01f, 1.01f,
                        0);
                //  stage.getBatch().draw(res.wall, 0, 16);
            }

        }

        for(int x=0; x<stage.getWidth(); x+=Recursos.TILE_SIZE)
        {
            stage.getBatch().draw(res.wall,
                    x, stage.getHeight()- Recursos.TILE_SIZE,
                    0,0,
                    Recursos.TILE_SIZE, Recursos.TILE_SIZE,
                    1.01f, 1.01f,
                    0);
            //  stage.getBatch().draw(res.wall, 0, 16);
        }


        stage.getBatch().end();
    }


}
