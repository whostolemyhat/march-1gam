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
	private ArrayList<Exit> exit = new ArrayList<Exit>();
	private Hero hero;
	
	public Hero getHero() {
		return hero;
	}
	
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
	
	public ArrayList<Exit> getExit() {
		return exit;
	}
	
	private void loadDemoLevel() {
		/**
		 * Demo level!
		 * TODO: import from Tiled
		 */
		width = 30;
		height = 24;
		blocks = new Block[width][height];
		
		hero = new Hero(new Vector2(2, 1));
		
		enemies.add(new Enemy(new Vector2(10, 1)));
		enemies.add(new Enemy(new Vector2(7, 8)));
		
		coins.add(new Coin(new Vector2(10, 10)));
		coins.add(new Coin(new Vector2(9, 10)));
		coins.add(new Coin(new Vector2(11, 10)));
		coins.add(new Coin(new Vector2(10, 11)));
		
		coins.add(new Coin(new Vector2(4, 1)));
		coins.add(new Coin(new Vector2(5, 1)));
		coins.add(new Coin(new Vector2(6, 1)));
		
		// wall
		coins.add(new Coin(new Vector2(20, 7)));
		coins.add(new Coin(new Vector2(20, 8)));
		coins.add(new Coin(new Vector2(20, 9)));
		coins.add(new Coin(new Vector2(21, 12)));

		// stiars
		coins.add(new Coin(new Vector2(14, 4)));
		coins.add(new Coin(new Vector2(15, 5)));
		coins.add(new Coin(new Vector2(16, 6)));
		coins.add(new Coin(new Vector2(17, 7)));
		
		for(int col = 0; col < width; col++) {
			for(int row = 0; row < height; row++) {
				blocks[col][row] = null;
			}
		}
		
		// floor
		for(int col = 0; col < width; col++) {
			blocks[col][0] = new Block(new Vector2(col, 0));
//			blocks[col][13] = new Wall(new Vector2(col, 13));
		}
		for(int row = 0; row < 6; row++) {
			blocks[0][row] = new Block(new Vector2(0, row));
			blocks[29][row] = new Block(new Vector2(29, row));
		}
		
		// stairs
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

		blocks[21][1] = new Wall(new Vector2(21, 1));
		blocks[21][2] = new Wall(new Vector2(21, 2));
		blocks[21][3] = new Wall(new Vector2(21, 3));
		blocks[21][4] = new Wall(new Vector2(21, 4));
		blocks[21][5] = new Wall(new Vector2(21, 5));
		blocks[21][6] = new Wall(new Vector2(21, 6));
		blocks[21][7] = new Wall(new Vector2(21, 7));
		blocks[21][8] = new Wall(new Vector2(21, 8));
		blocks[21][9] = new Wall(new Vector2(21, 9));
		blocks[21][10] = new Wall(new Vector2(21, 10));
		
		exit.add(new Exit(new Vector2(28, 2)));
		exit.add(new Exit(new Vector2(28, 1)));
	}

	public Block get(int x, int y) {
		return blocks[x][y];
	}

}
