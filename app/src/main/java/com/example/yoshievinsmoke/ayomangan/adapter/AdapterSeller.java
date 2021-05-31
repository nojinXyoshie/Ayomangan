package com.example.yoshievinsmoke.ayomangan.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import com.example.yoshievinsmoke.ayomangan.CustomerActivity;
import com.example.yoshievinsmoke.ayomangan.R;
import com.example.yoshievinsmoke.ayomangan.data.DataSeller;
import com.example.yoshievinsmoke.ayomangan.fragment.HomeFragment;
import com.example.yoshievinsmoke.ayomangan.fragment.MakananFragment;
import com.example.yoshievinsmoke.ayomangan.fragment.MakananFragmentCustomer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tawangga on 02/06/18.
 */

public class AdapterSeller extends RecyclerView.Adapter<AdapterSeller.ViewHolder> {
    private List<DataSeller> datas;
    private Context context;
    private List<DataSeller> orig;

    public AdapterSeller(Context context) {

        this.context = context;
        datas = new ArrayList<>();
    }

    @Override
    public AdapterSeller.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seller, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterSeller.ViewHolder holder, int position) {
        holder.nama_seller.setText(datas.get(position).getNamaToko());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setDatas(List<DataSeller> datas) {
        this.datas = datas;
    }

    public List<DataSeller> getDatas() {
        return datas;
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                final FilterResults oReturn = new FilterResults();
                final List<DataSeller> results = new ArrayList<>();
                if (orig == null) {
                    orig = datas;
                }
                if (charSequence != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final DataSeller g : orig) {
                            if (g.getNamaToko().toLowerCase().contains(charSequence.toString().toLowerCase())) {
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
                datas = (ArrayList<DataSeller>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.nama_seller)
        TextView nama_seller;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment = new MakananFragmentCustomer(datas.get(getAdapterPosition()));
                    FragmentTransaction ft = ((CustomerActivity) context).getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content_main, fragment);
                    ft.commit();

                }
            });
        }
    }
}
