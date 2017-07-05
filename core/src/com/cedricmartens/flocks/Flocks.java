package com.cedricmartens.flocks;

import com.badlogic.gdx.Game;
import com.cedricmartens.flocks.screens.PlayScreen;

public class Flocks extends Game {

	private PlayScreen playScreen;
	@Override
	public void create () {
		playScreen = new PlayScreen();
		setScreen(playScreen);
	}
	
	@Override
	public void dispose () {

	}
}
