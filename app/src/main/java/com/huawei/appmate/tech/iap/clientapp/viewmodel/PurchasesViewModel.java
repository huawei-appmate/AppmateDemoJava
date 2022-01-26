package com.huawei.appmate.tech.iap.clientapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.huawei.appmate.PurchaseClient;
import com.huawei.appmate.callback.ReceivedDataListener;
import com.huawei.appmate.model.GenericError;
import com.huawei.appmate.model.PurchaseInfo;
import com.huawei.appmate.tech.iap.clientapp.base.BaseViewModel;


import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PurchasesViewModel extends BaseViewModel {
    private SavedStateHandle savedStateHandle;

    public MutableLiveData<List<PurchaseInfo>> purchasesData = new MutableLiveData<>(null);
    public MutableLiveData<String> errorMessage = new MutableLiveData<>(null);

    @Inject
    public PurchasesViewModel(SavedStateHandle savedStateHandle) {
        this.savedStateHandle = savedStateHandle;
    }

    public void setTabSelectedPosition(int position) {
        if (position == 0) {
            getPurchases();
        } else if (position == 1) {
            getPurchasesHistory();
        }
    }

    private void getPurchasesHistory() {
        showProgress.setValue(true);
        PurchaseClient.getInstance().getPurchasesHistory(new ReceivedDataListener<List<PurchaseInfo>, GenericError>() {
            @Override
            public void onSucceeded(List<PurchaseInfo> purchaseInfos) {
                purchasesData.setValue(purchaseInfos);
                showProgress.setValue(false);
            }

            @Override
            public void onError(GenericError genericError) {
                errorMessage.setValue(genericError.getErrorMessage());
                showProgress.setValue(false);
            }
        });
    }


    public void getPurchases() {
        showProgress.setValue(true);
        PurchaseClient.getInstance().getPurchases(new ReceivedDataListener<List<PurchaseInfo>, GenericError>() {
            @Override
            public void onSucceeded(List<PurchaseInfo> purchaseInfos) {
                purchasesData.setValue(purchaseInfos);
                showProgress.setValue(false);
            }

            @Override
            public void onError(GenericError genericError) {
                errorMessage.setValue(genericError.getErrorMessage());
                showProgress.setValue(false);
            }
        });
    }

    public void getConsumePurchase(String purchaseToken) {
        showProgress.setValue(true);
        PurchaseClient.getInstance().consumePurchase(purchaseToken, new ReceivedDataListener<String, GenericError>() {
            @Override
            public void onSucceeded(String s) {
                errorMessage.setValue(s + " consumed successfully");
                getPurchases();
            }

            @Override
            public void onError(GenericError genericError) {
                errorMessage.setValue(genericError.getErrorMessage());
                showProgress.setValue(false);
            }
        });
    }

    public void getUnsubscribe(String purchaseToken) {
        showProgress.setValue(true);
        PurchaseClient.getInstance().unsubscribe(purchaseToken, new ReceivedDataListener<Boolean, GenericError>() {
            @Override
            public void onSucceeded(Boolean aBoolean) {
                errorMessage.setValue("unsubscribe " + (aBoolean ? "successfully" : "unsuccessful"));
                getPurchases();
            }

            @Override
            public void onError(GenericError genericError) {
                errorMessage.setValue(genericError.getErrorMessage());
                showProgress.setValue(false);
            }
        });
    }

}
