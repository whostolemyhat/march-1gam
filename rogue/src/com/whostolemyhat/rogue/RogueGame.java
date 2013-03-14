package com.whostolemyhat.rogue;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.whostolemyhat.rogue.screens.GameScreen;
import com.whostolemyhat.rogue.screens.TitleScreen;

public class RogueGame extends Game {

	public static final String LOG = RogueGame.class.getSimpleName();
	
	public TitleScreen getTitleScreen() {
		return new TitleScreen(this);
	}
	
	@Override
	public void create() {	
		Gdx.app.log(RogueGame.LOG, "Creating game");
//		setScreen(getTitleScreen());
		setScreen(new GameScreen(this));
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void render() {		
		super.render();
		
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
