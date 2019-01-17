package com.example.mahmudinm.androidcodeigniterinventory.view.penjualan.editor;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mahmudinm.androidcodeigniterinventory.R;
import com.example.mahmudinm.androidcodeigniterinventory.model.Barang;
import com.example.mahmudinm.androidcodeigniterinventory.network.response.BarangResponse;
import com.example.mahmudinm.androidcodeigniterinventory.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PenjualanActivity extends AppCompatActivity implements PenjualanView{

    PenjualanPresenter presenter;
    ProgressDialog progressDialog;
    SessionManager session;
    Context mContext;

    String jumlah_harga;

    @BindView(R.id.nama)
    Spinner nama;
    @BindView(R.id.content_simpan)
    LinearLayout content_simpan;
    @BindView(R.id.content_update)
    LinearLayout content_update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjualan);
        ButterKnife.bind(this);

        session = new SessionManager(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading ...");
        presenter = new PenjualanPresenter(this);

        presenter.getListBarang(session.getKeyToken());
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

    @Override
    public void setListBarang(BarangResponse barangResponse) {
        List<Barang> barangs = barangResponse.getData();
        List<String> listSpinner  = new ArrayList<String>();
        for (int i = 0; i < barangs.size(); i++) {
            listSpinner.add(barangs.get(i).getNama());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, listSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nama.setAdapter(adapter);

    }
}
