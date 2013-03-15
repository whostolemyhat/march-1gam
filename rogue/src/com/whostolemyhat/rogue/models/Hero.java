package com.whostolemyhat.rogue.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Hero {
	
	public enum State {
		IDLE, WALKING, DYING
	}
	
	public static final float SPEED = 4.0f;
	public static final float SIZE = 0.5f;

	Vector2 position = new Vector2();
	Vector2 acceleration = new Vector2();
	Vector2 velocity = new Vector2();
	Rectangle bounds = new Rectangle();
	State state = State.IDLE;
	
	public Color debugColour = new Color(1, 0, 0, 1);
	public Texture texture;
	
	public Hero(Vector2 position) {
		this.position = position;
		this.bounds.height = SIZE;
		this.bounds.width = SIZE;
		this.texture = new Texture(Gdx.files.internal("images/hero_01.png"));
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
	
	public Vector2 getAcceleration() {
		return acceleration;
	}
	
	public void setState(State newState) {
		this.state = newState;
	}
	
	public void update(float delta) {
		position.add(velocity.tmp().mul(delta));
	}
}
