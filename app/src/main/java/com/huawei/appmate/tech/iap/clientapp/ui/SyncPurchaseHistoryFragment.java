package com.huawei.appmate.tech.iap.clientapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.huawei.appmate.tech.iap.clientapp.base.BaseFragment;
import com.huawei.appmate.tech.iap.clientapp.databinding.FragmentSyncPurchaseHistoryBinding;
import com.huawei.appmate.tech.iap.clientapp.viewmodel.SyncPurchaseHistoryViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SyncPurchaseHistoryFragment extends BaseFragment {

    private FragmentSyncPurchaseHistoryBinding binding;
    private SyncPurchaseHistoryViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(SyncPurchaseHistoryViewModel.class);
        binding = FragmentSyncPurchaseHistoryBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        View view = binding.getRoot();
        setViewModel(viewModel);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setEventListener();
        bindViewModelObservers();
    }

    private void setEventListener() {
        binding.btnSync.setOnClickListener(v -> {
            viewModel.getCreatePurchaseHistoryTransactions();
        });
    }

    private void bindViewModelObservers() {
        viewModel.errorMessage.observe(getActivity(), message -> {
            if (message != null) {
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}