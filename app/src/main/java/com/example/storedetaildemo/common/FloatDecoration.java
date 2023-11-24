package com.example.storedetaildemo.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Introduction : RecyclerView 悬浮吸顶 Decoration
 * 填充的 decorationLayoutRes 根布局高度需为明确值
 */
public class FloatDecoration extends RecyclerView.ItemDecoration {

    private View mDecorationView;
    private int mDecorationHeight = 0;
    private DecorationCallback mCallback;
    private boolean haveFooterView = false;
    private int totalSize;
    /**
     * @param context
     * @param haveFooterView      有页脚view
     * @param recyclerView        被装饰的 RecyclerView
     * @param decorationLayoutRes 布局 layout
     * @param decorationCallback  回调接口
     */
    public FloatDecoration(Context context,
                           boolean haveFooterView,
                           RecyclerView recyclerView,
                           @LayoutRes int decorationLayoutRes,
                           DecorationCallback decorationCallback) {
        this.haveFooterView = haveFooterView;
        this.totalSize = totalSize;
        mDecorationView = LayoutInflater.from(context).inflate(decorationLayoutRes, recyclerView, false);
        // 计算装饰 View 的总高度
        if (mDecorationView.getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            mDecorationHeight = 0;
        } else {
            mDecorationHeight = mDecorationView.getLayoutParams().height;
        }
        mCallback = decorationCallback;
    }

    /**
     * 设置 Item 偏移量
     */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        final int position = parent.getChildAdapterPosition(view);
        if (isShowDecoration(position)) {
            outRect.top = mDecorationHeight;
        } else {
            outRect.top = 0;
        }
    }

    /**
     * 在 Canvas 上绘制内容，于绘制 item 之后，会覆盖 item
     */
    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if (mDecorationHeight == 0) return;
        int itemCount = state.getItemCount();
        int childCount = parent.getChildCount();
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        int preDecorationId = -1;
        int currentDecorationId = -1;

        for (int i = 0; i < childCount; i++) {
            final View view = parent.getChildAt(i);
            final int position = parent.getChildAdapterPosition(view);
            preDecorationId = currentDecorationId;
            currentDecorationId = mCallback.getDecorationFlag(position);
            if (preDecorationId == currentDecorationId) {
                continue;
            }
            final int viewBottom = view.getBottom();
            float top = (float) Math.max(mDecorationHeight, view.getTop());

            // 下一个和当前不一样移动当前
            if (position + 1 < itemCount) {
                final int nextDecorationId = mCallback.getDecorationFlag(position + 1);

                // 组内最后一个view进入了header
                if (nextDecorationId != currentDecorationId && viewBottom < top) {
                    top = (float) viewBottom;
                }
            }

            mCallback.onBindView(mDecorationView, position);
            mDecorationView.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            );
            mDecorationView.layout(left, 0, right, mDecorationHeight);
            mDecorationView.setDrawingCacheEnabled(true);

            if (haveFooterView && (position == itemCount - 1)) {
                break;
            }

            c.drawBitmap(
                    mDecorationView.getDrawingCache(),
                    (float) left,
                    top - mDecorationHeight,
                    null);
            mDecorationView.setDrawingCacheEnabled(false);
            mDecorationView.destroyDrawingCache();
        }

    }

    private boolean isShowDecoration(int position) {
        if (position == 0) {
            return true;
        } else {
            final int preDecorationId = mCallback.getDecorationFlag(position - 1);
            final int currentDecorationId = mCallback.getDecorationFlag(position);
            return preDecorationId != currentDecorationId;
        }
    }

    /**
     * 回调接口
     */
    public interface DecorationCallback {

        /**
         * 获得装饰标识位：用来区分不同分组，可以根据实际需求调整为 String(组名) 或者是 Long(组Id) 类型
         */
        int getDecorationFlag(int position);

        /**
         * 操作装饰View：填充数据
         */
        void onBindView(View decorationView, int position);

    }
}
