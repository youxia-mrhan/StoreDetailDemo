package com.example.home_store_detail.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baselibrary.decoration.ListItemDividerDecoration;
import com.example.baselibrary.ui.fragment.BaseFragment;
import com.example.baselibrary.util.DisplayUtils;
import com.example.baselibrary.util.ScreenUtils;
import com.example.home_store_detail.R;
import com.example.home_store_detail.adapter.StoreDetailEvaluateAdpH;
import com.example.home_store_detail.viewmodel.StoreDetailVMH;
import com.example.home_store_detail.behavior.StoreDetailTabPageBehaviorH;
import com.example.home_store_detail.databinding.StoreDetailFrmEvaluateHBinding;
import com.example.home_store_detail.ui.view.StoreDetailCoordinatorH;
import com.example.home_store_detail.ui.view.StoreDetailEvaluateTagH;
import com.example.home_store_detail.ui.view.StoreDetailNestedScrollViewH;
import com.example.home_store_detail.ui.view.StoreDetailTabPageH;
import com.example.provider.common.App;

import java.util.List;

/**
 * 评价页面
 */
public class StoreDetailEvaluateFrmH extends BaseFragment implements StoreDetailTabPageH.ScrollableViewProvider {

    private StoreDetailFrmEvaluateHBinding bind;
    private StoreDetailVMH.EvaluateInfoBean evaluateInfo;
    private StoreDetailTabPageBehaviorH.LayoutExpandControl mLayoutControl;
    private int currentIndex = 0;
    private int maxHeight = 0; // 整体容器高度 + 评分容器所占区域 高度

    private StoreDetailTabPageBehaviorH tabPageBehavior;

    public void setTabPageBehavior(StoreDetailTabPageBehaviorH tabPageBehavior) {
        this.tabPageBehavior = tabPageBehavior;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = StoreDetailFrmEvaluateHBinding.inflate(inflater, container, false);
        return bind.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dividerDecoration = new ListItemDividerDecoration(getContext());
        bind.setEvaluateInfo(evaluateInfo);
        initView();
    }

    public void initData(StoreDetailVMH.EvaluateInfoBean evaluateInfo) {
        this.evaluateInfo = evaluateInfo;
    }

    private void initView() {
        scoreView();
        evaluateTagGroup();
        bind.decorationBox.measure(0, 0);
        maxHeight = ScreenUtils.getScreenHeight(App.getInstance()) + bind.decorationBox.getMeasuredHeight();
    }

    private ListItemDividerDecoration dividerDecoration; // 底部灰色分割线

    /**
     * 加载评价列表
     *
     * @param position 索引
     */
    private void loadList(int position) {
        StoreDetailEvaluateAdpH evaluateAdpH = new StoreDetailEvaluateAdpH(getContext(), evaluateInfo.getEvaluates().get(position).getEvaluate_items());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        bind.evaluateList.removeItemDecoration(dividerDecoration); // RecyclerView复用机制，导致装饰器叠加，使用前 先删除之前的
        bind.evaluateList.addItemDecoration(dividerDecoration);
        bind.evaluateList.setLayoutManager(layoutManager);
        bind.evaluateList.setAdapter(evaluateAdpH);
    }

