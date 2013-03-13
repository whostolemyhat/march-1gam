package com.whostolemyhat.rogue.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.whostolemyhat.rogue.RogueGame;

public class MenuScreen extends AbstractScreen {
	
	private String message = "Menus, menus, everywhere";
	private float x, y;
	
//	private static final float BUTTON_WIDTH = 300f;
//	private static final float BUTTON_HEIGHT = 60f;
//	private static final float BUTTON_SPACING = 10f;
	
	public MenuScreen(RogueGame game) {
		super(game);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
//		final float buttonX = (width - BUTTON_WIDTH) / 2;
//		float currentY = 280f;
//		
//		Label welcomeLabel = new Label("Welcome to my lovely game", getSkin());
//		welcomeLabel.setX(((width - welcomeLabel.getWidth()) / 2));
//		welcomeLabel.setY(currentY + 100);
//		stage.addActor(welcomeLabel);
//		
//		TextButton startGame = new TextButton("Start Game", getSkin());
//		startGame.setX(buttonX);
//		startGame.setY(currentY);
//		startGame.setWidth(BUTTON_WIDTH);
//		startGame.setHeight(BUTTON_HEIGHT);
//		stage.addActor(startGame);
//		
//		TextButton options = new TextButton("Options", getSkin());
//		options.setX(buttonX);
//		options.setY(currentY -= BUTTON_HEIGHT + BUTTON_SPACING);
//		options.setWidth(BUTTON_WIDTH);
//		options.setHeight(BUTTON_HEIGHT);
//		stage.addActor(options);
		
//		startGame.addListener(new InputListener() {
//			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//				System.out.println("Touched!");
//				return false;
//			}
//		});
//		

		TextBounds bounds = getFont().getBounds(message);
		x = (width - bounds.width) / 2;
		y = (height - bounds.height) / 2;
	}
	
	@Override 
	public void render(float delta) {
		super.render(delta);
		
		Gdx.gl.glClearColor(0.9f, 0.1f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		BitmapFont font = getFont();
		SpriteBatch batch = getBatch();
		
		batch.begin();
		font.draw(batch, message, x, y);
		batch.end();
	}

}
