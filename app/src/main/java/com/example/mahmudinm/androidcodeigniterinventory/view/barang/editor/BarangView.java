package com.example.mahmudinm.androidcodeigniterinventory.view.barang.editor;

import com.example.mahmudinm.androidcodeigniterinventory.network.response.BarangResponse;

public interface BarangView {

    void statusSuccess(String message);
    void statusError(String message);
    void showProgress();
    void hideProgress();

}
