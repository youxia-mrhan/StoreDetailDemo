package com.example.storedetaildemo.ui.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.storedetaildemo.R;
import com.example.storedetaildemo.bean.CommentBean;
import com.example.storedetaildemo.databinding.WidgetCommentTagHoBinding;
import com.example.storedetaildemo.util.DisplayUtils;

/**
 * 评论点击按钮
 */
public class CommentTagHo extends FrameLayout {

    private final WidgetCommentTagHoBinding binding;
    public CommentBean.CommentsBean commentsBean;
    private boolean havaMargin = true;
    private int defaultStyleIndex;

    public CommentTagHo(@NonNull Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.widget_comment_tag_ho, this);
        binding = WidgetCommentTagHoBinding.bind(getChildAt(0));
    }

    public void initData(CommentBean.CommentsBean commentsBean, boolean havaMargin, int styleIndex) {
        this.commentsBean = commentsBean;
        this.havaMargin = havaMargin;
        this.defaultStyleIndex = styleIndex;
        initView();
    }

    public void initView() {
        if (commentsBean.getTotalCount() > 0) {
            binding.getRoot().setText(commentsBean.getGroup_name()+" "+commentsBean.getTotalCount());
        } else {
            binding.getRoot().setText(commentsBean.getGroup_name());
        }

        LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.topMargin = DisplayUtils.dp2px(getContext(),16);

        if (havaMargin) {
            params.rightMargin = DisplayUtils.dp2px(getContext(),16);
        }

        binding.getRoot().setLayoutParams(params);
        getStyleTag(defaultStyleIndex);
    }

    /**
     * @param highlight 高亮
     */
    public void updateTagState(boolean highlight) {
        if (highlight) {
            redStyleTag();
        } else {
            getStyleTag(defaultStyleIndex);
        }
    }

    public void getStyleTag(int styleIndex) {
        switch (styleIndex) {
            case 0:
                pinkStyleTag();
                break;
            case 1:
                grayStyleTag();
                break;
        }
    }

    private void redStyleTag() {
        binding.getRoot().setTextAppearance(R.style.Font_FBFBFB_12);
        binding.getRoot().setBackgroundResource(R.drawable.shape_comment_tag_red_ho);
    }

    private void pinkStyleTag() {
        binding.getRoot().setTextAppearance(R.style.Font_303133_12);
        binding.getRoot().setBackgroundResource(R.drawable.shape_comment_tag_pink_ho);
    }

    private void grayStyleTag() {
        binding.getRoot().setTextAppearance(R.style.Font_909399_12);
        binding.getRoot().setBackgroundResource(R.drawable.shape_comment_tag_gray_ho);
    }

}
