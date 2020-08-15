package com.game.data.io.readers;

import com.game.data.io.GameDataReader;
import com.game.play.wave.NPCWave;

/**
 * Reader for npc wave data
 *
 * @author Youri Dudock
 */
public class NPCWaveReader extends GameDataReader<NPCWave> {

    @Override
    public String getFileName() {
        return "npc_waves";
    }

    @Override
    public Class getClassType() {
        return NPCWave.class;
    }


}
