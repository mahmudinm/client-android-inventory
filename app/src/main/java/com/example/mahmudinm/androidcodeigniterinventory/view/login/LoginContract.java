package com.example.mahmudinm.androidcodeigniterinventory.view.login;

import com.example.mahmudinm.androidcodeigniterinventory.network.response.AuthResponse;

public interface LoginContract {

    interface View {
        void showProgress();
        void hideProgress();
//        void statusSuccess(AuthResponse authResponse);
        void statusSuccess(String message);
        void statusError(String message);
    }

    interface Presenter {
        void postAuth(String username, String password);
    }

}
