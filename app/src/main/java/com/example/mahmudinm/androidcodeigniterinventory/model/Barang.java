package com.example.mahmudinm.androidcodeigniterinventory.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Barang {

    @Expose
    @SerializedName("id") String id;
    @Expose
    @SerializedName("kode") String kode;
    @Expose
    @SerializedName("nama") String nama;
    @Expose
    @SerializedName("stock") String stock;
    @Expose
    @SerializedName("harga") String harga;
    @Expose
    @SerializedName("ukuran") String ukuran;
    @Expose
    @SerializedName("kategori") String kategori;
    @Expose
    @SerializedName("gambar") String gambar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
