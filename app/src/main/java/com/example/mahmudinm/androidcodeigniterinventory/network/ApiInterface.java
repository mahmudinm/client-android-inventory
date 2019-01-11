package com.example.mahmudinm.androidcodeigniterinventory.network;

import com.example.mahmudinm.androidcodeigniterinventory.network.response.AuthResponse;

import io.reactivex.Observable;

public interface ApiInterface {

    //Auth
    Observable<AuthResponse> postAuth(String username, String password);



}
