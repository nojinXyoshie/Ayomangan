package com.example.yoshievinsmoke.ayomangan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.yoshievinsmoke.ayomangan.fragment.HelpFragment;
import com.example.yoshievinsmoke.ayomangan.fragment.HomeFragment;
import com.example.yoshievinsmoke.ayomangan.fragment.PesananFragment;
import com.example.yoshievinsmoke.ayomangan.fragment.PesananKurirFragment;
import com.example.yoshievinsmoke.ayomangan.fragment.ProfilFragment;
import com.example.yoshievinsmoke.ayomangan.fragment.ReviewFragment;
import com.example.yoshievinsmoke.ayomangan.fragment.notifikasi;

import static com.example.yoshievinsmoke.ayomangan.fragment.FragmentKurir.TAG_NAMA;

public class MainActivityKurir extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;


    String email, username;
    SharedPreferences sharedpreferences;

    public static final String TAG_EMAIL = "email";
    public static final String TAG_USERNAME = "nama";

    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_kurir);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        email = getIntent().getStringExtra(TAG_EMAIL);
        username = getIntent().getStringExtra(TAG_NAMA);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //mViewPager = (ViewPager) findViewById(R.id.vp_tabs);
        //mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), this));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        View v = navigationView.getHeaderView(0);
        TextView nama = v.findViewById(R.id.namatoko);
        TextView email = v.findViewById(R.id.namapenjual);
        nama.setText(sharedpreferences.getString(TAG_NAMA,null));
        email.setText(sharedpreferences.getString(TAG_EMAIL,null));
        displaySelectedScreen(R.id.Pesanan);
//


    }


    private void displaySelectedScreen(int id) {
        Fragment fragment = null;

        switch (id) {
            case R.id.Pesanan:
                fragment = new PesananKurirFragment();
                break;
            case R.id.nav_logout:
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(Login.session_status, false);
                editor.putString(TAG_EMAIL, null);
                editor.putString(TAG_USERNAME, null);
                editor.commit();

                Intent intent = new Intent(MainActivityKurir.this, Login.class);
                finish();
                startActivity(intent);
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        displaySelectedScreen(id);

        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
