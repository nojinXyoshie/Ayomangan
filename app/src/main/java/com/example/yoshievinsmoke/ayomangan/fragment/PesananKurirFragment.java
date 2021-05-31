package com.example.yoshievinsmoke.ayomangan.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.example.yoshievinsmoke.ayomangan.adapter.AdapterPesananSeller;
import com.example.yoshievinsmoke.ayomangan.data.DataPesananSeller;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class PesananKurirFragment extends Fragment {



    private static final String TAG_ID = "id";
    @BindView(R.id.rv_pesanan)
    RecyclerView rv_pesanan;
    private AdapterPesananSeller adapter_pesanan_seller;
    private List<DataPesananSeller> list_pesanan;
    SharedPreferences sharedpreferences;
    public PesananKurirFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_pesanan_kurir, container, false);

        sharedpreferences = getActivity().getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        ButterKnife.bind(this, v);

        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    public void getData() {
        adapter_pesanan_seller = new AdapterPesananSeller(getActivity());
        rv_pesanan.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_pesanan.setAdapter(adapter_pesanan_seller);
        adapter_pesanan_seller.getDatas().clear();
        adapter_pesanan_seller.notifyDataSetChanged();
        final StringRequest request = new StringRequest(Request.Method.POST, Server.get_pesanan_kurir, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                Log.e("response", response);
                try {
                    JSONArray data = new JSONArray(response);
                    Gson gson = new Gson();
                    TypeToken<List<DataPesananSeller>> token = new TypeToken<List<DataPesananSeller>>() {
                    };
                    list_pesanan = gson.fromJson(data.toString(), token.getType());
                    adapter_pesanan_seller.setDatas(list_pesanan);
                    adapter_pesanan_seller.notifyDataSetChanged();
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
                map.put("id_kurir", sharedpreferences.getString(TAG_ID,null));
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
