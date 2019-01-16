package com.example.mahmudinm.androidcodeigniterinventory.view.supplier.editor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mahmudinm.androidcodeigniterinventory.R;
import com.example.mahmudinm.androidcodeigniterinventory.utils.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SupplierActivity extends AppCompatActivity implements SupplierView {

    SupplierPresenter presenter;
    ProgressDialog progressDialog;
    SessionManager session;

    String id, nama, no_hp, alamat;

    @BindView(R.id.nama)
    EditText txtNama;

    @BindView(R.id.no_hp)
    EditText txtNo_hp;

    @BindView(R.id.alamat)
    EditText txtAlamat;

    @BindView(R.id.content_simpan)
    LinearLayout content_simpan;

    @BindView(R.id.content_update)
    LinearLayout content_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier);

        ButterKnife.bind(this);

        session = new SessionManager(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading ...");
        presenter = new SupplierPresenter(this);

        initDataIntent();
        setTextEditor();
    }

    @OnClick(R.id.simpan) void simpan(){
        presenter.saveSupplier(
                session.getKeyToken(),
                txtNama.getText().toString(),
                txtNo_hp.getText().toString(),
                txtAlamat.getText().toString()
        );
    }

    @OnClick(R.id.update) void update() {
        presenter.updateSupplier(
                session.getKeyToken(),
                id,
                txtNama.getText().toString(),
                txtNo_hp.getText().toString(),
                txtAlamat.getText().toString()
        );
    }


    @OnClick(R.id.hapus) void hapus() {
        presenter.deleteSupplier(
                session.getKeyToken(),
                id
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

    private void initDataIntent() {
        Intent intent= getIntent();
        id = intent.getStringExtra("id");
        nama = intent.getStringExtra("nama");
        no_hp = intent.getStringExtra("no_hp");
        alamat = intent.getStringExtra("alamat");
    }

    private void setTextEditor() {
        if (id != null) {
            getSupportActionBar().setTitle("Update data");
            txtNama.setText(nama);
            txtAlamat.setText(alamat);
            txtNo_hp.setText(no_hp);
            content_update.setVisibility(View.VISIBLE);
            content_simpan.setVisibility(View.GONE);
        } else {
            getSupportActionBar().setTitle("Simpan data");
        }
    }
}
