package com.game.play.wave;

import com.badlogic.gdx.utils.Timer;
import com.game.data.GameRepository;
import com.game.entitiy.entities.NPCEntity;
import com.game.entitiy.life.EntitySpawner;
import com.game.event.EventAction;
import com.game.event.events.wave.WaveFinishedEvent;
import com.game.play.GameManager;
import com.game.entitiy.order.orders.EntityMoveOrder;

/**
 * A wave manager is a manager for the life cycle of the NPC waves
 *
 * @TODO Split a lot of things up in (like the tasks and orders)
 *
 * @author Youri Dudock
 */
public class WaveManager {

    // current wave that is active
    private NPCWave currentWave;

    // if the current wave is busy spawning
    private boolean isRunning;

    // instance back to our game manager
    private GameManager gameManager;

    // all the waves in the game
    private NPCWave[] waves;

    // a spawner for our entities
    private EntitySpawner spawner;

    // event for when a wave is finished
    private WaveFinishedEvent finishedEvent;



    public WaveManager(GameManager gameManager) {
        this.isRunning = false;
        this.gameManager = gameManager;
        this.spawner = new EntitySpawner(gameManager.getWorld());
    }

    /**
     * Loads the waves from the data storage
     */
    public void loadWaves() {
        waves = GameRepository.get().getNPCWavesAsArray();

       if (waves.length == 0) {
            System.out.println("No waves have been loaded.");
       }
    }


    /**
     * Starts the next wave
     */
    public void startWave() {
        if (waves.length == 0) {
            System.out.println("There are no waves.");
            return;
        }

        // set running true
        isRunning = true;

        if (currentWave == null) {
            // get the first wave in the array as starting wave (first round)
            currentWave = waves[0];
        } else {
            // set the next wave
            currentWave = waves[(currentWave.getWave() + 1) - 1];
        }

        // get the NPC batches
        NPCBatch[] batches = currentWave.getNPCBatches();

        // generate the NPC spawn tasks based on the batches
        generateNPCBatchSpawnTimers(batches);
    }


    /**
     * Creates and starts the timers for the NPC spawning in the NPC batches
     *
     * @param batches the npc batches that must spawn
     */
    private void generateNPCBatchSpawnTimers(NPCBatch[] batches) {
        // delay time for the timers
        // used for having a delay between each batch and
        // the delay between NPCS spawning in one row
        float delayTime = 0;

        // loop through all batches in this wave
        for (NPCBatch batch : batches) {
            // get batch size
            int size = batch.getSize();

            // loop amount equal to the size
            for (int i = 0; i < size; i++) {
                // create entity before the spawning task has been set
                // this is for performance
                NPCEntity npcToSpawn = spawner.getFactory().createNPC(batch.getNPC_ID());

                // schedule task for entity spawning
                Timer.instance().scheduleTask(getNPCSpawnTimerTask(npcToSpawn), delayTime);

                // add delay between NPC spawn in the same batch
                // this is to get some distance in a row of npcs
                delayTime += 1f;
            }

            // add delay for the next batch
            delayTime += batch.getTimeTillNextInSeconds();
        }
    }

    /**
     * Creates a NPC spawn task
     *
     * @param entity entity to spawn
     *
     * @return a spawn task
     */
    private Timer.Task getNPCSpawnTimerTask(NPCEntity entity) {
        return new Timer.Task() {
            @Override
            public void run() {
                // create a movement order based on the first tile of the map
                EntityMoveOrder order = new EntityMoveOrder(gameManager.getWorld().getMap().getWalkableTiles());

                // register when the entity reached the end
                order.getOrderFinishedEvent().register(this, gameManager.getNPCReachedEndActionEvent(entity));

                // set the order
                entity.setOrder(order);

                // set the death event
                entity.getDeathEvent().register(gameManager, gameManager.getNPCDeathActionEvent(entity));

                // spawn the entity in the world
                spawner.spawn(entity, gameManager.getWorld().getMap().getBeginTile());
            }
        };
    }


    /**
     *
     * @return the event for when a wave has finished
     */
    public WaveFinishedEvent getFinishedEvent() {
        if (finishedEvent == null) {
            finishedEvent = new WaveFinishedEvent();
        }

        return finishedEvent;
    }

}
