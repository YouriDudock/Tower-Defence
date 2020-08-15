package com.game.play.wave;

import com.game.entitiy.entities.NPCEntity;
import com.google.gson.annotations.Expose;

/**
 * A wave is a wave of entities that spawn every x amount of time
 * A wave is made up of batches
 *
 * @author Youri Dudock
 */
public class NPCWave {

    // the wave ID
    private int wave;

    // the batches in this wave
    private NPCBatch[] npcBatches;

    public NPCWave(int wave, NPCBatch[] batch) {
        this.wave = wave;
        this.npcBatches = batch;
    }

    public NPCBatch[] getNPCBatches() {
        return npcBatches;
    }

    public int getWave() {
        return wave;
    }



}
