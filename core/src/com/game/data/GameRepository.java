package com.game.data;

import com.game.data.io.readers.MapReader;
import com.game.data.io.readers.NPCEntityReader;
import com.game.data.io.readers.NPCWaveReader;
import com.game.data.io.readers.TowerEntityReader;
import com.game.entitiy.entities.NPCEntity;
import com.game.entitiy.entities.TowerEntity;
import com.game.play.wave.NPCWave;
import com.game.world.map.GameMap;

import java.util.List;

/**
 * A singleton data repository with external saved game data
 *
 * @author Youri Dudock
 */
public class GameRepository {

    /**
     * Path to the data map
     */
    public static final String DATA_PATH = "data/";

    /**
     * Path to the sprites map
     */
    public static final String SPRITES_PATH = "sprites/";

    /**
     * Path to the game maps folder
     */
    public static final String MAP_PATH = "maps/";

    /**
     * Singleton repository instance
     */
    private static GameRepository repository = new GameRepository();

    // list of npc entities
    private List<NPCEntity> npcs;

    // list of npc waves
    private List<NPCWave> waves;

    // list of towers
    private List<TowerEntity> towers;

    // list of game maps
    private List<GameMap> maps;

    /**
     * Private constructor as this class is singleton
     */
    private GameRepository() {

    }

    /**
     * Loads the data and creates static get
     */
    public void load() {
        // read data files
        npcs = new NPCEntityReader().read();
        waves = new NPCWaveReader().read();
        towers = new TowerEntityReader().read();
        maps = new MapReader().read();
    }

    public NPCEntity[] getNPCEntitiesAsArray() {
        return npcs.toArray(NPCEntity[]::new);
    }

    public NPCWave[] getNPCWavesAsArray() {
        return waves.toArray(NPCWave[]::new);
    }

    public List<TowerEntity> getTowers() {
        return towers;
    }

    public List<GameMap> getMaps() {
        return maps;
    }

    public static GameRepository get() {
        return repository;
    }

}
