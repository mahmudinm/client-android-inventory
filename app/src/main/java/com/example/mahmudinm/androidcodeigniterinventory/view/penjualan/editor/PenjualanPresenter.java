package com.example.mahmudinm.androidcodeigniterinventory.view.penjualan.editor;

import com.example.mahmudinm.androidcodeigniterinventory.network.ApiClient;
import com.example.mahmudinm.androidcodeigniterinventory.network.ApiInterface;
import com.example.mahmudinm.androidcodeigniterinventory.network.response.BarangResponse;
import com.example.mahmudinm.androidcodeigniterinventory.network.response.PenjualanResponse;

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
        Call<BarangResponse> getBarangList = apiInterface.getBarangList(token);
        getBarangList.enqueue(new Callback<BarangResponse>() {
            @Override
            public void onResponse(Call<BarangResponse> call, Response<BarangResponse> response) {
                view.setListBarang(response.body());
                view.hideProgress();
            }

            @Override
            public void onFailure(Call<BarangResponse> call, Throwable t) {
                view.statusError(t.getLocalizedMessage());
                view.hideProgress();
            }
        });
//        disposable.add(
//            apiInterface.getBarangList(token)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribeWith(new DisposableObserver<BarangResponse>(){
//                        @Override
//                        public void onNext(BarangResponse barangResponse) {
//                            view.setListBarang(barangResponse);
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            view.hideProgress();
//                            view.statusError(e.getLocalizedMessage());
//                        }
//
//                        @Override
//                        public void onComplete() {
//                            view.hideProgress();
//                        }
//                    })
//        );
    }

}
