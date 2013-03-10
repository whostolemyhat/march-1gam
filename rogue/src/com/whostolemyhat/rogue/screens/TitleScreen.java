package com.whostolemyhat.rogue.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.whostolemyhat.rogue.RogueGame;

public class TitleScreen extends AbstractScreen {
	private Texture splashTexture;
	private TextureRegion splashTextureRegion;
	
	public TitleScreen(RogueGame game) {
		super(game);
	}
	
	@Override
	public void show() {
		super.show();
		
		splashTexture = new Texture("splash.png");
		// filter improves stretching
		splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		// img dimension = 512x301
		splashTextureRegion = new TextureRegion(splashTexture, 0, 0, 512, 301);
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
		batch.begin();
		batch.draw(splashTextureRegion, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		
		splashTexture.dispose();
	}
}
