package com.example.yoshievinsmoke.ayomangan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yoshievinsmoke.ayomangan.R;
import com.example.yoshievinsmoke.ayomangan.data.DataMakanan;
import com.example.yoshievinsmoke.ayomangan.data.DataMakananCustomer;
import com.example.yoshievinsmoke.ayomangan.data.DataSeller;
import com.example.yoshievinsmoke.ayomangan.datail_makanan;
import com.example.yoshievinsmoke.ayomangan.detail_pesanan;
import com.example.yoshievinsmoke.ayomangan.fragment.DetailMakananFragment;
import com.example.yoshievinsmoke.ayomangan.util.Server;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tawangga on 02/06/18.
 */

public class AdapterMakananCustomer extends RecyclerView.Adapter<AdapterMakananCustomer.ViewHolder> {
    private Context context;
    private List<DataMakananCustomer> datas;
    private List<DataMakananCustomer> orig;

    public AdapterMakananCustomer(Context context) {

        this.context = context;
        datas = new ArrayList<>();
    }

    public void setDatas(List<DataMakananCustomer> datas) {
        this.datas = datas;
    }

    public List<DataMakananCustomer> getDatas() {
        return datas;
    }

    @Override
    public AdapterMakananCustomer.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seller, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterMakananCustomer.ViewHolder holder, int position) {
        DataMakananCustomer data = datas.get(position);
        Picasso.with(context).load(Server.URL_Foto + data.getFoto())
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .into(holder.foto_seller, new Callback() {
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

        holder.nama_seller.setText(data.getNamaMakanan());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                final FilterResults oReturn = new FilterResults();
                final List<DataMakananCustomer> results = new ArrayList<>();
                if (orig == null) {
                    orig = datas;
                }
                if (charSequence != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final DataMakananCustomer g : orig) {
                            if (g.getNamaMakanan().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                                results.add(g);
                            }
                        }
                        oReturn.values = results;
                    }
                }
                return oReturn;

            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                datas = (ArrayList<DataMakananCustomer>) filterResults.values;
                notifyDataSetChanged();

            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.nama_seller)
        TextView nama_seller;
        @BindView(R.id.foto_seller)
        ImageView foto_seller;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, datail_makanan.class)
                    .putExtra("data",datas.get(getAdapterPosition())));
                }
            });
        }
    }
}
