package com.game.event;

/**
 * The priority of the action event registered by an event subscriber
 *
 * This could be important in things such as cleaning up (LOW) after all actions have been triggered
 *
 * @author Youri Dudock
 */
public enum EventActionPriority {
    HIGH, // the event is fired first
    MEDIUM, // the event is fired after the high events
    LOW // the event is fired last
}
