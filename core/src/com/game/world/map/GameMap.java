package com.game.world.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.game.data.GameRepository;
import com.game.visual.rendering.RenderEngine;
import com.game.world.map.tiles.MapTile;

import java.util.List;

/**
 * A map in our game
 *
 * @author Youri Dudock
 */
public class GameMap {

    // id of the map
    private int mapId;

    // name of the map
    private String mapName;

    // tmx file location and name of the tiled map file
    private String tmxFileName;

    // list of walkable tiles (non converted)
    private List<MapTile> walkableTiles;

    // list of the converted walkable tiles
    private Array<MapTile> walkableConvertedTiles;

    // the tiled map instance
    private TiledMap map;

    // if the map has been loaded before
    private boolean hasBeenLoaded = false;

    /**
     * Loads the map
     */
    public void load() {
        map = new TmxMapLoader().load(GameRepository.MAP_PATH + tmxFileName);
        walkableConvertedTiles = new Array<>();

        // check if the map has been loaded before
        if (!hasBeenLoaded) {
            // we need to convert all the specified tiles in this get
            // because our map maker uses a different y axis then us
            convertMapTiles();
        }

        hasBeenLoaded = true;
    }

    /**
     * Converts map tiles in this get
     * because the used map maker has a different y axis starting point
     */
    private void convertMapTiles() {
        for (MapTile tile : walkableTiles) {
            tile.setLocation(new Vector2(tile.getX(), RenderEngine.GAME_HEIGHT - tile.getLocation().y));
            walkableConvertedTiles.add(tile);
        }
    }

    public TiledMap getTiledMapInstance() {
        return map;
    }

    /**
     *
     * @return all the walkable tiles in the map
     */
    public Array<MapTile> getWalkableTiles() {
        return walkableConvertedTiles;
    }

    /**
     * @return the map begin tile
     */
    public MapTile getBeginTile() {
        return walkableConvertedTiles.get(0);
    }

    /**
     * @return the map end tile
     */
    public MapTile getEndTile() {
        return walkableTiles.get(walkableConvertedTiles.size - 1);
    }


}
