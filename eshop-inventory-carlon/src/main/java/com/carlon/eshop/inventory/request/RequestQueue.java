package com.carlon.eshop.inventory.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 请求队列，需要单例，保证内存队列只会初始化一个，用同一个List<queue>
 * @author ruineng.chen
 * @date 2021/05/31
 */
public class RequestQueue {


    private List<ArrayBlockingQueue<Request>> requestQueue = new ArrayList<ArrayBlockingQueue<Request>>();

    private Map<Integer, Boolean> flagMap = new ConcurrentHashMap<>();

    /**
     * 单例有很多种方式去实现：这里采取绝对线程安全的一种方式
     *
     * 静态内部类的方式，去初始化单例
     *
     * @author Administrator
     *
     */
    private static class Singleton{
        private static RequestQueue instance;

        static {
            instance = new RequestQueue();
        }

        public static RequestQueue getInstance(){
            return instance;
        }

    }

    public static RequestQueue getInstance(){
        return Singleton.getInstance();
    }

    public void addQueue(ArrayBlockingQueue<Request> request){
        this.requestQueue.add(request);
    }

    public int getQueueSize (){
        return requestQueue.size();
    }

    /**
     * 获取内存队列
     * @param index
     * @return
     */
    public ArrayBlockingQueue<Request> getQueue(int index) {
        return requestQueue.get(index);
    }

    public Map<Integer, Boolean> getFlagMap() {
        return flagMap;
    }
}
