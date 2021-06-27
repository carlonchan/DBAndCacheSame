package com.carlon.eshop.inventory.request;

import com.carlon.eshop.inventory.model.ProductInventory;
import com.carlon.eshop.inventory.service.ProductInventoryService;

/**
 * @author ruineng.chen
 * @date 2021/06/01
 */
public class ProductInventoryDBUpdateRequest implements Request{

    private ProductInventory productInventory;

    private ProductInventoryService productInventoryService;

    public ProductInventoryDBUpdateRequest( ProductInventory productInventory, ProductInventoryService productInventoryService) {
        this.productInventory  = productInventory;
        this.productInventoryService = productInventoryService;
    }

    @Override
    public void process() {
        // 删除redis缓存
        productInventoryService.removeProductInventoryCache(productInventory);
        // 更新数据库
        productInventoryService.updateProductInventory(productInventory);
    }

    @Override
    public Integer getProductId() {
        return productInventory.getProductId();
    }
}
