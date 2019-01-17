package com.example.mahmudinm.androidcodeigniterinventory.network.response;

import com.example.mahmudinm.androidcodeigniterinventory.model.Penjualan;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PenjualanResponse extends BaseResponse {

    @Expose
    @SerializedName("data") List<Penjualan> data;

    public List<Penjualan> getData() {
        return data;
    }

    public void setData(List<Penjualan> data) {
        this.data = data;
    }
}
