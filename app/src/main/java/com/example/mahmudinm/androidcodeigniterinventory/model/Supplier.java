package com.example.mahmudinm.androidcodeigniterinventory.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Supplier {

    @Expose
    @SerializedName("id") String id;
    @Expose
    @SerializedName("nama") String nama;
    @Expose
    @SerializedName("no_hp") String no_hp;
    @Expose
    @SerializedName("alamat") String alamat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
