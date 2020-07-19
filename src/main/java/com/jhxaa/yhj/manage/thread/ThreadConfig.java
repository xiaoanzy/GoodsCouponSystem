package com.jhxaa.yhj.manage.thread;

import com.jhxaa.yhj.utli.PropertiesUtil;

import java.util.concurrent.*;

//@Configuration

public class ThreadConfig {

    public static ExecutorService getThreadPoolExecutor(RejectedExecutionHandler rejectedExecutionHandler) {
        if (null == rejectedExecutionHandler) {
            rejectedExecutionHandler = abortPolicy();
        }
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(
                PropertiesUtil.getIntger("thread.core.pool.size"),
                PropertiesUtil.getIntger("thread.max.size"),
                PropertiesUtil.getLong("thread.keep.alive.time"),
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(5),
                t -> {
                    Thread thread = new Thread(t);
                    return thread;
                },
                rejectedExecutionHandler);
        return threadPoolExecutor;
    }

    public static RejectedExecutionHandler abortPolicy() {
        return new ThreadPoolExecutor.AbortPolicy();
    }

    public static RejectedExecutionHandler discardPolicy() {
        return new ThreadPoolExecutor.DiscardPolicy();
    }

    public static RejectedExecutionHandler callerRunsPolicy() {
        return new ThreadPoolExecutor.CallerRunsPolicy();
    }

    public static RejectedExecutionHandler discardOldestPolicy() {
        return new ThreadPoolExecutor.DiscardOldestPolicy();
    }

    public ExecutorService getThreadPoolExecutor() {
        return getThreadPoolExecutor(null);
    }

    /**
     * 关闭线程池
     *
     * @param executorService 传入线程池对象
     */
    public void stopThreadPoolExecutor(ExecutorService executorService) {
        executorService.shutdown();
    }

}
