package com.whostolemyhat.rogue.controllers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.whostolemyhat.rogue.models.Block;
import com.whostolemyhat.rogue.models.Hero;
import com.whostolemyhat.rogue.models.World;

public class WorldRenderer {
	private World world;
	private OrthographicCamera cam;
	
	private static final float CAMERA_WIDTH = 20f;
	private static final float CAMERA_HEIGHT = 14f;
	
	// debug
	ShapeRenderer debugRenderer = new ShapeRenderer();

	private SpriteBatch batch;
	private boolean debug = false;
	private int width;
	private int height;
	private float ppuX;
	private float ppuY;
	
	public WorldRenderer(World world, boolean debug) {
		this.world = world;
		// 20 units wide, 14 tall
		this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		// x,y,z
		this.cam.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
		this.cam.update();
		this.debug = debug;
		this.batch = new SpriteBatch();
	}
	
	public void setSize(int w, int h) {
		this.width = w;
		this.height = h;
		ppuX = (float)width / CAMERA_WIDTH;
		ppuY = (float)height / CAMERA_HEIGHT;
	}
	
	public void render() {
		batch.begin();
		drawBlocks();
		drawHero();
		drawEnemies();
		batch.end();
		
		if(debug) {
			drawDebug();
		}
	}
	
	private void drawBlocks() {
		for(Block block : world.getBlocks()) {
			batch.draw(
					block.texture,
					block.getPosition().x * ppuX, 
					block.getPosition().y * ppuY,
					Block.SIZE * ppuX, 
					Block.SIZE * ppuY
					);
		}
	}
	
	private void drawHero() {
		Hero hero = world.getHero();
		batch.draw(
				hero.texture, 
				hero.getPosition().x * ppuX, 
				hero.getPosition().y * ppuY, 
				Hero.SIZE * ppuX, 
				Hero.SIZE * ppuY
				);
	}
	
	private void drawEnemies() {
		for(Hero enemy : world.getEnemies()) {
			batch.draw(
					enemy.texture,
					enemy.getPosition().x * ppuX,
					enemy.getPosition().y * ppuY,
					Hero.SIZE * ppuX,
					Hero.SIZE * ppuY
					);
		}
	}
	
	private void drawDebug() {
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Rectangle);
		
		for(Block block : world.getBlocks()) {
			Rectangle rect = block.getBounds();
			float x1 = block.getPosition().x + rect.x;
			float y1 = block.getPosition().y + rect.y;
			
			debugRenderer.setColor(block.debugColour);
			debugRenderer.rect(x1, y1, rect.width, rect.height);
		}
		
		Hero hero = world.getHero();
		Rectangle rect = hero.getBounds();
		float x1 = hero.getPosition().x + rect.x;
		float y1 = hero.getPosition().y + rect.y;
		
		debugRenderer.setColor(hero.debugColour);
		debugRenderer.rect(x1, y1, rect.width, rect.height);
		debugRenderer.end();
	}
}
