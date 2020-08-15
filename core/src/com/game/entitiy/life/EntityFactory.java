package com.game.entitiy.life;

import com.game.data.GameRepository;
import com.game.entitiy.entities.NPCEntity;
import com.game.entitiy.entities.TowerEntity;

/**
 * Creates copies of entities for spawning
 *
 * @author Youri Dudock
 */
public class EntityFactory {

    /**
     * Creates a NPC Entity based on an ID
     *
     * @param ID id of the NPC Entity
     *
     * @return a unreferenced NPC Entity
     */
    public NPCEntity createNPC(int ID) {
        NPCEntity entityToCopy = GameRepository.get().getNPCEntitiesAsArray()[ID];

        NPCEntity npc = new NPCEntity();
        npc.setSize(entityToCopy.getWidth(), entityToCopy.getHeight());
        npc.setName(entityToCopy.getName());
        npc.setID(entityToCopy.getID());
        npc.setSpriteName(entityToCopy.getSpriteName());
        npc.setDamage(entityToCopy.getDamage());
        npc.setHitpoints(entityToCopy.getHitpoints());
        npc.setKillValue(entityToCopy.getKillValue());
        npc.setSpeed(entityToCopy.getSpeed());

        return npc;
    }

    /**
     * Creates a Tower Entity based on an ID
     *
     * @param ID id of the Tower Entity
     *
     * @return a unreferenced Tower Entity
     */
    public TowerEntity createTower(int ID) {
        TowerEntity entityToCopy = GameRepository.get().getTowers().get(ID);

        TowerEntity tower = new TowerEntity();
        tower.setSize(entityToCopy.getWidth(), entityToCopy.getHeight());
        tower.setName(entityToCopy.getName());
        tower.setID(entityToCopy.getID());
        tower.setSpriteName(entityToCopy.getSpriteName());
        tower.setPrice(entityToCopy.getPrice());
        tower.setDamage(entityToCopy.getDamage());
        tower.setSpeed(entityToCopy.getSpeed());
        tower.setRange(entityToCopy.getRange());

        return tower;
    }

}
