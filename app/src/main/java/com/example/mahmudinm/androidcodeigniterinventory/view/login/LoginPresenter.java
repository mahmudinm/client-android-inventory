package com.example.mahmudinm.androidcodeigniterinventory.view.login;

import com.example.mahmudinm.androidcodeigniterinventory.network.ApiClient;
import com.example.mahmudinm.androidcodeigniterinventory.network.ApiInterface;
import com.example.mahmudinm.androidcodeigniterinventory.network.response.AuthResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter {

    private LoginView view;
    private ApiInterface apiInterface;
    private CompositeDisposable compositeDisposable;

    public LoginPresenter(LoginView view) {
        this.view = view;
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        compositeDisposable = new CompositeDisposable();
    }


    public void loginAuth(String username, String password) {
        view.showProgress();
        compositeDisposable.add(
          apiInterface.postAuth(username, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableObserver<AuthResponse>(){
                        @Override
                        public void onNext(AuthResponse authResponse) {
                            view.hideProgress();
                            if (authResponse.getStatus().equals("succes")) {
                                view.statusSuccess(authResponse);
                            } else {
                                view.statusError(authResponse.getStatus());
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
