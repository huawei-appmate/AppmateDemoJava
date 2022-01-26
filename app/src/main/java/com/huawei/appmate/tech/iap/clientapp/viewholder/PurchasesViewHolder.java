package com.huawei.appmate.tech.iap.clientapp.viewholder;

import androidx.recyclerview.widget.RecyclerView;

import com.huawei.appmate.model.PurchaseInfo;
import com.huawei.appmate.tech.iap.clientapp.callback.PurchaseCallback;
import com.huawei.appmate.tech.iap.clientapp.databinding.ItemPurchasesBinding;

public class PurchasesViewHolder extends RecyclerView.ViewHolder {

    private ItemPurchasesBinding binding;

    public PurchasesViewHolder(ItemPurchasesBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void setProduct(PurchaseInfo product, PurchaseCallback callback) {
        binding.setPurchase(product);
        binding.setCallback(callback);
    }
}
