package com.example.baselibrary.ui.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baselibrary.R;
import com.example.baselibrary.databinding.BackBarBinding;


/**
 * 顶部 返回bar
 */
public class BackBar extends FrameLayout {

    private final String title;
    private BackBarBinding bind;

    public BackBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BaseLayoutBackBar);
        title = typedArray.getString(R.styleable.BaseLayoutBackBar_title);
        typedArray.recycle();
        initView();
    }

    private void initView() {
        removeAllViews();
        LayoutInflater.from(getContext()).inflate(R.layout.back_bar, this);
        bind = BackBarBinding.bind(getChildAt(0));

        bind.backTitle.setText(title);
        bind.backIconBox.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = (Activity) getContext();
                activity.finish();
            }
        });
    }

}
