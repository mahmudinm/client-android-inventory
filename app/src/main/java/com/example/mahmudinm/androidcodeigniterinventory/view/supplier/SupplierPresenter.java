package com.example.mahmudinm.androidcodeigniterinventory.view.supplier;

import com.example.mahmudinm.androidcodeigniterinventory.network.ApiClient;
import com.example.mahmudinm.androidcodeigniterinventory.network.ApiInterface;
import com.example.mahmudinm.androidcodeigniterinventory.network.response.SupplierResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SupplierPresenter {

    SupplierView view;
    ApiInterface apiInterface;
    CompositeDisposable compositeDisposable;

    public SupplierPresenter(SupplierView supplierView) {
        this.view = supplierView;
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        compositeDisposable = new CompositeDisposable();
    }

    public void getSuppliers(String token) {
        view.showProgress();
        compositeDisposable.add(
                apiInterface.getSuppliers(token)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<SupplierResponse>(){
                            @Override
                            public void onNext(SupplierResponse supplierResponse) {
                                if (supplierResponse.getStatus().equals("success")) {
                                    view.statusSuccess(supplierResponse);
                                } else {
                                    view.statusError(supplierResponse.getStatus());
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                view.hideProgress();
                            }

                            @Override
                            public void onComplete() {
                                view.hideProgress();

                            }
                        })
        );
    }

}
