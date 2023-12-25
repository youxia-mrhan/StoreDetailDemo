package com.example.home_store_detail.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baselibrary.ui.fragment.BaseFragment;
import com.example.baselibrary.util.DisplayUtils;
import com.example.baselibrary.util.ScreenUtils;
import com.example.home_store_detail.R;
import com.example.home_store_detail.adapter.StoreDetailProductAdpH;
import com.example.home_store_detail.adapter.StoreDetailProductTypeAdpH;
import com.example.home_store_detail.behavior.StoreDetailTabPageBehaviorH;
import com.example.home_store_detail.databinding.StoreDetailFrmOrderFoodHBinding;
import com.example.home_store_detail.decoration.StoreDetailFloatDecorationH;
import com.example.home_store_detail.ex.StoreDetailCenterLayoutManagerH;
import com.example.home_store_detail.ui.view.StoreDetailCarouselH;
import com.example.home_store_detail.ui.view.StoreDetailCoordinatorH;
import com.example.home_store_detail.ui.view.StoreDetailNestedScrollViewH;
import com.example.home_store_detail.ui.view.StoreDetailTabPageH;
import com.example.home_store_detail.viewmodel.StoreDetailVMH;
import com.example.provider.common.App;

import java.util.List;

/**
 * 点餐页面
 */
public class StoreDetailOrderFoodFrmH extends BaseFragment implements StoreDetailTabPageH.ScrollableViewProvider {

    private StoreDetailFrmOrderFoodHBinding bind;
    private boolean mIsTouchRvLeftList = false; // 触摸了左边的列表
    private boolean mIsTouchRvRightList = false; // 触摸了右边的列表
    private StoreDetailTabPageBehaviorH.LayoutExpandControl mLayoutControl; // 布局整体向上偏移 回调
    private StoreDetailVMH.OrderFoodInfoBean orderFoodInfo;
    private View currentScrollView;
    private int currentSelectIndex = 0;
    private int maxHeight = 0; // 整体容器高度 + 轮播图所占区域 高度

    private StoreDetailTabPageBehaviorH tabPageBehavior;
    private StoreDetailProductTypeAdpH productTypeAdpH;

    private Handler mHandler;

    public StoreDetailOrderFoodFrmH() {
        mHandler = new Handler();
    }

    public void setTabPageBehavior(StoreDetailTabPageBehaviorH tabPageBehavior) {
        this.tabPageBehavior = tabPageBehavior;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = StoreDetailFrmOrderFoodHBinding.inflate(inflater, container, false);
        return bind.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    public void initData(StoreDetailVMH.OrderFoodInfoBean orderFoodInfo) {
        this.orderFoodInfo = orderFoodInfo;
    }

    public void setBannerItem() {
        bind.carousel.setBannerItem();
    }

    private void initView() {

        List<StoreDetailVMH.OrderFoodInfoBean.ProductTypeBean> productTypes = orderFoodInfo.getProduct_type(); // 商品类型 信息
        List<StoreDetailVMH.OrderFoodInfoBean.ProductsBean> products = orderFoodInfo.getProducts(); // 商品信息

        productTypeAdpH = new StoreDetailProductTypeAdpH(getContext(), productTypes);
        StoreDetailProductAdpH productAdpH = new StoreDetailProductAdpH(getContext(), this, orderFoodInfo.getStore_id(), products);
        StoreDetailCenterLayoutManagerH centerLayoutManagerH = new StoreDetailCenterLayoutManagerH(getContext()); // 让选中的商品类型 滚动时处于居中位置
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());

        // 优化，因为使用notifyDataSetChanged刷新，所以在快速滑动RecyclerView时有点卡顿，增加预加载item数量，默认2个
        centerLayoutManagerH.setInitialPrefetchItemCount(20);
        mLinearLayoutManager.setInitialPrefetchItemCount(20);

        // 轮播图
        bind.carousel.initView(orderFoodInfo.getCarousels());

        // 商品类型列表
        bind.productTypeList.setLayoutManager(centerLayoutManagerH);
        bind.productTypeList.setAdapter(productTypeAdpH);

        // 商品列表
        bind.productList.setLayoutManager(mLinearLayoutManager);
        bind.productList.setAdapter(productAdpH);

        // 左边类型选项 RecyclerView的Item点击事件监听
        productTypeAdpH.setCallback(new StoreDetailProductTypeAdpH.Callback() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClickItem(int position) {
                if (position <= (productTypes.size() - 1)) {
                    if (position == currentSelectIndex) { // 当前已经选中
                        return;
                    }

                    for (int i = 0; i < (products.size() - 1); i++) {
                        if (productTypes.get(position).getId() == products.get(i).getType_id()) {
                            // 整体布局没有向上偏移到最大值，且当前选中索引和上一个选中索引不一致
                            if (!mLayoutControl.arriveTopMax() && currentSelectIndex != position) {
                                mLayoutControl.offsetTopMax(); // 整体布局向上偏移
                            }

                            productTypeAdpH.notifyDataSetChanged();

                            // 右边菜品 RecyclerView 将指定 item 滚动到可见第一条
                            mLinearLayoutManager.scrollToPositionWithOffset(i, 0);
                            break; // 别忘了break
                        }
                    }
                    currentSelectIndex = position;
                    productTypeAdpH.currentPosition = currentSelectIndex;
                }
            }
        });

