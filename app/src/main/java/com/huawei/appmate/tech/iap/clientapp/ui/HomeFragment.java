package com.huawei.appmate.tech.iap.clientapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.huawei.appmate.tech.iap.clientapp.base.BaseFragment;
import com.huawei.appmate.tech.iap.clientapp.base.BaseViewModel;
import com.huawei.appmate.tech.iap.clientapp.databinding.FragmentHomeBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends BaseFragment {
    private FragmentHomeBinding binding;
    private BaseViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(BaseViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        setViewModel(viewModel);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setEventListener();
    }

    private void setEventListener() {
        binding.productListButton.setOnClickListener(view -> {
            NavDirections action = HomeFragmentDirections.actionHomeFragmentToProductListFragment();
            Navigation.findNavController(view).navigate(action);
        });

        binding.myPurchase.setOnClickListener(view -> {
            NavDirections action = HomeFragmentDirections.actionHomeFragmentToMyPurchasesFragment();
            Navigation.findNavController(view).navigate(action);
        });

        binding.errorCodeList.setOnClickListener(view -> {
            NavDirections action = HomeFragmentDirections.actionHomeFragmentToErrorCodeFragment();
            Navigation.findNavController(view).navigate(action);
        });

        binding.syncPurchaseHistory.setOnClickListener(view -> {
            NavDirections action = HomeFragmentDirections.actionHomeFragmentToSyncPurchaseHistoryFragment();
            Navigation.findNavController(view).navigate(action);
        });

        binding.setting.setOnClickListener(view -> {
            NavDirections action = HomeFragmentDirections.actionHomeFragmentToSettingFragment();
            Navigation.findNavController(view).navigate(action);
        });

        binding.userRelation.setOnClickListener(view -> {
            NavDirections action = HomeFragmentDirections.actionHomeFragmentToUserRelationFragment();
            Navigation.findNavController(view).navigate(action);
        });

    }


}