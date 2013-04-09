package com.whostolemyhat.rogue.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.whostolemyhat.rogue.RogueGame;
import com.whostolemyhat.rogue.models.Hero.Direction;

public class Projectile {
	
	Vector2 position = new Vector2();
	Vector2 acceleration = new Vector2();
	Vector2 velocity = new Vector2();
	Rectangle bounds = new Rectangle();
	public static final float SPEED = 15.0f;
	public final float SIZE = 0.3f;
	public Texture texture;
	
	public Projectile(Vector2 position, Direction direction) {
		this.position = position;
		switch(direction) {
		case LEFT:
			this.velocity = new Vector2(-SPEED, 0);
			break;
		case RIGHT:
			this.velocity = new Vector2(SPEED, 0);
			break;
		case UP:
			// 0,0 == bottom left
			this.velocity = new Vector2(0, SPEED);
			break;
		case DOWN:
			this.velocity = new Vector2(0, -SPEED);
			break;
		}
		this.bounds.height = SIZE;
		this.bounds.width = SIZE;
		this.bounds.x = this.position.x;
		this.bounds.y = this.position.y;
		this.texture = new Texture(Gdx.files.internal("images/projectile.png"));
		
		Gdx.app.log(RogueGame.LOG, position.toString());
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public Vector2 getVelocity() {
		return velocity;
	}
	
	public void update(float delta) {
		position.add(velocity.tmp().mul(delta));
		bounds.x = position.x;
		bounds.y = position.y;
	}
	
	public void draw(SpriteBatch batch, float ppuX, float ppuY) {
		batch.draw(
				this.texture, 
				this.getPosition().x * ppuX, 
				this.getPosition().y * ppuY, 
				this.SIZE * ppuX, 
				this.SIZE * ppuY
				);
	}
}
