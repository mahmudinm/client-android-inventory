package com.example.mahmudinm.androidcodeigniterinventory.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Penjualan {

    @Expose
    @SerializedName("id") String id;
    @Expose
    @SerializedName("barang_id") String barang_id;
    @Expose
    @SerializedName("jumlah_barang") String jumlah_barang;
    @Expose
    @SerializedName("jumlah_harga") String jumlah_harga;
    @Expose
    @SerializedName("tanggal") String tanggal;
    @Expose
    @SerializedName("nama") String nama;
    @Expose
    @SerializedName("harga") String harga;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBarang_id() {
        return barang_id;
    }

    public void setBarang_id(String barang_id) {
        this.barang_id = barang_id;
    }

    public String getJumlah_barang() {
        return jumlah_barang;
    }

    public void setJumlah_barang(String jumlah_barang) {
        this.jumlah_barang = jumlah_barang;
    }

    public String getJumlah_harga() {
        return jumlah_harga;
    }

    public void setJumlah_harga(String jumlah_harga) {
        this.jumlah_harga = jumlah_harga;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
