package com.example.home_store_detail.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baselibrary.ui.view.BaseView;
import com.example.home_store_detail.behavior.StoreDetailTabPageBehaviorH;

/**
 * 在ViewPager2外面套一层，处理事件传递，因为ViewPager2是 final 类型
 * <p>
 * public final class ViewPager2 extends ViewGroup { ... }
 */
public class StoreDetailViewPager2BoxH extends BaseView {

    public boolean isScrollable = true;
    private StoreDetailTabPageBehaviorH tabPageBehavior;

    public StoreDetailViewPager2BoxH(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setTabPageBehavior(StoreDetailTabPageBehaviorH tabPageBehavior) {
        this.tabPageBehavior = tabPageBehavior;
    }

    private int mScrollPointerId; // 当前最新放在屏幕上的手指
    private boolean mOriginalScrollPointerUp = true; // 原来放在屏幕上的手指离开了屏幕
    private int mLastTouchX; // 上一次触摸的X坐标
    private int mLastTouchY; // 上一次触摸的Y坐标
    private int mInitialTouchX; // 初始化触摸的X坐标
    private int mInitialTouchY; // 初始化触摸的Y坐标
    public MotionEvent copyEv;

    public final static int INIT_STATE = 0;
    public final static int HORIZONTAL = 1;
    public final static int VERTICAL = 2;
    public int scroll_direction = INIT_STATE;

    /**
     * 目标区域：
     * 从 RecyclerView的 top位置 到 屏幕Top 之间的区域
     * 或者
     * 从 NestedScrollView的 top位置 到 屏幕Top 之间的区域
     * <p>
     * <p>
     * 非目标区域：就是 RecyclerView 或者 NestedScrollView 区域
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        super.dispatchTouchEvent(event);

        // 返回正在执行的操作，不包含触摸点索引信息。即事件类型，如MotionEvent.ACTION_DOWN
        final int action = event.getActionMasked();
        int actionIndex = event.getActionIndex(); // Action的索引

        // 复制事件信息创建一个新的事件，防止被污染
        copyEv = MotionEvent.obtain(event);

        switch (action) {
            case MotionEvent.ACTION_DOWN: { // 手指按下

                // 特定触摸点相关联的触摸点id,获取第一个触摸点的id
                mScrollPointerId = event.getPointerId(0);
                mOriginalScrollPointerUp = false;

                // 记录down事件的X、Y坐标
                mInitialTouchX = mLastTouchX = (int) (event.getX() + 0.5f);
                mInitialTouchY = mLastTouchY = (int) (event.getY() + 0.5f);
            }
            break;
            case MotionEvent.ACTION_POINTER_DOWN: { // 多个手指按下
                // 更新mScrollPointerId,表示只会响应最近按下的手势事件
                mScrollPointerId = event.getPointerId(actionIndex);

                // 更新最近的手势坐标
                mInitialTouchX = mLastTouchX = (int) (event.getX() + 0.5f);
                mInitialTouchY = mLastTouchY = (int) (event.getY() + 0.5f);
            }
            break;
            case MotionEvent.ACTION_MOVE: { // 手指移动

                // 根据mScrollPointerId获取触摸点下标
                final int index = event.findPointerIndex(mScrollPointerId);
                // 替换最新触摸点的坐标，避免页面出现跳跃式偏移，比如原来的手指Y点在100，第二个手指触摸点Y点是 1000，
                // Move就会直接跳到 Y坐标 1000
                event.findPointerIndex(index); // 将坐标替换为 最新触摸点的坐标

                // 根据move事件产生的x，y来计算偏移量dx，dy
                final int x = (int) (event.getX() + 0.5f);
                final int y = (int) (event.getY() + 0.5f);

                int dx = Math.abs(mLastTouchX - x);
                int dy = Math.abs(mLastTouchY - y);

                if (mOriginalScrollPointerUp) { // 原来放在屏幕上的手指离开了屏幕
                    dx = 1;
                    dy = 1; // 避免 跳跃式偏移
                    mOriginalScrollPointerUp = false;
                }

                if (scroll_direction == INIT_STATE) {
                    if (Math.abs(dx) > Math.abs(dy)) {
                        scroll_direction = HORIZONTAL;
                    } else {
                        scroll_direction = VERTICAL;
                    }
                }

                mLastTouchX = x;
                mLastTouchY = y;
            }
            break;
            case MotionEvent.ACTION_POINTER_UP: { // 多个手指离开
                // 选择一个新的触摸点来处理结局，重新处理坐标
                onPointerUp(event);
                mOriginalScrollPointerUp = true;
            }
            break;
            case MotionEvent.ACTION_UP: // 手指离开，滑动事件结束
            case MotionEvent.ACTION_CANCEL: { //手势取消，释放各种资源
                scroll_direction = INIT_STATE;
            }
            break;

        }

        // 回收滑动事件，方便重用
        copyEv.recycle();
        return isScrollable;
    }


    /**
     * 有新手指触摸屏幕，更新初始坐标
     *
     * @param e
     */
    private void onPointerUp(MotionEvent e) {
        final int actionIndex = e.getActionIndex();
        if (e.getPointerId(actionIndex) == mScrollPointerId) {
            // Pick a new pointer to pick up the slack.
            final int newIndex = actionIndex == 0 ? 1 : 0;
            mScrollPointerId = e.getPointerId(newIndex);
            mInitialTouchX = mLastTouchX = (int) (e.getX(newIndex) + 0.5f);
            mInitialTouchY = mLastTouchY = (int) (e.getY(newIndex) + 0.5f);
        }
    }

}
