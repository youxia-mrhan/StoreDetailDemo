package com.example.storedetaildemo.ui.fragment.storedetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storedetaildemo.adapter.CommentItemAdapter;
import com.example.storedetaildemo.behavior.StoreDetailBehavior;
import com.example.storedetaildemo.bean.CommentBean;
import com.example.storedetaildemo.common.base.BaseFragment;
import com.example.storedetaildemo.databinding.FragmentCommentBinding;
import com.example.storedetaildemo.ui.widget.CommentTagHo;
import com.example.storedetaildemo.ui.widget.StoreDetailPagerView2Ho;

import java.util.List;


/**
 * 评论页面
 */
public class CommentFragment extends BaseFragment implements StoreDetailPagerView2Ho.ScrollableViewProvider {

    private FragmentCommentBinding binding;
    private List<CommentBean.CommentsBean> commentsBeans;
    private StoreDetailBehavior.LayoutExpandControl mLayoutControl;
    private int currentIndex = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCommentBinding.inflate(
                inflater,
                container,
                false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initCommentTagGroup();
    }

    /**
     * 初始化数据
     * @param commentBean  数据实体
     */
    public void initData(CommentBean commentBean) {
        this.commentsBeans = commentBean.getComments();
    }

    /**
     * 初始化评论 标签按钮
     */
    private void initCommentTagGroup() {
        for (int i = 0; i < commentsBeans.size(); i++) {
            CommentTagHo commentTagHo = new CommentTagHo(getContext());

            if (i == (commentsBeans.size() - 1)) {
                commentTagHo.initData(commentsBeans.get(i),false,1);
            } else {
                commentTagHo.initData(commentsBeans.get(i),true,0);
            }

            if (i == 0) {
                commentTagHo.updateTagState(true);
            }

            int finalI = i;
            commentTagHo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (currentIndex == finalI) return;

                    // 整体布局没有向上偏移到最大值
                    if (!mLayoutControl.arriveTopMax()) {
                        mLayoutControl.offsetTopMax(); // 整体布局向上偏移
                    }

                    updateList(commentsBeans.get(finalI).getGroup_id());
                    currentIndex = finalI;
                }
            });

            binding.couponGroup.addView(commentTagHo);
        }
        loadCommentList(0);
    }

    /**
     * 更新评论列表
     * @param groupId
     */
    private void updateList(int groupId) {
        for (int i = 0; i < binding.couponGroup.getChildCount(); i++) {
            CommentTagHo childView = (CommentTagHo) binding.couponGroup.getChildAt(i);
            if (childView.commentsBean.getGroup_id() == groupId) {
                childView.updateTagState(true);
                loadCommentList(i);
            } else {
                childView.updateTagState(false);
            }
        }
    }

    /**
     * 加载评论列表
     * @param position 索引
     */
    private void loadCommentList(int position) {
        CommentItemAdapter adapter = new CommentItemAdapter(getContext(),commentsBeans.get(position));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.commentList.setLayoutManager(layoutManager);
        binding.commentList.setAdapter(adapter);
    }

    @Override
    public View getScrollableView() {
        return binding.commentList;
    }

    @Override
    public void parentOnDown(MotionEvent event) {
        // Log.d("TAG","评论 --- 手指按下");
        if (binding.commentList.getScrollState() == RecyclerView.SCROLL_STATE_SETTLING) { // 列表正在滚动
            binding.commentList.stopScroll();
            binding.commentList.stopNestedScroll(ViewCompat.TYPE_NON_TOUCH);
            binding.commentList.startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL, ViewCompat.TYPE_TOUCH);
        }
    }

    @Override
    public void parentOnUp(MotionEvent event) {
        // Log.d("TAG","评论 --- 手指抬起");
    }

    @Override
    public boolean offsetScrollView(int dy, boolean direction,boolean fling) {
        if(binding == null) return false;
        RecyclerView rv = binding.commentList;

        int cuDy = 0;
        if (direction) {
            if (rv.canScrollVertically(1)) {
                cuDy = Math.abs(dy);
                rv.scrollBy(0, cuDy);
                return true;
            }
        } else {
            if (rv.canScrollVertically(-1)) {
                cuDy = Math.abs(dy);
                rv.scrollBy(0, -cuDy);
                return true;
            }
        }
        return false;
    }

    public void setLayoutExpandControl(StoreDetailBehavior.LayoutExpandControl layoutExpandControl) {
        mLayoutControl = layoutExpandControl;
    }

}