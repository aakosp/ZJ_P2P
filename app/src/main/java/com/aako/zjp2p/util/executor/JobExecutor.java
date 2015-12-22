package com.aako.zjp2p.util.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by aako on 2015/12/1.
 *
 * 包装类 {@link ThreadPoolExecutor}
 */
public class JobExecutor implements ThreadExecutor {

    private static final int DEFAULT_POOL_SIZE = 3;
    private static final int MAX_LOOL_SIZE = 5;

    private static final TimeUnit KEEP_ALIVE_TIMEUNIT = TimeUnit.SECONDS;
    private static final int KEEP_ALIVE_TIME = 10;

    private final BlockingQueue<Runnable> workQueue;
    private final ThreadPoolExecutor threadPoolExecutor;
    private final ThreadFactory threadFactory;

    public JobExecutor(){
        workQueue = new LinkedBlockingDeque<>();
        threadFactory = new JobThreadFactory();
        threadPoolExecutor = new ThreadPoolExecutor(DEFAULT_POOL_SIZE, MAX_LOOL_SIZE, KEEP_ALIVE_TIME, KEEP_ALIVE_TIMEUNIT, workQueue, threadFactory);
    }



    @Override
    public void execute(Runnable command) {

    }

    private static class JobThreadFactory implements ThreadFactory {
        private static final String THREAD_NAME = "android_";
        private int counter = 0;

        @Override public Thread newThread(Runnable runnable) {
            return new Thread(runnable, THREAD_NAME + counter);
        }
    }
}
