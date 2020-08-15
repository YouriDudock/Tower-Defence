package com.game.play;

import com.badlogic.gdx.Screen;
import com.game.TowerDefence;
import com.game.visual.camera.GameCamera;
import com.game.visual.rendering.RenderEngine;
import com.game.visual.scene2d.hud.GameHud;

/**
 * The playscreen in our game
 *
 * @author Youri Dudock
 */
public class PlayScreen implements Screen {

    // instance to the game manager
    private GameManager gameManager;

    private GameHud gameHud;

    // instance to the game main class
    private TowerDefence game;

    public PlayScreen(TowerDefence game) {
        this.game = game;
    }

    @Override
    public void show() {
        // start a new game manager
        this.gameManager = new GameManager(this);
        gameManager.start();

        gameHud = new GameHud(gameManager);
        gameHud.create();

    }

    @Override
    public void render(float delta) {
        gameManager.update();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public TowerDefence getGame() {
        return game;
    }

    public GameCamera getCamera() {
        return this.game.getCamera();
    }

    public GameManager getGameManager() {
        return this.gameManager;
    }

    public RenderEngine getRenderEngine() {
        return this.game.getRenderEngine();
    }
}
