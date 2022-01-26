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
import com.huawei.appmate.model.Product;
import com.huawei.appmate.model.ProductType;
import com.huawei.appmate.tech.iap.clientapp.adapter.ProductListAdapter;
import com.huawei.appmate.tech.iap.clientapp.base.BaseFragment;
import com.huawei.appmate.tech.iap.clientapp.callback.ProductCallback;
import com.huawei.appmate.tech.iap.clientapp.databinding.FragmentProductListBinding;
import com.huawei.appmate.tech.iap.clientapp.viewholder.ProductListViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProductListFragment extends BaseFragment implements ProductCallback {

    private FragmentProductListBinding binding;
    private ProductListViewModel viewModel;
    private ProductListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(ProductListViewModel.class);
        binding = FragmentProductListBinding.inflate(inflater, container, false);
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
    }

    private void setEventListener() {
        binding.productTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewModel.setTabSelectedPosition(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.btnSendProductList.setOnClickListener(view -> {
            String productIdListStringData = binding.etProductIdStringList.getText().toString();
            viewModel.getProductsByProductIdList(productIdListStringData);
        });

        binding.btnConsumable.setOnClickListener(view -> {
            viewModel.getProductsByProductType(ProductType.CONSUMABLE);
        });

        binding.btnNonconsumable.setOnClickListener(view -> {
            viewModel.getProductsByProductType(ProductType.NONCONSUMABLE);
        });

        binding.btnSubscription.setOnClickListener(view -> {
            viewModel.getProductsByProductType(ProductType.SUBSCRIPTION);
        });

    }

    private void bindViewModelObservers() {
        viewModel.productListData.observe(getActivity(), data -> {
            if (data != null) {
                initAdapter(data);
            }
        });

        viewModel.errorMessage.observe(getActivity(), message -> {
            if (message != null) {
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.initGetProductUI.observe(getActivity(), isStatus -> {
            if (isStatus) {
                binding.productsByProductIdLayout.setVisibility(View.GONE);
                binding.etProductIdStringList.setText("");

                binding.productsByProductTypeLayout.setVisibility(View.GONE);
            }
        });

        viewModel.initGetProductsByProductIdListUI.observe(getActivity(), isStatus -> {
            if (isStatus) {
                initAdapter(new ArrayList<>());

                binding.productsByProductIdLayout.setVisibility(View.VISIBLE);
                binding.etProductIdStringList.setText("");

                binding.productsByProductTypeLayout.setVisibility(View.GONE);
            }
        });

        viewModel.initGetProductsByProductTypeUI.observe(getActivity(), isStatus -> {
            if (isStatus) {
                initAdapter(new ArrayList<>());

                binding.productsByProductIdLayout.setVisibility(View.GONE);
                binding.etProductIdStringList.setText("");

                binding.productsByProductTypeLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initAdapter(List<Product> productList) {
        adapter = new ProductListAdapter(productList, this);
        binding.rvProductList.setAdapter(adapter);
    }

    @Override
    public void onBuyButtonClick(Product product) {
        viewModel.getPurchase(getActivity(), product);
    }


    @Override
    public boolean getProductPurchase(String productId) {
        return viewModel.getProductPurchase(productId);
    }
}