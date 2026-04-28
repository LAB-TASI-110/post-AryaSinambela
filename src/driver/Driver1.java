package driver;

import model.Model1;
import java.util.Scanner;

public class Driver1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Selamat Datang di Sistem Automasi Del-Express ===");
        System.out.println("Masukkan kode tujuan dan berat paket Butet secara bergantian.");
        System.out.println("Ketik 'END' pada kode kota untuk mengakhiri program.\n");

        while (true) {
            // Membaca kode kota
            String kodeInput = scanner.next();
            
            // Berhenti jika input adalah END
            if (kodeInput.equalsIgnoreCase("END")) {
                System.out.println("Terima kasih telah menggunakan layanan Del-Express!");
                break;
            }

            // Membaca input berat paket butet (x)
            double beratButet = scanner.nextDouble();

            // Memanggil Model1 untuk memproses dan mencetak struk
            Model1 transaksi = new Model1(kodeInput, beratButet);
            transaksi.cetakStruk();
        }
        
        scanner.close();
    }
}