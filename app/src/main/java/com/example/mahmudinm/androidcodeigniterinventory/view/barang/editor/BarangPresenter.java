package com.example.mahmudinm.androidcodeigniterinventory.view.barang.editor;

import com.example.mahmudinm.androidcodeigniterinventory.network.ApiClient;
import com.example.mahmudinm.androidcodeigniterinventory.network.ApiInterface;
import com.example.mahmudinm.androidcodeigniterinventory.network.response.BarangResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class BarangPresenter {

    BarangView view;
    CompositeDisposable disposable;
    ApiInterface apiInterface;

    public BarangPresenter(BarangView view) {
        this.view = view;
        disposable = new CompositeDisposable();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    void saveBarang(String token, MultipartBody.Part gambar, RequestBody kode, RequestBody nama, RequestBody
            stock, RequestBody harga, RequestBody ukuran) {
        view.showProgress();
        disposable.add(
            apiInterface.saveBarang(token, gambar, kode, nama, stock, harga, ukuran)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<BarangResponse>() {
                    @Override
                    public void onNext(BarangResponse barangResponse) {
                        view.statusSuccess(barangResponse.getStatus());
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.statusError(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        view.hideProgress();
                    }
                })
        );
    }

    void updateBarang(String token, String id, MultipartBody.Part gambar, RequestBody kode,
                      RequestBody nama, RequestBody stock, RequestBody harga, RequestBody ukuran) {
        view.showProgress();
        disposable.add(
                apiInterface.updateBarang(token, id, gambar, kode, nama, stock, harga, ukuran)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableCompletableObserver(){
                        @Override
                        public void onComplete() {
                            view.hideProgress();
                            view.statusSuccess("berhasil update");
                        }

                        @Override
                        public void onError(Throwable e) {
                            view.statusError(e.getLocalizedMessage());
                        }
                    })
        );
    }

    void deleteBarang(String token, String id) {
        view.showProgress();
        disposable.add(
                apiInterface.deleteBarang(token, id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableCompletableObserver(){
                            @Override
                            public void onComplete() {
                                view.hideProgress();
                                view.statusSuccess("berhasil update");
                            }

                            @Override
                            public void onError(Throwable e) {
                                view.statusError(e.getLocalizedMessage());
                            }
                        })
        );
    }
}
