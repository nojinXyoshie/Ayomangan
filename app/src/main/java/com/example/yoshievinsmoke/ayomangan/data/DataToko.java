package com.example.yoshievinsmoke.ayomangan.data;

/**
 * Created by yoshievinsmoke on 5/4/18.
 */

public class DataToko {
    private String id_toko, nama_toko;

    public DataToko() {
    }

    public DataToko(String id_toko, String nama_toko ) {
        this.id_toko = id_toko;
        this.nama_toko = nama_toko;
    }

    public String getIdToko() {
        return id_toko;
    }

    public void setIdToko(String id_toko) {
        this.id_toko = id_toko;
    }

    public String getNamaToko() {
        return nama_toko;
    }

    public void setNamaToko(String nama_toko) {
        this.nama_toko = nama_toko;
    }

}
