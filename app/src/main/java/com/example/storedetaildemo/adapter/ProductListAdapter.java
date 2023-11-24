package com.example.storedetaildemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storedetaildemo.R;
import com.example.storedetaildemo.bean.DetailProductBean;
import com.example.storedetaildemo.ui.widget.SelectProductHo;
import com.example.storedetaildemo.util.DisplayUtils;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<DetailProductBean.ProductsBean> productsBeans;
    private final int TYPE_FOOTER = 1;

    public ProductListAdapter(Context context, List<DetailProductBean.ProductsBean> productsBeans) {
        this.context = context;
        this.productsBeans = productsBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            View footerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_list_footer_container, parent, false);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    DisplayUtils.dp2px(parent.getContext(), 200)
            );
            footerView.setLayoutParams(params);
            return new ViewFooterHolder(footerView);
        } else {
            return new ViewHolder(new SelectProductHo(parent.getContext()));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            SelectProductHo selectProduct = ((ViewHolder) holder).selectProduct;
            selectProduct.setProductData(productsBeans.get(position));
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
            return super.getItemViewType(position);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        SelectProductHo selectProduct;

        public ViewHolder(@NonNull SelectProductHo selectProduct) {
            super(selectProduct);
            this.selectProduct = selectProduct;
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
