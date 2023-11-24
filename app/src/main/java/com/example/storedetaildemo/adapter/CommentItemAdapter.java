package com.example.storedetaildemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storedetaildemo.R;
import com.example.storedetaildemo.bean.CommentBean;
import com.example.storedetaildemo.ui.widget.CommentItemHo;
import com.example.storedetaildemo.util.DisplayUtils;

public class CommentItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private CommentBean.CommentsBean commentsBean;
    private RecyclerView.LayoutParams layoutParams;
    private final int TYPE_FOOTER = 1;

    public CommentItemAdapter(Context context, CommentBean.CommentsBean commentsBean) {
        this.context = context;
        this.commentsBean = commentsBean;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutParams = new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);

        if (viewType == TYPE_FOOTER) {
            View footerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_list_footer_container, parent, false);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    DisplayUtils.dp2px(parent.getContext(), 100)
            );
            footerView.setLayoutParams(params);
            return new ViewFooterHolder(footerView);
        } else {
            return new ViewHolder(new CommentItemHo(parent.getContext()));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolder) {
            CommentItemHo commentItemHo = ((ViewHolder) holder).commentItemHo;
            commentItemHo.initData(commentsBean.getComment_items().get(position));
            if (position == 0) {
                if (layoutParams.topMargin < DisplayUtils.dp2px(context, 8)) {
                    layoutParams.topMargin = DisplayUtils.dp2px(context, 8);
                    commentItemHo.setLayoutParams(layoutParams);
                }
            }
        } else if (holder instanceof ViewFooterHolder) {
            FrameLayout footer = ((ViewFooterHolder) holder).itemView;
            footer.setBackgroundColor(Color.WHITE);
        }

    }

    @Override
    public int getItemCount() {
        return commentsBean.getComment_items().size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == (getItemCount() - 1)) { // 当前位置是最后一个item
            return TYPE_FOOTER;
        } else {
            return super.getItemViewType(position);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        CommentItemHo commentItemHo;

        public ViewHolder(@NonNull CommentItemHo commentItemHo) {
            super(commentItemHo);
            this.commentItemHo = commentItemHo;
        }
    }

    class ViewFooterHolder extends RecyclerView.ViewHolder {

        FrameLayout itemView;

        public ViewFooterHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = (FrameLayout) itemView;
        }
    }
}
