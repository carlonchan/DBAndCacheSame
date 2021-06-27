package com.carlon.eshop.inventory.thread;

import com.carlon.eshop.inventory.request.Request;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

/**
 * @author ruineng.chen
 * @date 2021/05/31
 */
public class RequestProcessThread implements Callable {

    private ArrayBlockingQueue<Request> queue;

    public RequestProcessThread(ArrayBlockingQueue<Request> queue) {
        this.queue = queue;
    }

    @Override
    public Object call() throws Exception {
        try {
            while (true) {
                Request request = queue.take();
                System.out.println("===========日志===========: 工作线程处理请求，商品id=" + request.getProductId());
                request.process();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }
}
