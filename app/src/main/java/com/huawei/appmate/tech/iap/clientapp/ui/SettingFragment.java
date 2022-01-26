package com.huawei.appmate.tech.iap.clientapp.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.huawei.appmate.domain.model.PlatformServiceType;
import com.huawei.appmate.tech.iap.clientapp.base.BaseFragment;
import com.huawei.appmate.tech.iap.clientapp.databinding.FragmentSettingBinding;
import com.huawei.appmate.tech.iap.clientapp.util.AppUtil;
import com.huawei.appmate.tech.iap.clientapp.viewholder.SettingViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SettingFragment extends BaseFragment {

    private FragmentSettingBinding binding;
    private SettingViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(SettingViewModel.class);
        binding = FragmentSettingBinding.inflate(inflater, container, false);
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
            if (!viewModel.editMode) {
                AppUtil.copyToClipboard(getContext(), binding.clientUserIdValue.getText().toString());
            }
        });

        binding.rbGMS.setOnClickListener(view -> {
            viewModel.setPlatform(PlatformServiceType.GMS);
        });

        binding.rbHMS.setOnClickListener(view -> {
            viewModel.setPlatform(PlatformServiceType.HMS);
        });

        binding.btnSetUserId.setOnClickListener(v -> {
            viewModel.clickUserIdChange();
        });

        binding.clientUserIdValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setUserId(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void bindViewModelObservers() {
        viewModel.errorMessage.observe(getActivity(), message -> {
            if (message != null) Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        });

        viewModel.userIdValue.observe(getActivity(), userIdValue -> {
            if (userIdValue != null) binding.clientUserIdValue.setText(userIdValue);
        });

        viewModel.hmsSelected.observe(getActivity(), status -> {
            binding.rbHMS.setChecked(status);
        });

        viewModel.gmsSelected.observe(getActivity(), status -> {
            binding.rbGMS.setChecked(status);
        });

        viewModel.clearRadioButton.observe(getActivity(), status -> {
            if (status != null && status) binding.rdgMobileServices.clearCheck();
        });

        viewModel.setEditMode.observe(getActivity(), editMode -> {
            if (editMode) {
                binding.clientUserIdValue.setFocusable(true);
                binding.clientUserIdValue.setFocusableInTouchMode(true);
                binding.clientUserIdValue.setClickable(true);
                binding.btnSetUserId.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_menu_send));

            } else {
                binding.clientUserIdValue.setFocusable(false);
                binding.clientUserIdValue.setFocusableInTouchMode(false);
                binding.clientUserIdValue.setClickable(false);
                binding.btnSetUserId.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_menu_edit));
            }
        });
    }

}