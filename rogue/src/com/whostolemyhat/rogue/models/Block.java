package com.whostolemyhat.rogue.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Block {
	public static final float SIZE = 1f;
	
	Vector2 position = new Vector2();
	Rectangle bounds = new Rectangle();
	
	public Texture texture;
	public Color debugColour = new Color(0, 1, 0, 1);
	
	public Block(Vector2 pos) {
		this.position = pos;
		this.bounds.width = SIZE;
		this.bounds.height = SIZE;
		this.texture = new Texture(Gdx.files.internal("images/block.png"));
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void draw(SpriteBatch batch, float ppuX, float ppuY) {
		batch.draw(
				this.texture,
				this.getPosition().x * ppuX, 
				this.getPosition().y * ppuY,
				Block.SIZE * ppuX, 
				Block.SIZE * ppuY
				);
	}
	
}
