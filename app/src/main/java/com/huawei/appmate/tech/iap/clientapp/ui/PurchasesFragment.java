package com.huawei.appmate.tech.iap.clientapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.tabs.TabLayout;
import com.huawei.appmate.model.PurchaseInfo;
import com.huawei.appmate.tech.iap.clientapp.R;
import com.huawei.appmate.tech.iap.clientapp.adapter.PurchasesAdapter;
import com.huawei.appmate.tech.iap.clientapp.base.BaseFragment;
import com.huawei.appmate.tech.iap.clientapp.callback.PurchaseCallback;
import com.huawei.appmate.tech.iap.clientapp.databinding.FragmentMyProductBinding;
import com.huawei.appmate.tech.iap.clientapp.viewmodel.PurchasesViewModel;


import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PurchasesFragment extends BaseFragment implements PurchaseCallback {

    private FragmentMyProductBinding binding;
    private PurchasesViewModel viewModel;
    private PurchasesAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(PurchasesViewModel.class);
        binding = FragmentMyProductBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        View view = binding.getRoot();
        setViewModel(viewModel);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViewModelObservers();
        setEventListener();
        viewModel.getPurchases();
    }

    private void setEventListener() {
        binding.purchasesTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewModel.setTabSelectedPosition(tab.getPosition());
                binding.tvMyPurchasesDesc.setText(getString(tab.getPosition() == 0 ? R.string.fetching_current_purchase : R.string.fetching_all_purchase));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void bindViewModelObservers() {
        viewModel.purchasesData.observe(getActivity(), data -> {
            if (data != null) initAdapter(data);
        });

        viewModel.errorMessage.observe(getActivity(), message -> {
            if (message != null) Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        });
    }

    private void initAdapter(List<PurchaseInfo> productList) {
        adapter = new PurchasesAdapter(productList, this);
        binding.rvMyPurchasesList.setAdapter(adapter);
    }

    @Override
    public void onConsumeButtonClick(String purchaseToken) {
        viewModel.getConsumePurchase(purchaseToken);
    }

    @Override
    public void onUnsubscribeButtonClick(String purchaseToken) {
        viewModel.getUnsubscribe(purchaseToken);
    }

}