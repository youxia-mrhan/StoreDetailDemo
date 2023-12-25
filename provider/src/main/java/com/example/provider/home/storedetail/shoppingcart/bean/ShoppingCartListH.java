package com.example.provider.home.storedetail.shoppingcart.bean;

import com.example.provider.common.App;
import com.example.provider.home.storedetail.shoppingcart.util.SharedPreferenceCartList;
import com.example.provider.home.storedetail.shoppingcart.viewmodel.ShoppingCartVMH;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 每个店铺 都一个自己的购物车
 */
public class ShoppingCartListH implements Serializable {

    // 购物车 集合
    public List<ShoppingCartVMH> shoppingCarts = new ArrayList<ShoppingCartVMH>();

    /**
     * 获取当前店铺 购物车
     *
     * @param storeId 店铺Id
     * @return
     */
    public static ShoppingCartVMH getStoreShoppingCart(int storeId, boolean add) {
        if (storeId == -1) {
            throw new IllegalArgumentException("storeId is -1");
        }
        ShoppingCartListH cartListH = App.shoppingCartListH;
        List<ShoppingCartVMH> shoppingCarts = cartListH.shoppingCarts;
        for (int i = 0; i < shoppingCarts.size(); i++) {
            if (shoppingCarts.get(i).storeId == storeId) {
                return shoppingCarts.get(i);
            }
        }

        if (add) { // 添加操作
            // 如果没有 新建一个购物车
            ShoppingCartVMH shoppingCartVMH = new ShoppingCartVMH();
            shoppingCartVMH.storeId = storeId;
            App.shoppingCartListH.shoppingCarts.add(shoppingCartVMH);
            return shoppingCartVMH;
        }

        return null;
    }

    /**
     * 删除指定店铺 购物车
     */
    public static void clearShoppingCart(int storeId) {
        if (storeId == -1) {
            throw new IllegalArgumentException("storeId is -1");
        }
        List<ShoppingCartVMH> shoppingCarts = App.shoppingCartListH.shoppingCarts;
        for (int i = 0; i < shoppingCarts.size(); i++) {
            if (shoppingCarts.get(i).storeId == storeId) {

                // 如果直接删除，观察方法不会执行
                shoppingCarts.get(i).storeId = -1;
                shoppingCarts.get(i).products.clear();
                shoppingCarts.get(i).shoppingCartTotalCount.setValue(0);
                shoppingCarts.get(i).minusDiscountTotalPrice.setValue(0);
                shoppingCarts.get(i).originalTotalPrice.setValue(0);
                shoppingCarts.get(i).discountPrice.setValue(0);

                shoppingCarts.remove(i);
                break;
            }
        }
    }

    /**
     * 删除 没有商品的购物车
     */
    public static void clearNullShoppingCart() {
        List<ShoppingCartVMH> shoppingCarts = App.shoppingCartListH.shoppingCarts;
        for (int i = 0; i < shoppingCarts.size(); i++) {
            if (shoppingCarts.get(i).products.isEmpty()) {
                shoppingCarts.remove(i);
            }
        }
        SharedPreferenceCartList.addCartList();
    }

}
