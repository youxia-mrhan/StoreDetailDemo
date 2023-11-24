package com.example.storedetaildemo.ext;

import android.view.View;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IMeasurablePagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;

/**
 * 扩展
 * 增加 获取上一个选中的Item信息方法
 */
public interface IMeasurablePagerTitleViewEx extends IMeasurablePagerTitleView {
    void onSelected(int index, int totalCount, int previousIndex, View titleView);

    void onSelected(int index, int totalCount, int previousIndex, IPagerTitleView titleView);
}
