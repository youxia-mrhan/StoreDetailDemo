package com.example.home_store_detail.ui.view;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.baselibrary.ui.view.BaseView;
import com.example.baselibrary.util.MoneyConvertUtils;
import com.example.home_store_detail.R;
import com.example.home_store_detail.databinding.StoreDetailLvShoppingCartProductHBinding;
import com.example.home_store_detail.provider.ShoppingCartUtils;
import com.example.provider.home.storedetail.shoppingcart.bean.ShoppingCartProduct;

/**
 * 加入购物车的 商品列表项
 */
public class StoreDetailShoppingCartProductH extends BaseView implements View.OnClickListener{

    public StoreDetailLvShoppingCartProductHBinding bind;
    private ShoppingCartProduct shoppingCartProduct;
    private int storeId;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        return true;
    }

    public StoreDetailShoppingCartProductH(@NonNull Context context) {
        super(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        setLayoutParams(params);
    }

    public void initView(ShoppingCartProduct shoppingCartProduct,int storeId) {
        this.shoppingCartProduct = shoppingCartProduct;
        this.storeId = storeId;
        removeAllViews();
        LayoutInflater.from(getContext()).inflate(R.layout.store_detail_lv_shopping_cart_product_h,this);
        bind = StoreDetailLvShoppingCartProductHBinding.bind(getChildAt(0));
        bind.setShoppingCartProduct(shoppingCartProduct);
        bind.setStyleId(-1);
        bind.productImg.setImageResource(getAssetImage100(shoppingCartProduct.getImage()));

        if (!shoppingCartProduct.isIs_specification() && shoppingCartProduct.isIs_discount()) {
            bind.setStyleId(1); // 没有规格 + 有优惠 的商品样式
            bind.productDiscountPrice.setText(changeF2Y(shoppingCartProduct.getPrice()));
            bind.productOriginalPrice.setText(changeF2Y(shoppingCartProduct.getOriginal_price()));
            bind.productOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            bind.setStyleId(0); // 没有规格 的商品样式
            bind.productPrice.setText(changeF2Y(shoppingCartProduct.getPrice()));
        }
        bind.addBtnBox.setOnClickListener(this);
        bind.reduceBtnBox.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_btn_box) {
            ShoppingCartUtils.addProductToCart(shoppingCartProduct,storeId);
        } else if (v.getId() == R.id.reduce_btn_box) {
            ShoppingCartUtils.reduceProductToCart(shoppingCartProduct,storeId);
        }
    }

    /**
     * 分转元
     *
     * @param money
     * @return
     */
    private String changeF2Y(int money) {
        String price = "0";
        try {
            price = MoneyConvertUtils.changeF2Y(Long.parseLong(String.valueOf(money)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return price;
    }
}
