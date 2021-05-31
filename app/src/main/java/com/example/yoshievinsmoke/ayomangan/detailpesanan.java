package com.example.yoshievinsmoke.ayomangan;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.yoshievinsmoke.ayomangan.data.DataPesanan;
import com.example.yoshievinsmoke.ayomangan.data.DataPesananSeller;
import com.example.yoshievinsmoke.ayomangan.util.MySingleton;
import com.example.yoshievinsmoke.ayomangan.util.Server;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class detailpesanan extends AppCompatActivity {

    @BindView(R.id.nama_makanan)
    TextView nama_makanan;
    @BindView(R.id.foto_makanan)
    ImageView foto_makanan;
    @BindView(R.id.btntrack)
    Button btntrack;
    @BindView(R.id.jumlah)
    TextView jumlah;
    @BindView(R.id.total_harga)
    TextView total_harga;
    @BindView(R.id.alamat)
    TextView alamat;
    @BindView(R.id.konfir)
    Button konfir;
    DataPesanan data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailpesanan);
        ButterKnife.bind(this);
        data = (DataPesanan) getIntent().getSerializableExtra("data");
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

        if (!data.getStatusPengiriman().equals("1")){
            konfir.setVisibility(View.GONE);
        }

        konfir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final StringRequest request = new StringRequest(Request.Method.POST, Server.konfirmasi_penerimaan, new Response.Listener<String>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onResponse(String response) {
                        Log.e("response", response);
                        try {
                            JSONObject data = new JSONObject(response);
                            if (data.getString("success").equals("1")) {
                                finish();
                                Toast.makeText(detailpesanan.this, "Berhasil Dikonfirmasi", Toast.LENGTH_SHORT).show();
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
        });

    }
}
