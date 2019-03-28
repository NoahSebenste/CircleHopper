package com.mollitiamstudios.circlehopper;

import com.mollitiamstudios.circlehopper.Screens.StartMenu;
import com.mollitiamstudios.circlehopper.Screens.UpgradesScreen;

public class Game extends com.badlogic.gdx.Game {


	public static PlayServices playServices;
	public static UpgradesScreen upgradesScreen;
	public static StartMenu startMenu;

	public Game(PlayServices playServices)
	{
		this.playServices = playServices;
	}

	public Game()
	{
		playServices = null;
	}


	@Override
	public void create () {
		upgradesScreen = new UpgradesScreen(this);
		startMenu = new StartMenu(this);

        setScreen(startMenu);
	}

	@Override
	public void render () {
		super.render();

	}
	
	@Override
	public void dispose () {


	}
}
