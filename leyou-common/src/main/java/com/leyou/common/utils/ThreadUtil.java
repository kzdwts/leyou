package com.leyou.common.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 线程工具类
 * @author: kangyong
 * @date: 2020/9/14 23:02
 * @version: v1.0
 */
public class ThreadUtil {

    private static final ExecutorService es = Executors.newFixedThreadPool(10);

    /**
     * 异步执行
     *
     * @param runnable
     */
    public static void execute(Runnable runnable) {
        es.submit(runnable);
    }

}
