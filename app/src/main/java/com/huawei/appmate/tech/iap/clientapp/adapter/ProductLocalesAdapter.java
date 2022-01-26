package com.huawei.appmate.tech.iap.clientapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.huawei.appmate.model.ProductLocale;
import com.huawei.appmate.tech.iap.clientapp.databinding.ItemProductLocalesBinding;
import com.huawei.appmate.tech.iap.clientapp.viewholder.ProductLocalesViewHolder;

import java.util.List;

public class ProductLocalesAdapter extends RecyclerView.Adapter<ProductLocalesViewHolder> {

    private List<ProductLocale> productLocaleList;

    public ProductLocalesAdapter(List<ProductLocale> productLocaleList) {
        this.productLocaleList = productLocaleList;
    }

    @NonNull
    @Override
    public ProductLocalesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemProductLocalesBinding binding = ItemProductLocalesBinding.inflate(inflater, parent, false);
        return new ProductLocalesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductLocalesViewHolder holder, int position) {
        holder.setProduct(productLocaleList.get(position));
    }

    @Override
    public int getItemCount() {
        return productLocaleList.size();
    }
}
