package com.example.yoshievinsmoke.ayomangan.adapter;

/**
 * Created by yoshievinsmoke on 5/4/18.
 */

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.yoshievinsmoke.ayomangan.R;
import com.example.yoshievinsmoke.ayomangan.data.DataKurir;
import com.example.yoshievinsmoke.ayomangan.data.DataMakanan;

import java.util.List;

/**
 * Created by Kuncoro on 26/03/2016.
 */
public class MakananAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataMakanan> items;

    public MakananAdapter(Activity activity, List<DataMakanan> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.makanan_list_row, null);

        TextView id_makanan = (TextView) convertView.findViewById(R.id.id_makanan);
        TextView id_seller = (TextView) convertView.findViewById(R.id.id_seller);
        TextView nama = (TextView) convertView.findViewById(R.id.nama_makanan);
        TextView harga = (TextView) convertView.findViewById(R.id.harga_makanan);

        DataMakanan data = items.get(position);

        id_makanan.setText(data.getIdMakanan());
        id_seller.setText(data.getIdSeller());
        nama.setText(data.getNamaMakanan());
        harga.setText(data.getHarga());

        return convertView;
    }

}
