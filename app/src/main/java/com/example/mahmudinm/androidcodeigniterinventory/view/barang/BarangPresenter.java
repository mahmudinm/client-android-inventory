package com.example.mahmudinm.androidcodeigniterinventory.view.barang;

import com.example.mahmudinm.androidcodeigniterinventory.network.ApiClient;
import com.example.mahmudinm.androidcodeigniterinventory.network.ApiInterface;
import com.example.mahmudinm.androidcodeigniterinventory.network.response.BarangResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class BarangPresenter {

    BarangView view;
    ApiInterface apiInterface;
    CompositeDisposable compositeDisposable;

    public BarangPresenter(BarangView view) {
        this.view = view;
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        compositeDisposable = new CompositeDisposable();
    }

    public void getBarang(String token) {
        view.showProgress();
        compositeDisposable.add(
                apiInterface.getBarang(token)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<BarangResponse>(){
                            @Override
                            public void onNext(BarangResponse barangResponse) {
                                view.statusSuccess(barangResponse);
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
