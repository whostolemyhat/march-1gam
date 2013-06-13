package com.whostolemyhat.rogue.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.whostolemyhat.rogue.RogueGame;

public class Coin extends GameObject {
	
	public static final float SIZE = 0.5f;
	public static final int VALUE = 10;
	float stateTime;

	public Coin(Vector2 position) {
		super(position.x, position.y, SIZE, SIZE);
		stateTime = 0;
		this.texture = new Texture(Gdx.files.internal("images/coin.png"));
	}
	
	public void update(float delta) {
		stateTime += delta;
	}
	
//	public void draw(SpriteBatch batch, float ppuX, float ppuY) {
//		Gdx.app.log(RogueGame.LOG, "Coin draw");
//		
//		batch.draw(
//				this.texture, 
//				this.getPosition().x * ppuX, 
//				this.getPosition().y * ppuY, 
//				SIZE * ppuX, 
//				SIZE * ppuY
//				);
//	}
}
