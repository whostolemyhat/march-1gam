package com.whostolemyhat.rogue.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.whostolemyhat.rogue.RogueGame;
import com.whostolemyhat.rogue.models.Block;
import com.whostolemyhat.rogue.models.Enemy;
import com.whostolemyhat.rogue.models.Hero;
import com.whostolemyhat.rogue.models.Hero.Direction;
import com.whostolemyhat.rogue.models.Hero.State;
import com.whostolemyhat.rogue.models.World;

public class HeroController {
	enum Keys {
		LEFT, RIGHT, UP, DOWN, SHOOT
	}
	
	private World world;
	private Hero hero;
	private Array<Block> collidable = new Array<Block>();
	private float COLLISION_DISTANCE = 0.2f;
	private boolean shootPressed = false;
	
	static Map<Keys, Boolean> keys = new HashMap<HeroController.Keys, Boolean>();
	static {
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.UP, false);
		keys.put(Keys.DOWN, false);
		keys.put(Keys.SHOOT, false);
	};
	
	public HeroController(World world) {
		this.world = world;
		this.hero = world.getHero();
		for(Block block : world.getBlocks()) {
			collidable.add(block);
		}
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
		if(!shootPressed) {
			keys.get(keys.put(Keys.SHOOT, true));
			shootPressed = true;
		}
	}
	
	public void shootReleased() {
		keys.get(keys.put(Keys.SHOOT, false));
		shootPressed = false;
	}
	
	public void update(float delta) {
		processInput();
		checkCollisionWithBlocks(delta);
		checkCollisionWithEntities(delta);
		hero.update(delta);
		
		// TODO: lol this shouldn't be here
		ArrayList<Enemy> newEnemies = new ArrayList<Enemy>();
		for(Enemy e : world.getLevel().getEnemies()) {
			if(e.active) {
				newEnemies.add(e);
			}
		}
		world.getLevel().enemies = newEnemies;
		// TODO: lol this shouldn't be here
		for(Enemy e : world.getLevel().getEnemies()) {
			e.update(delta);
		}
		// TODO: shouldn't be in hero controller
//		for(Projectile p : world.getLevel().getProjectiles()) {
//			p.update(delta);
//		}
	} 
	
	// http://obviam.net/index.php/getting-started-in-android-game-development-with-libgdx-tutorial-part-4-collision-detection/
	private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
		@Override
		protected Rectangle newObject() {
			return new Rectangle();
		}
	};
	
	private void checkCollisionWithBlocks(float delta) {
		// velocity relative to time
		hero.getVelocity().mul(delta);
		// get rect to use for collisions
		Rectangle heroRect = rectPool.obtain();
		// set collision rect to hero pos	
		heroRect.set(
				hero.getBounds().x, 
				hero.getBounds().y, 
				hero.getBounds().width, 
				hero.getBounds().height
				);
		
		int startX, endX;
		int startY = (int)hero.getBounds().y;
		int endY = (int)(hero.getBounds().y + hero.getBounds().height);
		// going left
		if(hero.getVelocity().x < 0) {
			startX = endX = (int)Math.floor(hero.getBounds().x + hero.getVelocity().x);
		} else {
			// going right
			startX = endX = (int)Math.floor(hero.getBounds().x + hero.getBounds().width + hero.getVelocity().x);
		}
		populateCollidableBlocks(startX, startY, endX, endY);
		
		heroRect.x += hero.getVelocity().x;
		world.getCollisonRects().clear();
		for(Block block : collidable) {
			if(block != null) {
				if(heroRect.overlaps(block.getBounds())) {
					hero.getVelocity().x = 0; // if collided, movement = 0
//					if(hero.getPosition().x < block.getPosition().x) {
//						hero.getPosition().x -= COLLISION_DISTANCE;
//					} else {
//						hero.getPosition().x += COLLISION_DISTANCE;
//					}
					world.getCollisonRects().add(block.getBounds());
					break;
				}
			}
		}
		// reset bounding box rect to hero position
		heroRect.x = hero.getPosition().x;
		startX = (int)hero.getBounds().x;
		endX = (int)(hero.getBounds().x + hero.getBounds().width);
	
		if(hero.getVelocity().y < 0) {
			// if going down
			startY = endY = (int)Math.floor(hero.getBounds().y + hero.getVelocity().y);
		} else {
			startY = endY = (int)Math.floor(hero.getBounds().y + hero.getBounds().height + hero.getVelocity().y);
		}
		populateCollidableBlocks(startX, startY, endX, endY);
		heroRect.y += hero.getVelocity().y;
		for(Block block : collidable) {
			if(block != null) {
				if(heroRect.overlaps(block.getBounds())) {
					hero.getVelocity().y = 0;
					// reset pos
//					if(hero.getPosition().y < block.getPosition().y) {
//						hero.getPosition().y -= COLLISION_DISTANCE;
//					} else {
//						hero.getPosition().y += COLLISION_DISTANCE;
//					}
					world.getCollisonRects().add(block.getBounds());
					break;
				}
			}
		}
		heroRect.y = hero.getPosition().y;
		hero.getPosition().add(hero.getVelocity());
		hero.getBounds().x = hero.getPosition().x;
		hero.getBounds().y = hero.getPosition().y;
		
		// transform velocity back into base units
		hero.getVelocity().mul(1 / delta);	
	}
	
	private void populateCollidableBlocks(int startX, int startY, int endX, int endY) {
		collidable.clear();
		for(int x = startX; x <= endX; x++) {
			for(int y = startY; y <= endY; y++) {
				if(x >= 0 && x < world.getLevel().getWidth() && y >=0 && y <= world.getLevel().getHeight()) {
					collidable.add(world.getLevel().get(x, y));
				}
			}
		}
	}
	
	private void checkCollisionWithEntities(float delta) {
		// TODO: narrow to with x blocks
		hero.getVelocity().mul(delta);
		Rectangle heroRect = rectPool.obtain();
		heroRect.set(
				hero.getBounds().x, 
				hero.getBounds().y, 
				hero.getBounds().width, 
				hero.getBounds().height
				);
		for(Enemy enemy : world.getLevel().getEnemies()) {
			heroRect.x += hero.getVelocity().x;
			heroRect.y += hero.getVelocity().y;
			if(heroRect.overlaps(enemy.getBounds())) {
				hero.getVelocity().x = hero.getVelocity().y = 0;
				// reset position
				if(hero.getPosition().x < enemy.getPosition().x) {
					hero.getPosition().x -= COLLISION_DISTANCE;
				} else {
					hero.getPosition().x += COLLISION_DISTANCE;
				}
				if(hero.getPosition().y < enemy.getPosition().y) {
					hero.getPosition().y -= COLLISION_DISTANCE;
				} else {
					hero.getPosition().y += COLLISION_DISTANCE;
				}
				world.getCollisonRects().add(enemy.getBounds());
				
				Gdx.app.log(RogueGame.LOG, "You died!");
				// hit any enemy, don't need to check any others
				break;
			}
		}
		heroRect.y = hero.getPosition().y;
		hero.getPosition().add(hero.getVelocity());
		hero.getBounds().x = hero.getPosition().x;
		hero.getBounds().y = hero.getPosition().y;
		hero.getVelocity().mul(1 / delta);
	}
	
	private void processInput() {
		if(keys.get(Keys.LEFT)) {
			hero.setState(State.WALKING);
			hero.setDirection(Direction.LEFT);
			hero.getVelocity().x = -Hero.SPEED;
		}
		
		if(keys.get(Keys.RIGHT)) {
			hero.setState(State.WALKING);
			hero.setDirection(Direction.RIGHT);
			hero.getVelocity().x = Hero.SPEED;
		}
		
		if(keys.get(Keys.UP)) {
			hero.setState(State.WALKING);
			hero.setDirection(Direction.UP);
			hero.getVelocity().y = Hero.SPEED;
		}
		
		if(keys.get(Keys.DOWN)) {
			hero.setState(State.WALKING);
			hero.setDirection(Direction.DOWN);
			hero.getVelocity().y = -Hero.SPEED;
		}
		
		if((keys.get(Keys.LEFT) && keys.get(Keys.RIGHT)) ||
			(keys.get(Keys.UP) && keys.get(Keys.DOWN)) ||
			(!keys.get(Keys.LEFT) && !keys.get(Keys.RIGHT) && !keys.get(Keys.UP) && !keys.get(Keys.DOWN))) {
				hero.setState(State.IDLE);
				hero.getAcceleration().x = 0;
				hero.getAcceleration().y = 0;
				hero.getVelocity().x = 0;
				hero.getVelocity().y = 0;
		}
		
		if(!keys.get(Keys.LEFT) && !keys.get(Keys.RIGHT)) {
			hero.getAcceleration().x = 0;
			hero.getVelocity().x = 0;
		}
		
		if(!keys.get(Keys.UP) && !keys.get(Keys.DOWN)) {
			hero.getAcceleration().y = 0;
			hero.getVelocity().y = 0;
		}
		
//		if(keys.get(Keys.SHOOT)) {
		if(shootPressed) {
			hero.attack(world);	
			shootPressed = false;
		}
		
	}
}
