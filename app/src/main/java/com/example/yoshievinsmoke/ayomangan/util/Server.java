package com.example.yoshievinsmoke.ayomangan.util;

/**
 * Created by yoshievinsmoke on 4/12/18.
 */

public class Server {
//    public static final String URL = "http://192.168.1.136/ayomangan_login/";
    public static final String URL = "http://192.168.100.6/ayomangan_login/";
    public static final String URL_Foto = URL+"data/image/";
    public static final int TIMEOUT_ACCESS = 5000;
    public static String get_seller = URL+"master/get_seller.php";
    public static String get_makanan_byseller = URL+"master/select_makanan_byseller.php";
    public static String get_seller_byid = URL+"master/get_seller_byid.php";
    public static String input_pesanan=URL+"master/input_pesanan.php";
    public static String get_pesanan_customer = URL+"master/get_pesanan_customer.php";
    public static String get_pesanan_byseller = URL+"master/select_pesanan_bytoko.php";
    public static String get_kurir = URL+"master/select_kurir.php";
    public static String konfirmasi_pesanan = URL+"master/konfirmasi_pesanan.php";
    public static String get_pesanan_kurir = URL+"master/get_pesanan_kurir.php";
    public static String konfirmasi_pengiriman=URL+"master/konfirmasi_pengiriman.php";
    public static String konfirmasi_penerimaan=URL+"master/konfirmasi_customer.php";
}
