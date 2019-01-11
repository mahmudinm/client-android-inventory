package com.example.mahmudinm.androidcodeigniterinventory.network.response;

import com.example.mahmudinm.androidcodeigniterinventory.model.Supplier;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SupplierResponse extends BaseResponse {

    @Expose
    @SerializedName("data") List<Supplier> data;

    public List<Supplier> getData() {
        return data;
    }

    public void setData(List<Supplier> data) {
        this.data = data;
    }
}
