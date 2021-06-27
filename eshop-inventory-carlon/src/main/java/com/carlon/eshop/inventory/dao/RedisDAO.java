package com.carlon.eshop.inventory.dao;

/**
 * @author ruineng.chen
 * @date 2021/05/31
 */
public interface RedisDAO {
    void set(String key, String value);
    String get(String key);
    void delete(String key);
}
