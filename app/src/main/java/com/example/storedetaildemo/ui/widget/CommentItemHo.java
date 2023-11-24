package com.example.storedetaildemo.ui.widget;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.storedetaildemo.R;
import com.example.storedetaildemo.bean.CommentBean;
import com.example.storedetaildemo.databinding.WidgetCommentItemHoBinding;
import com.example.storedetaildemo.util.DisplayUtils;
import com.example.storedetaildemo.util.ShapeUtils;

/**
 * 评论项
 */
public class CommentItemHo extends FrameLayout {

    private final WidgetCommentItemHoBinding binding;
    private CommentBean.CommentsBean.CommentItemsBean commentItemsBean;

    public CommentItemHo(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.widget_comment_item_ho, this);
        binding = WidgetCommentItemHoBinding.bind(getChildAt(0));
    }

    /**
     * 初始化数据
     *
     * @param commentItemsBean 数据实体
     */
    public void initData(CommentBean.CommentsBean.CommentItemsBean commentItemsBean) {
        this.commentItemsBean = commentItemsBean;
        initView();
    }

    /**
     * 初始化view
     */
    private void initView() {
        setUserImage();
        binding.userName.setText(commentItemsBean.getName());
        binding.userLevel.setText(commentItemsBean.getLevel_title());
        levelView();
        binding.releaseTime.setText(commentItemsBean.getTime_str());
        scoreView();
        binding.commentContent.setText(commentItemsBean.getContent());
    }

    /**
     * 评分View
     */
    private void scoreView() {
        float score = commentItemsBean.getScore();
        int intNumber = (int) score;

        boolean isInteger = false;
        if (intNumber == score) { // 是整数
            isInteger = true;
        }

        int count = 5;
        for (int i = 0; i < intNumber; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(R.mipmap.score_2);
            binding.starGroup.addView(imageView);
        }

        if (count == intNumber) return;

        count -= intNumber;

        if (!isInteger) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(R.mipmap.score_1);
            binding.starGroup.addView(imageView);
            count -= 1;
        }

        for (int i = 0; i < count; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(R.mipmap.score_0);
            binding.starGroup.addView(imageView);
        }

    }

    /**
     * 用户评论头像
     */
    private void setUserImage() {
        switch (commentItemsBean.getUrl()) {
            case "comment_image01":
                binding.userUrl.setImageResource(R.mipmap.comment_image01);
                break;
            case "comment_image02":
                binding.userUrl.setImageResource(R.mipmap.comment_image02);
                break;
        }
    }

    /**
     * 用户等级view
     */
    private void levelView() {
        int bgColor = getContext().getResources().getColor(R.color.color_FFBA00, null);
        switch (commentItemsBean.getLevel()) {
            case 0:
                bgColor = getContext().getResources().getColor(R.color.color_409EFF, null);
                break;
            case 1:
                bgColor = getContext().getResources().getColor(R.color.color_F9230A, null);
                break;
            case 2:
                bgColor = getContext().getResources().getColor(R.color.color_FFBA00, null);
                break;
            case 3:
                bgColor = getContext().getResources().getColor(R.color.color_17DE85, null);
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
        binding.userLevel.setBackground(drawable);
    }

}



















