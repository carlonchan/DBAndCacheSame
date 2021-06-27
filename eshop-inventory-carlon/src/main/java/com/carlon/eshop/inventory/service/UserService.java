package com.carlon.eshop.inventory.service;

import com.carlon.eshop.inventory.model.User;

/**
 * @author ruineng.chen
 * @date 2021/05/31
 */
public interface UserService {

    User findUserInfo();

    User getCachedUserInfo();
}
