package com.example.yoshievinsmoke.ayomangan.data;

/**
 * Created by tawangga on 02/06/18.
 */

public class DataSeller {
    /**
     * id_seller : 2
     * nama : yoshie pangestu
     * no_hp : 089525321829
     * email : yoshie@hotmail.com
     * password : polindra
     * nama_toko : Toko Yoshie
     */

    @com.google.gson.annotations.SerializedName("id_seller")
    private String idSeller;
    @com.google.gson.annotations.SerializedName("nama")
    private String nama;
    @com.google.gson.annotations.SerializedName("no_hp")
    private String noHp;
    @com.google.gson.annotations.SerializedName("email")
    private String email;
    @com.google.gson.annotations.SerializedName("password")
    private String password;
    @com.google.gson.annotations.SerializedName("nama_toko")
    private String namaToko;

    public String getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(String idSeller) {
        this.idSeller = idSeller;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNamaToko() {
        return namaToko;
    }

    public void setNamaToko(String namaToko) {
        this.namaToko = namaToko;
    }
}
