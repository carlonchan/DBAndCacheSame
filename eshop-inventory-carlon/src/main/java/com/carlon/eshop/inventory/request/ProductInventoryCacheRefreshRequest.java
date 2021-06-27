package com.carlon.eshop.inventory.request;

import com.carlon.eshop.inventory.model.ProductInventory;
import com.carlon.eshop.inventory.service.ProductInventoryService;

/**
 * @author ruineng.chen
 * @date 2021/06/01
 */
public class ProductInventoryCacheRefreshRequest implements Request {

    private Integer productId;

    private ProductInventoryService productInventoryService;

    public ProductInventoryCacheRefreshRequest(Integer productId, ProductInventoryService productInventoryService) {
        this.productId = productId;
        this.productInventoryService = productInventoryService;
    }

    @Override
    public void process() {
        // 从数据库中查询最新的商品库存数量
        ProductInventory productInventory = productInventoryService.findProductInventory(productId);
        // 将最新的库存数刷新到redis
        productInventoryService.setProductInventoryCache(productInventory);
    }

    @Override
    public Integer getProductId() {
        return productId;
    }
}
