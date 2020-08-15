package com.game.entitiy.entities;

import com.game.entitiy.Entity;

/**
 * A NPC (NON-PLAYER-CHARACTER) that acts as the enemy in our game
 *
 * @author Youri Dudock
 */
public class NPCEntity extends Entity {

    // the hitpoints of this NPC
    private int hitpoints;

    // the value when this NPC is killed
    private int killValue;

    public NPCEntity() {

    }

    @Override
    public String getSpriteFolderName() {
        return "npcs";
    }


    /**
     * Hits this Entity
     *
     * @param attacker the entity that hits this entity
     */
    public void hit(Entity attacker) {
        // reduct hitpoints based on attacker damage
        hitpoints -= attacker.getDamage();

        // if hitpoints below 0, then die
        if (hitpoints <= 0) {
            this.kill();
        }
    }



    public int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    public int getKillValue() {
        return killValue;
    }

    public void setKillValue(int killValue) {
        this.killValue = killValue;
    }

}
