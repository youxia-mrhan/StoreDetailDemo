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
import com.example.home_store_detail.ui.view.StoreDetailShoppingCartProductH;
import com.example.provider.home.storedetail.shoppingcart.bean.ShoppingCartProduct;

import java.util.List;

/**
 * 购物车 适配器
 */
public class StoreDetailShoppingCartAdpH extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<ShoppingCartProduct> shoppingCartProducts;
    private int storeId = -1;
    private RecyclerView.LayoutParams layoutParams;
    private final int TYPE_FOOTER = 1;

    public StoreDetailShoppingCartAdpH(Context context, List<ShoppingCartProduct> shoppingCartProducts,int storeId) {
        this.context = context;
        this.shoppingCartProducts = shoppingCartProducts;
        this.storeId = storeId;
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
                    DisplayUtils.dp2px(parent.getContext(), 200)
            );
            footerView.setLayoutParams(params);
            return new ViewFooterHolder(footerView);
        } else {
            return new ViewHolder(new StoreDetailShoppingCartProductH(parent.getContext()));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            StoreDetailShoppingCartProductH shoppingCartProductH = ((ViewHolder) holder).shoppingCartProductH;
            shoppingCartProductH.initView(shoppingCartProducts.get(position),storeId);
            shoppingCartProductH.bind.executePendingBindings();
        } else if (holder instanceof ViewFooterHolder) {
            FrameLayout footer = ((ViewFooterHolder) holder).itemView;
            footer.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return shoppingCartProducts.size() + 1;
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

        StoreDetailShoppingCartProductH shoppingCartProductH;

        public ViewHolder(@NonNull StoreDetailShoppingCartProductH shoppingCartProductH) {
            super(shoppingCartProductH);
            this.shoppingCartProductH = shoppingCartProductH;
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
