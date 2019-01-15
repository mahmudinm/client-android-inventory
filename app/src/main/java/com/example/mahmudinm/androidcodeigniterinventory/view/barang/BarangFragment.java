package com.example.mahmudinm.androidcodeigniterinventory.view.barang;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mahmudinm.androidcodeigniterinventory.R;
import com.example.mahmudinm.androidcodeigniterinventory.utils.SessionManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class BarangFragment extends Fragment {

    SessionManager session;


    public BarangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View x = inflater.inflate(R.layout.fragment_barang, container, false);

        return x;
    }

}
