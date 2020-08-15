package com.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.game.data.GameRepository;
import com.game.play.PlayScreen;
import com.game.visual.camera.GameCamera;
import com.game.visual.rendering.RenderEngine;

/**
 * The main class for our game that inits the game itself
 * Also contains the most basic functionality and controlling
 *
 * @author Youri Dudock
 */
public class TowerDefence extends ApplicationAdapter {

	// our play screen
	private PlayScreen playScreen;

	// our render engine
	private RenderEngine renderEngine;

	// our game camera
	private GameCamera camera;

	@Override
	public void create () {
		// load the repository with game data
		GameRepository.get().load();

		// create the camera
		camera = new GameCamera();

		// setup the rendering engine
		renderEngine = new RenderEngine(camera);

		// start play screen
		playScreen = new PlayScreen(this);
		playScreen.show();
	}

	@Override
	public void render () {
		playScreen.render(Gdx.graphics.getDeltaTime());

		camera.update();

		renderEngine.render(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		// currently unimplemented
	}

	public RenderEngine getRenderEngine() {
		return renderEngine;
	}

	public GameCamera getCamera() {
		return camera;
	}


}
