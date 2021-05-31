package com.example.yoshievinsmoke.ayomangan;

/**
 * Created by yoshievinsmoke on 5/8/18.
 */
import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.yoshievinsmoke.ayomangan.adapter.Adapter;
import com.example.yoshievinsmoke.ayomangan.app.AppController;
import com.example.yoshievinsmoke.ayomangan.util.Server;

import android.support.v4.app.SupportActivity.ExtraData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputKurir extends Fragment{
    Adapter adapter;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    int success;
    EditText txt_id_kurir, txt_NamaKurir, txt_noHP;
    String id, id_kurir, nama_kurir, no_hp;

    private static final String TAG = InputKurir.class.getSimpleName();

    private static String url = Server.URL + "master/input_kurir.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_input_kurir, container, false);

        txt_id_kurir      = (EditText) view.findViewById(R.id.txt_id_kurir);
        txt_NamaKurir     = (EditText) view.findViewById(R.id.txt_NamaKurir);
        txt_noHP    = (EditText) view.findViewById(R.id.txt_noHP);

        Button btn_cancel = (Button) view.findViewById(R.id.btnBatal);
        Button btn_save = (Button) view.findViewById(R.id.btnSimpan);
/*
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentManager = getFragmentManager();
                transaction = fragmentManager.beginTransaction();

                ListDataBarang list = new ListDataBarang();
                transaction.replace(R.id.framekary, list);
                transaction.commit();
            }
        });
*/
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //id  = getActivity().getIntent().getStringExtra(TAG_ID);
                //id_kurir      = txt_id_kurir.getText().toString();
                nama_kurir  = txt_NamaKurir.getText().toString();
                no_hp  = txt_noHP.getText().toString();
                simpan();
                //Toast.makeText(getActivity(), id_kurir, Toast.LENGTH_SHORT).show();
                //Toast.makeText(getActivity(), nama_kurir, Toast.LENGTH_SHORT).show();
                //Toast.makeText(getActivity(), no_hp, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }




    // untuk mengosongi edittext pada form
    private void kosong(){
        txt_id_kurir.setText(null);
        txt_NamaKurir.setText(null);
        txt_noHP.setText(null);
    }


    // fungsi untuk menyimpan
    private void simpan() {

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

                        kosong();
                        Toast.makeText(getActivity(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

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
                //params.put("id", id);
                params.put("nama", nama_kurir);
                params.put("no_hp", no_hp);
                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }


}

