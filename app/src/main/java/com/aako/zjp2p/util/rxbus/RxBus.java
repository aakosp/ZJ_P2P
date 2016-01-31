package com.aako.zjp2p.util.rxbus;

/**
 * Created by aako on 16-1-29.
 */
public class RxBus {

    private static Bus sBus;

    public static synchronized Bus get() {
        if (sBus == null) {
            sBus = new Bus();
        }
        return sBus;
    }

}
