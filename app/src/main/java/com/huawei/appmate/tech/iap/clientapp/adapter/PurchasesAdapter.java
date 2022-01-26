package com.huawei.appmate.tech.iap.clientapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.huawei.appmate.model.PurchaseInfo;
import com.huawei.appmate.tech.iap.clientapp.callback.PurchaseCallback;
import com.huawei.appmate.tech.iap.clientapp.databinding.ItemPurchasesBinding;
import com.huawei.appmate.tech.iap.clientapp.viewholder.PurchasesViewHolder;

import java.util.List;

public class PurchasesAdapter extends RecyclerView.Adapter<PurchasesViewHolder> {

    private PurchaseCallback callback;
    private List<PurchaseInfo> purchasesList;

    public PurchasesAdapter(List<PurchaseInfo> purchasesList, PurchaseCallback callback) {
        this.purchasesList = purchasesList;
        this.callback = callback;
    }

    @NonNull
    @Override
    public PurchasesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemPurchasesBinding binding = ItemPurchasesBinding.inflate(inflater, parent, false);
        return new PurchasesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchasesViewHolder holder, int position) {
        holder.setProduct(purchasesList.get(position),callback);
    }

    @Override
    public int getItemCount() {
        return purchasesList.size();
    }
}
