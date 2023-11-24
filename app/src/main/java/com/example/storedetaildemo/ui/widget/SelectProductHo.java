package com.example.storedetaildemo.ui.widget;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.storedetaildemo.R;
import com.example.storedetaildemo.bean.DetailProductBean;
import com.example.storedetaildemo.common.base.BaseView;
import com.example.storedetaildemo.databinding.WidgetProductItemDiscountHoBinding;
import com.example.storedetaildemo.databinding.WidgetProductItemHoBinding;
import com.example.storedetaildemo.databinding.WidgetProductItemSpecificationDiscountHoBinding;
import com.example.storedetaildemo.databinding.WidgetProductItemSpecificationHoBinding;
import com.example.storedetaildemo.util.MoneyConvertUtils;

public class SelectProductHo extends BaseView {

    private DetailProductBean.ProductsBean productsBean;
    private WidgetProductItemHoBinding itemHoBinding;
    private WidgetProductItemDiscountHoBinding itemDiscountHoBinding;
    private WidgetProductItemSpecificationHoBinding specificationHoBinding;
    private WidgetProductItemSpecificationDiscountHoBinding specificationDiscountHoBinding;

    public SelectProductHo(@NonNull Context context) {
        super(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        setLayoutParams(params);
    }

    public void setProductData(DetailProductBean.ProductsBean productsBean) {
        this.productsBean = productsBean;
        initView();
    }

    private void initView() {
        removeAllViews();
        if (productsBean.isIs_specification() && productsBean.isIs_discount()) {
            specificationDiscountItem();
        } else if (productsBean.isIs_specification() && !productsBean.isIs_discount()) {
            specificationItem();
        } else if (!productsBean.isIs_specification() && productsBean.isIs_discount()) {
            discountItem();
        } else if (!productsBean.isIs_specification() && !productsBean.isIs_discount()) {
            defaultItem();
        }
    }

    private void defaultItem() {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_product_item_ho,this);
        itemHoBinding = WidgetProductItemHoBinding.bind(getChildAt(0));
        itemHoBinding.productName.setText(productsBean.getTitle());
        itemHoBinding.productDescribe.setText(productsBean.getDescribe());
        itemHoBinding.saleQuality.setText("售出"+productsBean.getQuantity());
        itemHoBinding.productImg.setImageResource(getAssetImage100(productsBean.getImage()));
        try {
            itemHoBinding.productPrice.setText(MoneyConvertUtils.changeF2Y((long)productsBean.getPrice()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void discountItem() {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_product_item_discount_ho,this);
        itemDiscountHoBinding = WidgetProductItemDiscountHoBinding.bind(getChildAt(0));
        itemDiscountHoBinding.productName.setText(productsBean.getTitle());
        itemDiscountHoBinding.productDescribe.setText(productsBean.getDescribe());
        itemDiscountHoBinding.saleQuality.setText("售出"+productsBean.getQuantity());
        itemDiscountHoBinding.discountStr.setText(productsBean.getDiscount_str());
        itemDiscountHoBinding.productImg.setImageResource(getAssetImage100(productsBean.getImage()));
        try {
            itemDiscountHoBinding.productPrice.setText(MoneyConvertUtils.changeF2Y((long)productsBean.getPrice()));
            itemDiscountHoBinding.productOriginalPrice.setText(MoneyConvertUtils.changeF2Y((long)productsBean.getOriginal_price()));
            itemDiscountHoBinding.productOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void specificationItem() {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_product_item_specification_ho,this);
        specificationHoBinding = WidgetProductItemSpecificationHoBinding.bind(getChildAt(0));
        specificationHoBinding.productName.setText(productsBean.getTitle());
        specificationHoBinding.productDescribe.setText(productsBean.getDescribe());
        specificationHoBinding.saleQuality.setText("售出"+productsBean.getQuantity());
        specificationHoBinding.productImg.setImageResource(getAssetImage100(productsBean.getImage()));
        try {
            specificationHoBinding.productPrice.setText(MoneyConvertUtils.changeF2Y((long)productsBean.getPrice()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void specificationDiscountItem() {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_product_item_specification_discount_ho,this);
        specificationDiscountHoBinding = WidgetProductItemSpecificationDiscountHoBinding.bind(getChildAt(0));
        specificationDiscountHoBinding.productName.setText(productsBean.getTitle());
        specificationDiscountHoBinding.productDescribe.setText(productsBean.getDescribe());
        specificationDiscountHoBinding.saleQuality.setText("售出"+productsBean.getQuantity());
        specificationDiscountHoBinding.discountStr.setText(productsBean.getDiscount_str());
        specificationDiscountHoBinding.productImg.setImageResource(getAssetImage100(productsBean.getImage()));
        try {
            specificationDiscountHoBinding.productPrice.setText(MoneyConvertUtils.changeF2Y((long)productsBean.getPrice()));
            specificationDiscountHoBinding.productOriginalPrice.setText(MoneyConvertUtils.changeF2Y((long)productsBean.getOriginal_price()));
            specificationDiscountHoBinding.productOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
