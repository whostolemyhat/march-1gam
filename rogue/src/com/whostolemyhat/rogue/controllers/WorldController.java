package com.whostolemyhat.rogue.controllers;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.whostolemyhat.rogue.RogueGame;
import com.whostolemyhat.rogue.models.Hero;
import com.whostolemyhat.rogue.models.Hero.State;
import com.whostolemyhat.rogue.models.World;

public class WorldController {
	enum Keys {
		LEFT, RIGHT, UP, DOWN, SHOOT
	}
	
	private World world;
	private Hero hero;
	private Hero enemy;
	
	static Map<Keys, Boolean> keys = new HashMap<WorldController.Keys, Boolean>();
	static {
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.UP, false);
		keys.put(Keys.DOWN, false);
		keys.put(Keys.SHOOT, false);
	};
	
	public WorldController(World world) {
		this.world = world;
		this.hero = world.getHero();
	}
	
	public void leftPressed() {
		keys.get(keys.put(Keys.LEFT, true));
	}
	
	public void leftReleased() {
		keys.get(keys.put(Keys.LEFT, false));
	}
	
	public void rightPressed() {
		keys.get(keys.put(Keys.RIGHT, true));
	}
	
	public void rightReleased() {
		keys.get(keys.put(Keys.RIGHT, false));
	}
	
	public void upPressed() {
		keys.get(keys.put(Keys.UP, true));
	}
	
	public void upReleased() {
		keys.get(keys.put(Keys.UP, false));
	}
	
	public void downPressed() {
		keys.get(keys.put(Keys.DOWN, true));
	}
	
	public void downReleased() {
		keys.get(keys.put(Keys.DOWN, false));
	}
	
	public void shootPressed() {
		keys.get(keys.put(Keys.SHOOT, true));
	}
	
	public void shootReleased() {
		keys.get(keys.put(Keys.SHOOT, false));
	}
	
	public void update(float delta) {
		processInput();
		hero.update(delta);
	}
	
	private void processInput() {
		if(keys.get(Keys.LEFT)) {
			hero.setState(State.WALKING);
			hero.getVelocity().x = -Hero.SPEED;
		}
		
		if(keys.get(Keys.RIGHT)) {
			hero.setState(State.WALKING);
			hero.getVelocity().x = Hero.SPEED;
		}
		
		if(keys.get(Keys.UP)) {
			hero.setState(State.WALKING);
			hero.getVelocity().y = Hero.SPEED;
		}
		
		if(keys.get(Keys.DOWN)) {
			hero.setState(State.WALKING);
			hero.getVelocity().y = -Hero.SPEED;
		}
		
		if((keys.get(Keys.LEFT) && keys.get(Keys.RIGHT)) ||
			(keys.get(Keys.UP) && keys.get(Keys.DOWN)) ||
			(!keys.get(Keys.LEFT) && !keys.get(Keys.RIGHT) && !keys.get(Keys.UP) && !keys.get(Keys.DOWN))) {
				hero.setState(State.IDLE);
				hero.getAcceleration().x = 0;
				hero.getAcceleration().y = 0;
				hero.getVelocity().x = 0;
				hero.getVelocity().y = 0;
		}
		
		if(keys.get(Keys.SHOOT)) {
			Gdx.app.log(RogueGame.LOG, "Shooting!");
		}
	}
}
