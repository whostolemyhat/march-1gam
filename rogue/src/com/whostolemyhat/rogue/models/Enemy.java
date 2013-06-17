package com.whostolemyhat.rogue.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.whostolemyhat.rogue.RogueGame;

public class Enemy extends Hero {

	public Color debugColour = new Color(0, 1, 0, 0);
	public int index;
	public boolean active;
	
	public enum State {
		IDLE, WALKING, ATTACKING
	}
	
	public Enemy(Vector2 position) {
		super(position);
		this.texture = new Texture(Gdx.files.internal("images/enemy.png"));
		this.active = true;
	}
	
	public void die() {
		Gdx.app.log(RogueGame.LOG, "Enemy died!");
		this.active = false;
	}

	public void hit(Hero.Direction direction, Weapon weapon) {
		// knockback
		float knockback = weapon.knockback;
		switch(direction) {
		case UP:
			this.position.y += knockback;
			break;
		case DOWN:
			this.position.y -= knockback;
			break;
		case LEFT:
			this.position.x -= knockback;
			break;
		case RIGHT:
			this.position.x += knockback;
			break;
		}
		// take damage
		int damage = weapon.damage;
		this.health -= damage;
		// check if dead
		if(health <= 0) {
			this.die();
		}
		Gdx.app.log(RogueGame.LOG, "Hit enemy!");
	}

}
