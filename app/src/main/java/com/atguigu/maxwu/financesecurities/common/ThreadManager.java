package com.atguigu.maxwu.financesecurities.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 作者: WuKai
 * 时间: 2017/6/21
 * 邮箱: 648838173@qq.com
 * 作用:
 */

public class ThreadManager {

    private static ThreadManager threadManager = new ThreadManager();

    private ExecutorService service = Executors.newCachedThreadPool();

    private ThreadManager() {
    }

    public static ThreadManager getInstance() {
        return threadManager;
    }

    public ExecutorService getThread() {
        return service;
    }
}
