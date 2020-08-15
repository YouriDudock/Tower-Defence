package com.game.visual.projectile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.game.data.GameRepository;
import com.game.entitiy.Entity;
import com.game.event.events.projectile.ProjectileHitEvent;

import static com.game.util.VectorUtil.distance;

/**
 * A projectile is a item going from one point to another
 * Examples: Cannon ball, arrow from bow, etc
 *
 * @author Youri Dudock
 */
public class Projectile implements Pool.Poolable {

    // projectile folder location
    // currently not in use
    private final String PROJECTILE_FOLDER = "projectile/";

    // sprite of this projectile
    private Sprite sprite;

    // location of this projectile
    private Vector2 location;

    // if the projectile is live
    private boolean isLive = false;

    // target of this projectile
    private Entity target;

    // speed of the projectile
    // currently set to default
    private final int speed = 50;

    // the projectile on hit event
    private ProjectileHitEvent hitEvent;

    public Projectile(String spriteName) {
        sprite = new Sprite(new Texture(GameRepository.SPRITES_PATH + PROJECTILE_FOLDER + "/" + spriteName));
        sprite.setSize(2, 2);
    }



    /**
     * Updates the projectile
     * @TODO make model dumb
     */
    public void update() {
        if (isLive) {
            // check if projectile is at destination
            if (distance(getLocation(), target.getLocation()) < 1) {

                if (hitEvent != null) {
                    // trigger on hit event
                    hitEvent.trigger();
                }

                // reset projectile for the pool
                reset();

            } else {

            // get location of the target
            float targetX = target.getLocation().x;
            float targetY = target.getLocation().y;

            // get angle of for target
            float angle = (float) Math.atan2(targetY - location.y, targetX - location.x);

            // set new location based on cos & sin calculations, speed and delta time
            location.add(
                    (float) Math.cos(angle) * speed * Gdx.graphics.getDeltaTime(),
                    (float) Math.sin(angle) * speed * Gdx.graphics.getDeltaTime()
            );

            }
        }
    }


    public Vector2 getLocation() {
        return location;
    }

    void setLocation(Vector2 location) {
        this.location = location;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public ProjectileHitEvent getProjectileHitEvent() {
        if (hitEvent == null) {
            hitEvent = new ProjectileHitEvent();
        }

        return hitEvent;
    }


    @Override
    public void reset() {
        // free this projectile for the thread pool

        isLive = false;
        sprite = null;
        target = null;
        location = null;
        hitEvent.clear();
    }

    void setTarget(Entity target) {
        this.target = target;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive() {
        this.isLive = true;
    }


}
