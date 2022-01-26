package com.huawei.appmate.tech.iap.clientapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.huawei.appmate.model.Product;
import com.huawei.appmate.tech.iap.clientapp.callback.ProductCallback;
import com.huawei.appmate.tech.iap.clientapp.databinding.ItemProductBinding;
import com.huawei.appmate.tech.iap.clientapp.viewholder.ProductViewHolder;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private List<Product> productList;
    private ProductCallback callback;

    public ProductListAdapter(List<Product> productList, ProductCallback callback) {
        this.productList = productList;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemProductBinding binding = ItemProductBinding.inflate(inflater, parent, false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.setProduct(productList.get(position),callback);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
