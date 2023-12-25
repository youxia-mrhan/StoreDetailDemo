package com.example.home_store_detail.ui.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.baselibrary.ui.view.BaseView;
import com.example.baselibrary.util.DisplayUtils;
import com.example.baselibrary.util.MoneyConvertUtils;
import com.example.baselibrary.util.ShapeUtils;
import com.example.home_store_detail.R;
import com.example.home_store_detail.databinding.StoreDetailLvSpecificationWindowHBinding;
import com.example.home_store_detail.provider.ShoppingCartUtils;
import com.example.home_store_detail.viewmodel.StoreDetailVMH;
import com.example.provider.home.storedetail.shoppingcart.bean.ShoppingCartProduct;
import com.google.android.flexbox.FlexboxLayout;

/**
 * 商品规格 对话框
 * <p>
 * 解释一下为什么不用 Dialog 或 DialogFragment，
 * 因为Dialog是图层是顶级，它会把 加入购物车动画 盖住
 */
public class StoreDetailSpecificationWindowH extends BaseView implements View.OnClickListener {

    private StoreDetailLvSpecificationWindowHBinding bind;
    private StoreDetailVMH.OrderFoodInfoBean.ProductsBean productBean;
    private int storeId; // 店铺id
    private int level1CurrentIndex = 0; // 一级规格索引
    private int level2CurrentIndex = 0; // 二级规格索引
    private StringBuilder selectedStr = new StringBuilder(); // 选择的规格属性
    private boolean addOption = false;  // 是否是添加操作
    private ShoppingCartProduct currentProductBean; // 当前商品规格

    private static ValueAnimator windowValueAnimator;
    private static int WINDOW_ANIMATOR_TIME = 300;
    public static boolean windowAnimatorEnd = true; // 动画是否结束
    public static boolean isShowWindow = false; // 对话框是否显示

    private static ViewGroup parentView; // 父级
    private static int showLayersIndex = 0; // 在父级中显示的图层位置 从 0 开始
    public static StoreDetailSpecificationWindowH instance;

    /**
     * 创建 实例
     *
     * @param context
     * @param productBean 商品数据
     * @param storeId     店铺id
     * @return
     */
    private static StoreDetailSpecificationWindowH createInstance(@NonNull Context context,
                                                                  StoreDetailVMH.OrderFoodInfoBean.ProductsBean productBean,
                                                                  int storeId) {
        if (instance == null) {
            instance = new StoreDetailSpecificationWindowH(context, productBean, storeId);
        }
        return instance;
    }

