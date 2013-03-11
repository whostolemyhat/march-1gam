package com.whostolemyhat.rogue.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.whostolemyhat.rogue.RogueGame;

public class MenuScreen extends AbstractScreen {
	
	private String message = "Menus, menus, everywhere";
	private float x, y;
	
	public MenuScreen(RogueGame game) {
		super(game);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void resize(int width, int height) {
		TextBounds bounds = font.getBounds(message);
		x = (width - bounds.width) / 2;
		y = (height - bounds.height) / 2;
	}
	
	@Override 
	public void render(float delta) {
		super.render(delta);
		
		Gdx.gl.glClearColor(0.9f, 0.1f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		font.draw(batch, message, x, y);
		batch.end();
	}

}
