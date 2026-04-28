package model;

import java.util.HashMap;
import java.util.Map;

public class Model2 {
    // Menggunakan HashMap untuk mengelompokkan dan menyimpan total stok per kategori
    private Map<String, Integer> rakPenyimpanan;

    public Model2() {
        this.rakPenyimpanan = new HashMap<>();
    }

    /**
     * Method untuk memasukkan barang ke dalam rak.
     * Jika kategori sudah ada, jumlahnya akan ditambahkan.
     * Jika belum ada, kategori baru akan dibuat.
     */
    public void tambahStok(String kodeKategori, int jumlah) {
        kodeKategori = kodeKategori.toUpperCase(); // Standarisasi kode menjadi huruf kapital
        
        // getOrDefault mengambil nilai saat ini, jika kosong maka kembalikan 0
        int stokSaatIni = rakPenyimpanan.getOrDefault(kodeKategori, 0);
        rakPenyimpanan.put(kodeKategori, stokSaatIni + jumlah);
    }

    /**
     * Method untuk mengambil total stok berdasarkan kode kategori yang diminta.
     */
    public int hitungTotalKategori(String kodeKategori) {
        kodeKategori = kodeKategori.toUpperCase();
        return rakPenyimpanan.getOrDefault(kodeKategori, 0);
    }

    /**
     * Method opsional untuk mengecek semua data yang ada di rak (transparansi data)
     */
    public void tampilkanSemuaStok() {
        System.out.println("\n--- Isi Rak Penyimpanan Saat Ini ---");
        if (rakPenyimpanan.isEmpty()) {
            System.out.println("Rak masih kosong.");
            return;
        }
        for (Map.Entry<String, Integer> barang : rakPenyimpanan.entrySet()) {
            System.out.println("Kategori " + barang.getKey() + " : " + barang.getValue() + " pcs");
        }
    }
}