package com.whostolemyhat.rogue.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Door extends Block {
	public static float SIZE = 1.5f;
	public Color debugColour = new Color(0.3f, 0.5f, 0.5f, 1);

	public Door(Vector2 pos) {
		super(pos);
		this.texture = new Texture(Gdx.files.internal("images/block.png"));
	}

}
