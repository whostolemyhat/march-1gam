package com.whostolemyhat.rogue.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
	
	private Texture heroTexture;
	private Texture blockTexture;
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
		loadTextures();
	}
	
	public void setSize(int w, int h) {
		this.width = w;
		this.height = h;
		ppuX = (float)width / CAMERA_WIDTH;
		ppuY = (float)height / CAMERA_HEIGHT;
	}
	
	private void loadTextures() {
		heroTexture = new Texture(Gdx.files.internal("images/hero_01.png"));
		blockTexture = new Texture(Gdx.files.internal("images/block.png"));
	}
	
	public void render() {
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Rectangle);
		
		for(Block block : world.getBlocks()) {
			Rectangle rect = block.getBounds();
			float x1 = block.getPosition().x + rect.x;
			float y1 = block.getPosition().y + rect.y;
			
			debugRenderer.setColor(new Color(1, 0, 0, 1));
			debugRenderer.rect(x1, y1, rect.width, rect.height);
		}
		
		Hero hero = world.getHero();
		Rectangle rect = hero.getBounds();
		float x1 = hero.getPosition().x + rect.x;
		float y1 = hero.getPosition().y + rect.y;
		
		debugRenderer.setColor(new Color(0, 1, 0, 1));
		debugRenderer.rect(x1, y1, rect.width, rect.height);
		debugRenderer.end();
		
	}
}
