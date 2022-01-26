package com.huawei.appmate.tech.iap.clientapp.viewholder;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.huawei.appmate.tech.iap.clientapp.base.BaseViewModel;
import com.huawei.appmate.PurchaseClient;
import com.huawei.appmate.callback.ReceivedDataListener;
import com.huawei.appmate.domain.model.PlatformServiceType;
import com.huawei.appmate.model.GenericError;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SettingViewModel extends BaseViewModel {
    private SavedStateHandle savedStateHandle;
    private String userId;

    public boolean editMode = false;
    public MutableLiveData<Boolean> setEditMode = new MutableLiveData<>(false);
    public MutableLiveData<String> errorMessage = new MutableLiveData<>(null);
    public MutableLiveData<String> userIdValue = new MutableLiveData<>(null);
    public MutableLiveData<Boolean> hmsSelected = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> gmsSelected = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> clearRadioButton = new MutableLiveData<>(false);

    @Inject
    public SettingViewModel(SavedStateHandle savedStateHandle) {
        this.savedStateHandle = savedStateHandle;
        getPlatform();
        getUserId();
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void clickUserIdChange() {
        editMode = !editMode;
        if (!editMode) {
            PurchaseClient.getInstance().setUserId(userId);
            errorMessage.setValue("Set User Id successfully");
            setEditMode.setValue(false);
        } else {
            setEditMode.setValue(true);
        }
    }

    public void getUserId() {
        showProgress.setValue(true);
        PurchaseClient.getInstance().getUserId(new ReceivedDataListener<String, GenericError>() {
            @Override
            public void onSucceeded(String userId) {
                setUserId(userId);
                userIdValue.setValue(userId);
                showProgress.setValue(false);
            }

            @Override
            public void onError(GenericError genericError) {
                errorMessage.setValue(genericError.getErrorMessage());
                showProgress.setValue(false);
            }
        });
    }

    public void getPlatform() {
        if (PurchaseClient.getInstance().getPlatformType() == PlatformServiceType.GMS) {
            gmsSelected.setValue(true);
        } else if (PurchaseClient.getInstance().getPlatformType() == PlatformServiceType.HMS) {
            hmsSelected.setValue(true);
        } else {
            clearRadioButton.setValue(true);
        }
    }

    public void setPlatform(PlatformServiceType platform) {
        showProgress.setValue(true);
        PurchaseClient.getInstance().setPlatformType(platform, new ReceivedDataListener<Boolean, GenericError>() {
            @Override
            public void onSucceeded(Boolean aBoolean) {
                errorMessage.setValue("Set Platform Type " + (aBoolean ? "successfully" : "unsuccessful"));
                showProgress.setValue(false);
            }

            @Override
            public void onError(GenericError genericError) {
                errorMessage.setValue(genericError.getErrorMessage());
                clearRadioButton.setValue(true);
                showProgress.setValue(false);
                getPlatform();
            }
        });
    }
}
