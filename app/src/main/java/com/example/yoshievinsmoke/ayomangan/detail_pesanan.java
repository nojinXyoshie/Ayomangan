package com.example.yoshievinsmoke.ayomangan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
import com.example.yoshievinsmoke.ayomangan.data.DataMakananCustomer;
import com.example.yoshievinsmoke.ayomangan.util.MySingleton;
import com.example.yoshievinsmoke.ayomangan.util.Server;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.yoshievinsmoke.ayomangan.fragment.FragmentKurir.TAG_NAMA;

public class detail_pesanan extends AppCompatActivity {

    @BindView(R.id.judul)
    TextView nama_makanan;
    @BindView(R.id.foto_makanan)
    ImageView foto_makanan;
    @BindView(R.id.kuantitas)
    Spinner kuantitasSpin;
    @BindView(R.id.ongkir)
    TextView ongkir;
    @BindView(R.id.total_harga)
    TextView total_harga;
    @BindView(R.id.harga)
    TextView harga;
    @BindView(R.id.alamat)
    EditText alamat;
    @BindView(R.id.deskripsi)
    EditText deskripsi;
    private DataMakananCustomer data;
    private String id_seller;

    String[]kuantitas = new String[]{
            "1","2","3","4","5","6","7","8","9","10"
    };
    double total;


    String id_customer;
    SharedPreferences sharedpreferences;

    public static final String TAG_ID = "id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan2);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        id_customer = sharedpreferences.getString(TAG_ID,null);
        data = (DataMakananCustomer) getIntent().getSerializableExtra("data");
        id_seller = getIntent().getStringExtra("id_seller");
        nama_makanan.setText(data.getNamaMakanan());
        harga.setText(FormatData.doubleToRupiah(Double.valueOf(data.getHarga())));
        Picasso.with(getApplicationContext()).load(Server.URL_Foto + data.getFoto())
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

        ArrayAdapter<String> adapterSpin = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,kuantitas);
        kuantitasSpin.setAdapter(adapterSpin);
        total = Double.valueOf(data.getHarga()) * Double.valueOf(kuantitas[kuantitasSpin.getSelectedItemPosition()])+ 5000;
        kuantitasSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                total = Double.valueOf(data.getHarga()) * Double.valueOf(kuantitas[i]) + 5000;
                total_harga.setText("Total Harga : "+FormatData.doubleToRupiah(total));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    @OnClick(R.id.beli)
    public void beli(){

        StringRequest request = new StringRequest(Request.Method.POST, Server.input_pesanan, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getString("success").equals("1")){
                        Toast.makeText(detail_pesanan.this, "Berhasil dipesan", Toast.LENGTH_SHORT).show();
                        finish();
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
                map.put("id_customer",id_customer);
                map.put("id_makanan",data.getIdMakanan());
                map.put("kuantitas",kuantitas[kuantitasSpin.getSelectedItemPosition()]);
                map.put("alamat",alamat.getText().toString().trim());
                map.put("keterangan",deskripsi.getText().toString().trim());
                map.put("ongkir","5000");
                map.put("total_harga",String.valueOf(total));
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
