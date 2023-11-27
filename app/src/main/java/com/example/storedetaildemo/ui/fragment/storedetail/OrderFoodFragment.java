package com.example.storedetaildemo.ui.fragment.storedetail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storedetaildemo.R;
import com.example.storedetaildemo.adapter.ProductListAdapter;
import com.example.storedetaildemo.adapter.SelectListAdapter;
import com.example.storedetaildemo.behavior.StoreDetailBehavior;
import com.example.storedetaildemo.bean.DetailProductBean;
import com.example.storedetaildemo.bean.DetailSelectBean;
import com.example.storedetaildemo.common.FloatDecoration;
import com.example.storedetaildemo.common.base.BaseFragment;
import com.example.storedetaildemo.databinding.FragmentOrderFoodBinding;
import com.example.storedetaildemo.ext.CenterLayoutManager;
import com.example.storedetaildemo.ui.widget.StoreDetailPagerView2Ho;
import com.example.storedetaildemo.ui.widget.StoreDetailRootViewHo;
import com.example.storedetaildemo.util.DisplayUtils;

import java.util.List;

/**
 * 点餐页面
 */
public class OrderFoodFragment extends BaseFragment implements StoreDetailPagerView2Ho.ScrollableViewProvider {

    private FragmentOrderFoodBinding binding;
    private boolean mIsTouchRvSelectList = false;
    private boolean mIsTouchRvProductList = false;
    private SelectListAdapter selectListAdapter;
    private ProductListAdapter productListAdapter;
    private RecyclerView.State mRvState = new RecyclerView.State();
    private StoreDetailBehavior.LayoutExpandControl mLayoutControl;
    private List<DetailSelectBean.ProductSelectBean> productSelect;
    private List<DetailProductBean.ProductsBean> products;
    private View currentScrollView;

    private int currentSelectIndex = 0;
    private CenterLayoutManager centerLayoutManager;
    private LinearLayoutManager mLinearLayoutManager;

