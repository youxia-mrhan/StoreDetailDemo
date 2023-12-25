package com.example.home_store_detail.ui.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.baselibrary.ui.view.BaseView;
import com.example.baselibrary.util.CustomizeUtils;
import com.example.baselibrary.util.DisplayUtils;
import com.example.baselibrary.util.MoneyConvertUtils;
import com.example.home_store_detail.R;
import com.example.home_store_detail.databinding.StoreDetailLvProductHBinding;
import com.example.home_store_detail.provider.ShoppingCartUtils;
import com.example.home_store_detail.viewmodel.StoreDetailVMH;
import com.example.provider.home.storedetail.shoppingcart.bean.ShoppingCartListH;
import com.example.provider.home.storedetail.shoppingcart.bean.ShoppingCartProduct;
import com.example.provider.home.storedetail.shoppingcart.viewmodel.ShoppingCartVMH;

/**
 * 商品 列表项
 */
public class StoreDetailProductH extends BaseView implements View.OnClickListener {

    public StoreDetailLvProductHBinding bind;
    private StoreDetailVMH.OrderFoodInfoBean.ProductsBean curProduct;
    private int storeId;
    private CustomizeUtils.AntiShake antiShake;
    private ObjectAnimator translationXAnimator;
    private ObjectAnimator alphaAnimator;
    private ObjectAnimator discountTranslationXAnimator;
    private ObjectAnimator discountAlphaAnimator;
    private boolean isFirst = false;
    private int ANIMATOR_TIME = 200;
    private AccelerateDecelerateInterpolator accelerateDecelerateInterpolator;
    private boolean addOption = false;

