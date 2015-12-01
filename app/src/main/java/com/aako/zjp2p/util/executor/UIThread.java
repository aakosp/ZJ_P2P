package com.aako.zjp2p.util.executor;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by aako on 2015/12/1.
 *
 * MainThread (UI Thread) implementation based on a {@link Scheduler}
 * 于Android UI主线程上执行任务
 */
public class UIThread implements PostExecutionThread {

    public UIThread(){}

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
