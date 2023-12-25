package com.example.baselibrary.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private boolean initWindowFocusFlag = false; // 初始化，确保只执行一次

    // 这个方法在 getViewTreeObserver 之后执行
    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (!initWindowFocusFlag) {
            fistInitWindowFocus();
            initWindowFocusFlag = true;
        }
    }

    // 只执行一次的方法
    public void fistInitWindowFocus() {}
}
