package com.example.yoshievinsmoke.ayomangan.data;

/**
 * Created by yoshievinsmoke on 5/4/18.
 */

public class DataKurir {
    private String id_kurir, nama, no_hp;

    public DataKurir() {
    }

    public DataKurir(String id_kurir, String nama, String no_hp ) {
        this.id_kurir = id_kurir;
        this.nama = nama;
        this.no_hp = no_hp;
    }

    public String getIdKurir() {
        return id_kurir;
    }

    public void setIdKurir(String id_kurir) {
        this.id_kurir = id_kurir;
    }

    public String getNamaKurir() {
        return nama;
    }

    public void setNamaKurir(String nama) {
        this.nama = nama;
    }

    public String getNoHp() {
        return no_hp;
    }

    public void setNoHp(String no_hp) {
        this.no_hp = no_hp;
    }
}
