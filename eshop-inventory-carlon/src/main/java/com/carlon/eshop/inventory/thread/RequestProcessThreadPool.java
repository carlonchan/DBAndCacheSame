package com.carlon.eshop.inventory.thread;

import com.carlon.eshop.inventory.request.Request;
import com.carlon.eshop.inventory.request.RequestQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池，单例
 *
 * @author ruineng.chen
 * @date 2021/05/31
 */
public class RequestProcessThreadPool {

    private ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public RequestProcessThreadPool() {
        RequestQueue requestQueue = RequestQueue.getInstance();
        for (int i = 0; i < 10; i++) {
            ArrayBlockingQueue<Request> queue = new ArrayBlockingQueue<Request>(1000);
            requestQueue.addQueue(queue);
            threadPool.submit(new RequestProcessThread(queue));
        }
    }

    private static class Singleton {

        private static RequestProcessThreadPool threadPool;

        static {
            threadPool = new RequestProcessThreadPool();
        }

        private static RequestProcessThreadPool getInstance() {
            return threadPool;
        }
    }

    public static RequestProcessThreadPool getInstance() {
        return Singleton.getInstance();
    }

    public static void init() {
        getInstance();
    }

}