    /**
     * 初始化评价 标签按钮
     */
    private void evaluateTagGroup() {
        List<StoreDetailVMH.EvaluateInfoBean.EvaluatesBean> evaluates = evaluateInfo.getEvaluates();
        for (int i = 0; i < evaluates.size(); i++) {
            StoreDetailEvaluateTagH commentTagHo = new StoreDetailEvaluateTagH(getContext());

            if (i == (evaluates.size() - 1)) {
                commentTagHo.initData(evaluates.get(i), false, 1);
            } else {
                commentTagHo.initData(evaluates.get(i), true, 0);
            }

            if (i == 0) {
                commentTagHo.updateTagState(true);
            }

            int finalI = i;
            commentTagHo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (currentIndex == finalI) return;

                    // 整体布局没有向上偏移到最大值
                    if (!mLayoutControl.arriveTopMax()) {
                        mLayoutControl.offsetTopMax(); // 整体布局向上偏移
                    }

                    updateList(evaluates.get(finalI).getGroup_id());
                    currentIndex = finalI;
                }
            });

            bind.evaluateTypeGroup.addView(commentTagHo);
        }
        loadList(0);
    }

    /**
     * 更新评价列表
     *
     * @param groupId
     */
    private void updateList(int groupId) {
        for (int i = 0; i < bind.evaluateTypeGroup.getChildCount(); i++) {
            StoreDetailEvaluateTagH childView = (StoreDetailEvaluateTagH) bind.evaluateTypeGroup.getChildAt(i);
            if (childView.evaluatesBean.getGroup_id() == groupId) {
                childView.updateTagState(true);
                loadList(i);
            } else {
                childView.updateTagState(false);
            }
        }
    }

    /**
     * 评分View
     */
    private void scoreView() {
        float score = evaluateInfo.getStore_score();
        int intNumber = (int) score;

        boolean isInteger = false;
        if (intNumber == score) { // 是整数
            isInteger = true;
        }

        int px = DisplayUtils.dp2px(getContext(), 15);

        int count = 5;
        for (int i = 0; i < intNumber; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(R.mipmap.score_2);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            bind.storeStarGroup.addView(imageView);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            params.width = px;
            params.height = px;
            imageView.setLayoutParams(params);
        }

        if (count == intNumber) return;

        count -= intNumber;

        if (!isInteger) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(R.mipmap.score_1);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            bind.storeStarGroup.addView(imageView);
            LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            params2.width = px;
            params2.height = px;
            imageView.setLayoutParams(params2);
            count -= 1;
        }

        for (int i = 0; i < count; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(R.mipmap.score_0);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            bind.storeStarGroup.addView(imageView);
            LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            params3.width = px;
            params3.height = px;
            imageView.setLayoutParams(params3);
        }

    }

    @Override
    public void parentOnDown(MotionEvent event) {
        // Log.d("TAG","评价 --- 手指按下");
        onScrollStopNested();
    }

    @Override
    public void parentOnUp(MotionEvent event) {
        // Log.d("TAG","评价 --- 手指抬起");
        StoreDetailNestedScrollViewH root = (StoreDetailNestedScrollViewH) bind.getRoot();
        root.onStopNestedScroll(null);
    }

    @Override
    public boolean offsetScrollView(int dy, boolean direction, boolean fling) {
        if (bind == null) return false;
        RecyclerView rv = bind.evaluateList;

        // 先偏移 评分容器
        int carouselTranslationY = (int) bind.getRoot().getTranslationY();
        int pageDecorationHeight = bind.decorationBox.getHeight();
        if (direction) {
            if (Math.abs(carouselTranslationY) != pageDecorationHeight) { // 没有折叠完成
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
            } else { // 完全没有折叠

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
        if (bind.evaluateList.getScrollState() == RecyclerView.SCROLL_STATE_SETTLING) { // 列表正在滚动
            bind.evaluateList.stopScroll();
            bind.evaluateList.stopNestedScroll(ViewCompat.TYPE_NON_TOUCH);
        }
        StoreDetailNestedScrollViewH root = (StoreDetailNestedScrollViewH) bind.getRoot();
        root.scrollBy(0, 0);
        root.stopNestedScroll(ViewCompat.TYPE_NON_TOUCH);
    }

    @Override
    public int getTabPageDecorationHeight() {
        return bind.decorationBox.getHeight();
    }

    @Override
    public int getTabPageFloatHeight() {
        return bind.evaluateTypeGroup.getHeight();
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull StoreDetailTabPageH child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        // 业务扩展
    }

    /**
     * 获取当前 scrollView
     */
    @Override
    public View getScrollableView() {
        StoreDetailCoordinatorH.currentScrollViewTag = "评价页 - 列表";
        return bind.evaluateList;
    }

    /**
     * 获取 根View
     */
    @Override
    public View getRootScrollView() {
        // 评分容器 偏移时，是整个视图进行偏移，所以要动态补足布局高度
        if (Math.abs(bind.getRoot().getTranslationY()) != getTabPageDecorationHeight()) {
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) bind.getRoot().getLayoutParams();
            params.height = maxHeight;
            bind.getRoot().setLayoutParams(params);
        }
        return bind.getRoot();
    }

    @Override
    public View getMainScrollView() {
        StoreDetailCoordinatorH.currentScrollViewTag = "评价页 - 列表";
        return bind.evaluateList;
    }

    public void setLayoutExpandControl(StoreDetailTabPageBehaviorH.LayoutExpandControl layoutExpandControl) {
        mLayoutControl = layoutExpandControl;
    }

}