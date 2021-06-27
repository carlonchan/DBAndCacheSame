package com.carlon.eshop.inventory.request;

/**
 * 请求接口
 * @author ruineng.chen
 * @date 2021/05/31
 */
public interface Request {

    void process();
    Integer getProductId();
}
