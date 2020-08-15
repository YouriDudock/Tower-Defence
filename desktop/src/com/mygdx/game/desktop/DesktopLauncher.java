package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.game.TowerDefence;

/**
 * Launches the Windows desktop client for the tower defence game
 *
 * @author Youri Dudock
 */
public class DesktopLauncher {

	private static final int WINDOW_WIDTH = 1920, WINDOW_HEIGHT = 1080;

	public static void main (String[] arg) {
		// app config
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		// static size
		config.width = WINDOW_WIDTH;
		config.height = WINDOW_HEIGHT;

		// full screen toggle
		//config.fullscreen = true;

		// start new application instance
		new LwjglApplication(new TowerDefence(), config);
	}
}
