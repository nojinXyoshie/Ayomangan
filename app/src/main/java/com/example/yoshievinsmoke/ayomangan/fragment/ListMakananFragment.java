package com.example.yoshievinsmoke.ayomangan.fragment;

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
import com.example.yoshievinsmoke.ayomangan.app.AppController;
import com.example.yoshievinsmoke.ayomangan.data.DataMakanan;
import com.example.yoshievinsmoke.ayomangan.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListMakananFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    Toolbar toolbar;
    FloatingActionButton fab;
    ListView list_makanan;
    SwipeRefreshLayout swipe;
    List<DataMakanan> itemList = new ArrayList<DataMakanan>();
    MakananAdapter adapter;
    int success;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    EditText txt_id_makanan, txt_nama_makanan, txt_harga;
    String id_makanan, nama_makanan, harga;

    private static final String TAG = MakananFragment.class.getSimpleName();

    private static String url_order1 	 = Server.URL + "master/customer_select_makanan.php";
    private static String url_order2 	 = Server.URL + "master/customer_select_makanan.php";
    private static String url_select 	 = Server.URL + "master/customer_select_makanan.php";

    public static final String TAG_ID_TOKO       = "id_toko";
    public static final String TAG_ID_MAKANAN       = "id_makanan";
    public static final String TAG_NAMA_MAKANAN     = "nama_makanan";
    public static final String TAG_HARGA   = "harga";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.customer_content_makanan, container, false);

        // menghubungkan variablel pada layout dan pada java
        fab     = (FloatingActionButton) view.findViewById(R.id.fab_add_makanan);
        swipe   = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        list_makanan    = (ListView) view.findViewById(R.id.list_makanan);

        // untuk mengisi data dari JSON ke dalam adapter
        adapter = new MakananAdapter(getActivity(), itemList);
        list_makanan.setAdapter(adapter);

        // listview ditekan lama akan menampilkan dua pilihan edit atau delete data
        list_makanan.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view,
                                           final int position, long id) {
                // TODO Auto-generated method stub
                final String idx = itemList.get(position).getIdMakanan();

                final CharSequence[] dialogitem = {"Order"};
                dialog = new AlertDialog.Builder(getActivity());
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                order(idx);
                                break;
                        }
                    }
                }).show();
                return false;
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

                        DataMakanan item = new DataMakanan();

                        item.setIdMakanan(obj.getString(TAG_ID_MAKANAN));
                        item.setNamaMakanan(obj.getString(TAG_NAMA_MAKANAN));
                        item.setHarga(obj.getString(TAG_HARGA));

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

    // fungsi untuk get edit data
    private void order(final String id_makananx){
        StringRequest strReq = new StringRequest(Request.Method.POST, url_order1, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("get edit data", jObj.toString());
                        String id_makananx      = jObj.getString(TAG_ID_MAKANAN);
                        String nama_makananx    = jObj.getString(TAG_NAMA_MAKANAN);
                        String hargax  = jObj.getString(TAG_HARGA);

                        DialogForm(id_makananx, nama_makananx, hargax, "UPDATE");

                        adapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(getActivity(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_makanan", id_makananx);

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    // untuk menampilkan dialog form biodata
    private void DialogForm(String id_makananx, String nama_makananx, String hargax, String button) {
        dialog = new AlertDialog.Builder(getActivity());
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.makanan_form, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Form Data Makanan");

        txt_id_makanan      = (EditText) dialogView.findViewById(R.id.txt_id_makanan);
        txt_nama_makanan    = (EditText) dialogView.findViewById(R.id.txt_nama_makanan);
        txt_harga  = (EditText) dialogView.findViewById(R.id.txt_harga);

        if (!id_makananx.isEmpty()){
            txt_id_makanan.setText(id_makananx);
            txt_nama_makanan.setText(nama_makananx);
            txt_harga.setText(hargax);
        } else {
            kosong();
        }

        dialog.setPositiveButton(button, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                id_makanan      = txt_id_makanan.getText().toString();
                nama_makanan    = txt_nama_makanan.getText().toString();
                harga  = txt_harga.getText().toString();

                simpan_update();
                dialog.dismiss();
            }
        });

        dialog.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                kosong();
            }
        });

        dialog.show();
    }

    // fungsi untuk menyimpan atau update
    private void simpan_update() {
        String url;
        // jika id kosong maka simpan, jika id ada nilainya maka update
            url = url_order2;

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("Add/update", jObj.toString());

                        callVolley();
                        kosong();

                        Toast.makeText(getActivity(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                        adapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(getActivity(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                // jika id kosong maka simpan, jika id ada nilainya maka update
                if (id_makanan.isEmpty()){
                    params.put("nama_makanan", nama_makanan);
                    params.put("harga", harga);
                } else {
                    params.put("id_makanan", id_makanan);
                    params.put("nama_makanan", nama_makanan);
                    params.put("harga", harga);
                }

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    // untuk mengosongi edittext pada form
    private void kosong(){
        txt_id_makanan.setText(null);
        txt_nama_makanan.setText(null);
        txt_harga.setText(null);
    }

}