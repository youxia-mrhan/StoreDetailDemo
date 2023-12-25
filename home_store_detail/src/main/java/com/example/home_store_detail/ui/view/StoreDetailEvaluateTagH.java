package com.example.home_store_detail.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.baselibrary.ui.view.BaseView;
import com.example.baselibrary.util.DisplayUtils;
import com.example.home_store_detail.R;
import com.example.home_store_detail.viewmodel.StoreDetailVMH;
import com.example.home_store_detail.databinding.StoreDetailLvEvaluateTagHBinding;

/**
 * 评价分类 按钮
 */
public class StoreDetailEvaluateTagH extends BaseView {

    private final StoreDetailLvEvaluateTagHBinding bind;
    public StoreDetailVMH.EvaluateInfoBean.EvaluatesBean evaluatesBean;
    private boolean havaMargin = true;
    private int defaultStyleIndex;

    public StoreDetailEvaluateTagH(@NonNull Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.store_detail_lv_evaluate_tag_h, this);
        bind = StoreDetailLvEvaluateTagHBinding.bind(getChildAt(0));
    }

    public void initData(StoreDetailVMH.EvaluateInfoBean.EvaluatesBean evaluatesBean, boolean havaMargin, int styleIndex) {
        this.evaluatesBean = evaluatesBean;
        this.havaMargin = havaMargin;
        this.defaultStyleIndex = styleIndex;
        initView();
    }

    public void initView() {
        TextView root = (TextView) bind.getRoot();
        if (evaluatesBean.getTotalCount() > 0) {
            root.setText(evaluatesBean.getGroup_name()+" "+evaluatesBean.getTotalCount());
        } else {
            root.setText(evaluatesBean.getGroup_name());
        }

        LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.topMargin = DisplayUtils.dp2px(getContext(),16);

        if (havaMargin) {
            params.rightMargin = DisplayUtils.dp2px(getContext(),16);
        }

        bind.getRoot().setLayoutParams(params);
        getStyleTag(defaultStyleIndex,root);
    }

    /**
     * @param highlight 高亮
     */
    public void updateTagState(boolean highlight) {
        TextView root = (TextView) bind.getRoot();

        if (highlight) {
            redStyleTag(root);
        } else {
            getStyleTag(defaultStyleIndex,root);
        }
    }

    public void getStyleTag(int styleIndex,TextView root) {
        switch (styleIndex) {
            case 0:
                pinkStyleTag(root);
                break;
            case 1:
                grayStyleTag(root);
                break;
        }
    }

    private void redStyleTag(TextView root) {
        root.setTextAppearance(getContext(),com.example.baselibrary.R.style.Font_FBFBFB_12);
        root.setBackgroundResource(R.drawable.store_detail_shape_evaluate_tag_red_h);
    }

    private void pinkStyleTag(TextView root) {
        root.setTextAppearance(getContext(),com.example.baselibrary.R.style.Font_303133_12);
        root.setBackgroundResource(R.drawable.store_detail_shape_evaluate_tag_pink_h);
    }

    private void grayStyleTag(TextView root) {
        root.setTextAppearance(getContext(),com.example.baselibrary.R.style.Font_909399_12);
        root.setBackgroundResource(R.drawable.store_detail_shape_evaluate_tag_gray_h);
    }

}
