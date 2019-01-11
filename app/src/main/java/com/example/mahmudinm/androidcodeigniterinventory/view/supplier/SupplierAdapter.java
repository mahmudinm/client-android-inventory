package com.example.mahmudinm.androidcodeigniterinventory.view.supplier;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mahmudinm.androidcodeigniterinventory.R;
import com.example.mahmudinm.androidcodeigniterinventory.model.Supplier;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SupplierAdapter extends RecyclerView.Adapter<SupplierAdapter.ViewHolder> {

    private List<Supplier> suppliers;

    public SupplierAdapter(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_supplier,
                viewGroup, false);
//        ButterKnife.bind(this, view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Supplier supplier = suppliers.get(i);
        viewHolder.nama.setText(supplier.getNama());
        viewHolder.nama.setText(supplier.getNama());
        viewHolder.nama.setText(supplier.getNama());
    }

    @Override
    public int getItemCount() {
        return suppliers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.nama) EditText nama;
        TextView nama, no_hp, alamat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            no_hp = itemView.findViewById(R.id.no_hp);
            alamat = itemView.findViewById(R.id.alamat);
        }
    }

}
