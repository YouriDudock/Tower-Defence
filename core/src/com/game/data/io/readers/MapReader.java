package com.game.data.io.readers;

import com.game.data.io.GameDataReader;
import com.game.world.map.GameMap;


/**
 * Reader for map data
 *
 * @author Youri Dudock
 */
public class MapReader extends GameDataReader<GameMap> {
    
    @Override
    public String getFileName() {
        return "maps";
    }

    @Override
    public Class getClassType() {
        return GameMap.class;
    }
}
