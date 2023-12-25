package com.example.baselibrary.ex;

import android.content.Context;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

/**
 * 扩展 IMeasurablePagerTitleView文本
 * 选中字体加粗
 */
public class SimplePagerTitleViewEx extends SimplePagerTitleView {

    public SimplePagerTitleViewEx(Context context) {
        super(context);
    }

    @Override
    public void onSelected(int index, int totalCount) {
        super.onSelected(index, totalCount);
        getPaint().setFakeBoldText(true);
    }

    @Override
    public void onDeselected(int index, int totalCount) {
        super.onDeselected(index, totalCount);
        getPaint().setFakeBoldText(false);
    }

}