    /**
     * 显示 规格对话框
     *
     * @param context
     * @param productBean 商品数据
     * @param storeId     店铺id
     * @param parent      父级View
     * @param layersIndex 显示在父级View中的图层位置，从0开始
     */
    public static void show(@NonNull Context context,
                            StoreDetailVMH.OrderFoodInfoBean.ProductsBean productBean,
                            int storeId,
                            ViewGroup parent,
                            int layersIndex) {
        parentView = parent;
        showLayersIndex = layersIndex;
        StoreDetailSpecificationWindowH windowH = StoreDetailSpecificationWindowH.createInstance(context, productBean, storeId);
        parentView.addView(windowH, parentView.getChildCount() - showLayersIndex);
        windowValueAnimator = ValueAnimator.ofFloat(0, 1f);
        windowValueAnimator.setDuration(WINDOW_ANIMATOR_TIME);
        windowValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                windowH.setAlpha(animatedValue);
            }
        });
        windowValueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                windowAnimatorEnd = false;
                isShowWindow = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                windowAnimatorEnd = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                dismiss();
                windowAnimatorEnd = true;
                isShowWindow = false;
            }
        });
        windowValueAnimator.start();
    }

    /**
     * 删除商品规格 对话框
     */
    public static void dismiss() {
        if (parentView == null) return;
        if (parentView.getChildCount() == 0 || !(parentView.getChildAt(parentView.getChildCount() - (showLayersIndex + 1)) instanceof StoreDetailSpecificationWindowH))
            return;
        StoreDetailSpecificationWindowH lastView = (StoreDetailSpecificationWindowH) parentView.getChildAt(parentView.getChildCount() - (showLayersIndex + 1));
        windowValueAnimator = ValueAnimator.ofFloat(1f, 0);
        windowValueAnimator.setDuration(WINDOW_ANIMATOR_TIME);
        windowValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                lastView.setAlpha(animatedValue);
            }
        });
        windowValueAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                windowAnimatorEnd = false;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (parentView.getChildAt(parentView.getChildCount() - (showLayersIndex + 1)) instanceof StoreDetailSpecificationWindowH) {
                    parentView.removeViewAt(parentView.getChildCount() - (showLayersIndex + 1));
                }
                windowAnimatorEnd = true;
                isShowWindow = false;
                stopAnimator();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                if (parentView.getChildAt(parentView.getChildCount() - (showLayersIndex + 1)) instanceof StoreDetailSpecificationWindowH) {
                    parentView.removeViewAt(parentView.getChildCount() - (showLayersIndex + 1));
                }
                windowAnimatorEnd = true;
                isShowWindow = false;
                stopAnimator();
            }
        });
        windowValueAnimator.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        windowAnimatorEnd = true;
        isShowWindow = false;
        parentView = null;
        showLayersIndex = 0;
        instance = null;
        stopAnimator();
    }

    private static void stopAnimator() {
        if (windowValueAnimator != null) {
            windowValueAnimator.setDuration(0);
            windowValueAnimator.cancel();
            windowValueAnimator = null;
        }
    }

    @SuppressLint("ResourceAsColor")
    private StoreDetailSpecificationWindowH(@NonNull Context context,
                                            StoreDetailVMH.OrderFoodInfoBean.ProductsBean productBean,
                                            int storeId) {
        super(context);
        this.storeId = storeId;
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(params);
        setAlpha(0);
        setBackgroundColor(com.example.baselibrary.R.color.color_66000000);
        initData(productBean);
    }

    private void initData(StoreDetailVMH.OrderFoodInfoBean.ProductsBean productBean) {
        this.productBean = productBean;
        initView();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        if (bind == null) {
            return true;
        } else {
            /**
             * 点击 对话框外的区域时，对话框隐藏
             */
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                if (StoreDetailSpecificationWindowH.windowAnimatorEnd) {
                    int[] location = new int[2];
                    bind.windowContent.getLocationOnScreen(location);
                    int left = location[0];
                    int right = location[0] + bind.windowContent.getWidth();
                    int top = location[1];
                    int bottom = location[1] + bind.windowContent.getHeight();
                    if ((ev.getX() < left || ev.getX() > right) ||
                            (ev.getY() < top || ev.getY() > bottom)) {
                        StoreDetailCoordinatorH.removeSpecificationWindow();
                    }
                }
            }
        }
        return true;
    }

    public void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.store_detail_lv_specification_window_h, this);
        View firstView = getChildAt(0);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) firstView.getLayoutParams();
        params.gravity = Gravity.CENTER;
        params.setMargins(DisplayUtils.dp2px(getContext(), 16), 0, DisplayUtils.dp2px(getContext(), 16), 0);
        firstView.setLayoutParams(params);
        bind = StoreDetailLvSpecificationWindowHBinding.bind(firstView);
        bind.setProduct(productBean);
        bind.productPrice.setText(MoneyConvertUtils.changeF2Y(getContext(), String.valueOf(productBean.getPrice())));

        currentProductBean = ShoppingCartUtils.deepCopy(productBean, 0, 0);
        bind.addCartBtn.setOnClickListener(this);
        bind.addBtnBox.setOnClickListener(this);
        bind.reduceBtnBox.setOnClickListener(this);
        level1Group();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_cart_btn) {
            updateProductCount(productBean, true);
        } else if (v.getId() == R.id.add_btn_box) {
            updateProductCount(productBean, true);
        } else if (v.getId() == R.id.reduce_btn_box) {
            updateProductCount(productBean, false);
        }
    }

    /**
     * 更新商品数量
     */
    private void updateProductCount(StoreDetailVMH.OrderFoodInfoBean.ProductsBean product, boolean add) {
        addOption = add;
        ShoppingCartProduct productVMH = ShoppingCartUtils.deepCopy(product, level1CurrentIndex, level2CurrentIndex);
        if (add) {
            ShoppingCartUtils.addProductToCart(productVMH, storeId);
        } else {
            ShoppingCartUtils.reduceProductToCart(productVMH, storeId);
        }
        if (addOption) {
            addCartAnimator();
        }
        isToShoppingCart();
    }

    /**
     * 更新一级列表状态
     *
     * @param index
     */
    private void updateLevel1GroupState(int index) {
        for (int i = 0; i < bind.specificationGroup.getChildCount(); i++) {
            TextView childView = (TextView) bind.specificationGroup.getChildAt(i);
            if (i == index) {
                highlightStyle(childView);
            } else {
                defaultStyle(childView);
            }
        }
        level2CurrentIndex = 0;
        selectedStr(index, 0);
        bind.selectedStr.setText(selectedStr.toString());
    }

    /**
     * 更新二级列表状态
     *
     * @param parentIndex 一级规格索引
     * @param index       二级规格索引
     */
    private void updateLevel2GroupState(int parentIndex, int index) {
        for (int i = 0; i < bind.attributesGroup.getChildCount(); i++) {
            TextView childView = (TextView) bind.attributesGroup.getChildAt(i);
            if (i == index) {
                highlightStyle(childView);
            } else {
                defaultStyle(childView);
            }
        }
        selectedStr(parentIndex, index);
    }

    /**
     * 一级列表
     */
    private void level1Group() {
        for (int i = 0; i < productBean.getSpecifications().size(); i++) {
            TextView option = new TextView(getContext());
            option.setGravity(Gravity.CENTER);
            option.setSingleLine(true);
            option.setEllipsize(TextUtils.TruncateAt.END);
            option.setPadding(
                    DisplayUtils.dp2px(getContext(), 12),
                    0,
                    DisplayUtils.dp2px(getContext(), 12),
                    0
            );
            option.setText(productBean.getSpecifications().get(i).getSpecification_title());
            bind.specificationGroup.addView(option);
            FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(
                    FlexboxLayout.LayoutParams.WRAP_CONTENT,
                    DisplayUtils.dp2px(getContext(), 32));
            params.topMargin = DisplayUtils.dp2px(getContext(), 12);
            params.rightMargin = DisplayUtils.dp2px(getContext(), 12);
            params.setMaxWidth(DisplayUtils.dp2px(getContext(), 100));
            params.setMinWidth(DisplayUtils.dp2px(getContext(), 90));
            option.setLayoutParams(params);

            if (i == 0) {
                highlightStyle(option);
            } else {
                defaultStyle(option);
            }

            int finalI = i;
            option.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    addOption = false;
                    if (finalI == level1CurrentIndex) return;
                    if (productBean.getSpecifications().size() > 0) {
                        updateLevel1GroupState(finalI);
                        level2Group(finalI, productBean.getSpecifications().get(finalI));
                    }
                    level1CurrentIndex = finalI;
                }
            });
        }

        if (productBean.getSpecifications().size() > 0) {
            selectedStr(0, 0);
            level2Group(0, productBean.getSpecifications().get(0));
        }

    }

    /**
     * 联动二级列表
     *
     * @param parentIndex
     * @param specificationsBean
     */
    private void level2Group(int parentIndex, StoreDetailVMH.OrderFoodInfoBean.ProductsBean.SpecificationsBean specificationsBean) {
        bind.attributesGroup.removeAllViews();
        for (int i = 0; i < specificationsBean.getAttributes().size(); i++) {
            TextView option = new TextView(getContext());
            option.setGravity(Gravity.CENTER);
            option.setSingleLine(true);
            option.setMaxWidth(DisplayUtils.dp2px(getContext(), 100));
            option.setEllipsize(TextUtils.TruncateAt.END);
            option.setPadding(
                    DisplayUtils.dp2px(getContext(), 12),
                    0,
                    DisplayUtils.dp2px(getContext(), 12),
                    0
            );
            option.setText(specificationsBean.getAttributes().get(i).getAttribute_title());
            bind.attributesGroup.addView(option);
            FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(
                    FlexboxLayout.LayoutParams.WRAP_CONTENT,
                    DisplayUtils.dp2px(getContext(), 32));
            params.topMargin = DisplayUtils.dp2px(getContext(), 12);
            params.rightMargin = DisplayUtils.dp2px(getContext(), 12);
            params.setMaxWidth(DisplayUtils.dp2px(getContext(), 120));
            params.setMinWidth(DisplayUtils.dp2px(getContext(), 90));
            option.setLayoutParams(params);

            if (i == 0) {
                highlightStyle(option);
            } else {
                defaultStyle(option);
            }

            int finalI = i;
            option.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    addOption = false;
                    if (finalI == level2CurrentIndex) return;
                    if (specificationsBean.getAttributes().size() > 0) {
                        updateLevel2GroupState(parentIndex, finalI);
                    }
                    level2CurrentIndex = finalI;
                }
            });
        }
    }

    /**
     * 是否加入了购物车，拿到数量
     */
    private void isToShoppingCart() {
        ShoppingCartProduct shoppingCartProduct = ShoppingCartUtils.toShoppingCartFindProduct(currentProductBean, storeId);
        if (shoppingCartProduct == null || shoppingCartProduct.getCount() == 0) {
            bind.addCartBtn.setVisibility(View.VISIBLE);
            bind.productCountBox.setVisibility(View.GONE);
        } else {
            bind.addCartBtn.setVisibility(View.GONE);
            bind.productCountBox.setVisibility(View.VISIBLE);
            bind.productCount.setText(shoppingCartProduct.getCount() + "");
        }
    }

    /**
     * 选中的文本
     *
     * @param parentIndex
     * @param index
     */
    private void selectedStr(int parentIndex, int index) {
        currentProductBean = ShoppingCartUtils.deepCopy(productBean, parentIndex, index);
        selectedStr.delete(0, selectedStr.length());
        selectedStr.append(productBean.getSpecifications().get(parentIndex).getSpecification_title());
        selectedStr.append("、" + productBean.getSpecifications().get(parentIndex).getAttributes().get(index).getAttribute_title());
        bind.selectedStr.setText(selectedStr.toString());
        isToShoppingCart();
    }

    /**
     * 默认样式
     *
     * @param btn
     */
    private void defaultStyle(TextView btn) {
        GradientDrawable drawable = ShapeUtils.newShape()
                .setCornerRadius(DisplayUtils.dp2px(getContext(), 18))
                .setColor(getResources().getColor(com.example.baselibrary.R.color.color_F5F6F7))
                .setStroke(0, getResources().getColor(android.R.color.transparent))
                .getDrawable();
        btn.setTextAppearance(getContext(), com.example.baselibrary.R.style.Font_303133_13);
        btn.setBackground(drawable);
    }

    /**
     * 高亮样式
     *
     * @param btn
     */
    private void highlightStyle(TextView btn) {
        GradientDrawable drawable = ShapeUtils.newShape()
                .setCornerRadius(DisplayUtils.dp2px(getContext(), 18))
                .setColor(getResources().getColor(com.example.baselibrary.R.color.color_14F9230A))
                .setStroke(DisplayUtils.dip2px(getContext(), 0.6f), getResources().getColor(com.example.baselibrary.R.color.color_F9230A))
                .getDrawable();
        btn.setTextAppearance(getContext(), com.example.baselibrary.R.style.Font_F9230A_13);
        btn.setBackground(drawable);
    }

    /**
     * 添加购物车 动画
     */
    private void addCartAnimator() {
        int[] startLocalPosition = getAddBtnLocalPosition();
        int[] endLocalPosition = StoreDetailCoordinatorH.getCartViewLocalPosition();
        ShoppingCartUtils.addCartAnimator(getContext(),
                (ViewGroup) getRootView(),
                startLocalPosition,
                endLocalPosition,
                currentProductBean);
    }


    /**
     * 获取添加按钮 在屏幕中的位置
     *
     * @return
     */
    public int[] getAddBtnLocalPosition() {
        int[] location = new int[2];
        if (bind == null) return location;
        if (bind.addCartBtn.getVisibility() == View.VISIBLE) {
            bind.addCartBtn.getLocationOnScreen(location);
        } else {
            bind.addBtn.getLocationOnScreen(location);
        }
        return location;
    }

}