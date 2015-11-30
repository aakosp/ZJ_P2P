package com.aako.zjp2p.interactor;

import rx.Scheduler;

/**
 * Created by aako on 2015/11/30.
 */
public interface PostExecutionThread {
    Scheduler getScheduler();
}
