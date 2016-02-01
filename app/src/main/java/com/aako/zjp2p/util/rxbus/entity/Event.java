package com.aako.zjp2p.util.rxbus.entity;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by aako on 2016/1/29.
 */
public class Event {
    public void throwRuntimeException(String msg, InvocationTargetException e) {
        throwRuntimeException(msg, e.getCause());
    }

    public void throwRuntimeException(String msg, Throwable e) {
        Throwable cause = e.getCause();
        if (cause != null) {
            throw new RuntimeException(msg + ": " + cause.getMessage(), cause);
        } else {
            throw new RuntimeException(msg + ": " + e.getMessage(), e);
        }
    }
}
