package com.example.yoshievinsmoke.ayomangan.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yoshievinsmoke.ayomangan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoshievinsmoke on 3/25/18.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    List<EndangeredItem> mItems;

    public GridAdapter() {
        super();
        mItems = new ArrayList<EndangeredItem>();
        EndangeredItem nama = new EndangeredItem();
        nama.setName("Ayam Geprek");
        nama.setThumbnail(R.drawable.ayam);
        mItems.add(nama);

        nama = new EndangeredItem();
        nama.setName("Bakso");
        nama.setThumbnail(R.drawable.baso);
        mItems.add(nama);

        nama = new EndangeredItem();
        nama.setName("Bakso Cuanki");
        nama.setThumbnail(R.drawable.ayam);
        mItems.add(nama);

        nama = new EndangeredItem();
        nama.setName("Nasi Goreng");
        nama.setThumbnail(R.drawable.nasgor);
        mItems.add(nama);

        nama = new EndangeredItem();
        nama.setName("Pizza");
        nama.setThumbnail(R.drawable.ayam);
        mItems.add(nama);

        nama = new EndangeredItem();
        nama.setName("Seblak");
        nama.setThumbnail(R.drawable.seblak);
        mItems.add(nama);

        nama = new EndangeredItem();
        nama.setName("Malor");
        nama.setThumbnail(R.drawable.ayam);
        mItems.add(nama);

        nama = new EndangeredItem();
        nama.setName("Mapin");
        nama.setThumbnail(R.drawable.mapin);
        mItems.add(nama);

        nama = new EndangeredItem();
        nama.setName("Sate");
        nama.setThumbnail(R.drawable.sate);
        mItems.add(nama);


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.grid_view, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        EndangeredItem nature = mItems.get(i);
        viewHolder.tvspecies.setText(nature.getName());
        viewHolder.imgThumbnail.setImageResource(nature.getThumbnail());
    }

    @Override
    public int getItemCount() {

        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder  {


        public ImageView imgThumbnail;
        public TextView tvspecies;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
            tvspecies = (TextView)itemView.findViewById(R.id.status);

        }
    }
}
