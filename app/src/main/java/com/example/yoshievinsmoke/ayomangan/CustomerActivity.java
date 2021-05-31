package com.example.yoshievinsmoke.ayomangan;

/**
 * Created by yoshievinsmoke on 5/15/18.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.TextView;
import android.widget.Toast;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.example.yoshievinsmoke.ayomangan.fragment.FragmentKurir;
import com.example.yoshievinsmoke.ayomangan.fragment.HelpFragment;
import com.example.yoshievinsmoke.ayomangan.fragment.HomeFragment;
import com.example.yoshievinsmoke.ayomangan.fragment.MakananFragment;
import com.example.yoshievinsmoke.ayomangan.fragment.MakananFragmentCustomer;
import com.example.yoshievinsmoke.ayomangan.fragment.PesananFragment;
import com.example.yoshievinsmoke.ayomangan.fragment.ProfilFragment;
import com.example.yoshievinsmoke.ayomangan.fragment.ReviewFragment;
import com.example.yoshievinsmoke.ayomangan.fragment.TokoFragment;
import com.example.yoshievinsmoke.ayomangan.fragment.TrackingFragment;
import com.example.yoshievinsmoke.ayomangan.fragment.notifikasi;

import static com.example.yoshievinsmoke.ayomangan.fragment.FragmentKurir.TAG_NAMA;

public class CustomerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Mendefinisikan variabel untuk navdraw
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
        setContentView(R.layout.customer_activity_main);

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
        displaySelectedScreen(R.id.nav_home);
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.content_main, new HomeFragment());
//        ft.commit();
//        toolbar.setTitle("  Home");
//        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
//        toolbar.setLogo(R.drawable.ic_home_black_24dp);

        /*
        mSlidingTabLayout=(SlidingTabLayout)findViewById(R.id.st1_tabs);
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.colorAccent));
        mSlidingTabLayout.setCustomTabView(R.layout.tab_view, R.id.tv_tab);
        mSlidingTabLayout.setViewPager(mViewPager);
*/
        //======================================================================================
 /*       headerNavigationLeft = new AccountHeader()
                .withActivity(this)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .withHeaderBackground(R.color.colorPrimary)
                .addProfiles(
                        new ProfileDrawerItem().withName(username).withEmail(email).withIcon(getResources().getDrawable(R.drawable.icon))
                )
                .build();
        navigationDrawerLeft = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withDisplayBelowToolbar(false)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.LEFT)
                .withSavedInstance(savedInstanceState)
                .withAccountHeader(headerNavigationLeft)
                .withSelectedItem(0)
                .build();

        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Home").withIcon(getResources().getDrawable(R.drawable.ic_home_black_24dp)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Saya").withIcon(getResources().getDrawable(R.drawable.ic_account_box_black_24dp)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Toko").withIcon(getResources().getDrawable(R.drawable.ic_local_grocery_store_black_24dp)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Kurir").withIcon(getResources().getDrawable(R.drawable.ic_local_grocery_store_black_24dp)));
*/

/*        // Menginisiasi  NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        //Mengatur Navigasi View Item yang akan dipanggil untuk menangani item klik menu navigasi
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //Memeriksa apakah item tersebut dalam keadaan dicek  atau tidak,
                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);
                //Menutup  drawer item klik
                drawerLayout.closeDrawers();
                //Memeriksa untuk melihat item yang akan dilklik dan melalukan aksi
                switch (menuItem.getItemId()){
                    // pilihan menu item navigasi akan menampilkan pesan toast klik kalian bisa menggantinya
                    //dengan intent activity
                    case R.id.navigation1:
                        Toast.makeText(getApplicationContext(), "Beranda Telah Dipilih", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.navigation2:
                        Toast.makeText(getApplicationContext(),"Profil Telah Dipilih",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.navigation3:
                        Toast.makeText(getApplicationContext(),"Daftar Telah Dipilih",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.navigation4:
                        Toast.makeText(getApplicationContext(),"Setting telah dipilih",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.navigation5:
                        Toast.makeText(getApplicationContext(),"About telah dipilih",Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(),"Kesalahan Terjadi ",Toast.LENGTH_SHORT).show();
                        return true;
                }
            }
        });
        // Menginisasi Drawer Layout dan ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){
            @Override
            public void onDrawerClosed(View drawerView) {
                // Kode di sini akan merespons setelah drawer menutup disini kita biarkan kosong
                super.onDrawerClosed(drawerView);
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                //  Kode di sini akan merespons setelah drawer terbuka disini kita biarkan kosong
                super.onDrawerOpened(drawerView);
            }
        };
        //Mensetting actionbarToggle untuk drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        //memanggil synstate
        actionBarDrawerToggle.syncState();
*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_help) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    private void displaySelectedScreen(int id) {
        Fragment fragment = null;

        switch (id) {
            case R.id.nav_profil:
                fragment = new ProfilFragment();
                toolbar.setTitle("  Profil");
                toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
                toolbar.setLogo(R.drawable.ic_account_box_black_24dp);
                break;
            case R.id.nav_home:
                fragment = new HomeFragment();
                toolbar.setTitle("  Home");
                toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
                toolbar.setLogo(R.drawable.ic_home_black_24dp);
                break;
            case R.id.nav_notif:
                fragment = new notifikasi();
                toolbar.setTitle("  Notifikasi");
                toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
                toolbar.setLogo(R.drawable.notif);
                break;
            case R.id.nav_history:
                fragment = new notifikasi();
                toolbar.setTitle("  Riwayat Order");
                toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
                toolbar.setLogo(R.drawable.riwayatorder);
                break;
            case R.id.nav_keranjang:
                fragment = new PesananFragment();
                toolbar.setTitle("  Pesanan");
                toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
                toolbar.setLogo(R.drawable.keranjang);
                break;
            case R.id.nav_review:
                fragment = new ReviewFragment();
                toolbar.setTitle("  Review");
                toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
                toolbar.setLogo(R.drawable.review);
                break;
            case R.id.nav_help:
                fragment = new HelpFragment();
                toolbar.setTitle(" Bantuan");
                toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
                toolbar.setLogo(R.drawable.bantuan);
                break;
            case R.id.nav_logout:
                toolbar.setTitle("  Keluar");
                toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
                toolbar.setLogo(R.drawable.poweroff);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(Login.session_status, false);
                editor.putString(TAG_EMAIL, null);
                editor.putString(TAG_USERNAME, null);
                editor.commit();

                Intent intent = new Intent(CustomerActivity.this, Login.class);
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

}



