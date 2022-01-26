package com.huawei.appmate.tech.iap.clientapp.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {

    public MutableLiveData<Boolean> showProgress = new MutableLiveData<>(false);

}
