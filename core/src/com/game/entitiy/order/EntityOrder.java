package com.game.entitiy.order;

import com.game.entitiy.Entity;
import com.game.event.events.entity.EntityOrderFailedEvent;
import com.game.event.events.entity.EntityOrderFinishedEvent;

/**
 * An order is a task assigned to an Entity ingame (ex: walking towards a tile)
 *
 * @author Youri Dudock
 */
public abstract class EntityOrder<E extends Entity> {

    // entity that is registered on this order
    protected E entity;

    // if the order has been completed
    private boolean orderFinished;

    // order finished event
    private EntityOrderFinishedEvent orderFinishedEvent;

    // order failed event
    private EntityOrderFailedEvent orderFailedEvent;

    protected EntityOrder() {
        orderFinished = false;
        orderFinishedEvent = new EntityOrderFinishedEvent();
        orderFailedEvent = new EntityOrderFailedEvent();
    }

    /**
     * This gets called every frame to execute the logic of an order until it is completed
     */
    public abstract void execute();

    /**
     *
     * @return if the order has been completed
     */
    public boolean hasFinished() {
        return orderFinished;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }

    /**
     * Must be called when the order has been finished
     */
    protected void orderCompleted() {
        orderFinished = true;
        orderFinishedEvent.trigger();
    }

    /**
     * Must be called when the order has failed
     */
    protected void orderFailed() {
        orderFinished = true;
        orderFailedEvent.trigger();
    }

    public EntityOrderFinishedEvent getOrderFinishedEvent() {
        return orderFinishedEvent;
    }

    public EntityOrderFailedEvent getOrderFailedEvent() {
        return orderFailedEvent;
    }

}
