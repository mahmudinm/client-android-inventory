package com.example.mahmudinm.androidcodeigniterinventory.view.barang;


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
import com.example.mahmudinm.androidcodeigniterinventory.network.response.BarangResponse;
import com.example.mahmudinm.androidcodeigniterinventory.utils.SessionManager;
import com.example.mahmudinm.androidcodeigniterinventory.view.barang.editor.BarangActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class BarangFragment extends Fragment implements BarangView {

    SessionManager session;
    BarangPresenter presenter;
    BarangAdapter adapter;
    ProgressDialog progressDialog;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipe;

    public BarangFragment() {
        // Required empty public constructor
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
        presenter = new BarangPresenter(this);
        presenter.getBarang(session.getKeyToken());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return x;
    }

    @OnClick(R.id.fab) void editor() {
        Intent intent = new Intent(getActivity(), BarangActivity.class);
        startActivity(intent);
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
    public void statusSuccess(BarangResponse barangResponse) {
        adapter = new BarangAdapter(barangResponse.getData(), getActivity());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void statusError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
