package com.example.yoshievinsmoke.ayomangan.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tawangga on 04/06/18.
 */

public class DataKurirDong {
    /**
     * id_kurir : 6
     * nama : irin
     * no_hp : 09876544447
     * email :
     */

    @SerializedName("id_kurir")
    private String idKurir;
    @SerializedName("nama")
    private String nama;
    @SerializedName("no_hp")
    private String noHp;
    @SerializedName("email")
    private String email;

    public String getIdKurir() {
        return idKurir;
    }

    public void setIdKurir(String idKurir) {
        this.idKurir = idKurir;
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
}
