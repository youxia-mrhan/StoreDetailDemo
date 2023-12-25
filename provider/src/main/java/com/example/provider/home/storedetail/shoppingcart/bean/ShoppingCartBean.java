package com.example.provider.home.storedetail.shoppingcart.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车 实体
 * 这个实体 用来做本地存储的，
 * ShoppingCartVMH 里面有 MutableLiveData，无法序列化
 */
public class ShoppingCartBean implements Serializable {

    public int storeId = -1;

    // 加入购物车的商品集合
    public List<ShoppingCartProduct> products = new ArrayList<ShoppingCartProduct>();

    // 购物车里的商品总数量
    public int shoppingCartTotalCount = 0;

    // 获取打完折扣之后的总价格
    public int minusDiscountTotalPrice = 0;

    // 获取原始总价格
    public int originalTotalPrice = 0;

    // 获取折扣价格
    public int discountPrice = 0;

    public ShoppingCartBean() {

    }

    public ShoppingCartBean(int storeId, List<ShoppingCartProduct> products, int shoppingCartTotalCount, int minusDiscountTotalPrice, int originalTotalPrice, int discountPrice) {
        this.storeId = storeId;
        this.products = products;
        this.shoppingCartTotalCount = shoppingCartTotalCount;
        this.minusDiscountTotalPrice = minusDiscountTotalPrice;
        this.originalTotalPrice = originalTotalPrice;
        this.discountPrice = discountPrice;
    }

}

