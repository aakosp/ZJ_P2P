package com.aako.zjp2p.util.rxbus.finder;

import com.aako.zjp2p.util.rxbus.entity.EventType;
import com.aako.zjp2p.util.rxbus.entity.ProducerEvent;
import com.aako.zjp2p.util.rxbus.entity.SubscriberEvent;

import java.util.Map;
import java.util.Set;

/**
 * Created by aako on 2016/1/29.
 */
public interface Finder {
    Map<EventType, ProducerEvent> findAllProducers(Object listener);

    Map<EventType, Set<SubscriberEvent>> findAllSubscribers(Object listener);


    Finder ANNOTATED = new Finder() {
        @Override
        public Map<EventType, ProducerEvent> findAllProducers(Object listener) {
            return AnnotatedFinder.findAllProducers(listener);
        }

        @Override
        public Map<EventType, Set<SubscriberEvent>> findAllSubscribers(Object listener) {
            return AnnotatedFinder.findAllSubscribers(listener);
        }
    };
}
