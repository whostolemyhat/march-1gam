package com.whostolemyhat.rogue.models;

import com.badlogic.gdx.math.Vector2;

public class Level {
	private int width;
	private int height;
	private Block[][] blocks;
	private Door[][] doors;
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}

	public Block[][] getBlocks() {
		return blocks;
	}
	
	public void setBlocks(Block[][] blocks) {
		this.blocks = blocks;
	}
	
	public Door[][] getDoors() {
		return doors;
	}
	
	public void setDoors(Door[][] doors) {
		this.doors = doors;
	}
	
	public Level() {
		loadDemoLevel();
	}
	
	private void loadDemoLevel() {
		width = 20;
		height = 14;
		blocks = new Block[width][height];
		doors = new Door[width][height];
		for(int col = 0; col < width; col++) {
			for(int row = 0; row < height; row++) {
				blocks[col][row] = null;
			}
		}
		
		for(int col = 0; col < width; col++) {
			for(int row = 0; row < height; row++) {
				doors[col][row] = null;
			}
		}
		
		for(int col = 0; col < width; col++) {
			if(col != 9) {
				blocks[col][0] = new Block(new Vector2(col, 0));
				blocks[col][13] = new Block(new Vector2(col, 13));
			}
		}
		for(int row = 0; row < height; row++) {
			blocks[0][row] = new Block(new Vector2(0, row));
			blocks[19][row] = new Block(new Vector2(19, row));
		}
	
		blocks[16][3] = new Block(new Vector2(16, 3));
		blocks[16][4] = new Block(new Vector2(16, 4));
		blocks[16][5] = new Block(new Vector2(16, 5));
		
		doors[9][0] = new Door(new Vector2(9, 0));
		doors[9][13] = new Door(new Vector2(9, 13));
	}

	public Block get(int x, int y) {
		return blocks[x][y];
	}

}
