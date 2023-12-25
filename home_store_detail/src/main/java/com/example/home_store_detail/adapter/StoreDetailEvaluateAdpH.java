package com.example.home_store_detail.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baselibrary.util.DisplayUtils;
import com.example.home_store_detail.viewmodel.StoreDetailVMH;
import com.example.home_store_detail.ui.view.StoreDetailEvaluateItemH;

import java.util.List;

/**
 * 评价 适配器
 */
public class StoreDetailEvaluateAdpH extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<StoreDetailVMH.EvaluateInfoBean.EvaluatesBean.EvaluateItemsBean> evaluateItemsBeans;
    private RecyclerView.LayoutParams layoutParams;
    private final int TYPE_FOOTER = 1;

    public StoreDetailEvaluateAdpH(Context context, List<StoreDetailVMH.EvaluateInfoBean.EvaluatesBean.EvaluateItemsBean> evaluateItemsBeans) {
        this.context = context;
        this.evaluateItemsBeans = evaluateItemsBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutParams = new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);

        if (viewType == TYPE_FOOTER) {
            View footerView = LayoutInflater.from(parent.getContext()).inflate(com.example.baselibrary.R.layout.rv_footer, parent, false);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    DisplayUtils.dp2px(parent.getContext(), 300)
            );
            footerView.setLayoutParams(params);
            return new ViewFooterHolder(footerView);
        } else {
            return new ViewHolder(new StoreDetailEvaluateItemH(parent.getContext()));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolder) {
            StoreDetailEvaluateItemH evaluateItemH = ((ViewHolder) holder).storeDetailEvaluateItemH;
            evaluateItemH.initView(evaluateItemsBeans.get(position));
            evaluateItemH.bind.executePendingBindings();
        } else if (holder instanceof ViewFooterHolder) {
            FrameLayout footer = ((ViewFooterHolder) holder).itemView;
            footer.setBackgroundColor(Color.WHITE);
        }

    }

    @Override
    public int getItemCount() {
        return evaluateItemsBeans.size() + 1;
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

        StoreDetailEvaluateItemH storeDetailEvaluateItemH;

        public ViewHolder(@NonNull StoreDetailEvaluateItemH storeDetailEvaluateItemH) {
            super(storeDetailEvaluateItemH);
            this.storeDetailEvaluateItemH = storeDetailEvaluateItemH;
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
