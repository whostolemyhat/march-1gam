package com.whostolemyhat.rogue.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.whostolemyhat.rogue.models.Block;
import com.whostolemyhat.rogue.models.Coin;
import com.whostolemyhat.rogue.models.Enemy;
import com.whostolemyhat.rogue.models.Hero;
import com.whostolemyhat.rogue.models.World;

public class WorldRenderer {
	private World world;
	private OrthographicCamera cam;
	private Hero hero;
	
	private static final float CAMERA_WIDTH = 20f;
	private static final float CAMERA_HEIGHT = 14f;
	
	// debug
	ShapeRenderer debugRenderer = new ShapeRenderer();

	private SpriteBatch batch;
	private boolean debug = false;
	public int width;
	public int height;
//	private float ppuX;
//	private float ppuY;
	
	public WorldRenderer(World world, boolean debug) {
		this.world = world;
		this.hero = world.getHero();
		
		this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
//		this.cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		// x,y,z
//		this.cam.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
		this.cam.setToOrtho(false, CAMERA_WIDTH, CAMERA_HEIGHT);
		this.cam.position.set(hero.getPosition().x, CAMERA_HEIGHT / 2, 0);
		this.cam.update();
		
		this.debug = debug;
		this.batch = new SpriteBatch();
	}
	
	public void setSize(int w, int h) {
		this.width = w;
		this.height = h;
//		ppuX = (float)width / CAMERA_WIDTH;
//		ppuY = (float)height / CAMERA_HEIGHT;
//		world.setPpuX(ppuX);
//		world.setPpuY(ppuY);
	}
	
	public void render() {
		cam.position.set(hero.getPosition().x, CAMERA_HEIGHT / 2, 0);
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		
		batch.begin();
		drawBlocks();
		drawHero();
		drawEnemies();
		drawCoins();
		batch.end();

		if(debug) {
			drawCollisionBlocks();
		}
	}
	
	private void drawBlocks() {
		for(Block block : world.getDrawableBlocks(width, height)) {
//			block.draw(batch, ppuX, ppuY);
			block.draw(batch);
		}
	}
	
	private void drawHero() {
		cam.position.set(hero.getPosition().x, hero.getPosition().y, 0);
		
//		batch.draw(
//				hero.texture,
//				hero.getPosition().x * ppuX,
//				hero.getPosition().y * ppuY,
//				Hero.SIZE * ppuX,
//				Hero.SIZE * ppuY
//				);
		batch.draw(
				hero.texture,
				hero.getPosition().x,
				hero.getPosition().y,
				Hero.SIZE,
				Hero.SIZE
				);
	}
	
	private void drawEnemies() {
		for(Enemy enemy : world.getLevel().getEnemies()) {
			batch.draw(
					enemy.texture,
					enemy.getPosition().x,
					enemy.getPosition().y,
					Enemy.SIZE,
					Enemy.SIZE
					);
		}

	}
	
	private void drawCoins() {
		for(Coin coin : world.getCoins()) {
			batch.draw(
					coin.texture,
					coin.getPosition().x,
					coin.getPosition().y,
					Coin.SIZE,
					Coin.SIZE
					);
		}
	}

	private void drawCollisionBlocks() {
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.FilledRectangle);
		debugRenderer.setColor(new Color(1,1,1,1));
		for(Rectangle rect :  world.getCollisonRects()) {
			debugRenderer.filledRect(rect.x, rect.y, rect.width, rect.height);
		}
		debugRenderer.end();
	}

}
