package com.huawei.appmate.tech.iap.clientapp.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.huawei.appmate.tech.iap.clientapp.MainActivity;

public class BaseFragment extends Fragment {

    public MainActivity mActivity;
    private BaseViewModel viewModel;

    public void setViewModel(BaseViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void onAttach(@NonNull Activity context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.showProgress.observe(getActivity(), status -> {
            if (status) mActivity.showProgress();
            else mActivity.hideProgress();
        });

    }
}
