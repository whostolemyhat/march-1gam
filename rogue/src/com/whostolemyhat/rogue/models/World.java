package com.whostolemyhat.rogue.models;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.whostolemyhat.rogue.RogueGame;

public class World {
	
	Array<Block> blocks = new Array<Block>();
	Hero hero;
	Array<Enemy> enemies = new Array<Enemy>();
	Array<Door> doors = new Array<Door>();
	
	Level level;
	public Array<Rectangle> collisionRects = new Array<Rectangle>();
	
	private float ppuX;
	private float ppuY;
	
	public float getPpuX() {
		return ppuX;
	}
	
	public float getPpuY() {
		return ppuY;
	}
	
	public void setPpuX(float newPpu) {
		this.ppuX = newPpu;
	}
	
	public void setPpuY(float newPpu) {
		this.ppuY = newPpu;
	}
	
	public World() {
		createDemoWorld();
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
	
	public Array<Door> getDoors() {
		return doors;
	}
	
	public Hero getHero() {
		return hero;
	}
	
	public Array<Enemy> getEnemies() {
		return enemies;
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
		hero = new Hero(new Vector2(7, 2));
//		enemies.add(new Enemy(new Vector2(10, 4)));
		level = new Level();
//		for(int i = 0; i < 20; i++) {
//			if(i != 9) {
//				blocks.add(new Block(new Vector2(i, 0)));
//				blocks.add(new Block(new Vector2(i, 13)));
//			}
//		}
//		for(int i = 0; i < 14; i++) {
//			blocks.add(new Block(new Vector2(0, i)));
//			blocks.add(new Block(new Vector2(19, i)));
//		}
//
//		blocks.add(new Block(new Vector2(16, 3)));
//		blocks.add(new Block(new Vector2(16, 4)));
//		blocks.add(new Block(new Vector2(16, 5)));
//		
//		doors.add(new Door(new Vector2(9, 0)));
//		doors.add(new Door(new Vector2(9, 13)));
	}
	
}
