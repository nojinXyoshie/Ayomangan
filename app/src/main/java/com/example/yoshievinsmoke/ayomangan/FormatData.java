package com.example.yoshievinsmoke.ayomangan;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

/**
 * Created by tawangga on 21/02/18.
 */

public class FormatData {
    public static String doubleToRupiah(double data) {
        double harga = data;

        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
//        System.out.printf("Harga Rupiah: %s %n", kursIndonesia.format(harga));
        return kursIndonesia.format(harga);
    }

    public static String doubleToRupiahNoSymbol(double data) {
        double harga = data;

        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
//        System.out.printf("Harga Rupiah: %s %n", kursIndonesia.format(harga));
        return kursIndonesia.format(harga);
    }

    public static double rupiahToDouble(String text){
        String x = text;
        double nilai = 0;
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
        try {
            Number number = kursIndonesia.parse(x);
            nilai = number.doubleValue();
//            System.out.println("Nilai dari rupiah: "+String.format("%.2f", nilai));
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
    return nilai;
    }
}
