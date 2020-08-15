package com.game.player;

import com.game.event.events.player.PlayerDeathEvent;
import com.game.event.events.player.PlayerHitpointsChangedEvent;
import com.game.event.events.player.PlayerPointsChangedEvent;

/**
 * The player is the human that plays the game
 *
 * @author Youri Dudock
 */
public class Player {

    // name of the player
    private String name;

    // current collected points
    private int points;

    // current hitpoints
    private int hitpoints;

    // when the player has dead
    private PlayerDeathEvent deathEvent;

    // when the player points are changed
    private PlayerPointsChangedEvent pointsChangedEvent;

    // when the player points are changed
    private PlayerHitpointsChangedEvent hitpointsChangedEvent;

    public Player() {
        // default for testing
        hitpoints = 100;
    }

    private void kill() {
        if (deathEvent != null) {
            deathEvent.trigger();
        }
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;

        if (pointsChangedEvent != null) {
            pointsChangedEvent.trigger();
        }
    }

    public int getHitpoints() {
        return hitpoints;
    }

    /**
     * Hits the player with damage
     *
     * @param damage damage to reduct from hitpoints
     */
    public void hit(int damage) {
        this.hitpoints -= damage;

        if (hitpointsChangedEvent != null) {
            hitpointsChangedEvent.trigger();
        }

        if (damage <= 0) {
            kill();
        }
    }

    public PlayerDeathEvent getDeathEvent() {
        if (deathEvent == null) {
            deathEvent = new PlayerDeathEvent();
        }

        return deathEvent;
    }

    public PlayerPointsChangedEvent getPointsChangedEvent() {
        if (pointsChangedEvent == null) {
            pointsChangedEvent = new PlayerPointsChangedEvent();
        }

        return pointsChangedEvent;
    }


    public PlayerHitpointsChangedEvent getHitpointsChangedEvent() {
        if (hitpointsChangedEvent == null) {
            hitpointsChangedEvent = new PlayerHitpointsChangedEvent();
        }

        return hitpointsChangedEvent;
    }

}
