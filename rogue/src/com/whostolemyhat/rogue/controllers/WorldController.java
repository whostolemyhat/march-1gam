package com.whostolemyhat.rogue.controllers;

import java.util.HashMap;
import java.util.Map;

import com.whostolemyhat.rogue.models.Hero;
import com.whostolemyhat.rogue.models.World;

public class WorldController {
	enum Keys {
		LEFT, RIGHT, UP, DOWN, SHOOT
	}
	
	private World world;
	private Hero hero;
	
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
}
