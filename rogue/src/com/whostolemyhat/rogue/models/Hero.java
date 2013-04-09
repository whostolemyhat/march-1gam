package com.whostolemyhat.rogue.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.whostolemyhat.rogue.RogueGame;

public class Hero {
	
	public enum State {
		IDLE, WALKING, DYING
	}
	
	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}
	
	public static final float SPEED = 3.0f;
	public final float SIZE = 0.5f;

	Vector2 position = new Vector2();
	Vector2 acceleration = new Vector2();
	Vector2 velocity = new Vector2();
	Rectangle bounds = new Rectangle();
	State state = State.IDLE;
	// direction of bullets
	public Direction direction = Direction.LEFT;
	int health = 6; // 1 health = 1/2 heart
	
	public Color debugColour = new Color(1, 0, 0, 1);
	public Texture texture;
	
	public Hero(Vector2 position) {
		this.position = position;
		this.bounds.height = SIZE;
		this.bounds.width = SIZE;
		this.bounds.x = this.position.x;
		this.bounds.y = this.position.y;
		this.texture = new Texture(Gdx.files.internal("images/hero_01.png"));
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public Vector2 getVelocity() {
		return velocity;
	}
	
	public Vector2 getAcceleration() {
		return acceleration;
	}
	
	public void setState(State newState) {
		this.state = newState;
	}
	
	public void setDirection(Direction newDirection) {
		this.direction = newDirection;
	}
	
	public void update(float delta) {
		position.add(velocity.tmp().mul(delta));
		bounds.x = position.x;
		bounds.y = position.y;
	}
	
	private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
		@Override
		protected Rectangle newObject() {
			return new Rectangle();
		}
	};
	
	// should be in controller!
	public void attack(World world) {
//		world.getLevel().projectiles.add(new Projectile(
//				new Vector2(this.getPosition().x, this.getPosition().y), 
//				this.direction));
		// Draw box in direction facing
		Rectangle weaponRect = rectPool.obtain();
		weaponRect.set(
				this.getBounds().x, 
				this.getBounds().y, 
				this.getBounds().width, 
				this.getBounds().height
				);
		// change x/y based on direction
		switch(this.direction) {
		case UP:
			weaponRect.y += this.SIZE;
			break;
		case DOWN:
			weaponRect.y -= this.SIZE;
			break;
		case LEFT:
			weaponRect.x -= this.SIZE;
			break;
		case RIGHT:
			weaponRect.x += this.SIZE;
			break;
		default:
			break;
		}
		// check collision
		for(Enemy enemy : world.getLevel().getEnemies()) {
			weaponRect.x += this.getVelocity().x;
			weaponRect.y += this.getVelocity().y;
			if(weaponRect.overlaps(enemy.getBounds())) {
				world.getCollisonRects().add(enemy.getBounds());
				
				Gdx.app.log(RogueGame.LOG, "Hit enemy!");
			}
		}
		// deal damage
	}
	
	public void draw(SpriteBatch batch, float ppuX, float ppuY) {
		batch.draw(
				this.texture, 
				this.getPosition().x * ppuX, 
				this.getPosition().y * ppuY, 
				this.SIZE * ppuX, 
				this.SIZE * ppuY
				);
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int newHealth) {
		this.health = newHealth;
	}
}
