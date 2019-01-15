package com.example.mahmudinm.androidcodeigniterinventory.view.barang;

import com.example.mahmudinm.androidcodeigniterinventory.network.response.BarangResponse;

public interface BarangView {

    void showProgress();
    void hideProgress();
    void statusSuccess(BarangResponse barangResponse);
    void statusError(String message);

}
