package com.example.provider.home.storedetail.shoppingcart.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.provider.home.storedetail.shoppingcart.bean.ShoppingCartProduct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车 ViewModel
 */
public class ShoppingCartVMH extends ViewModel implements Serializable {

    public int storeId = -1;

    // 加入购物车的商品集合
    public List<ShoppingCartProduct> products = new ArrayList<ShoppingCartProduct>();

    // 购物车里的商品总数量
    public MutableLiveData<Integer> shoppingCartTotalCount = new MutableLiveData<Integer>(0);

    // 获取打完折扣之后的总价格
    public MutableLiveData<Integer> minusDiscountTotalPrice = new MutableLiveData<Integer>(0);

    // 获取原始总价格
    public MutableLiveData<Integer> originalTotalPrice = new MutableLiveData<Integer>(0);

    // 获取折扣价格
    public MutableLiveData<Integer> discountPrice = new MutableLiveData<Integer>(0);

    public ShoppingCartVMH() {

    }

    public ShoppingCartVMH(ShoppingCartVMH shoppingCartVMH) {
        this.storeId = shoppingCartVMH.storeId;
        this.products = shoppingCartVMH.products;
        this.shoppingCartTotalCount = shoppingCartVMH.shoppingCartTotalCount;
        this.minusDiscountTotalPrice = shoppingCartVMH.minusDiscountTotalPrice;
        this.originalTotalPrice = shoppingCartVMH.originalTotalPrice;
        this.discountPrice = shoppingCartVMH.discountPrice;
    }

    /**
     * 查找指定商品
     */
    public ShoppingCartProduct findCartProduct(ShoppingCartProduct curProduct) {
        for (ShoppingCartProduct product : products) {
            if (product.getId() == curProduct.getId() &&
                    product.getSpecification().getId() == curProduct.getSpecification().getId() &&
                    product.getSpecification().getAttribute().getId() == curProduct.getSpecification().getAttribute().getId()) {
                return product;
            }
        }
        return null;
    }

    public static class ShoppingCartVMHFactory extends ViewModelProvider.NewInstanceFactory {

        private ShoppingCartVMH shoppingCartVMH;

        public ShoppingCartVMHFactory(ShoppingCartVMH shoppingCartVMH) {
            this.shoppingCartVMH = shoppingCartVMH;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ShoppingCartVMH(shoppingCartVMH);
        }
    }

}

