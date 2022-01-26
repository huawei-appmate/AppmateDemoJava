package com.huawei.appmate.tech.iap.clientapp.viewholder;

import androidx.recyclerview.widget.RecyclerView;

import com.huawei.appmate.model.Product;
import com.huawei.appmate.tech.iap.clientapp.adapter.ProductLocalesAdapter;
import com.huawei.appmate.tech.iap.clientapp.callback.ProductCallback;
import com.huawei.appmate.tech.iap.clientapp.databinding.ItemProductBinding;

public class ProductViewHolder extends RecyclerView.ViewHolder {

    private ItemProductBinding binding;
    private ProductLocalesAdapter adapter;

    public ProductViewHolder(ItemProductBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void setProduct(Product product, ProductCallback callback) {
        binding.setProductData(product);
        binding.setCallback(callback);
        adapter = new ProductLocalesAdapter(product.getProductLocales());
        binding.rvProductLocales.setAdapter(adapter);
    }
}
