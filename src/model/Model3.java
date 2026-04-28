package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model3 {
    // Nested class untuk merepresentasikan entitas Menu
    public static class ItemMenu {
        public String kode;
        public String nama;
        public double harga;
        public boolean isPaketPromo;

        public ItemMenu(String kode, String nama, double harga, boolean isPaketPromo) {
            this.kode = kode;
            this.nama = nama;
            this.harga = harga;
            this.isPaketPromo = isPaketPromo;
        }
    }

    private Map<String, ItemMenu> daftarMenu;
    private List<ItemMenu> keranjangPesanan;
    
    private String namaPelanggan;
    private String noMeja;
    private int tipePesanan; // 1: Dine-in, 2: Take-away, 3: Delivery
    private String metodePembayaran;

    public Model3() {
        daftarMenu = new HashMap<>();
        keranjangPesanan = new ArrayList<>();
        inisialisasiMenu();
    }

    // Mengisi database menu
    private void inisialisasiMenu() {
        // Makanan & Minuman Reguler
        daftarMenu.put("BKW-001", new ItemMenu("BKW-001", "Bakwan", 10000, false));
        daftarMenu.put("TMP-002", new ItemMenu("TMP-002", "Tempe Goreng", 10000, false));
        daftarMenu.put("THU-003", new ItemMenu("THU-003", "Tahu Goreng", 10000, false));
        daftarMenu.put("PSK-004", new ItemMenu("PSK-004", "Piscok", 10000, false));
        daftarMenu.put("GBN-005", new ItemMenu("GBN-005", "Gabin", 10000, false));
        daftarMenu.put("BRG-006", new ItemMenu("BRG-006", "Burger", 10000, false));
        daftarMenu.put("KPC-007", new ItemMenu("KPC-007", "Kopi Capucino", 10000, false));
        daftarMenu.put("SDB-008", new ItemMenu("SDB-008", "Soda Bahagia", 10000, false));
        daftarMenu.put("MIG-009", new ItemMenu("MIG-009", "Mie Goreng", 15000, false));
        
        // Menu Tambahan Ekstra
        daftarMenu.put("NAS-010", new ItemMenu("NAS-010", "Nasi Goreng Spesial", 20000, false));
        daftarMenu.put("ESM-011", new ItemMenu("ESM-011", "Es Teh Manis", 5000, false));

        // Paket Promo
        daftarMenu.put("PKT-HEM", new ItemMenu("PKT-HEM", "[PROMO HEMAT] Nasi Goreng + Es Teh", 22000, true));
        daftarMenu.put("PKT-ULT", new ItemMenu("PKT-ULT", "[PROMO ULTAH] Burger + Soda + Kentang", 25000, true));
    }

    public void tampilkanMenu() {
        System.out.println("\n=============================================");
        System.out.println("            MENU KAFETARIA IT DEL            ");
        System.out.println("=============================================");
        for (ItemMenu item : daftarMenu.values()) {
            System.out.printf("%-10s | %-30s | Rp %,.0f\n", item.kode, item.nama, item.harga);
        }
        System.out.println("=============================================\n");
    }

    // Setters untuk data pelanggan
    public void setDataPelanggan(String nama, String meja, int tipe, String pembayaran) {
        this.namaPelanggan = nama;
        this.noMeja = meja;
        this.tipePesanan = tipe;
        this.metodePembayaran = pembayaran;
    }

    // Menambah pesanan berdasarkan kode
    public boolean tambahPesanan(String kode) {
        kode = kode.toUpperCase();
        if (daftarMenu.containsKey(kode)) {
            keranjangPesanan.add(daftarMenu.get(kode));
            return true;
        }
        return false;
    }

    // Mencetak struk dan melakukan kalkulasi akhir
    public void cetakStruk() {
        double subTotal = 0;
        boolean adaPaketPromo = false;

        System.out.println("\n\n=============================================");
        System.out.println("               STRUK PEMBELIAN               ");
        System.out.println("=============================================");
        System.out.println("NAMA PELANGGAN : " + namaPelanggan.toUpperCase());
        
        String keteranganTipe = (tipePesanan == 1) ? "Dine-In" : (tipePesanan == 2) ? "Take-Away" : "Delivery";
        System.out.println("TIPE PESANAN   : " + keteranganTipe);
        
        if (tipePesanan == 1) { // Hanya tampilkan meja jika Dine-in
            System.out.println("NO MEJA        : " + (noMeja.isEmpty() ? "-" : noMeja));
        }
        System.out.println("---------------------------------------------");

        // List Item
        for (ItemMenu item : keranjangPesanan) {
            System.out.printf("%-10s %-20s Rp %,.0f\n", item.kode, item.nama, item.harga);
            subTotal += item.harga;
            if (item.isPaketPromo) adaPaketPromo = true;
        }
        System.out.println("--------------------------------------------- +");
        System.out.printf("%-31s Rp %,.0f\n", "SUBTOTAL", subTotal);

        double totalAkhir = subTotal;
        double diskon = 0;
        double ongkir = 0;

        // Logika Diskon (Take Away atau ada Paket Promo diskon 10%)
        if (tipePesanan == 2 || adaPaketPromo) {
            diskon = subTotal * 0.10;
            totalAkhir -= diskon;
            System.out.printf("%-31s Rp -%,.0f\n", "DISKON (10%)", diskon);
        }

        // Logika Ongkos Kirim (Delivery)
        if (tipePesanan == 3) {
            if (subTotal > 50000) {
                System.out.printf("%-31s Rp %,.0f\n", "ONGKIR (Promo Free Ongkir)", 0.0);
            } else {
                ongkir = 2000;
                totalAkhir += ongkir;
                System.out.printf("%-31s Rp %,.0f\n", "ONGKIR", ongkir);
            }
        }

        System.out.println("---------------------------------------------");
        System.out.printf("%-31s Rp %,.0f\n", "TOTAL BAYAR", totalAkhir);
        System.out.println("METODE PEMBAYARAN : " + metodePembayaran.toUpperCase());
        System.out.println("=============================================");
    }
}