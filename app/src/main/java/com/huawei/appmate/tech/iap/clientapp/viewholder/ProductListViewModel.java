package com.huawei.appmate.tech.iap.clientapp.viewholder;

import android.app.Activity;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.huawei.appmate.PurchaseClient;
import com.huawei.appmate.callback.PurchaseResultListener;
import com.huawei.appmate.callback.ReceivedDataListener;
import com.huawei.appmate.model.GenericError;
import com.huawei.appmate.model.GenericErrorCode;
import com.huawei.appmate.model.Product;
import com.huawei.appmate.model.ProductType;
import com.huawei.appmate.model.PurchaseInfo;
import com.huawei.appmate.model.PurchaseRequest;
import com.huawei.appmate.model.PurchaseResultInfo;
import com.huawei.appmate.tech.iap.clientapp.R;
import com.huawei.appmate.tech.iap.clientapp.base.BaseViewModel;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProductListViewModel extends BaseViewModel {
    private SavedStateHandle savedStateHandle;
    private List<PurchaseInfo> purchaseInfoList;

    public MutableLiveData<List<Product>> productListData = new MutableLiveData<>(null);
    public MutableLiveData<String> errorMessage = new MutableLiveData<>(null);

    public MutableLiveData<Boolean> initGetProductUI = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> initGetProductsByProductIdListUI = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> initGetProductsByProductTypeUI = new MutableLiveData<>(false);

    @Inject
    public ProductListViewModel(SavedStateHandle savedStateHandle) {
        this.savedStateHandle = savedStateHandle;
        getMyPurchases(); //later getProducts()
    }

    public void setTabSelectedPosition(int position) {
        clearUI();
        switch (position) {
            case 0:
                initGetProductUI.setValue(true);
                getProductList();
                break;
            case 1:
                initGetProductsByProductIdListUI.setValue(true);
                break;
            case 2:
                initGetProductsByProductTypeUI.setValue(true);
                break;
        }
    }

    private void clearUI() {
        initGetProductUI.setValue(true);
        initGetProductsByProductIdListUI.setValue(true);
        initGetProductsByProductTypeUI.setValue(true);
    }

    public void getProductList() {
        showProgress.setValue(true);
        PurchaseClient.getInstance().getProducts(new ReceivedDataListener<List<Product>, GenericError>() {
            @Override
            public void onSucceeded(List<Product> products) {
                productListData.setValue(products);
                showProgress.setValue(false);
            }

            @Override
            public void onError(GenericError genericError) {
                errorMessage.setValue(genericError.getErrorMessage());
                showProgress.setValue(false);
            }
        });
    }

    public void getProductsByProductIdList(String idListString) {
        showProgress.setValue(true);
        List<String> idList = Arrays.asList(idListString.split(","));
        PurchaseClient.getInstance().getProductsByProductIdList(idList, new ReceivedDataListener<List<Product>, GenericError>() {
            @Override
            public void onSucceeded(List<Product> products) {
                productListData.setValue(products);
                showProgress.setValue(false);
            }

            @Override
            public void onError(GenericError genericError) {
                errorMessage.setValue(genericError.getErrorMessage());
                showProgress.setValue(false);
            }
        });
    }

    public void getProductsByProductType(ProductType productType) {
        showProgress.setValue(true);
        PurchaseClient.getInstance().getProductsByProductType(productType, new ReceivedDataListener<List<Product>, GenericError>() {
            @Override
            public void onSucceeded(List<Product> products) {
                productListData.setValue(products);
                showProgress.setValue(false);
            }

            @Override
            public void onError(GenericError genericError) {
                errorMessage.setValue(genericError.getErrorMessage());
                showProgress.setValue(false);
            }
        });
    }

    public void getPurchase(Activity activity, Product product) {
        showProgress.setValue(true);
        PurchaseClient.getInstance().purchase(activity, new PurchaseRequest(product.getProductId(), product.getProductType()), new PurchaseResultListener<PurchaseResultInfo, GenericError>() {
            @Override
            public void onSuccess(PurchaseResultInfo purchaseResultInfo) {
                errorMessage.setValue(purchaseResultInfo.getPurchaseState().getMessage());
                getMyPurchases(); //later getProducts()
            }

            @Override
            public void onError(GenericError genericError) {
                if (genericError.getErrorCode() == GenericErrorCode.PurchaseCanceledByUserError) {
                    errorMessage.setValue(activity.getString(R.string.product_buy_stop));
                } else {
                    if (product.getProductType() == ProductType.CONSUMABLE) {
                        errorMessage.setValue(activity.getString(R.string.products_buy_button_already_product));
                    } else {
                        errorMessage.setValue(genericError.getErrorMessage());
                    }
                }
                showProgress.setValue(false);
            }
        });
    }

    private void getMyPurchases() {
        showProgress.setValue(true);
        PurchaseClient.getInstance().getPurchases(new ReceivedDataListener<List<PurchaseInfo>, GenericError>() {
            @Override
            public void onSucceeded(List<PurchaseInfo> purchaseInfos) {
                purchaseInfoList = purchaseInfos;
                getProductList();
            }

            @Override
            public void onError(GenericError genericError) {
                Log.e("getPurchases", genericError.getErrorMessage());
                getProductList();
            }
        });
    }

    public boolean getProductPurchase(String productId) {
        if (purchaseInfoList != null) {
            for (PurchaseInfo val : purchaseInfoList) {
                if (productId.equals(val.getProductId())) {
                    return true;
                }
            }
        }
        return false;
    }
}
