package com.mollitiamstudios.circlehopper.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mollitiamstudios.circlehopper.Game;
import com.mollitiamstudios.circlehopper.PlayServices;


public class DesktopLauncher  {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 720;
		config.height = 1280;
		new LwjglApplication(new Game(), config);
	}
}


