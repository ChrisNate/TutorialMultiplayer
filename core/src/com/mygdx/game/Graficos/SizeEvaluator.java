package com.mygdx.game.Graficos;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Recursos;

public class SizeEvaluator {

    private Stage stageMedida;
    private Recursos res;

    // 4X4

    private final int maxTileBaseX;
    private final int maxTileBaseY;

    public static final int BASE_MARGIN=3;

    public SizeEvaluator(Stage _stage, Recursos _res, int maxBaseX, int maxBaseY) {
        this.stageMedida = _stage;
        this.res = _res;
        this.maxTileBaseX = maxBaseX;
        this.maxTileBaseY = maxBaseY;
    }

    public float getBaseScreenX(int baseX){ // 0..3


        return this.stageMedida.getWidth()/2- (BASE_MARGIN +Recursos.TILE_SIZE) *(this.maxTileBaseX+1 - baseX);

    }

    public float getBaseScreenY(int baseY){ // 0..3


        return this.stageMedida.getHeight()/2- ((BASE_MARGIN +Recursos.TILE_SIZE)*2/3) *((this.maxTileBaseY+1)/2 - baseY);

    }

    public float getEnemyX(Sprite enemy){

        return (stageMedida.getWidth()*3/4)-enemy.getWidth()/2;
    }

    public float getEnemyY(Sprite enemy){

        return (stageMedida.getHeight()/2)-enemy.getHeight()/2;
    }


}
