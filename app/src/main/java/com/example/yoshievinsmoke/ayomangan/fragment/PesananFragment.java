package com.example.yoshievinsmoke.ayomangan.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.yoshievinsmoke.ayomangan.Login;
import com.example.yoshievinsmoke.ayomangan.R;
import com.example.yoshievinsmoke.ayomangan.adapter.AdapterMakananCustomer;
import com.example.yoshievinsmoke.ayomangan.adapter.AdapterPesananCustomer;
import com.example.yoshievinsmoke.ayomangan.data.DataMakananCustomer;
import com.example.yoshievinsmoke.ayomangan.data.DataPesanan;
import com.example.yoshievinsmoke.ayomangan.util.MySingleton;
import com.example.yoshievinsmoke.ayomangan.util.Server;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PesananFragment extends Fragment {

    @BindView(R.id.rv_pesanan)
    RecyclerView rv_pesanan;
    private AdapterPesananCustomer adapter_pesanan_customer;
    private List<DataPesanan> list_pesanan;
    SharedPreferences sharedpreferences;
    public static final String TAG_ID = "id";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pesanan, container, false);
        ButterKnife.bind(this,v);
        sharedpreferences = getActivity().getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        getData();    }

    public void getData() {
        adapter_pesanan_customer = new AdapterPesananCustomer(getActivity());
        rv_pesanan.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_pesanan.setAdapter(adapter_pesanan_customer);
        adapter_pesanan_customer.getDatas().clear();
        adapter_pesanan_customer.notifyDataSetChanged();
        final StringRequest request = new StringRequest(Request.Method.POST, Server.get_pesanan_customer, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                Log.e("response", response);
                try {
                    JSONArray data = new JSONArray(response);
                    Gson gson = new Gson();
                    TypeToken<List<DataPesanan>> token = new TypeToken<List<DataPesanan>>() {
                    };
                    list_pesanan = gson.fromJson(data.toString(), token.getType());
                    adapter_pesanan_customer.setDatas(list_pesanan);
                    adapter_pesanan_customer.notifyDataSetChanged();
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
                map.put("id_customer", sharedpreferences.getString(TAG_ID,null));
                return map;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(
                Server.TIMEOUT_ACCESS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(getActivity()).addToRequestQueue(request);

    }
}
