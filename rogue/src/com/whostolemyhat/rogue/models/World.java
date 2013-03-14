package com.whostolemyhat.rogue.models;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class World {
	
	Array<Block> blocks = new Array<Block>();
	Hero hero;
	
	public World() {
		createDemoWorld();
	}
	
	public Array<Block> getBlocks() {
		return blocks;
	}
	
	public Hero getHero() {
		return hero;
	}
	
	private void createDemoWorld() {
		hero = new Hero(new Vector2(7, 2));
		
		for(int i = 0; i < 20; i++) {
			blocks.add(new Block(new Vector2(i, 0)));
			blocks.add(new Block(new Vector2(i, 13)));
		}
		for(int i = 0; i < 14; i++) {
			blocks.add(new Block(new Vector2(0, i)));
			blocks.add(new Block(new Vector2(19, i)));
		}

		blocks.add(new Block(new Vector2(16, 3)));
		blocks.add(new Block(new Vector2(16, 4)));
		blocks.add(new Block(new Vector2(16, 5)));
	}
	
}
