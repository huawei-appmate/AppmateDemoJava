package com.huawei.appmate.tech.iap.clientapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.huawei.appmate.tech.iap.clientapp.base.BaseFragment;
import com.huawei.appmate.tech.iap.clientapp.base.BaseViewModel;
import com.huawei.appmate.tech.iap.clientapp.databinding.FragmentErrorCodeBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ErrorCodeFragment extends BaseFragment {

    private FragmentErrorCodeBinding binding;
    private BaseViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(BaseViewModel.class);
        binding = FragmentErrorCodeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        setViewModel(viewModel);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}