package com.whostolemyhat.rogue.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
//import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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
		
		splashTexture = new Texture("stars.png");
		// filter improves stretching
		splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		// atlas - splash img begins at 0,0
		// img dimension = 512x301
		splashTextureRegion = new TextureRegion(splashTexture, 0, 0, 512, 512);
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		
		stage.clear();
		TextureRegion splashRegion =  new TextureRegion(splashTexture, 0, 0, 512, 512);
		Image splashImage = new Image(splashRegion);
		Color c = splashImage.getColor();
		splashImage.setColor(c.r, c.g, c.b, 0);
		
		splashImage.addAction(sequence(fadeIn(0.5f), delay(1.75f), fadeOut(0.75f),
				new Action() {
			@Override
			public boolean act(float delta) {
//				game.setScreen(new MenuScreen(game));
				game.setScreen(new GameScreen(game));
				return true;
			}
		}));
		
		stage.addActor(splashImage);
	}
	
//	@Override
//	public void render(float delta) {
//		super.render(delta);
//		
//		batch.begin();
//		batch.draw(splashTextureRegion, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		batch.end();
//	}
	
	@Override
	public void dispose() {
		super.dispose();
		
		splashTexture.dispose();
	}
}
