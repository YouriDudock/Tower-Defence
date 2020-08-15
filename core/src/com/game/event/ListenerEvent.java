package com.game.event;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * An abstract listener for events, such as when an entity dies
 * Other classes can subscribe to this listener and get notified when the event is triggered
 *
 * @author Youri Dudock
 */
public abstract class ListenerEvent {

    /**
     * A list of registered actions for this event stored along with their callers
     */
    private Map<EventAction, Object> subscribers = new HashMap<>();

    /**
     * Gets called when an event happends
     * Notifies all the subscribers
     */
    public void trigger() {
        // get the key set
        Set<EventAction> set = subscribers.keySet();

        // @TODO avoid iterating three times

        // loop and trigger actions based on priority
        set.stream()
                .filter(k -> k.getPriority() == EventActionPriority.HIGH)
                .forEach(EventAction::trigger);

        set.stream()
                .filter(k -> k.getPriority() == EventActionPriority.MEDIUM)
                .forEach(EventAction::trigger);

        set.stream()
                .filter(k -> k.getPriority() == EventActionPriority.LOW)
                .forEach(EventAction::trigger);
    }

    /**
     * Register a new subscribers to the list
     *
     * @param caller the caller that wants to subscribe
     * @param action the action that gets executed on trigger
     */
    public void register(Object caller, EventAction action) {
        subscribers.put(action, caller);
    }

    /**
     * Unregisters a subscriber based on caller identification
     *
     * @param caller the caller that wants to be removed from the list
     */
    public void unregister(Object caller) {
        subscribers.values().removeIf(value -> value == caller);
    }

    /**
     * Clear all subscribers
     */
    public void clear() {
        subscribers.clear();
    }


}
