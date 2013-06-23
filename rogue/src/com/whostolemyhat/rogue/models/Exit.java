package com.whostolemyhat.rogue.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Exit extends Block {

	public Exit(Vector2 pos) {
		super(pos);
		this.texture =  new Texture(Gdx.files.internal("images/exit.png"));
	}

}
