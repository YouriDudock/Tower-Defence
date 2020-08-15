package com.game.entitiy.order.orders;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.game.entitiy.entities.NPCEntity;
import com.game.entitiy.entities.TowerEntity;
import com.game.entitiy.order.EntityOrder;
import com.game.event.EventAction;
import com.game.visual.projectile.Projectile;
import com.game.visual.projectile.ProjectileSpawner;
import com.game.world.GameWorld;

import static com.game.util.VectorUtil.distance;

/**
 * An order for a Tower Entity to attack NPC Entities
 *
 * @author Youri Dudock
 */
public class TowerAttackAnyNPCOrder extends EntityOrder<TowerEntity> {

    // attack task config
    private final int ATTACK_TASK_DELAY = 0, ATTACK_TASK_INTERVAL = 1;

    // instance of the world
    private GameWorld world;

    // the current target of the tower
    private NPCEntity target;

    // the projectile spawner
    private ProjectileSpawner projectileSpawner;


    public TowerAttackAnyNPCOrder(GameWorld world, ProjectileSpawner projectileSpawner) {
        this.world = world;
        this.projectileSpawner = projectileSpawner;

        // start the attack task
        startAttackTask();
    }

    @Override
    public void execute() {
        TowerEntity tower = entity;

        if (target == null || target.isDead()) {
            // find new target
            NPCEntity newTarget = findTarget();

            if (newTarget != null) {
                // set new target and notify on kill
                target = newTarget;
            }

        } else {
            // rotates tower
            tower.rotateTowards(target);
        }

    }

    /**
     * Gets called when the tower should attack
     */
    private void onAttack() {
        // check if target is still valid
        if (target == null || target.isDead()) {
            return;
        }

        TowerEntity tower = (TowerEntity)entity;

        // check if the target is out of range
        if (distance(entity.getLocation(), target.getLocation()) > tower.getRange()) {
            target = null;
            return;
        }

        // spawn a projectile from the tower to the target
        Projectile spawnedProjectile = projectileSpawner.spawn(entity, target);

        // register to damage the target when the projectile hits
        spawnedProjectile.getProjectileHitEvent().register(this, new EventAction() {
            @Override
            public void trigger() {
                if (target != null) {
                    target.hit(entity);
                }
            }
        });

    }

    /**
     * Starts the attacking task
     */
    private void startAttackTask() {
        Timer.Task attackTask = new Timer.Task() {
            @Override
            public void run() {
                onAttack();
            }
        };

        // start a timer
        Timer.schedule(attackTask, ATTACK_TASK_DELAY, ATTACK_TASK_INTERVAL);
    }

    /**
     * Finds a new target for the tower in the world
     *
     * @return a new target (might return null)
     */
    private NPCEntity findTarget() {
        TowerEntity tower = (TowerEntity)entity;

        for (NPCEntity npc : world.getNpcs()) {

            // check for invalid npcs
            if (npc.isDead() || distance(tower.getLocation(), npc.getLocation()) > tower.getRange()) {
                continue;
            }

            // return found npc
            return npc;
        }

        // nothing found
        return null;
    }



}
