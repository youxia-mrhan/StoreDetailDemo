package com.example.provider.common;

import android.app.Application;
import android.util.Log;

import com.example.provider.home.storedetail.shoppingcart.bean.ShoppingCartBean;
import com.example.provider.home.storedetail.shoppingcart.bean.ShoppingCartListH;
import com.example.provider.home.storedetail.shoppingcart.util.SharedPreferenceCartList;

import java.util.List;


public class App extends Application {

    private static App INSTANCE;

    public static ShoppingCartListH shoppingCartListH;

    public static synchronized App getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        shoppingCartListH = new ShoppingCartListH();

        // 获取本地购物车
        List<ShoppingCartBean> cartList = SharedPreferenceCartList.getCartList();
        if (cartList != null) {
            shoppingCartListH = SharedPreferenceCartList.convertShoppingCartListH(cartList);
        }
    }

}
