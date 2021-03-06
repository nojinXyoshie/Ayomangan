package com.example.yoshievinsmoke.ayomangan.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yoshievinsmoke.ayomangan.R;
import com.example.yoshievinsmoke.ayomangan.data.DataMakanan;
import com.example.yoshievinsmoke.ayomangan.data.DataToko;

import java.util.List;

/**
 * Created by yoshievinsmoke on 5/16/18.
 */

public class TokoAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataToko> items;

    public TokoAdapter(Activity activity, List<DataToko> items) {
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
            convertView = inflater.inflate(R.layout.toko_list_row, null);

        TextView id = (TextView) convertView.findViewById(R.id.id_toko);
        TextView nama = (TextView) convertView.findViewById(R.id.nama_toko);

        DataToko data = items.get(position);

        id.setText(data.getIdToko());
        nama.setText(data.getNamaToko());

        return convertView;
    }

}
