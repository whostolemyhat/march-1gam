package com.whostolemyhat.rogue.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends Hero {

	public Enemy(Vector2 position) {
		super(position);
		this.texture = new Texture(Gdx.files.internal("images/hero_01.png"));
	}

}
