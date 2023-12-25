package com.example.home_store_detail.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baselibrary.util.DisplayUtils;
import com.example.home_store_detail.viewmodel.StoreDetailVMH;
import com.example.home_store_detail.ui.view.StoreDetailProductH;

import java.util.List;

/**
 * 商品 适配器
 */
public class StoreDetailProductAdpH extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LifecycleOwner owner;
    private int storeId;
    private List<StoreDetailVMH.OrderFoodInfoBean.ProductsBean> productsBeans;
    private final int TYPE_DEFAULT = 0;
    private final int TYPE_FOOTER = 1;

    public StoreDetailProductAdpH(Context context,
                                  LifecycleOwner owner,
                                  int storeId,
                                  List<StoreDetailVMH.OrderFoodInfoBean.ProductsBean> productsBeans) {
        this.context = context;
        this.owner = owner;
        this.storeId = storeId;
        this.productsBeans = productsBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_DEFAULT) {
            return new DefaultViewHolder(new StoreDetailProductH(parent.getContext(),storeId,owner));
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
            StoreDetailProductH product = ((DefaultViewHolder) holder).product;
            product.initView(productsBeans.get(position));
            // executePendingBindings 让dataBinding立即刷新，dataBinding是延迟一帧刷新，会导致RecyclerView闪烁
            product.bind.executePendingBindings();
        } else if (holder instanceof ViewFooterHolder) {
            FrameLayout footer = ((ViewFooterHolder) holder).itemView;
        }
    }

    @Override
    public int getItemCount() {
        return productsBeans.size() + 1;
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

        StoreDetailProductH product;

        public DefaultViewHolder(@NonNull StoreDetailProductH product) {
            super(product);
            this.product = product;
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
