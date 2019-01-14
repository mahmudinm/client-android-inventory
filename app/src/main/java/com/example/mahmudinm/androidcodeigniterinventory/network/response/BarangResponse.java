package com.example.mahmudinm.androidcodeigniterinventory.network.response;

import com.example.mahmudinm.androidcodeigniterinventory.model.Barang;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BarangResponse extends BaseResponse {

    @Expose
    @SerializedName("data") List<Barang> data;

    public List<Barang> getData() {
        return data;
    }

    public void setData(List<Barang> data) {
        this.data = data;
    }
}