    public static String currentScrollViewTag;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderFoodBinding.inflate(
                inflater,
                container,
                false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    public void initData(DetailSelectBean selectBean, DetailProductBean productBean) {
        this.productSelect = selectBean.getProduct_select();
        this.products = productBean.getProducts();
    }

    private void initView() {
        selectListAdapter = new SelectListAdapter(getContext(), productSelect);
        productListAdapter = new ProductListAdapter(getContext(), products);

        centerLayoutManager = new CenterLayoutManager(getContext());
        binding.selectList.setLayoutManager(centerLayoutManager);
        binding.selectList.setAdapter(selectListAdapter);

        mLinearLayoutManager = new LinearLayoutManager(getContext());
        binding.productList.setLayoutManager(mLinearLayoutManager);
        binding.productList.setAdapter(productListAdapter);

        // 左边分类选项 RecyclerView的Item点击事件监听
        selectListAdapter.setCallback(new SelectListAdapter.Callback() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClickItem(int position) {
                if (position <= (products.size() - 1)) {
                    for (int i = 0; i < (products.size() - 1); i++) {
                        if (productSelect.get(position).getId() == products.get(i).getSort_group_id()) {
                            // 整体布局没有向上偏移到最大值，且当前选中索引和上一个选中索引不一致
                            if (!mLayoutControl.arriveTopMax() && currentSelectIndex != position) {
                                mLayoutControl.offsetTopMax(); // 整体布局向上偏移
                            }

                            if (binding.selectList.getLayoutParams().height != binding.getRoot().getHeight() ||
                                    binding.productList.getLayoutParams().height != binding.getRoot().getHeight()) {

                                binding.selectList.getLayoutParams().height = binding.getRoot().getHeight();
                                binding.productList.getLayoutParams().height = binding.getRoot().getHeight();
                                binding.selectList.setLayoutParams(binding.selectList.getLayoutParams());
                                binding.productList.setLayoutParams(binding.productList.getLayoutParams());
                            }

                            selectListAdapter.currentPosition = position;
                            selectListAdapter.notifyDataSetChanged();

                            // 右边菜品 RecyclerView 将指定 item 滚动到可见第一条
                            mLinearLayoutManager.scrollToPositionWithOffset(i, 0);
                            break; // 别忘了break
                        }
                    }
                    currentSelectIndex = position;
                }
            }
        });

        // 右边 RecyclerView 添加悬浮吸顶装饰
        binding.productList.addItemDecoration(
                new FloatDecoration(
                        getContext(),
                        true,
                        binding.productList,
                        R.layout.widget_detail_product_group,
                        new FloatDecoration.DecorationCallback() {
                            @Override
                            public int getDecorationFlag(int position) {
                                if (position <= (products.size() - 1)) {
                                    // 根据不同商品分类，区分不同条目装饰View的 id
                                    return products.get(position).getSort_group_id();
                                }
                                return -1;
                            }

                            @Override
                            public void onBindView(View decorationView, int position) {
                                if (position <= (products.size() - 1)) {
                                    // 装饰 View 数据绑定
                                    TextView tvGroupName = decorationView.findViewById(R.id.tv_group_name);

                                    String str = products.get(position).getSort_group_name() + "（" + products.get(position).getSort_group_describe() + "）";
                                    SpannableStringBuilder spannableStr = new SpannableStringBuilder(str);

                                    // 字体颜色
                                    ForegroundColorSpan groupNameColor = new ForegroundColorSpan(getResources().getColor(R.color.color_303133, null));

                                    // 字体大小
                                    AbsoluteSizeSpan groupNameSize = new AbsoluteSizeSpan(DisplayUtils.dp2px(getContext(), 15));

                                    // 字体颜色
                                    ForegroundColorSpan describeColor = new ForegroundColorSpan(getResources().getColor(R.color.color_909399, null));

                                    // 字体大小
                                    AbsoluteSizeSpan describeSize = new AbsoluteSizeSpan(DisplayUtils.dp2px(getContext(), 12));

                                    spannableStr.setSpan(groupNameColor, 0, products.get(position).getSort_group_name().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE); // 字体颜色
                                    spannableStr.setSpan(groupNameSize, 0, products.get(position).getSort_group_name().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE); // 字体大小

                                    spannableStr.setSpan(describeColor, products.get(position).getSort_group_name().length(), str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE); // 字体颜色
                                    spannableStr.setSpan(describeSize, products.get(position).getSort_group_name().length(), str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE); // 字体大小

                                    tvGroupName.setText(spannableStr);
                                }
                            }
                        }
                )
        );

        // 右边 RecyclerView 滚动监听
        binding.productList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // 实现右边滚动联动左边 RecyclerView
                LinearLayoutManager layoutManager = (LinearLayoutManager) binding.productList.getLayoutManager();
                assert layoutManager != null;
                final int position = layoutManager.findFirstVisibleItemPosition();
                if (productSelect.get(selectListAdapter.currentPosition).getId() != products.get(position).getSort_group_id()) {
                    for (int i = 0; i < productSelect.size(); i++) {
                        if (productSelect.get(i).getId() == products.get(position).getSort_group_id()) {
                            selectListAdapter.currentPosition = i;
                            selectListAdapter.notifyDataSetChanged();
                            centerLayoutManager.smoothScrollToPosition(
                                    binding.selectList,
                                    mRvState,
                                    selectListAdapter.currentPosition
                            );
                            currentSelectIndex = selectListAdapter.currentPosition;
                            break; // 别忘了break
                        }
                    }
                }
            }
        });

        // NestedScrollView嵌套RecyclerView时，RecyclerView的高度是无限大
        // 所以要将RecyclerView设置固定高度
        binding.getRoot().post(new Runnable() {
            @Override
            public void run() {
                binding.selectList.getLayoutParams().height = binding.getRoot().getHeight(); // 使用NestedScrollView的高度
                binding.productList.getLayoutParams().height = binding.getRoot().getHeight();
                binding.selectList.setLayoutParams(binding.selectList.getLayoutParams());
                binding.productList.setLayoutParams(binding.productList.getLayoutParams());
            }
        });
    }

    @Override
    public View getScrollableView() {
        final int scrollViewPositionY = StoreDetailRootViewHo.viewPager2PositionY;
        final int positionX = StoreDetailRootViewHo.firstDownPositionX;
        final int positionY = StoreDetailRootViewHo.firstDownPositionY;
        currentScrollView = binding.getRoot();

        if (positionY >= scrollViewPositionY) {
            if (positionX >= binding.selectList.getLeft() && positionX <= binding.selectList.getRight()) {
                getCurrentScrollView(binding.selectList, "leftScrollView");
                // Log.d("TAG","选择列表");
                mIsTouchRvProductList = false;
                mIsTouchRvSelectList = true;
                return currentScrollView;
            } else if (positionX >= binding.productList.getLeft() && positionX <= binding.productList.getRight()) {
                getCurrentScrollView(binding.productList, "rightScrollView");
                // Log.d("TAG","商品列表");
                mIsTouchRvSelectList = false;
                mIsTouchRvProductList = true;
                return currentScrollView;
            }
        }

        getCurrentScrollView(binding.getRoot(), "rootScrollView");
        mIsTouchRvSelectList = false;
        mIsTouchRvProductList = false;

        // Log.d("TAG","根列表");
        return currentScrollView;
    }

    @Override
    public View getRootScrollView() {
        return binding.getRoot();
    }

    @Override
    public void parentOnDown(MotionEvent event) {
        // Log.d("TAG", "点餐菜 --- 手指按下");

        // 先停止滚动，避免之前列表的惯性滚动还没结束
        // 假设 A滚动列表正在惯性滚动时，手指触摸到B滚动列表时，A滚动列表会立刻停止滚动，
        // 但是！注意了！这只是：
        //       ！！暂停滚动！！    ！！暂停滚动！！
        //  此时的惯性任务还在执行，如果当手指抬起up事件时，A滚动列表 速度没有消耗完，
        //  会继续执行 dispatchNestedPreScroll，CoordinatorLayout.Behavior中onNestedPreScroll也会执行，
        //  引发一系列奇怪的bug，所以要先 stopNestedScroll，再重新 startNestedScroll

        if (binding.selectList.getScrollState() == RecyclerView.SCROLL_STATE_SETTLING) { // 列表正在滚动
            binding.selectList.stopScroll();
            binding.selectList.stopNestedScroll(ViewCompat.TYPE_NON_TOUCH);
            binding.selectList.startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL, ViewCompat.TYPE_TOUCH);
        }

        if (binding.productList.getScrollState() == RecyclerView.SCROLL_STATE_SETTLING) { // 列表正在滚动
            binding.productList.stopScroll();
            binding.productList.stopNestedScroll(ViewCompat.TYPE_NON_TOUCH);
            binding.productList.startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL, ViewCompat.TYPE_TOUCH);
        }
    }

    @Override
    public void parentOnUp(MotionEvent event) {
        // Log.d("TAG", "点餐菜 --- 手指抬起");
    }

    @Override
    public boolean offsetScrollView(int dy, boolean direction, boolean fling) {
        RecyclerView rv = binding.productList;
        final int positionX = StoreDetailRootViewHo.firstDownPositionX;
        if (positionX >= binding.selectList.getLeft() && positionX <= binding.selectList.getRight()) {
            rv = binding.selectList;
            getCurrentScrollView(binding.selectList, "leftScrollView");
            mIsTouchRvProductList = false;
            mIsTouchRvSelectList = true;
        } else if (positionX >= binding.productList.getLeft() && positionX <= binding.productList.getRight()) {
            rv = binding.productList;
            getCurrentScrollView(binding.productList, "rightScrollView");
            mIsTouchRvSelectList = false;
            mIsTouchRvProductList = true;
        } else {
            getCurrentScrollView(binding.getRoot(), "rootScrollView");
            mIsTouchRvSelectList = false;
            mIsTouchRvProductList = false;
        }

        // 如果当前滚动列表无法滚动了，使用另一个可以滚动的列表
        boolean leftFormTopScrollView = binding.selectList.canScrollVertically(1);
        boolean leftFromBottomScrollView = binding.selectList.canScrollVertically(-1);

        boolean rightFromTopScrollView = binding.productList.canScrollVertically(1);
        boolean rightFromBottomScrollView = binding.productList.canScrollVertically(-1);

        if (mIsTouchRvSelectList) {
            if (direction) {
                if (!leftFormTopScrollView && rightFromTopScrollView) {
                    rv = binding.productList;
                    getCurrentScrollView(binding.selectList, "rightScrollView");
                }
            } else {
                if (!leftFromBottomScrollView && rightFromBottomScrollView) {
                    rv = binding.productList;
                    getCurrentScrollView(binding.selectList, "rightScrollView");
                }
            }
        } else if (mIsTouchRvProductList) {
            if (direction) {
                if (!rightFromTopScrollView && leftFormTopScrollView) {
                    rv = binding.selectList;
                    getCurrentScrollView(binding.selectList, "leftScrollView");
                }
            } else {
                if (!rightFromBottomScrollView && leftFromBottomScrollView) {
                    rv = binding.selectList;
                    getCurrentScrollView(binding.selectList, "leftScrollView");
                }
            }
        }

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

    public void getCurrentScrollView(View scrollView, String scrollViewTag) {
        currentScrollView = scrollView;
        currentScrollViewTag = scrollViewTag;
    }

    public void setLayoutExpandControl(StoreDetailBehavior.LayoutExpandControl layoutExpandControl) {
        mLayoutControl = layoutExpandControl;
    }

}