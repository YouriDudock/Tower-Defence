package com.game.visual.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.game.entitiy.Entity;
import com.game.event.EventAction;
import com.game.visual.rendering.RenderEngine;

/**
 * The camera in our game
 *
 * @author Youri Dudock
 */
public class GameCamera {

    // an get of the underlying LibGDX camera
    private OrthographicCamera camera;

    public GameCamera() {
        camera = new OrthographicCamera();
    }

    /**
     * Updates the camera on every frame
     */
    public void update() {
        camera.update();
    }


    /**
     * Points the camera to a location
     *
     * @param location the location to focus on
     */
    public void lookAt(Vector2 location) {
        camera.position.x = location.x;
        camera.position.y = location.y;
    }

    /**
     * Points the camera to an entity
     *
     * @param entity the entity to focus on
     */
    public void lookAt(Entity entity) {
        if (entity.getLocation() != null) {
            lookAt(entity.getLocation());
        }
    }

    /**
     * Follows the changing position of an entity
     *
     * @param entity entity to follow
     */
    public void follow(final Entity entity) {
        entity.getEntityMovementEvent().register(this, new EventAction() {
            @Override
            public void trigger() {
                lookAt(entity);
            }
        });
    }

    /**
     * Stops following an entity
     *
     * @param entity entity to unfollow
     */
    public void unfollow(Entity entity) {
        entity.getEntityMovementEvent().unregister(this);
    }

    /**
     * Sets the camera to show the full screen window
     */
    public void fullscreen() {
        camera.setToOrtho(false, RenderEngine.GAME_WIDTH, RenderEngine.GAME_HEIGHT);
    }

    public OrthographicCamera get() {
        return camera;
    }

}