    public StoreDetailProductH(@NonNull Context context, int storeId, LifecycleOwner owner) {
        super(context);
        this.storeId = storeId;
        accelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
        antiShake = new CustomizeUtils.AntiShake(500);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        setLayoutParams(params);
        setOnClickListener(this);
        ShoppingCartVMH storeShoppingCart = ShoppingCartListH.getStoreShoppingCart(storeId, false);
        storeShoppingCart.shoppingCartTotalCount.observe(owner, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                // 应用于 和 购物车对话框展开时的 增加 或 减少操作 联动
                if (bind == null || !isFirst || !StoreDetailShoppingCartWindowH.isExpand) {
                    isFirst = true;
                    return;
                }

                ShoppingCartProduct cartProduct = storeShoppingCart.findCartProduct(ShoppingCartUtils.deepCopy(curProduct, 0, 0));
                if (cartProduct != null) {
                    int count = cartProduct.getCount();
                    updateCountText(count);
                } else {
                    addOption = false;
                    updateDiscountBtnAnimator();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_btn_box) {
            defaultProductOption(true);
        } else if (v.getId() == R.id.reduce_btn_box) {
            defaultProductOption(false);
        } else if (v.getId() == R.id.product_discount_add_btn) {
            discountProductOption(true);
        } else if (v.getId() == R.id.product_discount_reduce_btn_box) {
            discountProductOption(false);
        } else if (v.getId() == R.id.product_specification_btn || v.getId() == R.id.product_specification_discount_btn) {
            if (antiShake.isFastClick()) {
                addOption = false;
                StoreDetailCoordinatorH.createSpecificationWindow(curProduct, storeId);
            }
        }
    }

    /**
     * 没有规格 + 没有优惠
     * 增加 或 减少
     */
    private void defaultProductOption(boolean add) {
        int count = 0;
        if (add) {
            count = updateProductCount(curProduct, true);
            updateCountText(count);
            updateBtnAnimator();
        } else {
            count = updateProductCount(curProduct, false);
            updateCountText(count);
            if (count == 0) {
                updateBtnAnimator();
            }
        }
    }

    /**
     * 没有规格 + 有优惠
     * 增加 或 减少
     */
    private void discountProductOption(boolean add) {
        int count = 0;
        if (add) {
            count = updateProductCount(curProduct, true);
            updateCountText(count);
            updateDiscountBtnAnimator();
        } else {
            count = updateProductCount(curProduct, false);
            updateCountText(count);
            if (count == 0) {
                updateDiscountBtnAnimator();
            }
        }
    }


    /**
     * 增减 商品动画（没有规格 不带优惠）
     */
    private void updateBtnAnimator() {
        if (addOption) {
            addCartAnimator(false);
            if (bind.translationBox.getVisibility() == View.VISIBLE) return;
            bind.translationBox.setVisibility(View.VISIBLE);
            translationXAnimator = ObjectAnimator.ofFloat(bind.translationBox, "translationX", -DisplayUtils.dp2px(getContext(), 2)).setDuration(ANIMATOR_TIME);
            alphaAnimator = ObjectAnimator.ofFloat(bind.translationBox, "alpha", 1f).setDuration(ANIMATOR_TIME);
        } else {
            if (bind.translationBox.getVisibility() == View.VISIBLE) {
                translationXAnimator = ObjectAnimator.ofFloat(bind.translationBox, "translationX", DisplayUtils.dp2px(getContext(), 50)).
                        setDuration(ANIMATOR_TIME);
                translationXAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        bind.translationBox.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        bind.translationBox.setVisibility(View.GONE);
                    }
                });
                alphaAnimator = ObjectAnimator.ofFloat(bind.translationBox, "alpha", 0).setDuration(ANIMATOR_TIME);
            } else {
                bind.translationBox.setAlpha(0);
                bind.translationBox.setVisibility(View.GONE);
            }
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(accelerateDecelerateInterpolator);
        animatorSet.play(translationXAnimator);
        animatorSet.play(alphaAnimator);
        animatorSet.start();
    }

    /**
     * 添加购物车 动画
     */
    private void addCartAnimator(boolean isDiscountAddBtn) {
        int[] startLocalPosition = isDiscountAddBtn ? getDiscountAddBtnLocalPosition() : getAddBtnLocalPosition();
        int[] endLocalPosition = StoreDetailCoordinatorH.getCartViewLocalPosition();
        ShoppingCartUtils.addCartAnimator(getContext(),
                (ViewGroup) getRootView(),
                startLocalPosition,
                endLocalPosition,
                ShoppingCartUtils.deepCopy(curProduct, 0, 0));
    }

    /**
     * 增减 商品动画（没有规格 带优惠）
     */
    private void updateDiscountBtnAnimator() {
        if (addOption) {
            addCartAnimator(true);
            if (bind.productDiscountTranslationBox.getVisibility() == View.VISIBLE) return;
            bind.productDiscountTranslationBox.setVisibility(View.VISIBLE);
            discountTranslationXAnimator = ObjectAnimator.ofFloat(bind.productDiscountTranslationBox, "translationX", -DisplayUtils.dp2px(getContext(), 2)).setDuration(ANIMATOR_TIME);
            discountAlphaAnimator = ObjectAnimator.ofFloat(bind.productDiscountTranslationBox, "alpha", 1f).setDuration(ANIMATOR_TIME);
        } else {
            if (bind.productDiscountTranslationBox.getVisibility() == View.VISIBLE) {
                discountTranslationXAnimator = ObjectAnimator.ofFloat(bind.productDiscountTranslationBox, "translationX", DisplayUtils.dp2px(getContext(), 50)).
                        setDuration(ANIMATOR_TIME);
                discountTranslationXAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        bind.productDiscountTranslationBox.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        bind.productDiscountTranslationBox.setVisibility(View.GONE);
                    }
                });
                discountAlphaAnimator = ObjectAnimator.ofFloat(bind.productDiscountTranslationBox, "alpha", 0).setDuration(ANIMATOR_TIME);
            } else {
                bind.productDiscountTranslationBox.setAlpha(0);
                bind.productDiscountTranslationBox.setVisibility(View.GONE);
            }
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(accelerateDecelerateInterpolator);
        animatorSet.play(discountTranslationXAnimator);
        animatorSet.play(discountAlphaAnimator);
        animatorSet.start();
    }

    public void initView(StoreDetailVMH.OrderFoodInfoBean.ProductsBean product) {
        curProduct = product;

        removeAllViews();
        LayoutInflater.from(getContext()).inflate(R.layout.store_detail_lv_product_h, this);
        bind = StoreDetailLvProductHBinding.bind(getChildAt(0));
        bind.setProduct(product);
        bind.setStyleId(-1);

        bind.productImg.setImageResource(getAssetImage100(product.getImage()));

        if (product.isIs_specification() && product.isIs_discount()) {
            bind.setStyleId(3); // 有规格 + 有优惠 的商品样式
            bind.productSpecificationDiscountPrice.setText(changeF2Y(product.getPrice()));
            bind.productOriginalPrice.setText("￥" + changeF2Y(product.getOriginal_price()));
            bind.productOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        } else if (product.isIs_specification() && !product.isIs_discount()) {
            bind.setStyleId(2); // 有规格 的商品样式
            bind.productSpecificationPrice.setText(changeF2Y(product.getPrice()));
        } else if (!product.isIs_specification() && product.isIs_discount()) {
            bind.setStyleId(1); // 没有规格 + 有优惠 的商品样式
            bind.productDiscountPrice.setText(changeF2Y(product.getPrice()));
            bind.productOriginalPrice.setText("￥" + changeF2Y(product.getOriginal_price()));
            bind.productOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            bind.setStyleId(0); // 没有规格 的商品样式
            bind.productPrice.setText(changeF2Y(product.getPrice()));
        }

        bind.addBtnBox.setOnClickListener(this);
        bind.reduceBtnBox.setOnClickListener(this);
        bind.productDiscountAddBtn.setOnClickListener(this);
        bind.productDiscountReduceBtnBox.setOnClickListener(this);
        bind.productSpecificationBtn.setOnClickListener(this);
        bind.productSpecificationDiscountBtn.setOnClickListener(this);

        isToShoppingCart();
    }

    /**
     * 是否加入了购物车，拿到数量
     */
    private void isToShoppingCart() {
        if (bind == null) return;
        ShoppingCartProduct cartProduct = ShoppingCartUtils.deepCopy(curProduct, 0, 0);
        ShoppingCartProduct shoppingCartProduct = ShoppingCartUtils.toShoppingCartFindProduct(cartProduct, storeId);
        if (shoppingCartProduct == null) {
            if (bind.getStyleId() == 0) {
                bind.translationBox.setAlpha(0);
                bind.translationBox.setVisibility(View.GONE);
            } else if (bind.getStyleId() == 1) {
                bind.productDiscountTranslationBox.setAlpha(0);
                bind.productDiscountTranslationBox.setVisibility(View.GONE);
            }
        } else {
            if (bind.getStyleId() == 0) {
                if (shoppingCartProduct.getCount() == 0) {
                    bind.translationBox.setAlpha(0);
                    bind.translationBox.setVisibility(View.GONE);
                } else {
                    updateCountText(shoppingCartProduct.getCount());
                }
            } else if (bind.getStyleId() == 1) {
                if (shoppingCartProduct.getCount() == 0) {
                    bind.productDiscountTranslationBox.setAlpha(0);
                    bind.productDiscountTranslationBox.setVisibility(View.GONE);
                } else {
                    updateCountText(shoppingCartProduct.getCount());
                }
            }
        }
    }

    /**
     * 修改TextView 数量
     *
     * @param count
     */
    private void updateCountText(int count) {
        if (bind.getStyleId() == 0) {
            if (count == 0) {
                bind.productQuantity.setText("1");
            } else {
                bind.productQuantity.setText(count + "");
            }
        } else if (bind.getStyleId() == 1) {
            if (count == 0) {
                bind.productDiscountQuantity.setText("1");
            } else {
                bind.productDiscountQuantity.setText(count + "");
            }
        }
    }

    /**
     * 更新商品数量
     */
    private int updateProductCount(StoreDetailVMH.OrderFoodInfoBean.ProductsBean product, boolean add) {
        addOption = add;
        ShoppingCartProduct productVMH = ShoppingCartUtils.deepCopy(product, 0, 0);
        if (add) {
            return ShoppingCartUtils.addProductToCart(productVMH, storeId);
        } else {
            return ShoppingCartUtils.reduceProductToCart(productVMH, storeId);
        }
    }

    /**
     * 获取添加按钮 在屏幕中的位置（没有规格 不带优惠）
     *
     * @return
     */
    public int[] getAddBtnLocalPosition() {
        int[] location = new int[2];
        if (bind == null) return location;
        bind.addBtn.getLocationOnScreen(location);
        return location;
    }

    /**
     * 获取添加按钮 在屏幕中的位置（没有规格 带优惠）
     *
     * @return
     */
    public int[] getDiscountAddBtnLocalPosition() {
        int[] location = new int[2];
        if (bind == null) return location;
        bind.productDiscountAddBtn.getLocationOnScreen(location);
        return location;
    }

    private void stopAnimator() {
        if (translationXAnimator != null) {
            translationXAnimator.setDuration(0);
            translationXAnimator.cancel();
            translationXAnimator = null;
        }
        if (alphaAnimator != null) {
            alphaAnimator.setDuration(0);
            alphaAnimator.cancel();
            alphaAnimator = null;
        }

        if (discountTranslationXAnimator != null) {
            discountTranslationXAnimator.setDuration(0);
            discountTranslationXAnimator.cancel();
            discountTranslationXAnimator = null;
        }
        if (discountAlphaAnimator != null) {
            discountAlphaAnimator.setDuration(0);
            discountAlphaAnimator.cancel();
            discountAlphaAnimator = null;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAnimator();
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
