package com.game.visual.rendering;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * A render job is code that deals with rendering that gets called on every frame
 *
 * @author Youri Dudock
 */
public abstract class RenderJob {

    // get of the sprite batch
    protected SpriteBatch spriteBatch;

    public RenderJob(SpriteBatch batch) {
        this.spriteBatch = batch;
    }

    /**
     * The method that gets called every frame
     * This executes the rendering code
     */
    public abstract void render();

    /**
     *
     * @return if the job should be executed outside of the sprite batch operation
     */
    public boolean renderOutsideSpriteBatch() {
        return false;
    }
}
