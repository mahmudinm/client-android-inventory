package com.example.mahmudinm.androidcodeigniterinventory.view.penjualan;

import com.example.mahmudinm.androidcodeigniterinventory.network.ApiClient;
import com.example.mahmudinm.androidcodeigniterinventory.network.ApiInterface;
import com.example.mahmudinm.androidcodeigniterinventory.network.response.PenjualanResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class PenjualanPresenter {

    PenjualanView view;
    ApiInterface apiInterface;
    CompositeDisposable compositeDisposable;

    public PenjualanPresenter(PenjualanView view) {
        this.view = view;
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        compositeDisposable = new CompositeDisposable();
    }

    public void getPenjualan(String token) {
        view.showProgress();
        compositeDisposable.add(
                apiInterface.getPenjualan(token)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<PenjualanResponse>(){
                            @Override
                            public void onNext(PenjualanResponse penjualanResponse) {
                                view.statusSuccess(penjualanResponse);
                            }

                            @Override
                            public void onError(Throwable e) {
                                view.hideProgress();
                                view.statusError(e.getLocalizedMessage());
                            }

                            @Override
                            public void onComplete() {
                                view.hideProgress();
                            }
                        })
        );
    }

}
