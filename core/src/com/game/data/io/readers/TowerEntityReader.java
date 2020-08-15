package com.game.data.io.readers;

import com.game.data.io.GameDataReader;
import com.game.entitiy.entities.TowerEntity;

/**
 * Reader for tower entities data
 *
 * @author Youri Dudock
 */
public class TowerEntityReader extends GameDataReader<TowerEntity> {

    @Override
    public String getFileName() {
        return "towers";
    }

    @Override
    public Class getClassType() {
        return TowerEntity.class;
    }
}
