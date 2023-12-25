package com.example.home_store_detail.ui.view;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.baselibrary.ui.view.BaseView;
import com.example.baselibrary.util.DisplayUtils;
import com.example.baselibrary.util.ShapeUtils;
import com.example.home_store_detail.R;
import com.example.home_store_detail.viewmodel.StoreDetailVMH;
import com.example.home_store_detail.databinding.StoreDetailLvEvaluateItemHBinding;

import java.util.List;

/**
 * 评价项
 */
public class StoreDetailEvaluateItemH extends BaseView {

    public StoreDetailLvEvaluateItemHBinding bind;

    public StoreDetailEvaluateItemH(Context context) {
        super(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        setLayoutParams(params);
    }

    /**
     * 初始化view
     */
    public void initView(StoreDetailVMH.EvaluateInfoBean.EvaluatesBean.EvaluateItemsBean evaluateItemsBean) {
        removeAllViews();
        LayoutInflater.from(getContext()).inflate(R.layout.store_detail_lv_evaluate_item_h, this);
        bind = StoreDetailLvEvaluateItemHBinding.bind(getChildAt(0));
        bind.setEvaluateItems(evaluateItemsBean);
        setUserImage(evaluateItemsBean);
        levelView(evaluateItemsBean);
        scoreView(evaluateItemsBean);

        List<StoreDetailVMH.EvaluateInfoBean.EvaluatesBean.EvaluateItemsBean.ContentBean> content = evaluateItemsBean.getContent();
        for (int i = 0; i < content.size(); i++) {
            TextView container = new TextView(getContext());
            bind.evaluateContent.addView(container);
            container.setText(content.get(i).getMessage());
            if (content.get(i).getType() == 0) {
                userEvaluateView(container,i);
            } else {
                merchantEvaluateView(container,i);
            }
        }
    }

    /**
     * 用户评价 View
     */
    private TextView userEvaluateView(TextView container, int position) {
        container.setTextAppearance(getContext(),com.example.baselibrary.R.style.Font_303133_13);
        container.setEllipsize(TextUtils.TruncateAt.END);
        container.setLineSpacing(0,1.3f);
        container.setMaxLines(20);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) container.getLayoutParams();
        if (position != 0) {
            params.topMargin = DisplayUtils.dp2px(getContext(),10);
        }
        params.width = LinearLayout.LayoutParams.MATCH_PARENT;
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;

        return container;
    }

    /**
     * 商家回复 view
     */
    private View merchantEvaluateView(TextView container, int position) {
        container.setTextAppearance(getContext(),com.example.baselibrary.R.style.Font_909399_12);
        container.setEllipsize(TextUtils.TruncateAt.END);
        container.setLineSpacing(0,1.3f);
        container.setMaxLines(20);
        container.setPadding(
                DisplayUtils.dp2px(getContext(),12),
                DisplayUtils.dp2px(getContext(),12),
                DisplayUtils.dp2px(getContext(),12),
                DisplayUtils.dp2px(getContext(),9)
        );
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) container.getLayoutParams();
        if (position != 0) {
            params.topMargin = DisplayUtils.dp2px(getContext(),10);
        }
        params.width = LinearLayout.LayoutParams.MATCH_PARENT;
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;

        GradientDrawable drawable = ShapeUtils
                .newShape()
                .setCornerRadius(DisplayUtils.dp2px(getContext(), 4))
                .setColor(getContext().getResources().getColor(com.example.baselibrary.R.color.color_F5F6F7))
                .getDrawable();
        container.setBackground(drawable);

        return container;
    }

    /**
     * 用户评价头像
     */
    private void setUserImage(StoreDetailVMH.EvaluateInfoBean.EvaluatesBean.EvaluateItemsBean evaluateItemsBean) {
        switch (evaluateItemsBean.getUrl()) {
            case "evaluate_image01":
                bind.userUrl.setImageResource(com.example.baselibrary.R.mipmap.evaluate_image01);
                break;
            case "evaluate_image02":
                bind.userUrl.setImageResource(com.example.baselibrary.R.mipmap.evaluate_image02);
                break;
        }
    }

    /**
     * 评分View
     */
    private void scoreView(StoreDetailVMH.EvaluateInfoBean.EvaluatesBean.EvaluateItemsBean evaluateItemsBean) {
        float score = evaluateItemsBean.getScore();
        int intNumber = (int) score;

        boolean isInteger = false;
        if (intNumber == score) { // 是整数
            isInteger = true;
        }

        int count = 5;
        for (int i = 0; i < intNumber; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(R.mipmap.score_2);
            bind.starGroup.addView(imageView);
        }

        if (count == intNumber) return;

        count -= intNumber;

        if (!isInteger) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(R.mipmap.score_1);
            bind.starGroup.addView(imageView);
            count -= 1;
        }

        for (int i = 0; i < count; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(R.mipmap.score_0);
            bind.starGroup.addView(imageView);
        }

    }

    /**
     * 用户等级view
     */
    private void levelView(StoreDetailVMH.EvaluateInfoBean.EvaluatesBean.EvaluateItemsBean evaluateItemsBean) {
        int bgColor = getContext().getResources().getColor(com.example.baselibrary.R.color.color_FFBA00);
        switch (evaluateItemsBean.getLevel()) {
            case 0:
                bgColor = getContext().getResources().getColor(com.example.baselibrary.R.color.color_409EFF);
                break;
            case 1:
                bgColor = getContext().getResources().getColor(com.example.baselibrary.R.color.color_F9230A);
                break;
            case 2:
                bgColor = getContext().getResources().getColor(com.example.baselibrary.R.color.color_FFBA00);
                break;
            case 3:
                bgColor = getContext().getResources().getColor(com.example.baselibrary.R.color.color_17DE85);
                break;
        }
        levelViewDrawable(bgColor);
    }

    /**
     * 用户等级view 样式
     *
     * @param bgColor 背景
     */
    private void levelViewDrawable(int bgColor) {
        GradientDrawable drawable = ShapeUtils.newShape().setCornerRadius(
                        0f,
                        DisplayUtils.dip2px(getContext(), 10f),
                        DisplayUtils.dip2px(getContext(), 10f),
                        DisplayUtils.dip2px(getContext(), 10f)).
                setColor(bgColor).
                getDrawable();
        bind.userLevel.setBackground(drawable);
    }

}



















