package com.example.mahmudinm.androidcodeigniterinventory.view.supplier.editor;

public interface ESupplierView {

    void showProgress();
    void hideProgress();
    void statusSuccess(String message);
    void statusError(String message);
}
