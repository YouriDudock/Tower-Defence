package com.game.data.io.readers;

import com.game.data.io.GameDataReader;
import com.game.entitiy.entities.NPCEntity;

/**
 * Reader for npc entities data
 *
 * @author Youri Dudock
 */
public class NPCEntityReader extends GameDataReader<NPCEntity> {

    @Override
    public String getFileName() {
        return "npcs";
    }

    @Override
    public Class getClassType() {
        return NPCEntity.class;
    }

}
