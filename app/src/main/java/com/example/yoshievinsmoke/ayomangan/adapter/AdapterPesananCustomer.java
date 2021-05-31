package com.example.yoshievinsmoke.ayomangan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yoshievinsmoke.ayomangan.FormatData;
import com.example.yoshievinsmoke.ayomangan.R;
import com.example.yoshievinsmoke.ayomangan.data.DataPesanan;
import com.example.yoshievinsmoke.ayomangan.detailpesanan;
import com.example.yoshievinsmoke.ayomangan.util.Server;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tawangga on 03/06/18.
 */

public class AdapterPesananCustomer extends RecyclerView.Adapter<AdapterPesananCustomer.ViewHolder> {
    private Context context;
    private List<DataPesanan> datas;

    public AdapterPesananCustomer(Context context) {

        this.context = context;
        datas=new ArrayList<>();
    }

    public void setDatas(List<DataPesanan> datas) {
        this.datas = datas;
    }

    public List<DataPesanan> getDatas() {
        return datas;
    }

    @Override
    public AdapterPesananCustomer.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pesanan, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterPesananCustomer.ViewHolder holder, int position) {
        DataPesanan data = datas.get(position);
        //holder.nama_makanan.setText(data.getDetail().getMakanan().getNamaMakanan());
        holder.kuantitas.setText("Jumlah Beli : "+data.getDetail().getKuantitas());
        holder.total_harga.setText("Total Harga : "+FormatData.doubleToRupiah(Double.parseDouble(data.getDetail().getTotalHarga())));
        Picasso.with(context).load(Server.URL_Foto + data.getDetail().getMakanan())
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .into(holder.foto_makanan, new Callback() {
                    @Override
                    public void onSuccess() {
//                        holder.loading_image.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
//                        holder.foto_seller.setImageDrawable(context.getResources().getDrawable(R.drawable.noimage));
//                        holder.loading_image.setVisibility(View.GONE);
                    }
                });

        if (data.getStatusPenjualan().equals("1")){
            holder.status.setText("Status : Dikonfirmasi/Belum Dikirim");
            if (data.getStatusPengiriman().equals("1")){
                holder.status.setText("Status : Sedang Dikirim");
                if (data.getKonfirmasiCustomer().equals("1")){
                    holder.status.setText("Status : Sudah Diterima");
                }
            }

        }else {
            holder.status.setText("Status : Belum Dikonfirmasi");
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.nama_makanan)
        TextView nama_makanan;
        @BindView(R.id.kuantitas)
        TextView kuantitas;
        @BindView(R.id.total_harga)
        TextView total_harga;
        @BindView(R.id.status)
        TextView status;
        @BindView(R.id.foto_makanan)
        ImageView foto_makanan;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, detailpesanan.class)
                    .putExtra("data",datas.get(getAdapterPosition())));
                }
            });
        }
    }
}
