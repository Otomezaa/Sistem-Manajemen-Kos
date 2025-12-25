import java.util.Scanner;

/*
 * Program : Sistem Manajemen Kos
 * Deskripsi :
 * Program yang berfungsi untuk membantu pemilik kos
 * dalam mengelola data kamar kos, termasuk menambah, melihat,
 * mengubah status, menghapus data, dan menghitung biaya sewa.
 */
public class ManajemenKos {

    // Konstanta maksimal jumlah kamar
    static final int MAX = 100;

    // Array untuk menyimpan data kamar kos
    static int[] idKamar = new int[MAX];
    static String[] namaKamar = new String[MAX];
    static int[] harga = new int[MAX];
    static String[] status = new String[MAX];
    static String[] fasilitasKamar = new String[MAX];

    // Menyimpan jumlah data kamar yang terisi
    static int n = 0;

    // Scanner untuk input dari pengguna
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;

        // Perulangan menu utama
        do {
            tampilMenu();
            pilihan = input.nextInt();
            input.nextLine(); // mengatasi sisa karakter newline pada buffer input

            switch (pilihan) {
                case 1:
                    tambahKamar();
                    break;
                case 2:
                    tampilKamar();
                    break;
                case 3:
                    updateStatus();
                    break;
                case 4:
                    hapusKamar();
                    break;
                case 5:
                    hitungBiayaSewa(); // fitur bonus
                    break;
                case 6:
                    System.out.println("Keluar dari sistem.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 6);
    }

    /*
     * Menampilkan menu utama sistem
     */
    static void tampilMenu() {
        System.out.println("\n=================================");
        System.out.println("       SISTEM MANAJEMEN KOS");
        System.out.println("=================================");
        System.out.println("1. Tambah Data Kamar");
        System.out.println("2. Lihat Data Kamar");
        System.out.println("3. Update Status Kamar");
        System.out.println("4. Hapus Data Kamar");
        System.out.println("5. Hitung Biaya Sewa");
        System.out.println("6. Keluar");
        System.out.print("Pilih menu [1-6]: ");
    }

    /*
     * Mengecek apakah ID kamar sudah terdaftar
     */
    static boolean isIdExist(int id) {
        for (int i = 0; i < n; i++) {
            if (idKamar[i] == id) {
                return true;
            }
        }
        return false;
    }

    /*
     * Menambahkan data kamar baru ke dalam array
     */
    static void tambahKamar() {
        if (n < MAX) {
            System.out.print("Masukkan ID Kamar       : ");
            int id = input.nextInt();
            input.nextLine();

            // Validasi ID kamar agar tidak duplikat
            if (isIdExist(id)) {
                System.out.println(">> ID kamar sudah terdaftar.");
                return;
            }

            idKamar[n] = id;

            System.out.print("Masukkan Nama Kamar     : ");
            namaKamar[n] = input.nextLine();

            System.out.print("Masukkan Harga (per bln): ");
            harga[n] = input.nextInt();
            input.nextLine();

            System.out.print("Masukkan Fasilitas      : ");
            fasilitasKamar[n] = input.nextLine();

            // Status awal kamar
            status[n] = "Tersedia";

            n++; // menambah jumlah data kamar
            System.out.println(">> Data kamar berhasil ditambahkan.");
        } else {
            System.out.println(">> Data kamar sudah penuh.");
        }
    }

    /*
     * Menampilkan seluruh data kamar kos dalam bentuk tabel
     */
    static void tampilKamar() {
        if (n == 0) {
            System.out.println("Belum ada data kamar.");
        } else {
            System.out.println("\n========================== DATA KAMAR KOS ==========================");
            System.out.printf("%-6s %-15s %-12s %-35s %-12s%n",
                    "ID", "Nama", "Harga", "Fasilitas", "Status");
            System.out.println("-------------------------------------------------------------------");

            for (int i = 0; i < n; i++) {
                System.out.printf("%-6d %-15s %-12d %-35s %-12s%n",
                        idKamar[i],
                        namaKamar[i],
                        harga[i],
                        fasilitasKamar[i],
                        status[i]);
            }

            System.out.println("===================================================================");
        }
    }

    /*
     * Mengubah status kamar berdasarkan ID kamar
     */
    static void updateStatus() {
        System.out.print("Masukkan ID Kamar yang ingin diupdate: ");
        int cari = input.nextInt();
        input.nextLine();

        boolean ketemu = false;

        for (int i = 0; i < n; i++) {
            if (idKamar[i] == cari) {
                System.out.print("Masukkan status baru (Tersedia/Dipesan/Terisi): ");
                status[i] = input.nextLine();
                System.out.println(">> Status kamar berhasil diupdate.");
                ketemu = true;
                break;
            }
        }

        if (!ketemu) {
            System.out.println(">> ID kamar tidak ditemukan.");
        }
    }

    /*
     * Menghapus data kamar berdasarkan ID kamar
     */
    static void hapusKamar() {
        System.out.print("Masukkan ID Kamar yang ingin dihapus: ");
        int hapus = input.nextInt();
        input.nextLine();

        boolean found = false;

        for (int i = 0; i < n; i++) {
            if (idKamar[i] == hapus) {

                // Menggeser seluruh data setelah index i
                for (int j = i; j < n - 1; j++) {
                    idKamar[j] = idKamar[j + 1];
                    namaKamar[j] = namaKamar[j + 1];
                    harga[j] = harga[j + 1];
                    fasilitasKamar[j] = fasilitasKamar[j + 1];
                    status[j] = status[j + 1];
                }

                n--; // mengurangi jumlah data kamar
                found = true;
                System.out.println(">> Data kamar berhasil dihapus.");
                break;
            }
        }

        if (!found) {
            System.out.println(">> ID kamar tidak ditemukan.");
        }
    }

    /*
     * Menghitung total biaya sewa berdasarkan durasi menginap
     */
    static void hitungBiayaSewa() {
        System.out.print("Masukkan ID Kamar   : ");
        int cari = input.nextInt();

        System.out.print("Masukkan durasi (bulan): ");
        int durasi = input.nextInt();

        boolean ketemu = false;

        for (int i = 0; i < n; i++) {
            if (idKamar[i] == cari) {
                int total = harga[i] * durasi;

                System.out.println("---------------------------------");
                System.out.printf("Total biaya sewa : Rp %,d%n", total);
                System.out.println("---------------------------------");

                ketemu = true;
                break;
            }
        }

        if (!ketemu) {
            System.out.println(">> ID kamar tidak ditemukan.");
        }
    }
}
