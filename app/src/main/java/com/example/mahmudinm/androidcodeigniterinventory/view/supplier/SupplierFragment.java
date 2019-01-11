package com.example.mahmudinm.androidcodeigniterinventory.view.supplier;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mahmudinm.androidcodeigniterinventory.R;
import com.example.mahmudinm.androidcodeigniterinventory.model.Supplier;
import com.example.mahmudinm.androidcodeigniterinventory.network.response.SupplierResponse;
import com.example.mahmudinm.androidcodeigniterinventory.utils.SessionManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SupplierFragment extends Fragment implements SupplierView{

    ProgressDialog progressDialog;
    SupplierPresenter presenter;
//    RecyclerView.LayoutManager manager;
    SessionManager session;
    SupplierAdapter adapter;
    List<Supplier> suppliers;

    @BindView(R.id.recyclerSupplier)
    RecyclerView recyclerView;


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

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading ...");

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        presenter = new SupplierPresenter(this);
        presenter.getSuppliers(session.getKeyToken());

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
    public void statusSuccess(SupplierResponse supplierResponse) {
        adapter = new SupplierAdapter(supplierResponse.getData());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void statusError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
