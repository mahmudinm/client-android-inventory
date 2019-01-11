package com.example.mahmudinm.androidcodeigniterinventory.network;

import com.example.mahmudinm.androidcodeigniterinventory.network.response.AuthResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    //Auth
    @FormUrlEncoded
    @POST("auth")
    Observable<AuthResponse> postAuth(@Field("username") String username,
                                      @Field("password") String password);



}
