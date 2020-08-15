package com.game.world;

import com.badlogic.gdx.utils.Array;
import com.game.entitiy.entities.NPCEntity;
import com.game.entitiy.Entity;
import com.game.entitiy.entities.TowerEntity;
import com.game.event.EventAction;
import com.game.world.map.GameMap;

/**
 * The world in the game
 *
 * @author Youri Dudock
 */
public class GameWorld {

    // all the towers ingame
    private Array<TowerEntity> towers;

    // al the npcs ingame
    private Array<NPCEntity> npcs;

    // the current map
    private GameMap gameMap;

    public GameWorld() {
        npcs = new Array<>();
        towers = new Array<>();
    }

    /**
     * Adds an entity to the world
     *
     * @param entity entity to add
     */
    public void addEntity(Entity entity) {
        if (entity instanceof TowerEntity) {
            towers.add((TowerEntity)entity);
        } else if (entity instanceof NPCEntity) {
            npcs.add((NPCEntity)entity);
        }

        // remove entity from the world if it has died
        entity.getDeathEvent().register(this, new EventAction() {
            @Override
            public void trigger() {
                removeEntity(entity);
            }
        });
    }

    /**
     * Removes an entity from the world
     *
     * @param entity entity to remove
     */
    public void removeEntity(Entity entity) {
        if (entity instanceof TowerEntity) {
            towers.removeValue((TowerEntity)entity, true);
        } else if (entity instanceof NPCEntity) {
            npcs.removeValue((NPCEntity)entity, true);
        }
    }

    public GameMap getMap() {
        return gameMap;
    }

    /**
     * Sets and loads the map for this world
     *
     * @param map the map to load
     */
    public void setMap(GameMap map) {
        this.gameMap = map;
        gameMap.load();
    }

    public Array<NPCEntity> getNpcs() {
        return npcs;
    }

    public Array<TowerEntity> getTowers() {
        return towers;
    }


}
