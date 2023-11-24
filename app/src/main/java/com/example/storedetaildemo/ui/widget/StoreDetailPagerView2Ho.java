package com.example.storedetaildemo.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.storedetaildemo.R;
import com.example.storedetaildemo.adapter.PageFragmentAdapter;
import com.example.storedetaildemo.behavior.StoreDetailBehavior;
import com.example.storedetaildemo.bean.CommentBean;
import com.example.storedetaildemo.bean.DetailProductBean;
import com.example.storedetaildemo.bean.DetailSelectBean;
import com.example.storedetaildemo.bean.MerchantBean;
import com.example.storedetaildemo.common.base.BaseView;
import com.example.storedetaildemo.databinding.WidgetDetailListHoBinding;
import com.example.storedetaildemo.ext.StoreDetailSimplePagerTitleView;
import com.example.storedetaildemo.ext.ViewPager2HelperEx;
import com.example.storedetaildemo.ui.fragment.storedetail.CommentFragment;
import com.example.storedetaildemo.ui.fragment.storedetail.MerchantFragment;
import com.example.storedetaildemo.ui.fragment.storedetail.OrderFoodFragment;
import com.example.storedetaildemo.util.DisplayUtils;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 点餐/评论/商家
 */
public class StoreDetailPagerView2Ho extends BaseView {

    private final WidgetDetailListHoBinding listHoBinding;
    private List<Fragment> fragments;
    public StoreDetailBehavior storeDetailBehavior;
    private List<String> tabs = new ArrayList<>(Arrays.asList("点餐", "评论", "商家"));
    public ViewPager2 storeTabView;
    public MagicIndicator storeTabLayout;
    private int currentPagerIndex = 0;

