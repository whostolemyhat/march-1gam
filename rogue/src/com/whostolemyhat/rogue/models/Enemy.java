package com.whostolemyhat.rogue.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.whostolemyhat.rogue.RogueGame;

public class Enemy extends Hero {

	public int index;
	public boolean active;
	private int score;
	
	public enum State {
		IDLE, WALKING, ATTACKING
	}
	
	public Enemy(Vector2 position) {
		super(position);
		this.texture = new Texture(Gdx.files.internal("images/enemy.png"));
		this.active = true;
		this.score = 12;
	}
	
	public void die(World world) {
		Gdx.app.log(RogueGame.LOG, "Enemy died!");
		world.score += this.score;
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