        // 右边商品 RecyclerView 添加悬浮吸顶装饰
        bind.productList.addItemDecoration(
                new StoreDetailFloatDecorationH(
                        getContext(),
                        true,
                        bind.productList,
                        R.layout.store_detail_lv_flot_decoration_h,
                        new StoreDetailFloatDecorationH.DecorationCallback() {
                            @Override
                            public int getDecorationFlag(int position) {
                                if (position <= (products.size() - 1)) {
                                    // 根据不同商品分类，区分不同条目装饰View的 id
                                    return products.get(position).getType_id();
                                }
                                return -1;
                            }

                            @Override
                            public void onBindView(View decorationView, int position) {
                                if (position <= (products.size() - 1)) {
                                    // 装饰 View 数据绑定
                                    TextView tvGroupName = decorationView.findViewById(R.id.product_type_title);

                                    String str = products.get(position).getType_name() + "（" + products.get(position).getType_describe() + "）";
                                    SpannableStringBuilder spannableStr = new SpannableStringBuilder(str);

                                    // 字体颜色
                                    ForegroundColorSpan groupNameColor = new ForegroundColorSpan(getResources().getColor(com.example.baselibrary.R.color.color_303133));

                                    // 字体大小
                                    AbsoluteSizeSpan groupNameSize = new AbsoluteSizeSpan(DisplayUtils.dp2px(getContext(), 15));

                                    // 字体颜色
                                    ForegroundColorSpan describeColor = new ForegroundColorSpan(getResources().getColor(com.example.baselibrary.R.color.color_909399));

                                    // 字体大小
                                    AbsoluteSizeSpan describeSize = new AbsoluteSizeSpan(DisplayUtils.dp2px(getContext(), 12));

                                    spannableStr.setSpan(groupNameColor, 0, products.get(position).getType_name().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE); // 字体颜色
                                    spannableStr.setSpan(groupNameSize, 0, products.get(position).getType_name().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE); // 字体大小

                                    spannableStr.setSpan(describeColor, products.get(position).getType_name().length(), str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE); // 字体颜色
                                    spannableStr.setSpan(describeSize, products.get(position).getType_name().length(), str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE); // 字体大小

                                    tvGroupName.setText(spannableStr);
                                }
                            }
                        }
                )
        );

        RecyclerView.State mRvState = new RecyclerView.State();

        // 右边商品 RecyclerView 滚动监听
        bind.productList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // 实现右边滚动联动左边 RecyclerView
                LinearLayoutManager layoutManager = (LinearLayoutManager) bind.productList.getLayoutManager();
                assert layoutManager != null;
                final int position = layoutManager.findFirstVisibleItemPosition();
                if (productTypes.get(productTypeAdpH.currentPosition).getId() != products.get(position).getType_id()) {
                    for (int i = 0; i < productTypes.size(); i++) {
                        if (productTypes.get(i).getId() == products.get(position).getType_id()) {
                            productTypeAdpH.currentPosition = i;

                            mHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    productTypeAdpH.notifyDataSetChanged();
                                }
                            }, 16); // 优化，延迟16毫秒再刷新 约等于1帧

