package com.example.yoshievinsmoke.ayomangan.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.yoshievinsmoke.ayomangan.R;
import com.example.yoshievinsmoke.ayomangan.adapter.AdapterMakananCustomer;
import com.example.yoshievinsmoke.ayomangan.adapter.AdapterSeller;
import com.example.yoshievinsmoke.ayomangan.data.DataMakananCustomer;
import com.example.yoshievinsmoke.ayomangan.data.DataSeller;
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
@SuppressLint("ValidFragment")
public class MakananFragmentCustomer extends Fragment implements View.OnKeyListener {


    private DataSeller dataSeller;
    @BindView(R.id.rv_makanan)
    RecyclerView rv_makanan;
    private List<DataMakananCustomer> list_makanan;
     AdapterMakananCustomer adapter_makanan_customer;

    @SuppressLint("ValidFragment")
    public MakananFragmentCustomer(DataSeller dataSeller) {
        // Required empty public constructor
        this.dataSeller = dataSeller;
    }

    public MakananFragmentCustomer() {
        // Required empty public constructor
        this.dataSeller = dataSeller;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_makanan_fragment_customer, container, false);
        ButterKnife.bind(this, v);
        getMakanan();
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.action_search);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter_makanan_customer.getFilter().filter(newText);
                return false;
            }
        });

        super.onPrepareOptionsMenu(menu);
    }


    public void getMakanan() {
        adapter_makanan_customer = new AdapterMakananCustomer(getActivity());
        rv_makanan.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rv_makanan.setAdapter(adapter_makanan_customer);
        adapter_makanan_customer.getDatas().clear();
        adapter_makanan_customer.notifyDataSetChanged();
        final StringRequest request = new StringRequest(Request.Method.POST, Server.get_makanan_byseller, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                Log.e("response", response);
                try {
                    JSONArray data = new JSONArray(response);
                    Gson gson = new Gson();
                    TypeToken<List<DataMakananCustomer>> token = new TypeToken<List<DataMakananCustomer>>() {
                    };
                    list_makanan = gson.fromJson(data.toString(), token.getType());
                    adapter_makanan_customer.setDatas(list_makanan);
                    adapter_makanan_customer.notifyDataSetChanged();
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
                map.put("id_seller", dataSeller.getIdSeller());
                return map;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(
                Server.TIMEOUT_ACCESS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(getActivity()).addToRequestQueue(request);

    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (i == KeyEvent.KEYCODE_BACK) {
                Fragment fragment = new HomeFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_main, fragment);
                ft.commit();
                return true;
            }


        return false;
    }
}
