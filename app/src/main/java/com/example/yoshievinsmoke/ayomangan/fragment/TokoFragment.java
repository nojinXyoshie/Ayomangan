package com.example.yoshievinsmoke.ayomangan.fragment;

/**
 * Created by yoshievinsmoke on 5/10/18.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.yoshievinsmoke.ayomangan.R;
import com.example.yoshievinsmoke.ayomangan.adapter.MakananAdapter;
import com.example.yoshievinsmoke.ayomangan.adapter.TokoAdapter;
import com.example.yoshievinsmoke.ayomangan.app.AppController;
import com.example.yoshievinsmoke.ayomangan.data.DataMakanan;
import com.example.yoshievinsmoke.ayomangan.data.DataToko;
import com.example.yoshievinsmoke.ayomangan.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TokoFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    Toolbar toolbar;
    ListView list_toko;
    SwipeRefreshLayout swipe;
    List<DataToko> itemList = new ArrayList<DataToko>();
    TokoAdapter adapter;
    int success;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    EditText txt_id_toko, txt_nama_toko;
    String id_toko, nama_toko;

    private static final String TAG = TokoFragment.class.getSimpleName();

    private static String url_select 	 = Server.URL + "master/select_toko.php";

    public static final String TAG_ID_TOKO       = "id_toko";
    public static final String TAG_NAMA_TOKO     = "nama_toko";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.customer_content_toko, container, false);

        // menghubungkan variablel pada layout dan pada java
        swipe   = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        //list_toko    = (ListView) view.findViewById(R.id.list_toko);
        ListView listView = (ListView) view.findViewById(R.id.list_toko);

        // untuk mengisi data dari JSON ke dalam adapter
        adapter = new TokoAdapter(getActivity(), itemList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Fragment fragment = new ListMakananFragment();
                //buat object fragmentkedua

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_main, fragment);
                ft.commit();
            }
        });

        // menamilkan widget refresh
        swipe.setOnRefreshListener(this);

        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(true);
                           itemList.clear();
                           adapter.notifyDataSetChanged();
                           callVolley();
                       }
                   }
        );

        return view;
    }

    @Override
    public void onRefresh() {
        itemList.clear();
        adapter.notifyDataSetChanged();
        callVolley();
    }


    // untuk menampilkan semua data pada listview
    private void callVolley(){
        itemList.clear();
        adapter.notifyDataSetChanged();
        swipe.setRefreshing(true);

        // membuat request JSON
        JsonArrayRequest jArr = new JsonArrayRequest(url_select, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());

                // Parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);

                        DataToko item = new DataToko();

                        item.setIdToko(obj.getString(TAG_ID_TOKO));
                        item.setNamaToko(obj.getString(TAG_NAMA_TOKO));

                        // menambah item ke array
                        itemList.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                // notifikasi adanya perubahan data pada adapter
                adapter.notifyDataSetChanged();

                swipe.setRefreshing(false);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                swipe.setRefreshing(false);
            }
        });

        // menambah request ke request queue
        AppController.getInstance().addToRequestQueue(jArr);
    }


}