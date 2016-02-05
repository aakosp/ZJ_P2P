package com.aako.zjp2p.util.rxbus.entity;

import com.aako.zjp2p.util.LogUtil;

/**
 * Created by aako on 16-1-29.
 */
public class EventType {
    private static final String TAG = "EventType";

    private final String tag;

    private final Class<?> clazz;

    private final int hashCode;

    public EventType(String tag, Class<?> clazz) {
        if (tag == null) {
            throw new NullPointerException("EventType Tag cannot be null.");
        }
        if (clazz == null) {
            throw new NullPointerException("EventType Clazz cannot be null.");
        }

        this.tag = tag;
        this.clazz = clazz;

        // Compute hash code eagerly since we know it will be used frequently and we cannot estimate the runtime of the
        // target's hashCode call.
        final int prime = 31;
        hashCode = (prime + tag.hashCode()) * prime + clazz.hashCode();
    }

    @Override
    public String toString() {
        return "[EventType " + tag + " && " + clazz + "]";
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final EventType other = (EventType) obj;

        LogUtil.d(TAG, this+" other:"+other);
        return tag.equals(other.tag) && clazz == other.clazz;
    }
}
