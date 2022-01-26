package com.huawei.appmate.tech.iap.clientapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.huawei.appmate.tech.iap.clientapp.base.BaseViewModel;
import com.huawei.appmate.PurchaseClient;
import com.huawei.appmate.callback.ReceivedDataListener;
import com.huawei.appmate.data.remote.response.UserRelation;
import com.huawei.appmate.model.GenericError;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UserRelationViewModel extends BaseViewModel {
    private SavedStateHandle savedStateHandle;
    public MutableLiveData<String> errorMessage = new MutableLiveData<>(null);
    public MutableLiveData<String> userIdValue = new MutableLiveData<>(null);

    @Inject
    public UserRelationViewModel(SavedStateHandle savedStateHandle) {
        this.savedStateHandle = savedStateHandle;
        getUserId();
    }

    public void getUserId() {
        showProgress.setValue(true);
        PurchaseClient.getInstance().getUserId(new ReceivedDataListener<String, GenericError>() {
            @Override
            public void onSucceeded(String userId) {
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

    public void createUserIdRelation(String masterUserId) {
        showProgress.setValue(true);
        PurchaseClient.getInstance().createUserIdRelation(masterUserId, new ReceivedDataListener<UserRelation, GenericError>() {
            @Override
            public void onSucceeded(UserRelation userRelation) {
                errorMessage.setValue("deleteSubUserIdRelation " + userRelation.toString());
                showProgress.setValue(false);
            }

            @Override
            public void onError(GenericError genericError) {
                errorMessage.setValue(genericError.getErrorMessage());
                showProgress.setValue(false);
            }
        });
    }

    public void deleteSubUserIdRelation(String subUserId) {
        showProgress.setValue(true);
        PurchaseClient.getInstance().deleteSubUserIdRelation(subUserId, new ReceivedDataListener<Boolean, GenericError>() {
            @Override
            public void onSucceeded(Boolean aBoolean) {
                errorMessage.setValue("deleteSubUserIdRelation " + (aBoolean ? "successfully" : "unsuccessful"));
                showProgress.setValue(false);
            }

            @Override
            public void onError(GenericError genericError) {
                errorMessage.setValue(genericError.getErrorMessage());
                showProgress.setValue(false);
            }
        });
    }

    public void deleteMasterUserIdRelation(String masterUserId) {
        showProgress.setValue(true);
        PurchaseClient.getInstance().deleteMasterUserIdRelation(masterUserId, new ReceivedDataListener<Boolean, GenericError>() {
            @Override
            public void onSucceeded(Boolean aBoolean) {
                errorMessage.setValue("deleteMasterUserIdRelation " + (aBoolean ? "successfully" : "unsuccessful"));
                showProgress.setValue(false);
            }

            @Override
            public void onError(GenericError genericError) {
                errorMessage.setValue(genericError.getErrorMessage());
                showProgress.setValue(false);
            }
        });
    }
}
