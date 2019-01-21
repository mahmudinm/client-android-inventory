package com.example.mahmudinm.androidcodeigniterinventory.view.supplier;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mahmudinm.androidcodeigniterinventory.R;
import com.example.mahmudinm.androidcodeigniterinventory.model.Supplier;
import com.example.mahmudinm.androidcodeigniterinventory.network.response.SupplierResponse;
import com.example.mahmudinm.androidcodeigniterinventory.utils.RecyclerItemClickListener;
import com.example.mahmudinm.androidcodeigniterinventory.utils.SessionManager;
import com.example.mahmudinm.androidcodeigniterinventory.utils.SimpleDividerItemDecoration;
import com.example.mahmudinm.androidcodeigniterinventory.view.supplier.editor.SupplierActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class SupplierFragment extends Fragment implements SupplierView{

    SupplierPresenter presenter;
    SessionManager session;
    SupplierAdapter adapter;

    private static final int REQUEST_ADD = 1;
    private static final int REQUEST_UPDATE = 1;

    @BindView(R.id.recyclerSupplier)
    RecyclerView recyclerView;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipe;


    public SupplierFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View x = inflater.inflate(R.layout.fragment_supplier, container, false);
        session = new SessionManager(getActivity());
        ButterKnife.bind(this, x);
        getActivity().setTitle("Data Supplier");

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        presenter = new SupplierPresenter(this);
        presenter.getSuppliers(session.getKeyToken());

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getSuppliers(session.getKeyToken());
            }
        });

        return x;
    }


    @OnClick(R.id.supplierFab) void editor() {
        Intent intent = new Intent(getActivity(), SupplierActivity.class);
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
    public void statusSuccess(SupplierResponse supplierResponse) {
        adapter = new SupplierAdapter(supplierResponse.getData());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Supplier supplier = adapter.getSupplier(position);

                        Intent intent = new Intent(getActivity(), SupplierActivity.class);

                        intent.putExtra("id", supplier.getId());
                        intent.putExtra("nama", supplier.getNama());
                        intent.putExtra("no_hp", supplier.getNo_hp());
                        intent.putExtra("alamat", supplier.getAlamat());

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
            presenter.getSuppliers(session.getKeyToken());
        } else if (requestCode == REQUEST_UPDATE && resultCode == RESULT_OK) {
            presenter.getSuppliers(session.getKeyToken());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
