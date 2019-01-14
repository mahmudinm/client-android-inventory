package com.example.mahmudinm.androidcodeigniterinventory.view.supplier;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mahmudinm.androidcodeigniterinventory.R;
import com.example.mahmudinm.androidcodeigniterinventory.utils.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ESupplierActivity extends AppCompatActivity implements ESupplierView{

    ESupplierPresenter presenter;
    ProgressDialog progressDialog;
    SessionManager session;

    @BindView(R.id.nama)
    EditText nama;

    @BindView(R.id.no_hp)
    EditText no_hp;

    @BindView(R.id.alamat)
    EditText alamat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esupplier);
        getSupportActionBar().setTitle("Edit Tambah Supplier");

        ButterKnife.bind(this);

        session = new SessionManager(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading ...");
        presenter = new ESupplierPresenter(this);

    }

    @OnClick(R.id.simpan) void simpan(){
        presenter.saveSupplier(
                session.getKeyToken(),
                nama.getText().toString(),
                no_hp.getText().toString(),
                alamat.getText().toString()
        );
    }


    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void statusSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void statusError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
