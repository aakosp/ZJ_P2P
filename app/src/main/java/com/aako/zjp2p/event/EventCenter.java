package com.aako.zjp2p.event;

import de.greenrobot.event.EventBus;

public class EventCenter {

    private static final EventBus instance = new EventBus();

    private EventCenter() {
    }


    public static final EventBus getInstance() {
        return instance;
    }
}