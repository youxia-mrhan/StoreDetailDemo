package com.example.home_store_detail.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baselibrary.util.DisplayUtils;
import com.example.home_store_detail.viewmodel.StoreDetailVMH;
import com.example.home_store_detail.ui.view.StoreDetailProductTypeH;

import java.util.List;

/**
 * 商品类型 适配器
 */
public class StoreDetailProductTypeAdpH extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public int currentPosition = 0;
    private Callback mCallback;
    private Context context;
    private final int TYPE_DEFAULT = 0;
    private final int TYPE_FOOTER = 1;
    private List<StoreDetailVMH.OrderFoodInfoBean.ProductTypeBean> productTypeBeans;

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    public StoreDetailProductTypeAdpH(Context context, List<StoreDetailVMH.OrderFoodInfoBean.ProductTypeBean> productTypeBeans) {
        this.context = context;
        this.productTypeBeans = productTypeBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_DEFAULT) {
            return new DefaultViewHolder(new StoreDetailProductTypeH(parent.getContext()));
        } else {
            View footerView = LayoutInflater.from(parent.getContext()).inflate(com.example.baselibrary.R.layout.rv_footer, parent, false);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    DisplayUtils.dp2px(parent.getContext(), 500)
            );
            footerView.setLayoutParams(params);
            return new ViewFooterHolder(footerView);
        }
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof DefaultViewHolder) {
            StoreDetailProductTypeH productTypeLayout = ((DefaultViewHolder) holder).productTypeLayout;
            productTypeLayout.initView(productTypeBeans.get(position));
            if (currentPosition == position) {
                productTypeLayout.updateHighlightState();
            } else {
                productTypeLayout.updateDefaultState();
            }

            productTypeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCallback != null) {
                        mCallback.onClickItem(position);
                    }
                }
            });

            // executePendingBindings 让dataBinding立即刷新，dataBinding是延迟一帧刷新，会导致RecyclerView闪烁
            productTypeLayout.bind.executePendingBindings();
        } else {
            FrameLayout footer = ((ViewFooterHolder) holder).itemView;
        }

    }

    @Override
    public int getItemCount() {
        return productTypeBeans.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == (getItemCount() - 1)) { // 当前位置是最后一个item
            return TYPE_FOOTER;
        } else {
            return TYPE_DEFAULT;
        }
    }

    class DefaultViewHolder extends RecyclerView.ViewHolder {

        StoreDetailProductTypeH productTypeLayout;

        public DefaultViewHolder(@NonNull StoreDetailProductTypeH productTypeLayout) {
            super(productTypeLayout);
            this.productTypeLayout = productTypeLayout;
        }
    }

    class ViewFooterHolder extends RecyclerView.ViewHolder {

        FrameLayout itemView;

        public ViewFooterHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = (FrameLayout) itemView;
        }
    }

    public interface Callback {
        void onClickItem(int position);
    }
}