    public StoreDetailPagerView2Ho(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.widget_detail_list_ho, this);
        listHoBinding = WidgetDetailListHoBinding.bind(getChildAt(0));
    }

    public void initView(DetailSelectBean selectBean, DetailProductBean productBean,
                         CommentBean commentBean, MerchantBean merchantBean) {

        storeTabView = (ViewPager2) listHoBinding.detailTabView;
        storeTabLayout = (MagicIndicator) listHoBinding.detailTabLayout;

        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        // 是否充满，每个tab平均分布，默认false
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return tabs.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                StoreDetailSimplePagerTitleView simplePagerTitleView = new StoreDetailSimplePagerTitleView(context);
                if (index == 1) {
                    String commentCount = "";
                    if (commentBean.getTotal_comments_count() > 0) {
                        commentCount = " " + commentBean.getTotal_comments_count();
                        simplePagerTitleView.setText(tabs.get(index) + commentCount);

                        // 让指示器 处于标题前两个字 正下方
                        int leftPadding = 0; // 这是误差值

                        if (commentCount.length() == 2) { // 最小长度
                            leftPadding = 20;
                        } else if (commentCount.length() == 3) {
                            leftPadding = 40;
                        } else if (commentCount.length() == 4) {
                            leftPadding = 58;
                        } else if (commentCount.length() == 5) { // 最大长度
                            leftPadding = 74;
                        }
                        simplePagerTitleView.setPadding(leftPadding, 0, 0, 0);
                    } else {
                        simplePagerTitleView.setText(tabs.get(index));

                    }
                } else {
                    simplePagerTitleView.setText(tabs.get(index));
                }

                simplePagerTitleView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (StoreDetailInfoHo.dragPositionState != StoreDetailInfoHo.FLOAT) {
                            storeTabView.setCurrentItem(index);
                        }
                    }
                });

                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                String commentCount = "";
                if (commentBean.getTotal_comments_count() > 0) {
                    commentCount = " " + commentBean.getTotal_comments_count();
                }
                RadiusLinePagerIndicator indicator = new RadiusLinePagerIndicator(context, commentCount);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setColors(getResources().getColor(R.color.color_F9230A, null));

                //  indicator.setLineWidth(DisplayUtils.dp2px(context, 12));
                indicator.setLineHeight(DisplayUtils.dp2px(context, 3));

                // 圆角
                indicator.setRoundRadius(DisplayUtils.dp2px(context, 2));

                // 拉伸动画
                // indicator.setStartInterpolator(new AccelerateInterpolator());
                // indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));

                return indicator;
            }

        });
        storeTabLayout.setNavigator(commonNavigator);

        // 禁止滑动
        // storeTabView.setUserInputEnabled(false);

        OrderFoodFragment orderFoodFragment = new OrderFoodFragment();
        orderFoodFragment.setLayoutExpandControl(new StoreDetailBehavior.LayoutExpandControl() {
            @Override
            public void offsetTopMax() {
                if (storeDetailBehavior != null) {
                    storeDetailBehavior.offsetTopMax();
                }
            }

            @Override
            public boolean arriveTopMax() {
                if (storeDetailBehavior != null) {
                    return storeDetailBehavior.arriveTopMax();
                }
                return false;
            }
        });

        CommentFragment commentFragment = new CommentFragment();
        commentFragment.setLayoutExpandControl(new StoreDetailBehavior.LayoutExpandControl() {
            @Override
            public void offsetTopMax() {
                if (storeDetailBehavior != null) {
                    storeDetailBehavior.offsetTopMax();
                }
            }

            @Override
            public boolean arriveTopMax() {
                if (storeDetailBehavior != null) {
                    return storeDetailBehavior.arriveTopMax();
                }
                return false;
            }
        });

        MerchantFragment merchantFragment = new MerchantFragment();

        fragments = new ArrayList<>(Arrays.asList(orderFoodFragment, commentFragment, merchantFragment));
        PageFragmentAdapter adapter = new PageFragmentAdapter((FragmentActivity) getContext(), fragments);
        storeTabView.setAdapter(adapter);
        ViewPager2HelperEx.bind(storeTabLayout, storeTabView);

        orderFoodFragment.initData(selectBean, productBean);
        commentFragment.initData(commentBean);
        merchantFragment.initData(merchantBean);

        storeTabView.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if (storeDetailBehavior == null || storeDetailBehavior.getStoreDetailCartView() == null) {
                    return;
                }
                currentPagerIndex = position;
                if (position == 0) {
                    storeDetailBehavior.getStoreDetailCartView().hideOfShow(View.VISIBLE);
                } else {
                    storeDetailBehavior.getStoreDetailCartView().hideOfShow(View.GONE);
                }
            }
        });
    }

    public int getCurrentPagerIndex() {
        return currentPagerIndex;
    }

    public void setStoreDetailBehavior(StoreDetailBehavior storeDetailBehavior) {
        this.storeDetailBehavior = storeDetailBehavior;
    }

    public View getScrollableView() {
        ScrollableViewProvider provider = (ScrollableViewProvider) fragments.get(listHoBinding.detailTabView.getCurrentItem());
        return provider.getScrollableView();
    }

    public View getRootScrollView() {
        ScrollableViewProvider provider = (ScrollableViewProvider) fragments.get(listHoBinding.detailTabView.getCurrentItem());
        return provider.getRootScrollView();
    }

    public void parentOnDown(MotionEvent event) {
        ScrollableViewProvider provider = (ScrollableViewProvider) fragments.get(listHoBinding.detailTabView.getCurrentItem());
        provider.parentOnDown(event);
    }

    public void parentOnUp(MotionEvent event) {
        ScrollableViewProvider provider = (ScrollableViewProvider) fragments.get(listHoBinding.detailTabView.getCurrentItem());
        provider.parentOnUp(event);
    }

    public boolean sharedScrollVelocity(int dy, boolean direction, boolean fling) {
        ScrollableViewProvider provider = (ScrollableViewProvider) fragments.get(listHoBinding.detailTabView.getCurrentItem());
        return provider.offsetScrollView(dy, direction, fling);
    }

    public interface ScrollableViewProvider {
        View getScrollableView();

        default View getRootScrollView() {
            return null;
        }

        void parentOnDown(MotionEvent event);

        void parentOnUp(MotionEvent event);

        boolean offsetScrollView(int dy, boolean direction, boolean fling);
    }
}
