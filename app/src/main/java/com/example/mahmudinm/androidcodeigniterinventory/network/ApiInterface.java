package com.example.mahmudinm.androidcodeigniterinventory.network;

import com.example.mahmudinm.androidcodeigniterinventory.model.Barang;
import com.example.mahmudinm.androidcodeigniterinventory.network.response.AuthResponse;
import com.example.mahmudinm.androidcodeigniterinventory.network.response.BarangResponse;
import com.example.mahmudinm.androidcodeigniterinventory.network.response.PenjualanResponse;
import com.example.mahmudinm.androidcodeigniterinventory.network.response.SupplierResponse;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @FormUrlEncoded
    @POST("supplier/update/{id}")
    Completable updateSupplier(@Header("Authorization") String token,
                               @Path("id") String id,
                               @Field("nama") String nama,
                               @Field("no_hp") String no_hp,
                               @Field("alamat") String alamat);

    @POST("supplier/delete/{id}")
    Completable deleteSupplier(@Header("Authorization") String token,
                               @Path("id") String id);

//Barang CRUD
    @GET("barang")
    Observable<BarangResponse> getBarang(@Header("Authorization") String token);

    @GET("barang/list")
//    Call<BarangResponse> getBarangList(@Header("Authorization") String token);
    Observable<BarangResponse> getBarangList(@Header("Authorization") String token);

    @Multipart
    @POST("barang")
    Observable<BarangResponse> saveBarang(@Header("Authorization") String token,
                                          @Part MultipartBody.Part gambar,
                                          @Part("kode") RequestBody kode,
                                          @Part("nama") RequestBody nama,
                                          @Part("stock") RequestBody stock,
                                          @Part("harga") RequestBody harga,
                                          @Part("ukuran") RequestBody ukuran);
    @Multipart
    @POST("barang/update/{id}")
    Completable updateBarang(@Header("Authorization") String token,
                             @Path("id") String id,
                             @Part MultipartBody.Part gambar,
                             @Part("kode") RequestBody kode,
                             @Part("nama") RequestBody nama,
                             @Part("stock") RequestBody stock,
                             @Part("harga") RequestBody harga,
                             @Part("ukuran") RequestBody ukuran);

    @POST("barang/delete/{id}")
    Completable deleteBarang(@Header("Authorization") String token,
                             @Path("id") String id);

//Penjualan CRUD
    @GET("penjualan")
    Observable<PenjualanResponse> getPenjualan(@Header("Authorization") String token,
                                               @Query("search") String search);

    @FormUrlEncoded
    @POST("penjualan")
    Observable<PenjualanResponse> savePenjualan(@Header("Authorization") String token,
                                                @Field("barang_id") String barang_id,
                                                @Field("jumlah_barang") String jumlah_barang,
                                                @Field("jumlah_harga") String jumlah_harga);

    @FormUrlEncoded
    @POST("penjualan/update/{id}")
    Completable updatePenjualan(@Header("Authorization") String token,
                                @Path("id") String id,
                                @Field("barang_id") String barang_id,
                                @Field("jumlah_barang") String jumlah_barang,
                                @Field("jumlah_harga") String jumlah_harga);

    @POST("penjualan/delete/{id}")
    Completable deletePenjualan(@Header("Authorization") String token,
                                @Path("id") String id);

}
