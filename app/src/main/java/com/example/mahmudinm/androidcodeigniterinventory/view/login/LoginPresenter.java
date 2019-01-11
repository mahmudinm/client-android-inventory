package com.example.mahmudinm.androidcodeigniterinventory.view.login;

import com.example.mahmudinm.androidcodeigniterinventory.network.ApiClient;
import com.example.mahmudinm.androidcodeigniterinventory.network.ApiInterface;
import com.example.mahmudinm.androidcodeigniterinventory.network.response.AuthResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    private ApiInterface apiInterface;
    private CompositeDisposable compositeDisposable;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void postAuth(String username, String password) {
        view.showProgress();
        compositeDisposable.add(
          apiInterface.postAuth(username, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableObserver<AuthResponse>(){
                        @Override
                        public void onNext(AuthResponse authResponse) {
                            view.hideProgress();
                            if (authResponse.getStatus().equals("success")) {
                                view.statusSuccess(authResponse.getToken());
                            } else {
                                view.statusError("password atau username salah");
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
