package com.example.mahmudinm.androidcodeigniterinventory.view.login;

import com.example.mahmudinm.androidcodeigniterinventory.network.response.AuthResponse;

public interface LoginView {

        void showProgress();
        void hideProgress();
        void statusSuccess(AuthResponse authResponse);
        void statusError(String message);

}
