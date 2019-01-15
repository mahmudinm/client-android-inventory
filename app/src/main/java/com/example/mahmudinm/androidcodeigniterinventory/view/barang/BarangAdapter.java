package com.example.mahmudinm.androidcodeigniterinventory.view.barang;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.mahmudinm.androidcodeigniterinventory.R;
import com.example.mahmudinm.androidcodeigniterinventory.model.Barang;

import java.util.List;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.ViewHolder> {

    List<Barang> barangs;
    Context mContext;

    public BarangAdapter(List<Barang> barangs, Context context) {
        mContext = context;
        this.barangs = barangs;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_barang,
                viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Barang barang = barangs.get(i);
        viewHolder.nama.setText(barang.getNama());
        viewHolder.harga.setText(barang.getHarga());
        viewHolder.stock.setText(barang.getStock());
        viewHolder.ukuran.setText(barang.getUkuran());

        String url = "https://9b1c432f.ngrok.io/android_codeigniter_inventory/upload/";

        Glide.with(mContext).load(url + barang.getGambar())
                .thumbnail(0.5f)
                .transition(new DrawableTransitionOptions().crossFade())
                .into(viewHolder.gambar);

    }

    @Override
    public int getItemCount() {
        return barangs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nama, harga, stock, ukuran;
        ImageView gambar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            harga = itemView.findViewById(R.id.harga);
            stock = itemView.findViewById(R.id.stock);
            ukuran = itemView.findViewById(R.id.ukuran);
            gambar = itemView.findViewById(R.id.gambar);
        }
    }
}
