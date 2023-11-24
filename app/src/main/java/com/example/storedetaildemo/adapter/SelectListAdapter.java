package com.example.storedetaildemo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storedetaildemo.R;
import com.example.storedetaildemo.bean.DetailSelectBean;
import com.example.storedetaildemo.ui.widget.SelectItemHo;
import com.example.storedetaildemo.util.DisplayUtils;

import java.util.List;

public class SelectListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public int currentPosition = 0;
    private Callback mCallback;
    private Context context;
    private final int TYPE_FOOTER = 1;
    private List<DetailSelectBean.ProductSelectBean> productSelects;

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    public SelectListAdapter(Context context, List<DetailSelectBean.ProductSelectBean> productSelects) {
        this.context = context;
        this.productSelects = productSelects;
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
            return new ViewHolder(new SelectItemHo(parent.getContext()));
        }
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolder) {
            SelectItemHo selectItem = ((ViewHolder) holder).selectItem;
            selectItem.setSelectData(productSelects.get(position));
            if (productSelects.get(position).isIs_hot() || productSelects.get(position).isIs_must_order()) {

                if (currentPosition == position) {
                    selectItem.iconHoBinding.getRoot().setBackgroundResource(R.color.white);
                    selectItem.iconHoBinding.selectTitle.setTextColor(context.getResources().getColor(R.color.color_303133, null));
                } else {
                    selectItem.iconHoBinding.getRoot().setBackgroundResource(R.color.color_F5F6F7);
                    selectItem.iconHoBinding.selectTitle.setTextColor(context.getResources().getColor(R.color.color_909399, null));
                }

                selectItem.iconHoBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mCallback != null) {
                            mCallback.onClickItem(position);
                        }
                    }
                });
            } else {

                if (currentPosition == position) {
                    selectItem.itemHoBinding.getRoot().setBackgroundResource(R.color.white);
                    selectItem.itemHoBinding.selectTitle.setTextColor(context.getResources().getColor(R.color.color_303133, null));
                } else {
                    selectItem.itemHoBinding.getRoot().setBackgroundResource(R.color.color_F5F6F7);
                    selectItem.itemHoBinding.selectTitle.setTextColor(context.getResources().getColor(R.color.color_909399, null));
                }

                selectItem.itemHoBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mCallback != null) {
                            mCallback.onClickItem(position);
                        }
                    }
                });
            }
        } else if (holder instanceof ViewFooterHolder) {
            FrameLayout footer = ((ViewFooterHolder) holder).itemView;
        }
    }

    @Override
    public int getItemCount() {
        return productSelects.size() + 1;
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

        SelectItemHo selectItem;

        public ViewHolder(@NonNull SelectItemHo selectItem) {
            super(selectItem);
            this.selectItem = selectItem;
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
