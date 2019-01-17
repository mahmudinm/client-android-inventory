package com.example.mahmudinm.androidcodeigniterinventory.view.penjualan.editor;

import com.example.mahmudinm.androidcodeigniterinventory.network.response.BarangResponse;
import com.example.mahmudinm.androidcodeigniterinventory.network.response.PenjualanResponse;

public interface PenjualanView {

    void showProgress();
    void hideProgress();
    void statusSuccess(String message);
    void statusError(String message);
    void setListBarang(BarangResponse barangResponse);
}
