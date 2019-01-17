package com.example.mahmudinm.androidcodeigniterinventory.view.penjualan;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mahmudinm.androidcodeigniterinventory.R;
import com.example.mahmudinm.androidcodeigniterinventory.network.response.PenjualanResponse;
import com.example.mahmudinm.androidcodeigniterinventory.utils.RecyclerItemClickListener;
import com.example.mahmudinm.androidcodeigniterinventory.utils.SessionManager;
import com.example.mahmudinm.androidcodeigniterinventory.view.barang.BarangAdapter;
import com.example.mahmudinm.androidcodeigniterinventory.view.barang.BarangPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class PenjualanFragment extends Fragment implements PenjualanView {


    SessionManager session;
    PenjualanPresenter presenter;
    PenjualanAdapter adapter;
    ProgressDialog progressDialog;

    private static final int REQUEST_ADD = 1;
    private static final int REQUEST_UPDATE = 2;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipe;

    public PenjualanFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View x = inflater.inflate(R.layout.fragment_barang, container, false);
        ButterKnife.bind(this, x );

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading ...");

        session = new SessionManager(getActivity());
        presenter = new PenjualanPresenter(this);
        presenter.getPenjualan(session.getKeyToken());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return x;

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
    public void statusSuccess(PenjualanResponse penjualanResponse) {
        adapter = new PenjualanAdapter(penjualanResponse.getData());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void statusError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD && resultCode == RESULT_OK) {
            presenter.getPenjualan(session.getKeyToken());
        } else if (requestCode == REQUEST_UPDATE && resultCode == RESULT_OK) {
            presenter.getPenjualan(session.getKeyToken());
        }
    }
}
