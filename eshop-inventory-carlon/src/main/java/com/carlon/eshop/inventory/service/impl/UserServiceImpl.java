package com.carlon.eshop.inventory.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.carlon.eshop.inventory.dao.RedisDAO;
import com.carlon.eshop.inventory.mapper.UserMapper;
import com.carlon.eshop.inventory.model.User;
import com.carlon.eshop.inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @author ruineng.chen
 * @date 2021/05/31
 */
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private RedisDAO redisDAO;

    @Override
    public User findUserInfo() {
        return userMapper.findUserInfo();
    }

    @Override
    public User getCachedUserInfo() {
        redisDAO.set("cached_user_lisi", "{\"name\": \"lisi\", \"age\":28}");
        String userJSON = redisDAO.get("cached_user_lisi");

        JSONObject jsonObject = JSONObject.parseObject(userJSON);
        User user = new User();
        user.setName(jsonObject.getString("name"));
        user.setAge(jsonObject.getInteger("age"));
        return user;
    }
}
