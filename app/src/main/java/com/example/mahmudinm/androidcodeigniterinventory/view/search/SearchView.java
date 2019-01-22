package com.example.mahmudinm.androidcodeigniterinventory.view.search;

import com.example.mahmudinm.androidcodeigniterinventory.network.response.PenjualanResponse;

public interface SearchView {

    void showProgress();
    void hideProgress();
    void statusSuccess(PenjualanResponse penjualanResponse);
    void statusError(String message);
}
