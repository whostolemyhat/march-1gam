package com.whostolemyhat.rogue.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.whostolemyhat.rogue.RogueGame;
import com.whostolemyhat.rogue.controllers.WorldRenderer;
import com.whostolemyhat.rogue.models.World;

public class GameScreen extends AbstractScreen {
	
	private World world;
	private WorldRenderer renderer;
	
	public GameScreen(RogueGame game) {
		super(game);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		renderer.render();
	}

	@Override
	public void show() {
		world = new World();
		renderer = new WorldRenderer(world, true);
	}
	
	@Override
	public void resize(int width, int height) {
		renderer.setSize(width, height);
	}

}
