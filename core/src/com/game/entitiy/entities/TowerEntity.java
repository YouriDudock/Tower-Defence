package com.game.entitiy.entities;

import com.game.entitiy.Entity;

/**
 * A defence tower that the player can place to protect from NPCEntities
 *
 * @author Youri Dudock
 *
 */
public class TowerEntity extends Entity {

    // the price of an tower
    private int price;

    // the range of a tower
    private int range;


    public TowerEntity() {
    }

    @Override
    public String getSpriteFolderName() {
        return "towers";
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
