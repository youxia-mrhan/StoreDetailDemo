package com.example.home_store_detail.provider;


import static com.example.baselibrary.ui.view.BaseView.getAssetImage100;
import static com.example.provider.home.storedetail.shoppingcart.bean.ShoppingCartListH.getStoreShoppingCart;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.baselibrary.common.BizierEvaluator2;
import com.example.baselibrary.util.DisplayUtils;
import com.example.home_store_detail.viewmodel.StoreDetailVMH;
import com.example.provider.common.App;
import com.example.provider.home.storedetail.shoppingcart.bean.ShoppingCartBean;
import com.example.provider.home.storedetail.shoppingcart.bean.ShoppingCartListH;
import com.example.provider.home.storedetail.shoppingcart.bean.ShoppingCartProduct;
import com.example.provider.home.storedetail.shoppingcart.util.SharedPreferenceCartList;
import com.example.provider.home.storedetail.shoppingcart.viewmodel.ShoppingCartVMH;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车 添加商品 相关方法
 */
public class ShoppingCartUtils {

    /**
     * 清空购物车
     *
     * @param storeId 店铺Id
     */
    public static void clearShoppingCart(int storeId) {
        ShoppingCartVMH shoppingCart = getStoreShoppingCart(storeId, false);
        if (shoppingCart == null) return;
        shoppingCart.products.clear();
        shoppingCartTotalCount(storeId);
    }

    /**
     * 购物车总商品数量
     *
     * @param storeId 店铺Id
     */
    public static int shoppingCartTotalCount(int storeId) {
        int total = 0;
        ShoppingCartVMH shoppingCart = getStoreShoppingCart(storeId, false);
        if (shoppingCart == null) {
            SharedPreferenceCartList.addCartList();
        } else {
            for (int i = 0; i < shoppingCart.products.size(); i++) {
                total += shoppingCart.products.get(i).getCount();
            }
            shoppingCart.shoppingCartTotalCount.setValue(total);
            getOriginalTotalPrice(storeId);
            getMinusDiscountTotalPrice(storeId);
            getDiscountPrice(storeId);

            SharedPreferenceCartList.addCartList();
        }
        return total;
    }

    /**
     * 获取打完折扣之后的总价格
     *
     * @param storeId
     * @return
     */
    public static int getMinusDiscountTotalPrice(int storeId) {
        ShoppingCartVMH shoppingCart = getStoreShoppingCart(storeId, false);
        if (shoppingCart == null) return 0;
        int totalPrice = 0;
        for (int i = 0; i < shoppingCart.products.size(); i++) {
            totalPrice += (shoppingCart.products.get(i).getPrice() * shoppingCart.products.get(i).getCount());
        }
        shoppingCart.minusDiscountTotalPrice.setValue(totalPrice);
        return totalPrice;
    }

    /**
     * 获取原始总价格
     *
     * @param storeId
     * @return
     */
    public static int getOriginalTotalPrice(int storeId) {
        ShoppingCartVMH shoppingCart = getStoreShoppingCart(storeId, false);
        if (shoppingCart == null) return 0;
        int totalPrice = 0;
        for (int i = 0; i < shoppingCart.products.size(); i++) {
            totalPrice += (shoppingCart.products.get(i).getOriginal_price() * shoppingCart.products.get(i).getCount());
        }
        shoppingCart.originalTotalPrice.setValue(totalPrice);
        return totalPrice;
    }

    /**
     * 获取折扣价格
     *
     * @param storeId
     * @return
     */
    public static int getDiscountPrice(int storeId) {
        int price = getOriginalTotalPrice(storeId) - getMinusDiscountTotalPrice(storeId);
        ShoppingCartVMH shoppingCart = getStoreShoppingCart(storeId, false);
        if (shoppingCart == null) return 0;
        int max = Math.max(price, 0);
        shoppingCart.discountPrice.setValue(max);
        return max;
    }

    /**
     * 添加购物车
     *
     * @param productBean
     * @param storeId
     * @return
     */
    public static int addProductToCart(ShoppingCartProduct productBean, int storeId) {
        ShoppingCartProduct bean = toShoppingCartFindProduct(productBean, storeId);
        int count = 0;
        if (bean == null) {
            productBean.addCount();
            ShoppingCartVMH shoppingCart = getStoreShoppingCart(storeId, true);
            shoppingCart.products.add(productBean);
            count = productBean.getCount();
        } else {
            bean.addCount();
            count = bean.getCount();
        }
        shoppingCartTotalCount(storeId);
        return count;
    }

    /**
     * 减少商品
     *
     * @param productBean
     * @param storeId
     * @return
     */
    public static int reduceProductToCart(ShoppingCartProduct productBean, int storeId) {
        ShoppingCartProduct bean = toShoppingCartFindProduct(productBean, storeId);
        int count = 0;
        if (bean != null) {
            count = bean.reduceCount(storeId);
            shoppingCartTotalCount(storeId);
        }
        return count;
    }

