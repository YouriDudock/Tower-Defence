package com.game.event.events.entity;

import com.game.entitiy.Entity;
import com.game.event.ListenerEvent;

/**
 * When an entity moves
 *
 * @author Youri Dudock
 */
public class EntityMovementEvent extends ListenerEvent {

    private Entity entity;

    public EntityMovementEvent(Entity entity) {
        this.entity = entity;
    }





}
