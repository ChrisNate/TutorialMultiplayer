package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;

public class SoundManager {

    public static AssetManager assets=new AssetManager();
    private static Music bMusic=null;

    public static void LoadSounds(){

        for(int i=0; i<3;i++){

            assets.load(Gdx.files.internal("music/swing"+i+".ogg").path(), Sound.class);
            assets.load(Gdx.files.internal("music/walk"+i+".ogg").path(), Sound.class);
        }

        assets.load(Gdx.files.internal("music/coin.ogg").path(), Sound.class);
        assets.load(Gdx.files.internal("music/heal.ogg").path(), Sound.class);
        assets.finishLoading();
    }

    public static void ReleaseSounds(){

        assets.dispose();
    }

    private static void playSoundRandomVolume(Sound sound, float min, float max){

        if(sound!=null)sound.play(MathUtils.random(min, max));

    }

    public static void PlaySwingSound(){

        Sound s=assets.get("music/swing" + MathUtils.random(2)+ ".ogg", Sound.class);
        playSoundRandomVolume(s, 0.9f, 1.0f);
    }

    public static void PlayWalkSound(){

        Sound s=assets.get("music/walk" + MathUtils.random(2)+ ".ogg", Sound.class);
        playSoundRandomVolume(s, 0.5f, 0.6f);
    }

    public static void PlayCoinSound(){

        Sound s=assets.get("music/coin.ogg", Sound.class);
        playSoundRandomVolume(s, 0.9f, 1.0f);
    }

    public static void PlayHealSound(){

        Sound s=assets.get("music/heal.ogg", Sound.class);
        playSoundRandomVolume(s, 0.9f, 1.0f);
    }

    public static void StopBattleMusic(){

        if(bMusic!=null){
            bMusic.stop();
            bMusic=null;
        }
    }

    public static void PlayBattleMusic(){

        bMusic=Gdx.audio.newMusic(Gdx.files.internal("music/music"+ MathUtils.random(5)+ ".mp3"));
        bMusic.setLooping(true);
        bMusic.play();

    }
}
