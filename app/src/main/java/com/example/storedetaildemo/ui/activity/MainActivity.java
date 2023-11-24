package com.example.storedetaildemo.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.storedetaildemo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toStoreDetailPager(View v) {
        Intent intent = new Intent(this, StoreDetailActivity.class);
        startActivity(intent);
    }
}