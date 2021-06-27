package com.carlon.eshop.inventory.service.impl;

import com.carlon.eshop.inventory.request.ProductInventoryCacheRefreshRequest;
import com.carlon.eshop.inventory.request.ProductInventoryDBUpdateRequest;
import com.carlon.eshop.inventory.request.Request;
import com.carlon.eshop.inventory.request.RequestQueue;
import com.carlon.eshop.inventory.service.RequestAsyncProcessService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 请求异步处理service
 *
 * @author ruineng.chen
 * @date 2021/06/01
 */
@Service("requestAsyncProcessService")
public class RequestAsyncProcessServiceImpl implements RequestAsyncProcessService {

    @Override
    public void process(Request request) {
        // 拿到请求之后，进行读请求去重
        RequestQueue requestQueue = RequestQueue.getInstance();
        Map<Integer, Boolean> flagMap = requestQueue.getFlagMap();
        // 更新请求，设置为true
        if (request instanceof ProductInventoryDBUpdateRequest) {
            flagMap.put(request.getProductId(), true);
        } else if (request instanceof ProductInventoryCacheRefreshRequest) {
            Boolean flag = flagMap.putIfAbsent(request.getProductId(), false);
            // 默认最最初始的库存，是会被初始化到缓存中的，所以flag == null的情况就两种，
            //一个是数据库压根没有这条数据，一个就是在执行更新操作，删除了缓存，此时
            if (flag != null && flag) {
                flagMap.put(request.getProductId(), false);
            }
            // 缓存刷新的请求，flag不为空，为false，说明已经有一个更新请求+ 缓存刷新请求
            if(flag != null && !flag){
                return;
            }
        }
        // 请求路由，根据productId路由到内存队列中
        ArrayBlockingQueue<Request> routingQueue = getRoutingQueue(request.getProductId());
        // 将请求放到对应的队列中
        try {
            routingQueue.put(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private ArrayBlockingQueue<Request> getRoutingQueue(Integer productId) {
        RequestQueue requestQueue = RequestQueue.getInstance();

        String key = String.valueOf(productId);
        int h;
        int hash = (key == null) ? 0 : ((h = key.hashCode()) ^ h >>> 16);
        // 对hash取模，路由到对应的队列
        int index = hash & (requestQueue.getQueueSize() - 1);
        return requestQueue.getQueue(index);
    }
}
