package com.carlon.eshop.inventory.service;

import com.carlon.eshop.inventory.request.Request;

/**
 * 请求异步执行service
 *
 * @author ruineng.chen
 * @date 2021/06/01
 */
public interface RequestAsyncProcessService {

    void process(Request request);
}
