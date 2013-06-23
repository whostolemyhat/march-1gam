package com.whostolemyhat.rogue.models;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class Level {
	private int width;
	private int height;
	private Block[][] blocks;
	public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
//	public ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	public ArrayList<Coin> coins = new ArrayList<Coin>();
	private Exit exit;
	
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
	
	public Level() {
		loadDemoLevel();
	}
	
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	
	public void removeEnemy(int index) {
		enemies.remove(index);
	}
	
	public Exit getExit() {
		return exit;
	}
	
	private void loadDemoLevel() {
		/**
		 * Level must contain exit
		 */
		width = 30;
		height = 14;
		blocks = new Block[width][height];
		
		enemies.add(new Enemy(new Vector2(10, 1)));
		enemies.add(new Enemy(new Vector2(7, 8)));
		
		coins.add(new Coin(new Vector2(10, 10)));
		coins.add(new Coin(new Vector2(9, 10)));
		coins.add(new Coin(new Vector2(11, 10)));
		coins.add(new Coin(new Vector2(10, 11)));
		
		for(int col = 0; col < width; col++) {
			for(int row = 0; row < height; row++) {
				blocks[col][row] = null;
			}
		}
		
		for(int col = 0; col < width; col++) {
			blocks[col][0] = new Wall(new Vector2(col, 0));
			blocks[col][13] = new Wall(new Vector2(col, 13));
		}
		for(int row = 0; row < height; row++) {
			blocks[0][row] = new Wall(new Vector2(0, row));
			blocks[29][row] = new Wall(new Vector2(29, row));
		}

		blocks[14][2] = new Block(new Vector2(14, 2));
		blocks[15][3] = new Block(new Vector2(15, 3));
		blocks[16][3] = new Block(new Vector2(16, 3));
		blocks[16][4] = new Block(new Vector2(16, 4));
		blocks[17][5] = new Block(new Vector2(17, 5));

		blocks[15][7] = new Block(new Vector2(15, 7));
		blocks[14][7] = new Block(new Vector2(14, 7));
		blocks[13][7] = new Block(new Vector2(13, 7));
		blocks[12][7] = new Block(new Vector2(12, 7));
		blocks[11][7] = new Block(new Vector2(11, 7));
		blocks[11][8] = new Block(new Vector2(11, 8));
		blocks[10][7] = new Block(new Vector2(10, 7));
		blocks[9][7] = new Block(new Vector2(9, 7));
		blocks[8][7] = new Block(new Vector2(8, 7));
		blocks[7][7] = new Block(new Vector2(7, 7));
		blocks[6][7] = new Block(new Vector2(6, 7));
		blocks[6][8] = new Block(new Vector2(6, 8));
		
		exit = new Exit(new Vector2(28, 3));
	}

	public Block get(int x, int y) {
		return blocks[x][y];
	}

}
