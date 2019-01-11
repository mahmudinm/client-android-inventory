package com.example.mahmudinm.androidcodeigniterinventory.view.supplier;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mahmudinm.androidcodeigniterinventory.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SupplierFragment extends Fragment {


    public SupplierFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_supplier, container, false);
    }

}
