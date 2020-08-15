package com.game.visual.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.game.visual.camera.GameCamera;
import com.game.visual.projectile.ProjectileSpawner;
import com.game.visual.rendering.jobs.ProjectileRenderingJob;
import com.game.world.GameWorld;
import com.game.visual.rendering.jobs.MapRenderingJob;
import com.game.visual.rendering.jobs.WorldRenderingJob;

import java.util.ArrayList;

/**
 * The engine that deals with all the game rendering
 *
 * @author Youri Dudock
 */
public class RenderEngine {

    // the game size
    public static final int GAME_WIDTH = 80, GAME_HEIGHT = 64;

    // the unit size used (ex: 1/4, 4 pixels = 1 tile)
    public static final float UNIT_SCALE = 1/16f;

    /**
     * The registered rendering jobs that must be executed on every frame
     */
    private ArrayList<RenderJob> jobs = new ArrayList<>();

    // the sprite batch used to display sprites
    // instanced because it is being recycled on every frame
    private SpriteBatch spriteBatch;

    // an get of the game camera
    private GameCamera camera;

    // the rendering for our Scene2D UI elements
    private Stage uiStage;

    // the Scene2D ui skin of our UI items
    private Skin uiSkin;

    public RenderEngine(GameCamera camera) {
        this.camera = camera;
        spriteBatch = new SpriteBatch();

        // Scene2D
        uiStage = new Stage(new ScreenViewport());
        uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
    }

    /**
     * Starts rendering the world
     *
     * @param world get of the world
     */
    public void startWorldRendering(GameWorld world) {
        jobs.add(new WorldRenderingJob(spriteBatch, world));
    }

    /**
     * Starts rendering the map used in the world
     *
     * @param world get of the world that is the map is in
     */
    public void startMapRendering(GameWorld world) {
        jobs.add(new MapRenderingJob(spriteBatch, world, camera));
    }

    /**
     * Starts rendering the projectile in the world
     *
     * @param spawner projectile manager
     */
    public void startProjectileRendering(ProjectileSpawner spawner) { jobs.add(new ProjectileRenderingJob(spriteBatch, spawner)); }

    /**
     * Renders the game on every frame
     */
    public void render(float delta) {
        // reset the screen before rendering
        clearScreen();

        // set projection matrix for the sprite batch
        spriteBatch.setProjectionMatrix(camera.get().combined);

        // execute the jobs that work outside of the sprite batch
        jobs.stream()
                .filter(RenderJob::renderOutsideSpriteBatch)
                .forEach(RenderJob::render);

        // start the sprite batch operations
        spriteBatch.begin();

        // execute the jobs that render sprites
        jobs.stream()
                .filter(job -> !job.renderOutsideSpriteBatch())
                .forEach(RenderJob::render);

        // stop the sprite batch operations
        spriteBatch.end();

        // scene2d rendering
        uiStage.act(delta);
        uiStage.draw();
    }

    /**
     * Clears the game screen
     */
    private void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public Skin getUISkin() {
        return uiSkin;
    }

    public Stage getUIStage() {
        return uiStage;
    }
}
