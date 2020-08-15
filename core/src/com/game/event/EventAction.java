package com.game.event;

/**
 * An Event Action is the actual action when an event has been triggered
 *
 * @author Youri Dudock
 */
public abstract class EventAction {

    /**
     * Gets called when an event has been triggered
     */
    public abstract void trigger();

    /**
     * Priority of this action, medium by default
     *
     * @return the event priority
     */
    public EventActionPriority getPriority() {
        return EventActionPriority.MEDIUM;
    }

}
