package com.whostolemyhat.rogue.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;
import com.whostolemyhat.rogue.RogueGame;
import com.whostolemyhat.rogue.models.Block;
import com.whostolemyhat.rogue.models.Coin;
import com.whostolemyhat.rogue.models.Enemy;
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
	public int width;
	public int height;
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
		world.setPpuX(ppuX);
		world.setPpuY(ppuY);
	}
	
	public void render() {
		batch.begin();
		drawBlocks();
//		drawDoors();
		drawHero();
		drawEnemies();
//		drawProjectiles();
		drawCoins();
		batch.end();
		
		
		
		if(debug) {
			drawCollisionBlocks();
//			drawDebug();
		}
	}
	
	private void drawBlocks() {
		for(Block block : world.getDrawableBlocks(width, height)) {
			block.draw(batch, ppuX, ppuY);
		}
	}
	
//	private void drawDoors() {
//		for(Door door :  world.getDoors()) {
//			door.draw(batch, ppuX, ppuY);
//		}
//	}
	
	private void drawHero() {
		Hero hero = world.getHero();
//		hero.draw(batch, ppuX, ppuY);
		batch.draw(
				hero.texture,
				hero.getPosition().x * ppuX,
				hero.getPosition().y * ppuY,
				Hero.SIZE * ppuX,
				Hero.SIZE * ppuY
				);
	}
	
	private void drawEnemies() {
		for(Enemy enemy : world.getEnemies()) {
//			enemy.draw(batch, ppuX, ppuY);
			batch.draw(
					enemy.texture,
					enemy.getPosition().x * ppuX,
					enemy.getPosition().y * ppuY,
					Enemy.SIZE * ppuX,
					Enemy.SIZE * ppuY
					);
		}
	}
	
	private void drawCoins() {
		for(Coin coin : world.getCoins()) {
			batch.draw(
					coin.texture,
					coin.getPosition().x * ppuX,
					coin.getPosition().y * ppuY,
					Coin.SIZE * ppuX,
					Coin.SIZE * ppuY
					);
			
//			coin.draw(batch, ppuX, ppuY);
		}
	}
	


//	private void drawProjectiles() {
//		for(Projectile projectile : world.getProjectiles()) {
//			projectile.draw(batch, ppuX, ppuY);
//		}
//	}
	
//	private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
//		@Override
//		protected Rectangle newObject() {
//			return new Rectangle();
//		}
//	};
	
//	private void projectileCollision(float delta) {
//		Rectangle collisionRect = rectPool.obtain();
//		for(Projectile projectile : world.getProjectiles()) {
//			projectile.getVelocity().mul(delta);
//			collisionRect.set(
//					projectile.getBounds().x, 
//					projectile.getBounds().y, 
//					projectile.getBounds().width, 
//					projectile.getBounds().height
//					);
//		}
//	}
	
	private void drawCollisionBlocks() {
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.FilledRectangle);
		debugRenderer.setColor(new Color(1,1,1,1));
		for(Rectangle rect :  world.getCollisonRects()) {
			debugRenderer.filledRect(rect.x, rect.y, rect.width, rect.height);
		}
		debugRenderer.end();
	}
	
//	private boolean inBounds(Projectile entity) {
//		return (entity.getBounds().x > 0) && (entity.getBounds().x < width + entity.getBounds().width) 
//				&& (entity.getBounds().y > 0) && (entity.getBounds().y < height + entity.getBounds().height);
//	}
	
//	private void drawDebug() {
//		debugRenderer.setProjectionMatrix(cam.combined);
//		debugRenderer.begin(ShapeType.Rectangle);
		
//		for(Block block : world.getDrawableBlocks(width, height)) {
//			Rectangle rect = block.getBounds();
//			float x1 = block.getPosition().x + rect.x;
//			float y1 = block.getPosition().y + rect.y;
//			
//			debugRenderer.setColor(block.debugColour);
//			debugRenderer.rect(x1, y1, rect.width, rect.height);
//		}
//		
//		Hero hero = world.getHero();
//		Rectangle rectHero = hero.getBounds();
//		
//		float heroX = hero.getPosition().x + rectHero.x;
//		float heroY = hero.getPosition().y + rectHero.y;
//		debugRenderer.setColor(hero.debugColour);
//		debugRenderer.rect(heroX, heroY, rectHero.width, rectHero.height);
		
//		for(Enemy enemy : world.getEnemies()) {
//			Rectangle rectEnemy = enemy.getBounds();
//			float x2 = enemy.getPosition().x + rectEnemy.x;
//			float y2 = enemy.getPosition().y + rectEnemy.y;
//			
//			debugRenderer.setColor(enemy.debugColour);
//			debugRenderer.rect(x2, y2, rectEnemy.width, rectEnemy.height);
//		}
		// end rect, call again with filledRect
//		debugRenderer.end();
		
//		for(Door door : world.getDoors()) {
//			Rectangle doorRect = door.getBounds();
//			float x = door.getPosition().x + doorRect.x;
//			float y = door.getPosition().y + doorRect.y;
//			
//			debugRenderer.begin(ShapeType.FilledRectangle);
//			debugRenderer.setColor(door.debugColour);
//			debugRenderer.filledRect(x, y, doorRect.width, doorRect.height);
//			debugRenderer.end();
//		}
		
		
//	}
}
