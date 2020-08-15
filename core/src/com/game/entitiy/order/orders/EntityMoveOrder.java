package com.game.entitiy.order.orders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.game.entitiy.order.EntityOrder;
import com.game.world.map.tiles.MapTile;

import java.util.Arrays;

import static com.game.util.VectorUtil.distance;


/**
 * An order to move an entity to a different location
 *
 * @author Youri Dudock
 */
public class EntityMoveOrder extends EntityOrder {

    // the map tiles to visit one-by-one
    private Array<MapTile> destinations;

    // the current destination in the map tile array
    private int currentDestination = 0;

    private final double RANGE_PRECISION = 0.3;

    public EntityMoveOrder(Array<MapTile> destinations) {
        this.destinations = destinations;
    }

    @Override
    public void execute() {
        // rotate our entity towards the location
        entity.rotateTowards(destinations.get(currentDestination));

        // get map tile to go-to
        MapTile to = destinations.get(currentDestination);

        // get the different in x & y between the current and target location
        float x = to.getLocation().x - entity.getLocation().x;
        float y = to.getLocation().y - entity.getLocation().y;

        // calculate new x and y
        float newX = (x / Math.abs(x)) * entity.getSpeed() * Gdx.graphics.getDeltaTime();
        float newY = (y / Math.abs(y)) * entity.getSpeed() * Gdx.graphics.getDeltaTime();

        // add to current location
        // check for NaN floats because 0/0 returns NaN
        entity.getLocation().add(
                Float.isNaN(newX) ? 0 : newX,
                Float.isNaN(newY) ? 0 : newY
        );


        // check if entity has arrived
        if (distance(entity.getLocation(), to.getLocation()) < RANGE_PRECISION) {

            // set entity location to rounded location
            entity.setLocation(to.getLocation().cpy());

            // check if at final destination
            if ((currentDestination + 1) == destinations.size) {
                System.out.println("Finished!");

                // order has been completed
                orderCompleted();

            } else {
                // set new destination
                currentDestination++;
            }

        }
    }


}
