package com.whostolemyhat.rogue.models;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class World {
	
	public interface WorldListener {
		public void enemy();
		public void hit();
		public void coin();
		public void exit();
	}

	Hero hero;
	Level level;
	public final WorldListener listener;
	public int score;
	
	public static final float GRAVITY = -40f;
	public Array<Rectangle> collisionRects = new Array<Rectangle>();

	public World(WorldListener listener) {
		createDemoWorld();
		this.hero = this.getHero();
		this.listener = listener;
		score = 0;
	}
	
	public void update(float delta) {
		updateHero(delta);
		updateEnemies(delta);
		updateCoins(delta);
	}
	
	private void updateHero(float delta) {
		hero.update(delta);
	}
	
	// TODO: optimise to on-screen enemies
	private void updateEnemies(float delta) {
		for(Enemy enemy : getEnemies()) {
			enemy.update(delta);
		}
	}
	
	// TODO: optimise for on-screen 
	private void updateCoins(float delta) {
		for(Coin coin : getCoins()) {
			coin.update(delta);
		}
	}

	public ArrayList<Block> getBlocks() {
		Block block;
		ArrayList<Block> blocks = new ArrayList<Block>();
		
		for(int col = 0; col <= level.getWidth() - 1; col++) {
			for(int row = 0; row < level.getHeight() - 1; row++) {
				block = level.getBlocks()[col][row];
				if(block != null) {
					blocks.add(block);
				}
			}
		}
		
		return blocks;
	}
	
	public Array<Rectangle> getCollisionRects() {
		return collisionRects;
	}
	
	public Hero getHero() {
		return getLevel().getHero();
	}
	
	public ArrayList<Exit> getExit() {
		return getLevel().getExit();
	}
	
	public ArrayList<Enemy> getEnemies() {
		return getLevel().getEnemies();
	}
	
	public ArrayList<Coin> getCoins() {
		return getLevel().coins;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public Array<Rectangle> getCollisonRects() {
		return collisionRects;
	}
	
	public List<Block> getDrawableBlocks(int width, int height) {
		int x = (int)hero.getPosition().x - width;
		int y = (int)hero.getPosition().y - height;
		
		if(x < 0) {
			x = 0;
		}
		if(y < 0) {
			y = 0;
		}
		int x2 = x + 2 * width;
		int y2 = y + 2 * height;
		if(x2 > level.getWidth()) {
			x2 = level.getWidth() - 1;
		}
		if(y2 > level.getHeight()) {
			y2 = level.getHeight() - 1;
		}
		
		List<Block> blocks = new ArrayList<Block>();
		Block block;
		for(int col = 0; col <= x2; col++) {
			for(int row = 0; row <= y2; row++) {
				block = level.getBlocks()[col][row];
				if(block != null) {
					blocks.add(block);
				}
			}
		}
		return blocks;
	}
	
	private void createDemoWorld() {
//		hero = new Hero(new Vector2(7, 2));
		level = new Level();
	}
	
}
