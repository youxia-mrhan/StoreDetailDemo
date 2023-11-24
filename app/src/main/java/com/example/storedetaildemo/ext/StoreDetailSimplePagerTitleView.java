package com.example.storedetaildemo.ext;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

import com.example.storedetaildemo.R;
import com.example.storedetaildemo.util.DisplayUtils;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

public class StoreDetailSimplePagerTitleView extends SimplePagerTitleView {

    public StoreDetailSimplePagerTitleView(Context context) {
        super(context);
    }

    @Override
    public void onSelected(int index, int totalCount) {
        if (index == 1) { // 评论
            splicingHighlightTextStyle();
        } else {
            highlightTextStyle();
        }
    }

    @Override
    public void onDeselected(int index, int totalCount) {
        if (index == 1) { // 评论
            splicingDefaultTextStyle();
        } else {
            defaultTextStyle();
        }
    }

    /**
     * 默认样式
     */
    private void defaultTextStyle() {
        setTextSize(15);
        setTextColor(getResources().getColor(R.color.color_606266, null));
        getPaint().setFakeBoldText(false);
    }

    /**
     * 高亮样式
     */
    private void highlightTextStyle() {
        setTextSize(15);
        setTextColor(getResources().getColor(R.color.color_303133, null));
        getPaint().setFakeBoldText(true);
    }

    private SpannableStringBuilder spannableStringBuilder;


    // 评论 默认样式

    // 字体颜色
    ForegroundColorSpan defaultSpanColor = new ForegroundColorSpan(getResources().getColor(R.color.color_606266,null));

    // 字体大小
    AbsoluteSizeSpan defaultSpanSize = new AbsoluteSizeSpan(DisplayUtils.dp2px(getContext(), 15));

    // 字体样式
    StyleSpan defaultSpanStyle = new StyleSpan(Typeface.NORMAL);


    // 评论 高亮样式

    // 字体颜色
    ForegroundColorSpan highlightSpanColor = new ForegroundColorSpan(getResources().getColor(R.color.color_303133,null));

    // 字体样式
    StyleSpan highlightSpanStyle = new StyleSpan(Typeface.BOLD); // 字体加粗


    // 评论数量 样式

    // 字体颜色
    ForegroundColorSpan countColor = new ForegroundColorSpan(getResources().getColor(R.color.color_909399, null));

    // 字体大小
    AbsoluteSizeSpan countSize = new AbsoluteSizeSpan(DisplayUtils.dp2px(getContext(), 12));

    /**
     * 不同样式拼接 默认样式
     */
    private void splicingDefaultTextStyle() {
        spannableStringBuilder = new SpannableStringBuilder(getText());

        // 评论
        spannableStringBuilder.removeSpan(highlightSpanColor); // 样式是叠加的，所以要清除之前的样式
        spannableStringBuilder.removeSpan(highlightSpanStyle);

        spannableStringBuilder.setSpan(defaultSpanColor, 0, 2, Spanned.SPAN_INCLUSIVE_INCLUSIVE); // 字体颜色
        spannableStringBuilder.setSpan(defaultSpanSize, 0, 2, Spanned.SPAN_INCLUSIVE_INCLUSIVE); // 字体大小
        spannableStringBuilder.setSpan(defaultSpanStyle, 0, 2, Spanned.SPAN_INCLUSIVE_INCLUSIVE); // 字体样式

        // 评论数量
        spannableStringBuilder.setSpan(countColor, 2, getText().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE); // 字体颜色
        spannableStringBuilder.setSpan(countSize, 2, getText().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE); // 字体大小
        spannableStringBuilder.setSpan(defaultSpanStyle, 2, getText().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE); // 字体样式

        setText(spannableStringBuilder);
    }

    /**
     * 不同样式拼接 高亮样式
     */
    private void splicingHighlightTextStyle() {
        spannableStringBuilder = new SpannableStringBuilder(getText());

        // 评论
        spannableStringBuilder.removeSpan(defaultSpanColor); // 样式是叠加的，所以要清除之前的样式
        spannableStringBuilder.removeSpan(defaultSpanStyle);

        spannableStringBuilder.setSpan(highlightSpanColor, 0, 2, Spanned.SPAN_INCLUSIVE_INCLUSIVE); // 字体颜色
        spannableStringBuilder.setSpan(defaultSpanSize, 0, 2, Spanned.SPAN_INCLUSIVE_INCLUSIVE); // 字体大小
        spannableStringBuilder.setSpan(highlightSpanStyle, 0, 2, Spanned.SPAN_INCLUSIVE_INCLUSIVE); // 字体样式

        // 评论数量
        spannableStringBuilder.setSpan(countColor, 2, getText().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE); // 字体颜色
        spannableStringBuilder.setSpan(countSize, 2, getText().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE); // 字体大小
        spannableStringBuilder.setSpan(defaultSpanStyle, 2, getText().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE); // 字体样式

        setText(spannableStringBuilder);
    }


}
