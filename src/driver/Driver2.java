package driver;

import model.Model2;
import java.util.Scanner;

public class Driver2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Model2 sistemGudang = new Model2();

        System.out.println("================================================");
        System.out.println("   SISTEM PENDATAAN STOK BARANG DI RAK GUDANG   ");
        System.out.println("================================================");

        // 1. Input Jumlah Total Data (N)
        System.out.print("Masukkan Jumlah Total Data (N) yang akan diinput: ");
        int n = scanner.nextInt();

        // 2. Input Deret Stok dan Kode Kategori
        System.out.println("\nMasukkan data dengan format: [Jumlah Stok] [Kode Kategori]");
        System.out.println("Contoh: 22 SRG (untuk 22 pcs Seragam)");
        
        for (int i = 0; i < n; i++) {
            System.out.print("Data ke-" + (i + 1) + " : ");
            int jumlahStok = scanner.nextInt();
            String kodeKategori = scanner.next();
            
            // Menyimpan data ke dalam sistem (Model)
            sistemGudang.tambahStok(kodeKategori, jumlahStok);
        }

        System.out.println("\n------------------------------------------------");
        
        // 3. Menentukan total stok hanya untuk kategori yang diminta
        System.out.print("Masukkan Kode Kategori yang ingin dicek totalnya: ");
        String kategoriPencarian = scanner.next();

        int totalStokDitemukan = sistemGudang.hitungTotalKategori(kategoriPencarian);

        System.out.println("\n================ HASIL PENCARIAN ===============");
        System.out.println("Total stok untuk kategori [" + kategoriPencarian.toUpperCase() + "] adalah : " + totalStokDitemukan + " pcs");
        System.out.println("================================================");

        scanner.close();
    }
}