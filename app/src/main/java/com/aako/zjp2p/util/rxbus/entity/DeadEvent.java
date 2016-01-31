package com.aako.zjp2p.util.rxbus.entity;

/**
 * Created by aako on 2016/1/29.
 */
public class DeadEvent {
    public final Object source;
    public final Object event;

    /**
     * Creates a new DeadEvent.
     *
     * @param source object broadcasting the DeadEvent (generally the {@link Bus}).
     * @param event  the event that could not be delivered.
     */
    public DeadEvent(Object source, Object event) {
        this.source = source;
        this.event = event;
    }
}
