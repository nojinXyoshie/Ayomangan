package com.example.yoshievinsmoke.ayomangan.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tawangga on 03/06/18.
 */

public class DataPesanan implements Serializable{

    @SerializedName("id_order")
    private String idOrder;
    @SerializedName("id_customer")
    private String idCustomer;
    @SerializedName("id_kurir")
    private String idKurir;
    @SerializedName("id_seller")
    private String idSeller;
    @SerializedName("status_penjualan")
    private String statusPenjualan;
    @SerializedName("status_pengiriman")
    private String statusPengiriman;
    @SerializedName("konfirmasi_kurir")
    private String konfirmasiKurir;
    @SerializedName("konfirmasi_customer")
    private String konfirmasiCustomer;
    @SerializedName("detail")
    private DetailBean detail;

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getIdKurir() {
        return idKurir;
    }

    public void setIdKurir(String idKurir) {
        this.idKurir = idKurir;
    }

    public String getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(String idSeller) {
        this.idSeller = idSeller;
    }

    public String getStatusPenjualan() {
        return statusPenjualan;
    }

    public void setStatusPenjualan(String statusPenjualan) {
        this.statusPenjualan = statusPenjualan;
    }

    public String getStatusPengiriman() {
        return statusPengiriman;
    }

    public void setStatusPengiriman(String statusPengiriman) {
        this.statusPengiriman = statusPengiriman;
    }

    public String getKonfirmasiKurir() {
        return konfirmasiKurir;
    }

    public void setKonfirmasiKurir(String konfirmasiKurir) {
        this.konfirmasiKurir = konfirmasiKurir;
    }

    public String getKonfirmasiCustomer() {
        return konfirmasiCustomer;
    }

    public void setKonfirmasiCustomer(String konfirmasiCustomer) {
        this.konfirmasiCustomer = konfirmasiCustomer;
    }

    public DetailBean getDetail() {
        return detail;
    }

    public void setDetail(DetailBean detail) {
        this.detail = detail;
    }

    public static class DetailBean implements Serializable{

        @SerializedName("id_detail")
        private String idDetail;
        @SerializedName("id_order")
        private String idOrder;
        @SerializedName("id_makanan")
        private String idMakanan;
        @SerializedName("kuantitas")
        private String kuantitas;
        @SerializedName("keterangan")
        private String keterangan;
        @SerializedName("alamat")
        private String alamat;
        @SerializedName("ongkir")
        private String ongkir;
        @SerializedName("total_harga")
        private String totalHarga;
        @SerializedName("makanan")
        private MakananBean makanan;

        public String getIdDetail() {
            return idDetail;
        }

        public void setIdDetail(String idDetail) {
            this.idDetail = idDetail;
        }

        public String getIdOrder() {
            return idOrder;
        }

        public void setIdOrder(String idOrder) {
            this.idOrder = idOrder;
        }

        public String getIdMakanan() {
            return idMakanan;
        }

        public void setIdMakanan(String idMakanan) {
            this.idMakanan = idMakanan;
        }

        public String getKuantitas() {
            return kuantitas;
        }

        public void setKuantitas(String kuantitas) {
            this.kuantitas = kuantitas;
        }

        public String getKeterangan() {
            return keterangan;
        }

        public void setKeterangan(String keterangan) {
            this.keterangan = keterangan;
        }

        public String getAlamat() {
            return alamat;
        }

        public void setAlamat(String alamat) {
            this.alamat = alamat;
        }

        public String getOngkir() {
            return ongkir;
        }

        public void setOngkir(String ongkir) {
            this.ongkir = ongkir;
        }

        public String getTotalHarga() {
            return totalHarga;
        }

        public void setTotalHarga(String totalHarga) {
            this.totalHarga = totalHarga;
        }

        public MakananBean getMakanan() {
            return makanan;
        }

        public void setMakanan(MakananBean makanan) {
            this.makanan = makanan;
        }

        public static class MakananBean implements Serializable{

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
    }
}
