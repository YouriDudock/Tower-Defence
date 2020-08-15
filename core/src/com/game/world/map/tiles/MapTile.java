package com.game.world.map.tiles;

import com.badlogic.gdx.math.Vector2;

/**
 * A tile on our tiled map
 *
 * @author Youri Dudock
 */
public class MapTile {

    // correct converted vector2 location of the map tile in our game
    // only init when requested, lazy loading
    private Vector2 location;

    // non converted x, y from data storage
    private float x, y;

    public MapTile() {

    }

    public Vector2 getLocation() {
        // check if location already exist, lazy loading
        if (location == null) {
            // create location object
            location = new Vector2(x, y);
        }

        return location;
    }

    public void setLocation(Vector2 location) {
        this.location = location;
    }

    public float getX() {
        return this.x;
    }

    @Override
    public String toString() {
        return getLocation().toString();
    }

}
