package com.example.mahmudinm.androidcodeigniterinventory.view.penjualan.editor;

import com.example.mahmudinm.androidcodeigniterinventory.network.ApiClient;
import com.example.mahmudinm.androidcodeigniterinventory.network.ApiInterface;
import com.example.mahmudinm.androidcodeigniterinventory.network.response.BarangResponse;
import com.example.mahmudinm.androidcodeigniterinventory.network.response.PenjualanResponse;
import com.example.mahmudinm.androidcodeigniterinventory.network.response.SupplierResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PenjualanPresenter {
    PenjualanView view;
    ApiInterface apiInterface;
    CompositeDisposable disposable;

    public PenjualanPresenter(PenjualanView view) {
        this.view = view;
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        disposable = new CompositeDisposable();
    }

    void getListBarang(String token) {
        view.showProgress();
        disposable.add(
            apiInterface.getBarangList(token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableObserver<BarangResponse>(){
                        @Override
                        public void onNext(BarangResponse barangResponse) {
                            view.setListBarang(barangResponse);
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

    void savePenjualan(String token, String barang_id, String jumlah_barang, String jumlah_harga) {
        view.showProgress();
        disposable.add(
                apiInterface.savePenjualan(token, barang_id, jumlah_barang, jumlah_harga)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<PenjualanResponse>() {
                            @Override
                            public void onNext(PenjualanResponse penjualanResponse) {
                                view.statusSuccess(penjualanResponse.getStatus());
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
