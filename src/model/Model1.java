package model;

import java.util.ArrayList;
import java.util.List;

public class Model1 {
    private String kodeKota;
    private String namaKota;
    private double beratButet;
    private double beratUcok;
    private double totalBerat;
    private double totalOngkosKirim;
    private List<String> daftarPromo;

    // Constructor untuk inisialisasi awal dan otomatis menghitung semua kebutuhan
    public Model1(String kodeKota, double beratButet) {
        this.kodeKota = kodeKota.toUpperCase();
        this.beratButet = beratButet;
        this.beratUcok = 1.5 * beratButet; // Ucok = 3/2 x berat Butet
        this.totalBerat = this.beratButet + this.beratUcok;
        this.daftarPromo = new ArrayList<>();
        kalkulasiPengiriman();
    }

    // Method private untuk internal class processing (Encapsulation)
    private void kalkulasiPengiriman() {
        double tarifPerKg = 0;
        boolean isLuarPulau = false;

        // Menentukan tarif dan status pulau berdasarkan kode
        switch (this.kodeKota) {
            case "MDN":
                this.namaKota = "Medan";
                tarifPerKg = 8000;
                break;
            case "BLG":
                this.namaKota = "Balige";
                tarifPerKg = 5000;
                break;
            case "JKT":
                this.namaKota = "Jakarta";
                tarifPerKg = 12000;
                isLuarPulau = true;
                break;
            case "SBY":
                this.namaKota = "Surabaya";
                tarifPerKg = 13000;
                isLuarPulau = true;
                break;
            default:
                this.namaKota = "Kota Tidak Ditemukan";
                break;
        }

        // Perhitungan awal
        double ongkosAwal = this.totalBerat * tarifPerKg;

        // Pengecekan Promo 1: Diskon 10% jika berat > 10 kg
        if (this.totalBerat > 10) {
            ongkosAwal = ongkosAwal - (ongkosAwal * 0.10);
            this.daftarPromo.add("Diskon Ongkir 10%");
        }

        // Pengecekan Promo 2: Asuransi Gratis jika ke Luar Pulau
        if (isLuarPulau) {
            this.daftarPromo.add("Asuransi Gratis");
        }

        // Jika tidak ada promo sama sekali
        if (this.daftarPromo.isEmpty()) {
            this.daftarPromo.add("Tidak ada promo");
        }

        this.totalOngkosKirim = ongkosAwal;
    }

    // Method untuk menampilkan struk secara rapi
    public void cetakStruk() {
        System.out.println("========================================");
        System.out.println("      STRUK PEMBAYARAN DEL-EXPRESS      ");
        System.out.println("========================================");
        System.out.println("Kota Tujuan          : " + this.namaKota + " (" + this.kodeKota + ")");
        System.out.println("Berat Paket Butet    : " + this.beratButet + " kg");
        System.out.println("Berat Paket Ucok     : " + this.beratUcok + " kg");
        System.out.println("Total Berat          : " + this.totalBerat + " kg");
        System.out.println("Total Ongkos Kirim   : Rp " + this.totalOngkosKirim);
        System.out.println("Informasi Promo      : " + String.join(", ", this.daftarPromo));
        System.out.println("========================================\n");
    }
}