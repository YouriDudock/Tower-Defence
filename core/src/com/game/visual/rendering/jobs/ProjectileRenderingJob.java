package com.game.visual.rendering.jobs;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.visual.projectile.Projectile;
import com.game.visual.projectile.ProjectileSpawner;
import com.game.visual.rendering.RenderJob;

/**
 * Deals with projectile rendering
 *
 * @author Youri Dudock
 */
public class ProjectileRenderingJob extends RenderJob {

    // instance to the projectile spawner
    private ProjectileSpawner spawner;

    public ProjectileRenderingJob(SpriteBatch batch, ProjectileSpawner spawner) {
        super(batch);
        this.spawner = spawner;
    }

    @Override
    public void render() {
        // get all active projectile
        for (Projectile projectile : spawner.getPool().getActiveProjectiles()) {

            // update the projectile
            projectile.update();

            // check if the projectile is still live
            if (!projectile.isLive()) {
                continue;
            }

            // get the projectile sprite
            Sprite sprite = projectile.getSprite();

            // set new sprite pos
            sprite.setPosition(projectile.getLocation().x, projectile.getLocation().y);

            // draw projectile
            projectile.getSprite().draw(spriteBatch);
        }
    }

}