                            centerLayoutManagerH.smoothScrollToPosition(
                                    bind.productTypeList,
                                    mRvState,
                                    productTypeAdpH.currentPosition
                            );
                            currentSelectIndex = productTypeAdpH.currentPosition;
                            break; // 别忘了break
                        }
                    }
                }
            }
        });
        bind.carousel.measure(0, 0);
        maxHeight = ScreenUtils.getScreenHeight(App.getInstance()) + bind.carousel.getMeasuredHeight();
    }

    @Override
    public View getScrollableView() {

        final int scrollViewPositionY = StoreDetailCoordinatorH.scrollViewPositionY;
        final int positionX = StoreDetailCoordinatorH.firstDownPositionX;
        final int positionY = StoreDetailCoordinatorH.firstDownPositionY;
        currentScrollView = bind.getRoot();

        if (positionY >= scrollViewPositionY) {
            if (positionX >= bind.productTypeList.getLeft() && positionX <= bind.productTypeList.getRight()) {
                getCurrentScrollView(bind.productTypeList, "点餐页 - 左边 - 商品类型列表");
                // Log.d("TAG","选择列表");
                mIsTouchRvRightList = false;
                mIsTouchRvLeftList = true;
                return currentScrollView;
            } else if (positionX >= bind.productList.getLeft() && positionX <= bind.productList.getRight()) {
                getCurrentScrollView(bind.productList, "点餐页 - 右边 - 商品列表");
                // Log.d("TAG","商品列表");
                mIsTouchRvLeftList = false;
                mIsTouchRvRightList = true;
                return currentScrollView;
            }
        }

        getCurrentScrollView(bind.getRoot(), "点餐页 - rootScrollView");
        mIsTouchRvLeftList = false;
        mIsTouchRvRightList = false;

        // Log.d("TAG","根列表");
        return currentScrollView;
    }

    @Override
    public void parentOnDown(MotionEvent event) {
        // Log.d("TAG", "点餐页 --- 手指按下");
        onScrollStopNested();
    }

    @Override
    public void parentOnUp(MotionEvent event) {
        // Log.d("TAG", "点餐页 --- 手指抬起");
        StoreDetailNestedScrollViewH root = (StoreDetailNestedScrollViewH) bind.getRoot();
        root.onStopNestedScroll(null);
    }

    @Override
    public boolean offsetScrollView(int dy, boolean direction, boolean fling) {
        if (bind == null) return false;

        RecyclerView rv = bind.productList;
        final int positionX = StoreDetailCoordinatorH.firstDownPositionX;
        if (positionX >= bind.productTypeList.getLeft() && positionX <= bind.productTypeList.getRight()) {
            rv = bind.productTypeList;
            getCurrentScrollView(bind.productTypeList, "点餐页 - 左边 - 商品类型列表");
            mIsTouchRvRightList = false;
            mIsTouchRvLeftList = true;
        } else if (positionX >= bind.productList.getLeft() && positionX <= bind.productList.getRight()) {
            rv = bind.productList;
            getCurrentScrollView(bind.productList, "点餐页 - 右边 - 商品列表");
            mIsTouchRvLeftList = false;
            mIsTouchRvRightList = true;
        } else {
            getCurrentScrollView(bind.getRoot(), "点餐页 - rootScrollView");
            mIsTouchRvLeftList = false;
            mIsTouchRvRightList = false;
        }

        // 如果当前滚动列表无法滚动了，使用另一个可以滚动的列表
        boolean leftFormTopScrollView = bind.productTypeList.canScrollVertically(1);
        boolean leftFromBottomScrollView = bind.productTypeList.canScrollVertically(-1);

        boolean rightFromTopScrollView = bind.productList.canScrollVertically(1);
        boolean rightFromBottomScrollView = bind.productList.canScrollVertically(-1);

        if (mIsTouchRvLeftList) {
            if (direction) { // 向上滑
                // 如果当前左边列表已经到底部无法滚动， 但 右边列表可以滚动，切换成右边列表 继续向上滚动
                if (!leftFormTopScrollView && rightFromTopScrollView) {
                    rv = bind.productList;
                    getCurrentScrollView(bind.productTypeList, "点餐页 - 右边 - 商品列表");
                }
            } else { // 向下滑
                // 如果当前左边列表已经到顶部无法滚动， 但 右边列表可以滚动，切换成右边列表 继续向下滚动
                if (!leftFromBottomScrollView && rightFromBottomScrollView) {
                    rv = bind.productList;
                    getCurrentScrollView(bind.productTypeList, "点餐页 - 右边 - 商品列表");
                }
            }
        } else if (mIsTouchRvRightList) {
            if (direction) { // 向上滑
                // 如果当前右边边列表已经到底部无法滚动， 但 左边边列表可以滚动，切换成左边列表 继续向上滚动
                if (!rightFromTopScrollView && leftFormTopScrollView) {
                    rv = bind.productTypeList;
                    getCurrentScrollView(bind.productTypeList, "点餐页 - 左边 - 商品类型列表");
                }
            } else { // 向下滑
                // 如果当前右边列表已经到底部无法滚动， 但 左边列表可以滚动，切换成左边列表 继续向下滚动
                if (!rightFromBottomScrollView && leftFromBottomScrollView) {
                    rv = bind.productTypeList;
                    getCurrentScrollView(bind.productTypeList, "点餐页 - 左边 - 商品类型列表");
                }
            }
        }


        // 先偏移 轮播图
        int carouselTranslationY = (int) bind.getRoot().getTranslationY();
        int pageDecorationHeight = decorationHeight();
        if (direction) {
            if (Math.abs(carouselTranslationY) != pageDecorationHeight) { // 没有折叠
                float curY = carouselTranslationY - dy;
                if (Math.abs(curY) > pageDecorationHeight) {
                    curY = -pageDecorationHeight;
                }
                bind.getRoot().setTranslationY(curY);
                return true;
            } else { // 已经折叠完成

            }
        } else {
            if (Math.abs(carouselTranslationY) != 0) { // 处于折叠状态
                if (!rv.canScrollVertically(-1)) { // 已经下拉到顶时
                    float curY = carouselTranslationY + Math.abs(dy);
                    if (curY > 0) {
                        curY = 0;
                    }
                    bind.getRoot().setTranslationY(curY);
                    return true;
                }
            } else {

            }
        }

        // 滚动指定列表
        int cuDy = 0;
        if (direction) {
            if (rv.canScrollVertically(1)) {
                cuDy = Math.abs(dy);
                rv.scrollBy(0, cuDy);
                return true;
            }
        } else {
            if (rv.canScrollVertically(-1)) {
                cuDy = Math.abs(dy);
                rv.scrollBy(0, -cuDy);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onScrollStopNested() {
        // 停止嵌套滚动，避免之前列表的惯性滚动还没结束
        // 假设 A滚动列表正在惯性滚动时，手指触摸到B滚动列表时，A滚动列表会立刻停止滚动，
        // 但是！注意了！这只是：
        //       ！！暂停滚动！！    ！！暂停滚动！！
        //  此时的惯性任务还在执行，如果当手指抬起up事件时，A滚动列表 速度没有消耗完，
        //  会继续执行 dispatchNestedPreScroll，CoordinatorLayout.Behavior中onNestedPreScroll也会执行，
        //  引发一系列奇怪的bug，比如在惯性没有结束前，无法响应其他事件，所以要 stopNestedScroll

        if (bind.productTypeList.getScrollState() == RecyclerView.SCROLL_STATE_SETTLING) { // 列表正在滚动
            bind.productTypeList.stopScroll();
            bind.productTypeList.stopNestedScroll(ViewCompat.TYPE_NON_TOUCH);
        }
        if (bind.productList.getScrollState() == RecyclerView.SCROLL_STATE_SETTLING) { // 列表正在滚动
            bind.productList.stopScroll();
            bind.productList.stopNestedScroll(ViewCompat.TYPE_NON_TOUCH);
        }
        StoreDetailNestedScrollViewH root = (StoreDetailNestedScrollViewH) bind.getRoot();
        root.scrollBy(0, 0);
        root.stopNestedScroll(ViewCompat.TYPE_NON_TOUCH);
    }

    @Override
    public int getTabPageDecorationHeight() {
        return decorationHeight();
    }

    @Override
    public int getTabPageFloatHeight() {
        return 0;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull StoreDetailTabPageH child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        // 业务扩展
    }

    /**
     * 轮播图占据的高度
     *
     * @return
     */
    private int decorationHeight() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) bind.carousel.getLayoutParams();
        return bind.carousel.getHeight() + params.topMargin + params.bottomMargin;
    }

    /**
     * 获取当前 scrollView
     *
     * @param scrollView
     * @param scrollViewTag
     */
    public void getCurrentScrollView(View scrollView, String scrollViewTag) {
        currentScrollView = scrollView;
        StoreDetailCoordinatorH.currentScrollViewTag = scrollViewTag;
    }

    /**
     * 获取 根View
     */
    @Override
    public View getRootScrollView() {
        // 轮播图偏移时，是整个视图进行偏移，所以要动态补足布局高度
        if (Math.abs(bind.getRoot().getTranslationY()) != getTabPageDecorationHeight()) {
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) bind.getRoot().getLayoutParams();
            params.height = maxHeight;
            bind.getRoot().setLayoutParams(params);
        }
        return bind.getRoot();
    }

    @Override
    public View getMainScrollView() {
        getCurrentScrollView(bind.productList, "点餐页 - 右边 - 商品列表");
        return bind.productList;
    }

    public void setLayoutExpandControl(StoreDetailTabPageBehaviorH.LayoutExpandControl layoutExpandControl) {
        mLayoutControl = layoutExpandControl;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        mHandler = null;
    }
}