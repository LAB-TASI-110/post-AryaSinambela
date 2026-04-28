package driver;

import model.Model3;
import java.util.Scanner;

public class Driver3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Model3 sistemKafetaria = new Model3();

        System.out.println("Selamat Datang di Sistem Pemesanan Kafetaria IT Del");
        System.out.print("Masukkan Nama Pelanggan: ");
        String nama = scanner.nextLine();

        System.out.println("\nPilih Tipe Pesanan:");
        System.out.println("1. Makan di Tempat (Dine-In)");
        System.out.println("2. Jemput (Take-Away) - Diskon 10%");
        System.out.println("3. Pesan Antar (Delivery) - Ongkir Rp2.000 / Free Ongkir > Rp50.000");
        System.out.print("Pilihan (1/2/3): ");
        int tipe = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String meja = "";
        if (tipe == 1) {
            System.out.print("Masukkan Nomor Meja: ");
            meja = scanner.nextLine();
        }

        sistemKafetaria.tampilkanMenu();

        System.out.println("Silakan masukkan Kode Makanan/Minuman yang ingin dipesan.");
        System.out.println("Ketik 'SELESAI' jika sudah selesai memilih.\n");

        while (true) {
            System.out.print("Input Kode: ");
            String inputKode = scanner.nextLine();

            if (inputKode.equalsIgnoreCase("SELESAI")) {
                break;
            }

            boolean berhasil = sistemKafetaria.tambahPesanan(inputKode);
            if (berhasil) {
                System.out.println(" -> Ditambahkan ke keranjang.");
            } else {
                System.out.println(" -> ERROR: Kode tidak ditemukan! Silakan coba lagi.");
            }
        }

        System.out.print("\nPilih Metode Pembayaran (Tunai / Transfer): ");
        String pembayaran = scanner.nextLine();

        // Mengirim data ke model dan mencetak struk
        sistemKafetaria.setDataPelanggan(nama, meja, tipe, pembayaran);
        sistemKafetaria.cetakStruk();

        scanner.close();
    }
}