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


public class SupplierAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final int TYPE_LIST = 0 ;
    public final int TYPE_LOAD = 1 ;

    private List<Supplier> suppliers;
    OnLoadMoreListener loadMoreListener;
    boolean isLoading = false;
    boolean isMoreDataAvailable = true;

    public SupplierAdapter(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        if (i == TYPE_LIST) {
            return new ListHolder(inflater.inflate(R.layout.list_supplier, viewGroup, false));
        } else {
            return new LoadHolder(inflater.inflate(R.layout.list_loading, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Supplier supplier = suppliers.get(i);

//        if(i>=getItemCount()-1 && isMoreDataAvailable && !isLoading && loadMoreListener!=null){
//            isLoading = true;
//            loadMoreListener.onLoadMore();
//        }
//
//        if(getItemViewType(i)==TYPE_LIST){
//            ((ListHolder)viewHolder).bindData(suppliers.get(i));
//        }
        if (i >= getItemCount()-1 && isMoreDataAvailable && !isLoading && loadMoreListener !=
                null) {
            isLoading = true;
            loadMoreListener.onLoadMore();
        }

        if (getItemViewType(i) == TYPE_LIST){
//            ((ListHolder)viewHolder).bindData(suppliers.get(i));
            ListHolder listHolder = (ListHolder) viewHolder;
            listHolder.nama.setText(supplier.getNama());
            listHolder.no_hp.setText(supplier.getNo_hp());
            listHolder.alamat.setText(supplier.getAlamat());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (position == suppliers.size()) ? TYPE_LOAD : TYPE_LIST;
    }

    @Override
    public int getItemCount() {
        return suppliers.size();
    }

    public Supplier getSupplier(int position) {
        return suppliers.get(position);
    }

    static class ListHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.nama) EditText nama;
        TextView nama, no_hp, alamat;

        public ListHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            no_hp = itemView.findViewById(R.id.no_hp);
            alamat = itemView.findViewById(R.id.alamat);
        }

//        void bindData(Supplier supplier){
//            nama.setText(supplier.getNama());
//            no_hp.setText(supplier.getNo_hp());
//            alamat.setText(supplier.getAlamat());
////            tvNama.setText(supplier.getId());
////            tvAlamat.setText("Rating "+supplier.getAlamat());
//        }
    }

    static class LoadHolder extends RecyclerView.ViewHolder {
        public LoadHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void setMoreDataAvailable(boolean moreDataAvailable) {
        isMoreDataAvailable = moreDataAvailable;
    }

    public void notifyDataChanged() {
        notifyDataSetChanged();
        isLoading =  false;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

}
