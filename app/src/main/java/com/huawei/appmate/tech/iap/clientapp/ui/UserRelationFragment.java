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
import com.huawei.appmate.tech.iap.clientapp.databinding.FragmentUserRelationBinding;
import com.huawei.appmate.tech.iap.clientapp.util.AppUtil;
import com.huawei.appmate.tech.iap.clientapp.viewmodel.UserRelationViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UserRelationFragment extends BaseFragment {

    private FragmentUserRelationBinding binding;
    private UserRelationViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(UserRelationViewModel.class);
        binding = FragmentUserRelationBinding.inflate(inflater, container, false);
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
        binding.clientUserIdValue.setOnClickListener(view -> {
            AppUtil.copyToClipboard(getContext(), binding.clientUserIdValue.getText().toString());
        });

        binding.btnCreateRelation.setOnClickListener(view -> {
            viewModel.createUserIdRelation(binding.createRelationUserId.getText().toString());
        });

        binding.btnSubUserDeleteRelation.setOnClickListener(view -> {
            viewModel.deleteSubUserIdRelation(binding.deleteRelationSubUserId.getText().toString());
        });

        binding.btnMasterUserDeleteRelation.setOnClickListener(view -> {
            viewModel.deleteMasterUserIdRelation(binding.deleteRelationMasterUserId.getText().toString());
        });
    }

    private void bindViewModelObservers() {
        viewModel.errorMessage.observe(getActivity(), message -> {
            if (message != null) Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        });

        viewModel.userIdValue.observe(getActivity(), userIdValue -> {
            if (userIdValue != null) {
                binding.deleteRelationSubUserId.setText(userIdValue);
                binding.clientUserIdValue.setText(userIdValue);
            }
        });
    }

}