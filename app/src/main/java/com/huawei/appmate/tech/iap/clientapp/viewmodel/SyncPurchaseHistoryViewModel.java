package com.huawei.appmate.tech.iap.clientapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.huawei.appmate.tech.iap.clientapp.base.BaseViewModel;
import com.huawei.appmate.PurchaseClient;
import com.huawei.appmate.callback.ReceivedDataListener;
import com.huawei.appmate.model.GenericError;
import com.huawei.appmate.model.PurchaseInfo;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SyncPurchaseHistoryViewModel extends BaseViewModel {
    private SavedStateHandle savedStateHandle;
    public MutableLiveData<String> errorMessage = new MutableLiveData<>(null);

    @Inject
    public SyncPurchaseHistoryViewModel(SavedStateHandle savedStateHandle) {
        this.savedStateHandle = savedStateHandle;
    }

    public void getCreatePurchaseHistoryTransactions() {
        showProgress.setValue(true);
        getPurchasesHistory();
    }

    private void getCreatePurchaseHistoryTransactions(List<PurchaseInfo> purchases) {
        PurchaseClient.getInstance().createPurchaseHistoryTransactions(purchases, new ReceivedDataListener<Boolean, GenericError>() {
            @Override
            public void onSucceeded(Boolean aBoolean) {
                errorMessage.setValue("sync " + (aBoolean ? "successfully" : "unsuccessful"));
                showProgress.setValue(false);
            }

            @Override
            public void onError(GenericError genericError) {
                errorMessage.setValue(genericError.getErrorMessage());
                showProgress.setValue(false);
            }
        });
    }

    private void getPurchasesHistory() {
        PurchaseClient.getInstance().getPurchasesHistory(new ReceivedDataListener<List<PurchaseInfo>, GenericError>() {
            @Override
            public void onSucceeded(List<PurchaseInfo> purchaseInfos) {
                getCreatePurchaseHistoryTransactions(purchaseInfos);
            }

            @Override
            public void onError(GenericError genericError) {
                errorMessage.setValue(genericError.getErrorMessage());
                showProgress.setValue(false);
            }
        });
    }

}
