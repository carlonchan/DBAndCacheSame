package com.carlon.eshop.inventory.service.impl;

import com.carlon.eshop.inventory.dao.RedisDAO;
import com.carlon.eshop.inventory.mapper.ProductInventoryMapper;
import com.carlon.eshop.inventory.model.ProductInventory;
import com.carlon.eshop.inventory.service.ProductInventoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ruineng.chen
 * @date 2021/06/01
 */
@Service("productInventoryService")
public class ProductInventoryServiceImpl implements ProductInventoryService {

    @Resource
    private RedisDAO redisDAO;

    @Resource
    private ProductInventoryMapper productInventoryMapper;

    @Override
    public void updateProductInventory(ProductInventory productInventory) {
        productInventoryMapper.updateProductInventory(productInventory);
    }

    @Override
    public void removeProductInventoryCache(ProductInventory productInventory) {
        String key = "product:inventory" + productInventory.getProductId();
        redisDAO.delete(key);
    }

    @Override
    public ProductInventory findProductInventory(Integer productId) {
        return productInventoryMapper.findProductInventory(productId);
    }

    @Override
    public void setProductInventoryCache(ProductInventory productInventory) {
        String key = "product:inventory" + productInventory.getProductId();
        redisDAO.set(key, String.valueOf(productInventory.getInventoryCnt()));
    }

    @Override
    public ProductInventory getProductInventoryCache(Integer productId) {
        String key = "product:inventory" + productId;
        String result = redisDAO.get(key);
        if (null != result && !"".equals(result)) {
            Long inventoryCnt = Long.valueOf(result);
            ProductInventory productInventory = new ProductInventory(productId, inventoryCnt);
            return productInventory;
        }
        return null;
    }
}
