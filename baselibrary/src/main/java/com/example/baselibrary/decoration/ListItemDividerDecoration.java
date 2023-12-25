package com.example.baselibrary.decoration;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baselibrary.R;
import com.example.baselibrary.util.DisplayUtils;

/**
 * @desc 自定义 分割线ItemDecoration
 */
public class ListItemDividerDecoration extends RecyclerView.ItemDecoration {

    private Paint mPaint;   // 分割线画笔
    private int lingHeight = 0;  // 分割线高度
    private int lineMarginLeft = 0;

    public ListItemDividerDecoration(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(context.getResources().getColor((R.color.color_EEEEEE))); //设置分割线颜色
        // lingHeight = dpToPx(context, 1);
        lingHeight = DisplayUtils.dip2px(context,0.6f);
        lineMarginLeft = dpToPx(context, 16);  //设置左侧偏移量
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = lingHeight;  //底部偏移量
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + lingHeight;
            final int left = child.getLeft() + lineMarginLeft;
            final int right = child.getWidth() + left - lineMarginLeft;
            c.drawRect(left, top, right, bottom, mPaint);
        }
    }

    int dpToPx(Context context, int dps) {
        return Math.round(context.getResources().getDisplayMetrics().density * dps);
    }
}

