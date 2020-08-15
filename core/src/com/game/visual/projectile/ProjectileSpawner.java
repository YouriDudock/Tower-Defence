package com.game.visual.projectile;

import com.game.entitiy.Entity;
import com.game.event.EventAction;
import com.game.event.EventActionPriority;

/**
 * Spawns projectile in game
 *
 * @author Youri Dudock
 */
public class ProjectileSpawner {

    // the projectile pool
    private ProjectilePool pool;

    /**
     * Spawns a new projectile in game
     *
     * @param attacker entity that shoots the projectile
     * @param target entity that is the projectile its target
     *
     * @return the created projectile
     */
    public Projectile spawn(Entity attacker, Entity target) {
        // obtain a new projectile from the pool
        Projectile projectile = pool.obtain();

        // set projectile target
        projectile.setTarget(target);

        // set projectile location
        projectile.setLocation(attacker.getLocation().cpy());

        // register to the on hit event for clean up
        // low priority because we want this to be called as last
        projectile.getProjectileHitEvent().register(this, new EventAction() {
            @Override
            public void trigger() {
                pool.getActiveProjectiles().removeValue(projectile, true);
            }

            @Override
            public EventActionPriority getPriority() {
                return EventActionPriority.LOW;
            }
        });

        // set the projectile status to live
        projectile.setLive();

        // add to pool of active projectile
        pool.getActiveProjectiles().add(projectile);

        return projectile;
    }

    public ProjectileSpawner() {
        pool = new ProjectilePool();
    }

    public ProjectilePool getPool() {
        return pool;
    }

}
