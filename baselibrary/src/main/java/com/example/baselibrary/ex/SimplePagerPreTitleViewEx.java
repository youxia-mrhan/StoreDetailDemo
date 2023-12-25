package com.example.baselibrary.ex;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;

/**
 * 扩展 IMeasurablePagerTitleView文本
 * 增加 获取上一个选中项 方法
 */
public class SimplePagerPreTitleViewEx extends LinearLayout implements IMeasurablePagerTitleViewEx {

    public SimplePagerPreTitleViewEx(Context context) {
        super(context);
    }

    @Override
    public int getContentLeft() {
        return 0;
    }

    @Override
    public int getContentTop() {
        return 0;
    }

    @Override
    public int getContentRight() {
        return 0;
    }

    @Override
    public int getContentBottom() {
        return 0;
    }

    @Override
    public void onSelected(int index, int totalCount) {}

    @Override
    public void onDeselected(int index, int totalCount) {

    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {}

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {}

    /**
     *
     * @param index             当前索引
     * @param totalCount        总数量
     * @param previousIndex     上一个选中的索引
     * @param titleView         上一个选中的View
     */
    @Override
    public void onSelected(int index, int totalCount, int previousIndex, View titleView) {
        // Log.d("TAG", "onSelected------index：" + index + "totalCount：" + totalCount + "previousIndex：" + previousIndex);
        // SimplePagerTitleViewIconEx view = (SimplePagerTitleViewIconEx) titleView;
    }

    /**
     *
     * @param index             当前索引
     * @param totalCount        总数量
     * @param previousIndex     上一个选中的索引
     * @param titleView         上一个选中的View
     */
    @Override
    public void onSelected(int index, int totalCount, int previousIndex, IPagerTitleView titleView) {
        // Log.d("TAG", "onSelected------index：" + index + "totalCount：" + totalCount + "previousIndex：" + previousIndex);
        // SimplePagerTitleViewIconEx view = (SimplePagerTitleViewIconEx) titleView;
    }

}