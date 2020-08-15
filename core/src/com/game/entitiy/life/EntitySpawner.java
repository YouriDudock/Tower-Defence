package com.game.entitiy.life;

import com.badlogic.gdx.math.Vector2;
import com.game.entitiy.Entity;
import com.game.event.EventAction;
import com.game.world.GameWorld;
import com.game.world.map.tiles.MapTile;

/**
 * A class that provides functionality for an entity its life cycle
 *
 * @author Youri Dudock
 */
public class EntitySpawner {

    // instance of an entity factory for unreferenced entities
    private EntityFactory factory;

    // instance of the active world
    private GameWorld world;

    public EntitySpawner(GameWorld world) {
        this.factory = new EntityFactory();
        this.world = world;
    }

    /**
     * Spawns an entity in the world
     *
     * @param id id of the entity to spawn
     * @param location starting location of the entity
     *
     * @return an instance of the spawned entity
     */
    public Entity spawn(int id, Vector2 location) {
        // create a new entity
        Entity entity = factory.createNPC(id);

        // set the entity its location
        entity.setLocation(location);

        // register a death event to despawn the entity
        entity.getDeathEvent().register(this, new EventAction() {
            @Override
            public void trigger() {
                despawn(entity);
            }
        });

        // add the entity to the world
        world.addEntity(entity);

        return entity;
    }

    /**
     * Spawns an entity in the world
     *
     * @param id id of the entity to spawn
     * @param tile the map tile that the entity should spawn on
     *
     * @return an instance of the spawned entity
     */
    public Entity spawn(int id, MapTile tile) {
        return this.spawn(id, tile.getLocation());
    }

    /**
     * Spawns an existing entity into the world
     *
     * @param entity an already existing entity
     * @param location the starting location of the entity
     */
    public void spawn(Entity entity, Vector2 location) {
        entity.setLocation(location);

        entity.getDeathEvent().register(this, new EventAction() {
            @Override
            public void trigger() {
                world.removeEntity(entity);
            }
        });

        world.addEntity(entity);
    }

    /**
     * Spawns an existing entity into the world
     *
     * @param entity an already existing entity
     * @param tile the starting tile of the entity
     */
    public void spawn(Entity entity, MapTile tile) {
        this.spawn(entity, tile.getLocation());
    }

    /**
     * Despawns an entity from the world
     *
     * @param entity the entity to despawn
     */
    private void despawn(Entity entity) {
        world.removeEntity(entity);
    }

    public EntityFactory getFactory() {
        return factory;
    }
}
