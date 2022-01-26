package com.huawei.appmate.tech.iap.clientapp.callback;

public interface PurchaseCallback {
    void onConsumeButtonClick(String purchaseToken);
    void onUnsubscribeButtonClick(String purchaseToken);
}
