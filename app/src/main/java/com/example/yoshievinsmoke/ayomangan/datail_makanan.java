package com.example.yoshievinsmoke.ayomangan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.yoshievinsmoke.ayomangan.data.DataMakananCustomer;
import com.example.yoshievinsmoke.ayomangan.data.DataSeller;
import com.example.yoshievinsmoke.ayomangan.util.MySingleton;
import com.example.yoshievinsmoke.ayomangan.util.Server;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class datail_makanan extends AppCompatActivity {

    DataMakananCustomer data;
    @BindView(R.id.judul)
    TextView judul;
    @BindView(R.id.foto_makanan)
    ImageView foto;
    @BindView(R.id.harga_makanan)
    TextView harga;
    @BindView(R.id.nama_toko)
    TextView nama_toko;
    @BindView(R.id.deskripsi)
    TextView deskripsi;
    private String id_seller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datail_makanan);
        ButterKnife.bind(this);
        data = (DataMakananCustomer) getIntent().getSerializableExtra("data");


        judul.setText(data.getNamaMakanan());
        harga.setText(FormatData.doubleToRupiah(Double.valueOf(data.getHarga())));
        deskripsi.setText(data.getDeskripsi());
        Picasso.with(getApplicationContext()).load(Server.URL_Foto + data.getFoto())
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .into(foto, new Callback() {
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


         StringRequest request = new StringRequest(Request.Method.POST, Server.get_seller_byid, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                Log.e("response",response);
                try {
                    JSONArray data = new JSONArray(response);
                    if (data.length()==1){
                        nama_toko.setText(data.getJSONObject(0).getString("nama_toko"));
                        id_seller = data.getJSONObject(0).getString("id_seller");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onErrorResponse(VolleyError error) {
//                loading_keramik.setVisibility(View.GONE);
                error.printStackTrace();
            }
        }){
             @Override
             protected Map<String, String> getParams() throws AuthFailureError {
                 Map<String, String> map = new HashMap<>();
                 map.put("id_seller",data.getIdSeller());
                 return map;
             }
         };

        request.setRetryPolicy(new DefaultRetryPolicy(
                Server.TIMEOUT_ACCESS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);

    }


    @OnClick(R.id.beli)
    public void beli(){
        startActivity(new Intent(getApplicationContext(),detail_pesanan.class)
        .putExtra("data",data)
        .putExtra("id_seller",id_seller));
    }

}
