package com.example.mahmudinm.androidcodeigniterinventory.view.penjualan;

import com.example.mahmudinm.androidcodeigniterinventory.network.response.PenjualanResponse;

public interface PenjualanView {

    void showProgress();
    void hideProgress();
    void statusSuccess(PenjualanResponse penjualanResponse);
    void statusError(String message);

}
