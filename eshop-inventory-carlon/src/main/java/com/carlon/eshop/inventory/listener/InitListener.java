package com.carlon.eshop.inventory.listener;

import com.carlon.eshop.inventory.thread.RequestProcessThreadPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * 初始化的系统监听器
 * @author ruineng.chen
 * @date 2021/05/31
 */
public class InitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        RequestProcessThreadPool.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
