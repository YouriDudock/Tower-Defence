package com.game.visual.projectile;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

/**
 * The projectile pool is an object pool for our projectile
 * Projectiles are recycled for performance
 *
 * @author Youri Dudock
 */
public class ProjectilePool extends Pool<Projectile> {

    // active projectile in game
    private Array<Projectile> activeProjectiles = new Array<>();

    @Override
    protected Projectile newObject() {
        return new Projectile("test.png");
    }

    public Array<Projectile> getActiveProjectiles() {
        return activeProjectiles;
    }
}
