package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Logica.GameProgress;
import com.mygdx.game.Pantallas.CharacterSelectionScreen;
import com.mygdx.game.Pantallas.GameScreen;

public class MTutorial extends Game {

	public Recursos res;
	
	@Override
	public void create () {


		res=new Recursos();
		GameProgress.Load();
		SoundManager.LoadSounds();
		setScreen(new CharacterSelectionScreen(this));

	}

	@Override
	public void dispose() {
		GameProgress.Save();
		SoundManager.ReleaseSounds();
		res.dispose();
	}
}
