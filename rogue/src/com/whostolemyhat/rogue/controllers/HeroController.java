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
		LEFT, RIGHT, JUMP, DOWN, SHOOT
	}
	
	private World world;
	private Hero hero;
	private Array<Block> collidable = new Array<Block>();
	private float COLLISION_DISTANCE = 1f;
	private boolean shootPressed = false;
	
	private boolean jumpPressed = false;
	private long jumpPressedTime;
	private static final long LONG_JUMP_PRESS = 10;
//	private static final float GRAVITY = -40f;
	private static final float MAX_JUMP_SPEED = 15f;
	private boolean grounded = false;
	
	
	static Map<Keys, Boolean> keys = new HashMap<HeroController.Keys, Boolean>();
	static {
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.JUMP, false);
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
		keys.get(keys.put(Keys.JUMP, true));
	}
	
	public void upReleased() {
		keys.get(keys.put(Keys.JUMP, false));
		jumpPressed = false;
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
		
		if(grounded && hero.getState().equals(State.JUMPING)) {
			hero.setState(State.IDLE);
		}
		
		hero.getVelocity().add(0, World.GRAVITY * delta);
		
		// update pos then check whether out of bounds
		checkCollisionWithBlocks(delta);
		checkEnemyCollision();
		checkItemCollision();

		hero.update(delta);

//		
		// TODO: lol this shouldn't be here
//		ArrayList<Enemy> newEnemies = new ArrayList<Enemy>();
//		for(Enemy e : world.getLevel().getEnemies()) {
//			if(e.active) {
//				newEnemies.add(e);
//			}
//		}
//		world.getLevel().enemies = newEnemies;
//		// TODO: lol this shouldn't be here
//		for(Enemy e : world.getLevel().getEnemies()) {
//			e.update(delta);
//		}
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
	
	private void checkEnemyCollision() {
		Rectangle heroRect = rectPool.obtain();
		// set the rectangle to bob's bounding box
		heroRect.set(
				hero.getBounds().x, 
				hero.getBounds().y, 
				hero.getBounds().width, 
				hero.getBounds().height
				);
		
		// TODO: optimise - don't loop through every enemy!
		for(Enemy enemy : world.getLevel().getEnemies()) {
			if(heroRect.overlaps(enemy.getBounds())) {
				// reset position
				if(hero.getVelocity().y < 0) {
					enemy.die();
				} else if(hero.getPosition().x < enemy.getPosition().x) {
					hero.getPosition().x -= COLLISION_DISTANCE;
					hero.getVelocity().x = 0;
					Gdx.app.log(RogueGame.LOG, "You died!");
				} else {
					hero.getPosition().x += COLLISION_DISTANCE;
					hero.getVelocity().x = 0;
					Gdx.app.log(RogueGame.LOG, "You died!");
				}
				world.getCollisonRects().add(enemy.getBounds());

				// hit an enemy, don't need to check any others
				break;
			}
		}
	}
	
	private void checkItemCollision() {
		
	}
	
	private void checkCollisionWithBlocks(float delta) {
		// scale velocity to frame units 
		hero.getVelocity().mul(delta);
		
		// Obtain the rectangle from the pool instead of instantiating it
		Rectangle heroRect = rectPool.obtain();
		// set the rectangle to bob's bounding box
		heroRect.set(
				hero.getBounds().x, 
				hero.getBounds().y, 
				hero.getBounds().width, 
				hero.getBounds().height
				);
		
		// we first check the movement on the horizontal X axis
		int startX, endX;
		int startY = (int) hero.getBounds().y;
		int endY = (int) (hero.getBounds().y + hero.getBounds().height);
		// if hero is heading left then we check if he collides with the block on his left
		// we check the block on his right otherwise
		if (hero.getVelocity().x < 0) {
			startX = endX = (int) Math.floor(hero.getBounds().x + hero.getVelocity().x);
		} else {
			startX = endX = (int) Math.floor(hero.getBounds().x + hero.getBounds().width + hero.getVelocity().x);
		}

		// get the block(s) bob can collide with
		populateCollidableBlocks(startX, startY, endX, endY);

		// simulate bob's movement on the X
		heroRect.x += hero.getVelocity().x;
		
		// clear collision boxes in world
		world.getCollisionRects().clear();
		
		// if bob collides, make his horizontal velocity 0
		for (Block block : collidable) {
			if (block == null) continue;
			if (heroRect.overlaps(block.getBounds())) {
//				if(!grounded) {
//					// Sticky / wall jump
//					hero.getVelocity().y = 0;
//					hero.setState(State.STICKY);
//				}
				hero.getVelocity().x = 0;
				world.getCollisionRects().add(block.getBounds());
				break;
			}
		}

		// reset the x position of the collision box
		heroRect.x = hero.getPosition().x;
		
		// the same thing but on the vertical Y axis
		startX = (int) hero.getBounds().x;
		endX = (int) (hero.getBounds().x + hero.getBounds().width);
		if (hero.getVelocity().y < 0) {
			startY = endY = (int) Math.floor(hero.getBounds().y + hero.getVelocity().y);
		} else {
			startY = endY = (int) Math.floor(hero.getBounds().y + hero.getBounds().height + hero.getVelocity().y);
		}
		
		populateCollidableBlocks(startX, startY, endX, endY);
		
		heroRect.y += hero.getVelocity().y;
		
		for (Block block : collidable) {
			if (block == null) continue;
			if (heroRect.overlaps(block.getBounds())) {
				if (hero.getVelocity().y < 0) {
					grounded = true;
				}
				hero.getVelocity().y = 0;
				world.getCollisionRects().add(block.getBounds());
				break;
			}
		}
		
		// here
		
		// reset the collision box's position on Y
		heroRect.y = hero.getPosition().y;

		// update position
		hero.getPosition().add(hero.getVelocity());
		hero.getBounds().x = hero.getPosition().x;
		hero.getBounds().y = hero.getPosition().y;
		
		// un-scale velocity (not in frame time)
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
	
	private boolean processInput() {
		if (keys.get(Keys.JUMP)) {
			if (!hero.getState().equals(State.JUMPING)) {
				jumpPressed = true;
				jumpPressedTime = System.currentTimeMillis();
				hero.setState(State.JUMPING);
				hero.getVelocity().y = MAX_JUMP_SPEED; 
				grounded = false;
			} else {
				if (jumpPressed && ((System.currentTimeMillis() - jumpPressedTime) >= LONG_JUMP_PRESS)) {
					jumpPressed = false;
				}
			}
		}
		
		if(keys.get(Keys.LEFT)) {
			if(!hero.getState().equals(State.JUMPING)) {
				hero.setState(State.WALKING);
			}
			hero.setDirection(Direction.LEFT);
			hero.getVelocity().x = -Hero.SPEED;
//			hero.getAcceleration().x = -ACCELERATION;
			
		} else if(keys.get(Keys.RIGHT)) {
			if(!hero.getState().equals(State.JUMPING)) {
				hero.setState(State.WALKING);
			}
			hero.setDirection(Direction.RIGHT);
			hero.getVelocity().x = Hero.SPEED;
//			hero.getAcceleration().x = ACCELERATION;
		} else {
			if(!hero.getState().equals(State.JUMPING)) {
				hero.setState(State.IDLE);
			}
//			hero.getAcceleration().x = 0;
			hero.getVelocity().x = 0;
		}

		if(shootPressed) {
			hero.attack(world);	
			shootPressed = false;
		}
		
		return false;
	}
}
