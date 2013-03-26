package com.whostolemyhat.rogue.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.whostolemyhat.rogue.RogueGame;
import com.whostolemyhat.rogue.controllers.HeroController;
import com.whostolemyhat.rogue.controllers.WorldRenderer;
import com.whostolemyhat.rogue.models.World;

public class GameScreen extends AbstractScreen implements InputProcessor {
	
	private World world;
	private WorldRenderer renderer;
	private HeroController controller;
	
	private int width, height;
	
	public GameScreen(RogueGame game) {
		super(game);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		controller.update(delta);
		renderer.render();
	}

	@Override
	public void show() {
		world = new World();
		renderer = new WorldRenderer(world, false);
		controller = new HeroController(world);
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public void resize(int width, int height) {
		renderer.setSize(width, height);
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}
	
	@Override
	public void dispose() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.LEFT) {
			controller.leftPressed();
		}
		if(keycode == Keys.RIGHT) {
			controller.rightPressed();
		}
		if(keycode == Keys.UP) {
			controller.upPressed();
		}
		if(keycode == Keys.DOWN) {
			controller.downPressed();
		}
		if(keycode == Keys.C) {
			controller.shootPressed();
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.LEFT) {
			controller.leftReleased();
		}
		if(keycode == Keys.RIGHT) {
			controller.rightReleased();
		}
		if(keycode == Keys.UP) {
			controller.upReleased();
		}
		if(keycode == Keys.DOWN) {
			controller.downReleased();
		}
		if(keycode == Keys.C) {
			controller.shootReleased();
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		if(x < width / 2 && y > height / 2) {
			controller.leftPressed();
		}
		if(x > width / 2 && y > height / 2) {
			controller.rightPressed();
		}
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if(x < width / 2 && y > height / 2) {
			controller.leftReleased();
		}
		if(x > width / 2 && y > height / 2) {
			controller.rightReleased();
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
