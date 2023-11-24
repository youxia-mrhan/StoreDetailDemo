package com.example.storedetaildemo.ext;

import androidx.viewpager2.widget.ViewPager2;

import net.lucode.hackware.magicindicator.MagicIndicator;

/**
 * 扩展 MagicIndicator
 * 与ViewPager2绑定
 */
public class ViewPager2HelperEx {
    public static void bind(final MagicIndicator magicIndicator, ViewPager2 viewPager) {

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                magicIndicator.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                magicIndicator.onPageScrollStateChanged(state);
            }
        });

    }
}

