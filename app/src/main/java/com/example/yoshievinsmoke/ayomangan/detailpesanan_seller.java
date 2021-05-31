package com.example.yoshievinsmoke.ayomangan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.yoshievinsmoke.ayomangan.data.DataKurir;
import com.example.yoshievinsmoke.ayomangan.data.DataKurirDong;
import com.example.yoshievinsmoke.ayomangan.data.DataMakananCustomer;
import com.example.yoshievinsmoke.ayomangan.data.DataPesananSeller;
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
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class detailpesanan_seller extends AppCompatActivity {

    private static final String TAG_LEVEL = "level";
    DataPesananSeller data;
    @BindView(R.id.nama_pembeli)
    TextView nama_pembeli;
    @BindView(R.id.foto_makanan)
    ImageView foto_makanan;
    @BindView(R.id.btntrack)
    Button btntrack;
    @BindView(R.id.nama_makanan)
    TextView nama_makanan;
    @BindView(R.id.jumlah)
    TextView jumlah;
    @BindView(R.id.total_harga)
    TextView total_harga;
    @BindView(R.id.kurir)
    Spinner kurir_spin;
    @BindView(R.id.alamat)
    TextView alamat;
    @BindView(R.id.konfir)
    Button konfir;
    private List<DataKurirDong> list_kurir;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailpesanan_seller);
        ButterKnife.bind(this);
        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        data = (DataPesananSeller) getIntent().getSerializableExtra("data");

        nama_pembeli.setText(data.getPembeli().getNama());
        nama_makanan.setText(data.getDetail().getMakanan().getNamaMakanan());
        jumlah.setText("Jumlah : " + data.getDetail().getKuantitas() + " Porsi");
        total_harga.setText("Total Harga : " + FormatData.doubleToRupiah(Double.parseDouble(data.getDetail().getTotalHarga())));
        alamat.setText("Alamat : " + data.getDetail().getAlamat());
        Picasso.with(getApplicationContext()).load(Server.URL_Foto + data.getDetail().getMakanan().getFoto())
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .into(foto_makanan, new Callback() {
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
        if (sharedpreferences.getString(TAG_LEVEL, null).equals("kurir")){
            konfir.setText("Kirim Pesanan");
        }

        if (sharedpreferences.getString(TAG_LEVEL, null).equals("seller") && data.getStatusPenjualan().equals("1")) {
            konfir.setVisibility(View.GONE);
        }else if (sharedpreferences.getString(TAG_LEVEL, null).equals("kurir") && data.getStatusPengiriman().equals("1")){
            konfir.setVisibility(View.GONE);
        }
        getKurir();
    }

    public void getKurir() {
        final StringRequest request = new StringRequest(Request.Method.POST, Server.get_kurir, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                Log.e("response", response);
                try {
                    JSONArray data = new JSONArray(response);
                    Gson gson = new Gson();
                    TypeToken<List<DataKurirDong>> token = new TypeToken<List<DataKurirDong>>() {
                    };
                    list_kurir = gson.fromJson(data.toString(), token.getType());
                    List<String> datakurir = new ArrayList<>();
                    for (int i = 0; i < list_kurir.size(); i++) {
                        datakurir.add(list_kurir.get(i).getNama());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(detailpesanan_seller.this, android.R.layout.simple_list_item_1, datakurir);
                    kurir_spin.setAdapter(adapter);

                    if (!detailpesanan_seller.this.data.getIdKurir().equals("0")) {

                        for (int i = 0; i < list_kurir.size(); i++) {
                            if (detailpesanan_seller.this.data.getIdKurir().equals(list_kurir.get(i).getIdKurir())) {
                                kurir_spin.setSelection(i);
                            }
                        }
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
        });

        request.setRetryPolicy(new DefaultRetryPolicy(
                Server.TIMEOUT_ACCESS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);

    }

    @OnClick(R.id.konfir)
    public void konfir() {
        if (sharedpreferences.getString(TAG_LEVEL, null).equals("seller")){
            konfirmasiSeller();
        }else {
            konfirmasiKurir();
        }
    }

    private void konfirmasiKurir() {
        final StringRequest request = new StringRequest(Request.Method.POST, Server.konfirmasi_pengiriman, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                Log.e("response", response);
                try {
                    JSONObject data = new JSONObject(response);
                    if (data.getString("success").equals("1")) {
                        finish();
                        Toast.makeText(detailpesanan_seller.this, "Berhasil Dikonfirmasi", Toast.LENGTH_SHORT).show();
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
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id_order", data.getIdOrder());
                return map;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(
                Server.TIMEOUT_ACCESS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    private void konfirmasiSeller() {
        final StringRequest request = new StringRequest(Request.Method.POST, Server.konfirmasi_pesanan, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                Log.e("response", response);
                try {
                    JSONObject data = new JSONObject(response);
                    if (data.getString("success").equals("1")) {
                        finish();
                        Toast.makeText(detailpesanan_seller.this, "Berhasil Dikonfirmasi", Toast.LENGTH_SHORT).show();
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
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id_order", data.getIdOrder());
                map.put("id_kurir", list_kurir.get(kurir_spin.getSelectedItemPosition()).getIdKurir());
                return map;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(
                Server.TIMEOUT_ACCESS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }
}
