package com.example.provider.home.storedetail.shoppingcart.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.example.provider.common.App;
import com.example.provider.home.storedetail.shoppingcart.bean.ShoppingCartBean;
import com.example.provider.home.storedetail.shoppingcart.bean.ShoppingCartListH;
import com.example.provider.home.storedetail.shoppingcart.viewmodel.ShoppingCartVMH;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车集合 存储本地
 */
public class SharedPreferenceCartList {

    public final static String SP_NAME = "store_detail_shopping_cart_list";

    /**
     * 使用SharedPreference保存序列化对象
     * 用Base64.encode将字节文件转换成Base64编码保存在String中
     *
     * @param context 上下文
     * @param key     储存对象的key
     * @param object  object对象  对象必须实现Serializable序列化，否则会出问题，
     *                out.writeObject 无法写入 Parcelable 序列化的对象
     */
    public static void setObject(Context context, String key, Object object) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        //创建字节输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //创建字节对象输出流
        ObjectOutputStream out = null;
        try {
            //然后通过将字对象进行64转码，写入sp中
            out = new ObjectOutputStream(baos);
            out.writeObject(object);
            String objectValue = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(key, objectValue);
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }

                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取SharedPreference保存的对象
     * 使用Base64解密String，返回Object对象
     *
     * @param context 上下文
     * @param key     储存对象的key
     * @param <T>     泛型
     * @return 返回保存的对象
     */
    public static <T> T getObject(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        if (sp.contains(key)) {
            String objectValue = sp.getString(key, null);
            byte[] buffer = Base64.decode(objectValue, Base64.DEFAULT);
            //一样通过读取字节流，创建字节流输入流，写入对象并作强制转换
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(bais);
                T t = (T) ois.readObject();
                return t;
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bais != null) {
                        bais.close();
                    }

                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public final static String CART_LIST = "cart_list";

    /**
     * 本地存储 添加购物车集合
     */
    public static void addCartList() {
        List<ShoppingCartBean> cartBeanList = new ArrayList<ShoppingCartBean>();
        for (ShoppingCartVMH cartVMH : App.shoppingCartListH.shoppingCarts) {
            ShoppingCartBean cartBean = new ShoppingCartBean(
                    cartVMH.storeId,
                    cartVMH.products,
                    cartVMH.shoppingCartTotalCount.getValue(),
                    cartVMH.minusDiscountTotalPrice.getValue(),
                    cartVMH.originalTotalPrice.getValue(),
                    cartVMH.discountPrice.getValue());
            cartBeanList.add(cartBean);
        }
        SharedPreferenceCartList.setObject(App.getInstance(), CART_LIST, cartBeanList);
    }

    /**
     * 本地存储 获取购物车集合
     */
    public static List<ShoppingCartBean> getCartList() {
        List<ShoppingCartBean> shoppingCartBeans = SharedPreferenceCartList.getObject(App.getInstance(), CART_LIST);
        if (shoppingCartBeans != null) {
            // 删除没有商品的购物车
            for (int i = 0; i < shoppingCartBeans.size(); i++) {
                if (shoppingCartBeans.get(i).products.isEmpty()) {
                    shoppingCartBeans.remove(i);
                }
            }
            SharedPreferenceCartList.setObject(App.getInstance(), CART_LIST, shoppingCartBeans);
        }
        return shoppingCartBeans;
    }

    /**
     * @param cartBeanList
     * @return ShoppingCartListH
     * 将 List<ShoppingCartBean> 转换为 List<ShoppingCartVMH>
     */
    public static ShoppingCartListH convertShoppingCartListH(List<ShoppingCartBean> cartBeanList) {
        ShoppingCartListH cartListH = new ShoppingCartListH();
        for (ShoppingCartBean cartBean : cartBeanList) {
            ShoppingCartVMH cartVMH = new ShoppingCartVMH();
            cartVMH.storeId = cartBean.storeId;
            cartVMH.products = cartBean.products;
            cartVMH.shoppingCartTotalCount.setValue(cartBean.shoppingCartTotalCount);
            cartVMH.minusDiscountTotalPrice.setValue(cartBean.minusDiscountTotalPrice);
            cartVMH.originalTotalPrice.setValue(cartBean.originalTotalPrice);
            cartVMH.discountPrice.setValue(cartBean.discountPrice);
            cartListH.shoppingCarts.add(cartVMH);
        }
        return cartListH;
    }

}
