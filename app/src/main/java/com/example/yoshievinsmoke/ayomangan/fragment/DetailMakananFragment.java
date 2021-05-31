package com.example.yoshievinsmoke.ayomangan.fragment;

import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.yoshievinsmoke.ayomangan.R;
import com.example.yoshievinsmoke.ayomangan.app.AppController;
import com.example.yoshievinsmoke.ayomangan.util.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yoshievinsmoke on 5/17/18.
 */

public class DetailMakananFragment extends Fragment {
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    int success;

    private static final String TAG = ListMakananFragment.class.getSimpleName();

    private static String url_delete     = Server.URL + "master/order.php";

    public static final String TAG_ID_MAKANAN       = "id_makanan";
    public static final String TAG_ID_TOKO       = "id_toko";
    public static final String TAG_NAMA_MAKANAN     = "nama_makanan";
    public static final String TAG_HARGA   = "harga";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";


    String idMakanan, namaMakanan, hargaMakanan, jumlahMakanan;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_makanan, container, false);

        Button order = (Button) view.findViewById(R.id.btnOrder);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        TextView idtxt = (TextView) view.findViewById(R.id.txtdet_id_makanan);
        TextView namatxt = (TextView) view.findViewById(R.id.txtdet_NamaMakanan);
        TextView hargatxt = (TextView) view.findViewById(R.id.txtdet_Harga);
        TextView jumlahtxt = (TextView) view.findViewById(R.id.txtdet_Jumlah);

        idtxt.setText(TAG_ID_MAKANAN);
        namatxt.setText(TAG_NAMA_MAKANAN);
        hargatxt.setText(hargaMakanan);
        jumlahtxt.setText(jumlahMakanan);

        return view;
    }



    public void getDetailId(String id){

        idMakanan = id;
    }

    public void getDetailNama(String nama){

        namaMakanan = nama;
    }

    public void getDetailHargaJual(String hargaM){

        hargaMakanan = hargaM;
    }
    public void getDetailHargaBeli(String jumlahM){

        jumlahMakanan = jumlahM;
    }

}