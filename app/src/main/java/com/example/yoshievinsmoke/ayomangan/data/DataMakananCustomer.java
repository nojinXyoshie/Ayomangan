package com.example.yoshievinsmoke.ayomangan.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tawangga on 02/06/18.
 */

public class DataMakananCustomer implements Serializable {
    /**
     * id_makanan : 5
     * id_seller : 1
     * nama_makanan : gorengan
     * harga : 3000
     * foto : test.jpg
     * deskripsi :  Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eleifend metus lectus, nec lobortis sapien vestibulum sit amet. Nulla eget nulla tortor. Duis eget semper odio. Phasellus efficitur tempor ullamcorper. Etiam in diam lorem. Sed tortor est, elementum nec efficitur tincidunt, volutpat eu massa. Etiam commodo justo venenatis lorem posuere, quis venenatis velit consectetur. Curabitur scelerisque, odio nec egestas tempus, mi ante venenatis nulla, ac pellentesque risus augue ac nibh. Proin eu velit magna.
     */

    @SerializedName("id_makanan")
    private String idMakanan;
    @SerializedName("id_seller")
    private String idSeller;
    @SerializedName("nama_makanan")
    private String namaMakanan;
    @SerializedName("harga")
    private String harga;
    @SerializedName("foto")
    private String foto;
    @SerializedName("deskripsi")
    private String deskripsi;

    public String getIdMakanan() {
        return idMakanan;
    }

    public void setIdMakanan(String idMakanan) {
        this.idMakanan = idMakanan;
    }

    public String getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(String idSeller) {
        this.idSeller = idSeller;
    }

    public String getNamaMakanan() {
        return namaMakanan;
    }

    public void setNamaMakanan(String namaMakanan) {
        this.namaMakanan = namaMakanan;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
