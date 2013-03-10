package com.whostolemyhat.rogue.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.whostolemyhat.rogue.RogueGame;

public class AbstractScreen implements Screen {
	
	protected final RogueGame game;
	protected final BitmapFont font;
	protected final SpriteBatch batch;
	protected final Stage stage;
	
	public AbstractScreen(RogueGame game) {
		this.game = game;
		this.font = new BitmapFont();
		this.batch = new SpriteBatch();
		this.stage = new Stage(0, 0, true);
	}
	
	protected String getName() {
		return getClass().getSimpleName();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.9f, 0.1f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log(RogueGame.LOG, String.format("Resizing screen %s to %dx%d", getName(), width, height));
		stage.setViewport(width, height, true);
	}

	@Override
	public void show() {
		Gdx.app.log(RogueGame.LOG, String.format("Showing screen: %s", getName()));
	}

	@Override
	public void hide() {
		Gdx.app.log(RogueGame.LOG, String.format("Hiding screen: %s", getName()));
	}

	@Override
	public void pause() {
		Gdx.app.log(RogueGame.LOG, String.format("Pausing screen: %s", getName()));
	}

	@Override
	public void resume() {
		Gdx.app.log(RogueGame.LOG, String.format("Resuming screen: %s", getName()));
	}

	@Override
	public void dispose() {
		stage.dispose();
		font.dispose();
		batch.dispose();
	}
	
}
