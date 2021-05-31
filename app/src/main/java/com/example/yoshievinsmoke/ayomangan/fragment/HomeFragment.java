package com.example.yoshievinsmoke.ayomangan.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.yoshievinsmoke.ayomangan.CustomerActivity;
import com.example.yoshievinsmoke.ayomangan.R;
import com.example.yoshievinsmoke.ayomangan.adapter.AdapterSeller;
import com.example.yoshievinsmoke.ayomangan.adapter.GridAdapter;
import com.example.yoshievinsmoke.ayomangan.data.DataSeller;
import com.example.yoshievinsmoke.ayomangan.util.MySingleton;
import com.example.yoshievinsmoke.ayomangan.util.Server;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends Fragment {

    private List<DataSeller> list_seller;

    public HomeFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.rv_seller)
    RecyclerView rv_seller;
    AdapterSeller adapter_seller;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
            /*mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
            mRecyclerView.setHasFixedSize(true);

            mLayoutManager = new GridLayoutManager(getActivity(),2);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new GridAdapter();
            mRecyclerView.setAdapter(mAdapter);
            */

//        final GridView gridview = (GridView) view.findViewById(R.id.gridview);
//        gridview.setAdapter(new ImageAdapter(getActivity()));
//
//
//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//
//                Fragment fragment = new MakananFragment();
//                //buat object fragmentkedua
//
//                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                ft.replace(R.id.content_main, fragment);
//                ft.commit();
//            }
//        });

        getSeller();

        ((CustomerActivity) getActivity()).getToolbar().setTitle("  Menu");
        ((CustomerActivity) getActivity()).getToolbar().setTitleTextColor(getResources().getColor(android.R.color.black));
        ((CustomerActivity) getActivity()).getToolbar().setLogo(R.drawable.ic_home_black_24dp);

        return view;
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
                adapter_seller.getFilter().filter(newText);
                return false;
            }
        });

        super.onPrepareOptionsMenu(menu);
    }

    public void getSeller() {

        adapter_seller = new AdapterSeller(getActivity());
        rv_seller.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rv_seller.setAdapter(adapter_seller);
        adapter_seller.getDatas().clear();
        adapter_seller.notifyDataSetChanged();
        final StringRequest request = new StringRequest(Request.Method.POST, Server.get_seller, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray data = new JSONArray(response);
                    Gson gson = new Gson();
                    TypeToken<List<DataSeller>> token = new TypeToken<List<DataSeller>>() {
                    };
                    list_seller = gson.fromJson(data.toString(), token.getType());
                    adapter_seller.setDatas(list_seller);
                    adapter_seller.notifyDataSetChanged();
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
        MySingleton.getInstance(getActivity()).addToRequestQueue(request);
    }


//    public class ImageAdapter extends BaseAdapter {
//        private Context mContext;
//
//        public ImageAdapter(Context c) {
//            mContext = c;
//        }
//
//        public int getCount() {
//            return myThumbIds.length;
//        }
//
//        public Object getItem(int position) {
//            return null;
//        }
//
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        // buat ImageView baru untuk setiap item yang direferensikan oleh Adaptor
//        public View getView(int position, View convertView, ViewGroup parent) {
//            ImageView imageView;
//            //jika tidak di daur ulang
//            if (convertView == null) {
//                //menginisialisasi beberapa atribut
//                imageView = new ImageView(mContext);
//                imageView.setLayoutParams(new GridView.LayoutParams(160, 190));
//                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                imageView.setPadding(8, 8, 8, 8);
//            } else {
//                imageView = (ImageView) convertView;
//            }
//
//            imageView.setImageResource(myThumbIds[position]);
//            return imageView;
//        }
//
//        // menampilkan referensi gambar di gridView
//        private Integer[] myThumbIds = {
//                R.drawable.ayam, R.drawable.mapin,
//                R.drawable.ayam, R.drawable.mapin,
//                R.drawable.ayam, R.drawable.ayam,
//        };
////    }
//    }

}