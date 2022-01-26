package com.huawei.appmate.tech.iap.clientapp.callback;


import com.huawei.appmate.model.Product;

public interface ProductCallback {
    void onBuyButtonClick(Product product);

    boolean getProductPurchase(String productId);
}
