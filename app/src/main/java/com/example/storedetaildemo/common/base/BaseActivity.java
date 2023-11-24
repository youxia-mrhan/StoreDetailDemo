package com.example.storedetaildemo.common.base;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private boolean isFist = false;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!isFist) {
            fistInit();
            isFist = true;
        }
    }

    public void fistInit() {

    }
}
