package com.example.yoshievinsmoke.ayomangan.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tawangga on 04/06/18.
 */

public class DataPesananSeller implements Serializable{
    /**
     * id_order : 11
     * id_customer : 1
     * id_kurir : 0
     * id_seller : 2
     * status_penjualan : 0
     * status_pengiriman : 0
     * konfirmasi_kurir : 0
     * konfirmasi_customer : 0
     * pembeli : {"id_customer":"1","nama":"irin windiyati","email":"irin@gmail.com","password":"polindra","no_hp":"089"}
     * detail : {"id_detail":"6","id_order":"11","id_makanan":"5","kuantitas":"1","keterangan":"disini","alamat":"disana","ongkir":"5000","total_harga":"8000.0","makanan":{"id_makanan":"5","id_seller":"2","nama_makanan":"gorengan","harga":"3000","foto":"test.jpg","deskripsi":" Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eleifend metus lectus, nec lobortis sapien vestibulum sit amet. Nulla eget nulla tortor. Duis eget semper odio. Phasellus efficitur tempor ullamcorper. Etiam in diam lorem. Sed tortor est, elementum nec efficitur tincidunt, volutpat eu massa. Etiam commodo justo venenatis lorem posuere, quis venenatis velit consectetur. Curabitur scelerisque, odio nec egestas tempus, mi ante venenatis nulla, ac pellentesque risus augue ac nibh. Proin eu velit magna. "}}
     */

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
    @SerializedName("pembeli")
    private PembeliBean pembeli;
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

    public PembeliBean getPembeli() {
        return pembeli;
    }

    public void setPembeli(PembeliBean pembeli) {
        this.pembeli = pembeli;
    }

    public DetailBean getDetail() {
        return detail;
    }

    public void setDetail(DetailBean detail) {
        this.detail = detail;
    }

    public static class PembeliBean implements Serializable{
        /**
         * id_customer : 1
         * nama : irin windiyati
         * email : irin@gmail.com
         * password : polindra
         * no_hp : 089
         */

        @SerializedName("id_customer")
        private String idCustomer;
        @SerializedName("nama")
        private String nama;
        @SerializedName("email")
        private String email;
        @SerializedName("password")
        private String password;
        @SerializedName("no_hp")
        private String noHp;

        public String getIdCustomer() {
            return idCustomer;
        }

        public void setIdCustomer(String idCustomer) {
            this.idCustomer = idCustomer;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
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

        public String getNoHp() {
            return noHp;
        }

        public void setNoHp(String noHp) {
            this.noHp = noHp;
        }
    }

    public static class DetailBean implements Serializable{
        /**
         * id_detail : 6
         * id_order : 11
         * id_makanan : 5
         * kuantitas : 1
         * keterangan : disini
         * alamat : disana
         * ongkir : 5000
         * total_harga : 8000.0
         * makanan : {"id_makanan":"5","id_seller":"2","nama_makanan":"gorengan","harga":"3000","foto":"test.jpg","deskripsi":" Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eleifend metus lectus, nec lobortis sapien vestibulum sit amet. Nulla eget nulla tortor. Duis eget semper odio. Phasellus efficitur tempor ullamcorper. Etiam in diam lorem. Sed tortor est, elementum nec efficitur tincidunt, volutpat eu massa. Etiam commodo justo venenatis lorem posuere, quis venenatis velit consectetur. Curabitur scelerisque, odio nec egestas tempus, mi ante venenatis nulla, ac pellentesque risus augue ac nibh. Proin eu velit magna. "}
         */

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
            /**
             * id_makanan : 5
             * id_seller : 2
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
    }
}
