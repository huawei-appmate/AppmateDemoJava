package com.huawei.appmate.tech.iap.clientapp.viewholder;

import androidx.recyclerview.widget.RecyclerView;

import com.huawei.appmate.model.ProductLocale;
import com.huawei.appmate.tech.iap.clientapp.databinding.ItemProductLocalesBinding;

public class ProductLocalesViewHolder extends RecyclerView.ViewHolder {

    private ItemProductLocalesBinding binding;

    public ProductLocalesViewHolder(ItemProductLocalesBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void setProduct(ProductLocale productLocale) {
        binding.setProductLocale(productLocale);
    }
}
