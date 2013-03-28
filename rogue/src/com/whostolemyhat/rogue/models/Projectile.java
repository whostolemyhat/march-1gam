package com.whostolemyhat.rogue.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Projectile {
	
	Vector2 position = new Vector2();
	Vector2 acceleration = new Vector2();
	Vector2 velocity = new Vector2();
	Rectangle bounds = new Rectangle();
	public static final float SPEED = 7.0f;
	public final float SIZE = 0.3f;
	
	public Projectile(Vector2 position) {
		this.position = position;
		this.bounds.height = SIZE;
		this.bounds.width = SIZE;
		this.bounds.x = this.position.x;
		this.bounds.y = this.position.y;
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
		// pass
	}
}
