package com.carlon.eshop.inventory.model;

/**
 * @author ruineng.chen
 * @date 2021/05/31
 */
public class ProductInventory {


    private Integer productId;

    private Long inventoryCnt;

    public ProductInventory(Integer productId, Long inventoryCnt) {
        this.productId = productId;
        this.inventoryCnt = inventoryCnt;
    }

    public ProductInventory() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Long getInventoryCnt() {
        return inventoryCnt;
    }

    public void setInventoryCnt(Long inventoryCnt) {
        this.inventoryCnt = inventoryCnt;
    }
}