    /**
     * 去购物车查找
     *
     * @param productBean
     * @param storeId
     * @return
     */
    public static ShoppingCartProduct toShoppingCartFindProduct(ShoppingCartProduct productBean, int storeId) {
        ShoppingCartVMH shoppingCart = getStoreShoppingCart(storeId, false);
        if (shoppingCart == null) return null;
        List<ShoppingCartProduct> products = shoppingCart.products;
        ShoppingCartProduct currentSelectProductBean = null;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == productBean.getId() &&
                    products.get(i).getSpecification().getId() == productBean.getSpecification().getId() &&
                    products.get(i).getSpecification().getAttribute().getId() == productBean.getSpecification().getAttribute().getId()) {
                currentSelectProductBean = products.get(i);
                break;
            }
        }
        return currentSelectProductBean;
    }

    /**
     * 加入购物车动画
     *
     * @param context
     * @param getRootView       根目录
     * @param startLocalXY      开始屏幕中位置
     * @param targetLocalXY     目标屏幕中位置
     * @param selectProductBean 加入购物车的商品
     */
    public static void addCartAnimator(Context context,
                                       ViewGroup getRootView,
                                       int[] startLocalXY,
                                       int[] targetLocalXY,
                                       ShoppingCartProduct selectProductBean) {
        // 生成一个View
        final ImageView imageView = new ImageView(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                DisplayUtils.dp2px(context, 40),
                DisplayUtils.dp2px(context, 40));
        imageView.setLayoutParams(params);

        imageView.setImageResource(getAssetImage100(selectProductBean.getImage()));
        getRootView.addView(imageView);

        Point startPosition = new Point(startLocalXY[0] - DisplayUtils.dp2px(context, 20), startLocalXY[1] - DisplayUtils.dp2px(context, 20));
        Point endPosition = new Point(targetLocalXY[0] + DisplayUtils.dp2px(context, 20), targetLocalXY[1] + DisplayUtils.dp2px(context, 30));

        int pointX = (startPosition.x + endPosition.x) / 2 - 150; // 抛物线倾斜度
        int pointY = startPosition.y - 100; // 抛物线的高度
        Point controllPoint = new Point(pointX, pointY);

        ValueAnimator valueAnimator = ValueAnimator.ofObject(new BizierEvaluator2(controllPoint), startPosition, endPosition);
        valueAnimator.setDuration(300);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Point point = (Point) valueAnimator.getAnimatedValue();
                imageView.setX(point.x);
                imageView.setY(point.y);
                float progress = 1.35f - ((float) point.y / (float) endPosition.y);
                imageView.setAlpha(progress);
            }
        });

        // 动画结束，移除图片
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                getRootView.removeView(imageView);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                getRootView.removeView(imageView);
            }
        });

        valueAnimator.start();
    }

    /**
     * 深拷贝，返回购物车需要的实体
     *
     * @param productBean 商品实体
     * @param parentIndex 商品规格 一级Id
     * @param index       商品规格 二级Id
     * @return
     */
    public static ShoppingCartProduct deepCopy(StoreDetailVMH.OrderFoodInfoBean.ProductsBean productBean, int parentIndex, int index) {

        ShoppingCartProduct shoppingCartProduct = new ShoppingCartProduct();
        shoppingCartProduct.setId(productBean.getId());
        shoppingCartProduct.setTitle(productBean.getTitle());
        shoppingCartProduct.setDescribe(productBean.getDescribe());
        shoppingCartProduct.setQuantity(productBean.getQuantity());
        shoppingCartProduct.setPrice(productBean.getPrice());
        shoppingCartProduct.setOriginal_price(productBean.getOriginal_price());
        shoppingCartProduct.setDiscount_str(productBean.getDiscount_str());
        shoppingCartProduct.setSort_group_id(productBean.getType_id());
        shoppingCartProduct.setSort_group_name(productBean.getType_name());
        shoppingCartProduct.setSort_group_describe(productBean.getType_describe());
        shoppingCartProduct.setIs_specification(productBean.isIs_specification());
        shoppingCartProduct.setIs_discount(productBean.isIs_discount());
        shoppingCartProduct.setWidget(productBean.getWidget());
        shoppingCartProduct.setImage(productBean.getImage());

        ShoppingCartProduct.SpecificationsBean specificationsBean = new ShoppingCartProduct.SpecificationsBean();
        specificationsBean.setId(productBean.getSpecifications().get(parentIndex).getId());
        specificationsBean.setSpecification_title(productBean.getSpecifications().get(parentIndex).getSpecification_title());
        shoppingCartProduct.setSpecifications(specificationsBean);

        ShoppingCartProduct.SpecificationsBean.AttributesBean attributesBean = new ShoppingCartProduct.SpecificationsBean.AttributesBean();
        attributesBean.setId(productBean.getSpecifications().get(parentIndex).getAttributes().get(index).getId());
        attributesBean.setAttribute_title(productBean.getSpecifications().get(parentIndex).getAttributes().get(index).getAttribute_title());
        specificationsBean.setAttribute(attributesBean);

        return shoppingCartProduct;
    }

}
