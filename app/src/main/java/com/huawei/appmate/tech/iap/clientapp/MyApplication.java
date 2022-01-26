package com.huawei.appmate.tech.iap.clientapp;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

import com.huawei.appmate.PurchaseClient;

import dagger.hilt.android.HiltAndroidApp;


@HiltAndroidApp
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        PurchaseClient.getInstance(this, "4ssZsJbISA-o7PPavwR7Kw");
    }
}