package com.whostolemyhat.rogue.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Block extends GameObject {
	public static final float SIZE = 1f;
	
	public Block(Vector2 pos) {
		super(pos.x, pos.y, SIZE, SIZE);

		this.bounds.x = this.position.x;
		this.bounds.y = this.position.y;
		this.texture = new Texture(Gdx.files.internal("images/block.png"));
	}
	
//	public void draw(SpriteBatch batch) {
//		batch.draw(
//				this.texture,
//				this.getPosition().x, 
//				this.getPosition().y,
//				Block.SIZE, 
//				Block.SIZE
//				);
//	}
}
