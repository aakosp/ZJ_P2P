package com.aako.zjp2p.util.executor;

import rx.Scheduler;

/**
 * Created by aako on 2015/11/30.
 */
public interface PostExecutionThread {
    Scheduler getScheduler();
}
