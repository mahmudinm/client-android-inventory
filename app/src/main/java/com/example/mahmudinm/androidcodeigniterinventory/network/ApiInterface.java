package com.example.mahmudinm.androidcodeigniterinventory.network;

import com.example.mahmudinm.androidcodeigniterinventory.network.response.AuthResponse;
import com.example.mahmudinm.androidcodeigniterinventory.network.response.SupplierResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    //Auth
    @FormUrlEncoded
    @POST("auth")
    Observable<AuthResponse> postAuth(@Field("username") String username,
                                      @Field("password") String password);

    //Supplier CRUD
    @GET("supplier")
    Observable<SupplierResponse> getSuppliers(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("supplier")
    Observable<SupplierResponse> saveSupplier(@Header("Authorization") String token,
                                               @Field("nama") String nama,
                                               @Field("no_hp") String no_hp,
                                               @Field("alamat") String alamat);


}
