package com.whostolemyhat.rogue.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.whostolemyhat.rogue.RogueGame;

public class GameObject {
	public Vector2 position;
	public Rectangle bounds;
	public float SIZE;
	public Texture texture;
	
	public GameObject(float x, float y, float width, float height) {
		this.position = new Vector2(x, y);
		this.bounds = new Rectangle(x - width / 2, y - height / 2, width, height);
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
//	public void draw(SpriteBatch batch, float ppuX, float ppuY) {
//		Gdx.app.log(RogueGame.LOG, "GameObject draw");
//		
//		batch.draw(
//				texture, 
//				this.getPosition().x * ppuX, 
//				this.getPosition().y * ppuY, 
//				this.SIZE * ppuX, 
//				this.SIZE * ppuY
//				);
//	}
}