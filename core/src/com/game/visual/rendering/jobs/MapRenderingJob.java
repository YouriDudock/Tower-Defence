package com.game.visual.rendering.jobs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.game.visual.camera.GameCamera;
import com.game.visual.rendering.RenderEngine;
import com.game.visual.rendering.RenderJob;
import com.game.world.GameWorld;

/**
 * Deals with Map Rendering
 *
 * @author Youri Dudock
 */
public class MapRenderingJob extends RenderJob {

    // get of our world
    private GameWorld world;

    // get of our camera
    private GameCamera camera;

    // we are using a tiled map, so we need a tiled map rendering get
    private TiledMapRenderer tiledMapRenderer;

    public MapRenderingJob(SpriteBatch batch, GameWorld world, GameCamera camera) {
        super(batch);
        this.world = world;
        this.camera = camera;
    }

    @Override
    public void render() {
        // check if an map exists in the current world
        if (world.getMap() != null && world.getMap().getTiledMapInstance() != null) {

            // check if we are already have a map renderer
            if (tiledMapRenderer == null) {
                // createNPC an Orthogonal map renderer for our 2d game
                tiledMapRenderer = new OrthogonalTiledMapRenderer(world.getMap().getTiledMapInstance(), RenderEngine.UNIT_SCALE);
            }

            // this avoids rendering anything outside the view
            tiledMapRenderer.setView(camera.get());

            // render tiled map
            tiledMapRenderer.render();

        }

    }

    @Override
    public boolean renderOutsideSpriteBatch() {
        return true;
    }
}
