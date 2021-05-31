package com.example.yoshievinsmoke.ayomangan.data;

/**
 * Created by yoshievinsmoke on 5/4/18.
 */

public class DataMakanan {
    private String id_makanan, id_seller, nama_makanan, harga;

    public DataMakanan() {
    }

    public DataMakanan(String id_makanan, String id_seller, String nama_makanan, String harga ) {
        this.id_makanan = id_makanan;
        this.id_seller = id_seller;
        this.nama_makanan = nama_makanan;
        this.harga = harga;
    }

    public String getIdMakanan() {
        return id_makanan;
    }

    public void setIdMakanan(String id_makanan) {
        this.id_makanan = id_makanan;
    }

    public String getIdSeller() {
        return id_seller;
    }

    public void setIdSeller(String id_seller) {
        this.id_seller = id_seller;
    }

    public String getNamaMakanan() {
        return nama_makanan;
    }

    public void setNamaMakanan(String nama_makanan) {
        this.nama_makanan = nama_makanan;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
