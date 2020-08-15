package com.game.util;

import com.badlogic.gdx.math.Vector2;

/**
 * Provides vector related functionality
 *
 * @author Youri Dudock
 */
public class VectorUtil {

    /**
     * Distance between two locations
     *
     * @param location the first location
     * @param otherLocation the second location
     *
     * @return the distance as one single double
     */
    public static double distance(Vector2 location, Vector2 otherLocation) {
        return Math.sqrt(Math.pow((otherLocation.x - location.x), 2) + Math.pow((otherLocation.y - location.y), 2));
    }

    /**
     * Gets the angle from one point towards another
     *
     * @param from
     * @param target
     *
     * @return the angle in degrees
     */
    public static float degree(Vector2 from, Vector2 target) {
        return from.cpy().sub(target).angle();
    }

}
