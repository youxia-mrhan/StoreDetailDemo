package com.example.home_store_detail.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.baselibrary.ui.view.BaseView;
import com.example.baselibrary.util.MoneyConvertUtils;
import com.example.home_store_detail.R;
import com.example.home_store_detail.behavior.StoreDetailTabPageBehaviorH;
import com.example.home_store_detail.databinding.StoreDetailLvShoppingCartHBinding;
import com.example.provider.home.storedetail.shoppingcart.bean.ShoppingCartListH;
import com.example.provider.home.storedetail.shoppingcart.viewmodel.ShoppingCartVMH;

import java.math.BigDecimal;

/**
 * 购物车
 */
public class StoreDetailShoppingCartH extends BaseView implements View.OnClickListener {

    private StoreDetailLvShoppingCartHBinding bind;
    private StoreDetailTabPageBehaviorH tabPageBehavior;
    private int storeId = -1;
    private ShoppingCartVMH shoppingCartVMH;
    private LifecycleOwner owner;

    public StoreDetailShoppingCartH(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
        LayoutInflater.from(context).inflate(R.layout.store_detail_lv_shopping_cart_h, this);
        bind = StoreDetailLvShoppingCartHBinding.bind(getChildAt(0));
        bind.productOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    public void setTabPageBehavior(StoreDetailTabPageBehaviorH tabPageBehavior) {
        this.tabPageBehavior = tabPageBehavior;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        if (bind == null) return true;

        // 事件穿透范围，购物车是叠加在最上层的
        if (bind.shoppingCartNo.getVisibility() == View.VISIBLE) {
            if (ev.getY() > bind.cartNoWelfareInfo.getBottom()) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    @SuppressLint("SetTextI18n")
    public void initData(int storeId, LifecycleOwner owner) {
        this.storeId = storeId;
        this.owner = owner;

        // 购物车 ViewModel
        ShoppingCartVMH storeShoppingCart = ShoppingCartListH.getStoreShoppingCart(storeId, true);
        ViewModelProvider.Factory shoppingCartFactory = (ViewModelProvider.Factory) new ShoppingCartVMH.ShoppingCartVMHFactory(storeShoppingCart);
        shoppingCartVMH = new ViewModelProvider((ViewModelStoreOwner) owner, shoppingCartFactory).get(ShoppingCartVMH.class);
        bind.setShoppingCartViewModel(shoppingCartVMH);
        bind.setLifecycleOwner(owner);

        bind.productPrice.setText("¥" + setStoreDetailMoneyConvert(shoppingCartVMH.minusDiscountTotalPrice.getValue()));
        shoppingCartVMH.minusDiscountTotalPrice.observe(owner, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                bind.productPrice.setText("¥" + setStoreDetailMoneyConvert(integer));
            }
        });

        bind.productOriginalPrice.setText("¥" + setStoreDetailMoneyConvert(shoppingCartVMH.originalTotalPrice.getValue()));
        shoppingCartVMH.originalTotalPrice.observe(owner, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                bind.productOriginalPrice.setText("¥" + setStoreDetailMoneyConvert(integer));
            }
        });

        bind.productLimitedTimePrice.setText("已减限时折扣 ￥" + setStoreDetailMoneyConvert(shoppingCartVMH.discountPrice.getValue()));
        shoppingCartVMH.discountPrice.observe(owner, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                bind.productLimitedTimePrice.setText("已减限时折扣 ￥" + setStoreDetailMoneyConvert(integer));
            }
        });
    }

    @Override
    public void fistInitWindowFocus() {
        shoppingCartVMH.shoppingCartTotalCount.observe(owner, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (shoppingCartVMH.shoppingCartTotalCount.getValue() > 0) {
                    tabPageBehavior.createShoppingCartWindow(storeId);
                } else {
                    if (StoreDetailShoppingCartWindowH.instance != null) {
                        tabPageBehavior.removeShoppingCartWindow();
                    }
                }
            }
        });
    }

    /**
     * 分转元，超过10000 使用 万 为单位
     *
     * @param money
     * @return
     */
    private String setStoreDetailMoneyConvert(int money) {
        String m = MoneyConvertUtils.changeF2Y(getContext(), String.valueOf(money));
        float y = Float.valueOf(m);
        if (y > 10000) {
            return MoneyConvertUtils.getFormatMoney(BigDecimal.valueOf(money), 2, 10000);
        } else {
            return String.valueOf(y);
        }
    }

    /**
     * 动态计算变化
     */
    public void effectByOffset(float progress) {
        if (tabPageBehavior.getStoreDetailTabPage().getCurrentPagerIndex() != 0) {
            if (bind.getRoot().getVisibility() != View.GONE) {
                bind.getRoot().setVisibility(View.GONE);
            }
            return;
        }
        if (tabPageBehavior.getStoreDetailTabPage().getTranslationY() == tabPageBehavior.getMaxOffsetBottom()) {
            hideOfShow(View.GONE);
        } else {
            hideOfShow(View.VISIBLE);
        }
        bind.getRoot().setAlpha(progress);
        if (StoreDetailShoppingCartWindowH.instance != null) {
            StoreDetailShoppingCartWindowH.instance.setAlpha(progress);
        }
    }

    /**
     * 隐藏或显示
     */
    public void hideOfShow(int viewState) {
        if (tabPageBehavior.getStoreDetailTabPage().getCurrentPagerIndex() != 0) {
            if (bind.getRoot().getVisibility() != View.GONE) {
                bind.getRoot().setVisibility(View.GONE);
                if (StoreDetailShoppingCartWindowH.instance != null) {
                    StoreDetailShoppingCartWindowH.instance.setVisibility(View.GONE);
                }
            }
            return;
        }
        if (viewState == View.GONE) {
            if (bind.getRoot().getVisibility() != View.GONE) {
                bind.getRoot().setVisibility(View.GONE);
                if (StoreDetailShoppingCartWindowH.instance != null) {
                    StoreDetailShoppingCartWindowH.instance.setVisibility(View.GONE);
                }
            }
        } else if (viewState == View.VISIBLE) {
            if (bind.getRoot().getVisibility() != View.VISIBLE) {
                bind.getRoot().setVisibility(View.VISIBLE);
                if (StoreDetailShoppingCartWindowH.instance != null) {
                    StoreDetailShoppingCartWindowH.instance.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (StoreDetailShoppingCartWindowH.instance != null) {
            StoreDetailShoppingCartWindowH.expandOrShrink();
        }
    }
}
