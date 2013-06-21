package com.whostolemyhat.rogue.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Wall extends Block {
	
	public Wall(Vector2 pos) {
		super(pos);
		// TODO: already set in Block - inefficient
		this.texture =  new Texture(Gdx.files.internal("images/wall.png"));
	}

}
