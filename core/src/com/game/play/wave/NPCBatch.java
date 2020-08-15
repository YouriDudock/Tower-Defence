package com.game.play.wave;

/**
 * A NPC batch is a batch that contains NPCs
 */
public class NPCBatch {

    // id of the npcs in this batch
    private int NPC_ID;

    // size of this batch
    private int size;

    // time till the next batch can spawn
    private int timeTillNext;

    public NPCBatch(int id, int size) {
        this.NPC_ID = id;
        this.size = size;
    }

    public int getNPC_ID() {
        return NPC_ID;
    }

    public int getSize() {
        return size;
    }

    public int getTimeTillNext() {
        return timeTillNext;
    }

    public float getTimeTillNextInSeconds() {
        return (timeTillNext / 1000f);
    }
}
