package com.example.mahmudinm.androidcodeigniterinventory.view.supplier;

import com.example.mahmudinm.androidcodeigniterinventory.network.response.SupplierResponse;

public interface SupplierView {

    void showProgress();
    void hideProgress();
    void statusSuccess(SupplierResponse supplierResponse);
    void statusError(String message);

}
