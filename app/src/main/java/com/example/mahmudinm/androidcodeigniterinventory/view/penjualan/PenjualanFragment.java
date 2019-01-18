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
import com.example.mahmudinm.androidcodeigniterinventory.model.Penjualan;
import com.example.mahmudinm.androidcodeigniterinventory.network.response.PenjualanResponse;
import com.example.mahmudinm.androidcodeigniterinventory.utils.RecyclerItemClickListener;
import com.example.mahmudinm.androidcodeigniterinventory.utils.SessionManager;
import com.example.mahmudinm.androidcodeigniterinventory.utils.SimpleDividerItemDecoration;
import com.example.mahmudinm.androidcodeigniterinventory.view.barang.BarangAdapter;
import com.example.mahmudinm.androidcodeigniterinventory.view.barang.BarangPresenter;
import com.example.mahmudinm.androidcodeigniterinventory.view.penjualan.editor.PenjualanActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class PenjualanFragment extends Fragment implements PenjualanView {


    SessionManager session;
    PenjualanPresenter presenter;
    PenjualanAdapter adapter;

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
        getActivity().setTitle("Penjualan / Transaksi ");

        session = new SessionManager(getActivity());
        presenter = new PenjualanPresenter(this);
        presenter.getPenjualan(session.getKeyToken());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return x;

    }

    @OnClick(R.id.fab) void editor() {
        Intent intent = new Intent(getActivity(), PenjualanActivity.class);
        startActivityForResult(intent, REQUEST_ADD);
    }

    @Override
    public void showProgress() {
        swipe.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipe.setRefreshing(false);
    }

    @Override
    public void statusSuccess(PenjualanResponse penjualanResponse) {
        adapter = new PenjualanAdapter(penjualanResponse.getData());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Penjualan penjualan = adapter.getPenjualan(position);

                        Intent intent = new Intent(getActivity(), PenjualanActivity.class);

                        intent.putExtra("id", penjualan.getId());
                        intent.putExtra("barang_id", penjualan.getBarang_id());
                        intent.putExtra("jumlah_barang", penjualan.getJumlah_barang());
                        intent.putExtra("jumlah_harga", penjualan.getJumlah_harga());

                        startActivityForResult(intent, REQUEST_UPDATE);
                    }
                }));
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
