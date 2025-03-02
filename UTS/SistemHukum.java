import java.util.Scanner;
import java.util.ArrayList;

class PelanggaranHukum {
    private int id;
    private String namaPelaku;
    private String jenisPelanggaran;
    private String sanksi;
    private double nilaiSanksi;

    // Konstruktor untuk menginisialisasi objek PelanggaranHukum
    public PelanggaranHukum(int id, String namaPelaku, String jenisPelanggaran, String sanksi, double nilaiSanksi) {
        this.id = id;
        this.namaPelaku = namaPelaku;
        this.jenisPelanggaran = jenisPelanggaran;
        this.sanksi = sanksi;
        this.nilaiSanksi = nilaiSanksi;
    }

    // Getter dan Setter untuk mengakses dan mengubah atribut privat
    public int getId() { return id; }
    public void setNamaPelaku(String namaPelaku) { this.namaPelaku = namaPelaku; }
    public String getNamaPelaku() { return namaPelaku; }
    public void setJenisPelanggaran(String jenisPelanggaran) { this.jenisPelanggaran = jenisPelanggaran; }
    public String getJenisPelanggaran() { return jenisPelanggaran; }
    public void setSanksi(String sanksi) { this.sanksi = sanksi; }
    public String getSanksi() { return sanksi; }
    public void setNilaiSanksi(double nilaiSanksi) { this.nilaiSanksi = nilaiSanksi; }
    public double getNilaiSanksi() { return nilaiSanksi; }

    // Method untuk menampilkan detail pelanggaran dalam format rapi
    public void tampilkanDetailPelanggaran() {
        System.out.println("\nDetail Pelanggaran Hukum:");
        System.out.println("ID: " + id);
        System.out.println("Pelaku: " + namaPelaku);
        System.out.println("Jenis Pelanggaran: " + jenisPelanggaran);
        if (sanksi.equalsIgnoreCase("denda")) {
            System.out.println("Sanksi: Denda Rp " + String.format("%,.2f", nilaiSanksi));
        } else {
            System.out.println("Sanksi: Penjara " + nilaiSanksi + " tahun");
        }
        System.out.println("------------------------");
    }
}

public class SistemHukum {
    // Method untuk membersihkan layar (menggunakan kode ANSI)
    private static void bersihkanLayar() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Method untuk menampilkan semua data pelanggaran yang ada di ArrayList
    private static void tampilkanSemuaData(ArrayList<PelanggaranHukum> daftarPelanggaran) {
        if (daftarPelanggaran.isEmpty()) {
            System.out.println("Belum ada data pelanggaran.");
        } else {
            for (PelanggaranHukum p : daftarPelanggaran) {
                p.tampilkanDetailPelanggaran();
            }
        }
    }

    // Method untuk memvalidasi input integer dalam rentang tertentu
    private static int getValidIntInput(Scanner scanner, String prompt, int min, int max) {
        int input;
        while (true) { // Looping hingga input valid
            System.out.print(prompt);
            try {
                input = scanner.nextInt();
                if (input >= min && input <= max) { // Cek apakah input dalam rentang
                    return input;
                } else {
                    System.out.println("Input harus antara " + min + " dan " + max + ". Coba lagi.");
                }
            } catch (Exception e) {
                System.out.println("Input tidak valid. Harap masukkan angka.");
                scanner.nextLine(); // Bersihkan buffer jika input salah
            }
        }
    }

    // Method untuk memvalidasi input double (harus non-negatif)
    private static double getValidDoubleInput(Scanner scanner, String prompt) {
        double input;
        while (true) { // Looping hingga input valid
            System.out.print(prompt);
            try {
                input = scanner.nextDouble();
                if (input >= 0) { // Pastikan input tidak negatif
                    return input;
                } else {
                    System.out.println("Input tidak boleh negatif. Coba lagi.");
                }
            } catch (Exception e) {
                System.out.println("Input tidak valid. Harap masukkan angka.");
                scanner.nextLine(); // Bersihkan buffer jika input salah
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<PelanggaranHukum> daftarPelanggaran = new ArrayList<>(); // Menyimpan semua data pelanggaran
        int idCounter = 1; // Penghitung ID otomatis untuk setiap pelanggaran
        boolean running = true; // Kontrol loop utama

        // Loop utama untuk menjalankan menu
        while (running) {
            bersihkanLayar();
            System.out.println("\nSistem Pengelolaan Pelanggaran Hukum");
            System.out.println("1. Tambah Data Pelanggaran");
            System.out.println("2. Tampilkan Semua Data Pelanggaran");
            System.out.println("3. Ubah Data Pelanggaran");
            System.out.println("4. Hapus Data Pelanggaran");
            System.out.println("5. Keluar");
            int pilihan = getValidIntInput(scanner, "Pilih menu (1-5): ", 1, 5); // Validasi pilihan menu
            scanner.nextLine();

            switch (pilihan) {
                case 1: // Tambah Data Pelanggaran
                    bersihkanLayar();
                    System.out.print("Masukkan nama pelaku: ");
                    String nama = scanner.nextLine();

                    System.out.println("Pilih jenis pelanggaran:");
                    System.out.println("1. Pencurian");
                    System.out.println("2. Penganiayaan");
                    System.out.println("3. Penipuan");
                    int pilihanPelanggaran = getValidIntInput(scanner, "Masukkan pilihan (1-3): ", 1, 3);
                    String jenis;
                    switch (pilihanPelanggaran) {
                        case 1: jenis = "Pencurian"; break;
                        case 2: jenis = "Penganiayaan"; break;
                        case 3: jenis = "Penipuan"; break;
                        default: jenis = "Tidak Diketahui"; break; // Tidak akan tercapai karena validasi
                    }

                    System.out.println("Pilih sanksi:");
                    System.out.println("1. Denda");
                    System.out.println("2. Penjara");
                    int pilihanSanksi = getValidIntInput(scanner, "Masukkan pilihan (1-2): ", 1, 2);
                    String sanksi = (pilihanSanksi == 1) ? "Denda" : "Penjara"; // Operator ternary untuk sanksi
                    double nilaiSanksi;

                    if (sanksi.equalsIgnoreCase("Denda")) {
                        nilaiSanksi = getValidDoubleInput(scanner, "Masukkan jumlah denda (Rp): ");
                    } else {
                        nilaiSanksi = getValidDoubleInput(scanner, "Masukkan lama penjara (tahun): ");
                    }
                    scanner.nextLine();

                    // Tambahkan data baru ke ArrayList
                    daftarPelanggaran.add(new PelanggaranHukum(idCounter++, nama, jenis, sanksi, nilaiSanksi));
                    System.out.println("Data pelanggaran berhasil ditambahkan. Tekan Enter untuk kembali...");
                    scanner.nextLine();
                    break;

                case 2: // Tampilkan Semua Data
                    bersihkanLayar();
                    tampilkanSemuaData(daftarPelanggaran);
                    System.out.println("Tekan Enter untuk kembali...");
                    scanner.nextLine();
                    break;

                case 3: // Ubah Data Pelanggaran
                    bersihkanLayar();
                    if (daftarPelanggaran.isEmpty()) {
                        System.out.println("Belum ada data pelanggaran.");
                    } else {
                        tampilkanSemuaData(daftarPelanggaran); // Tampilkan data agar user bisa pilih ID
                        int idUbah = getValidIntInput(scanner, "Masukkan ID pelanggaran yang akan diubah: ", 1, idCounter - 1);
                        scanner.nextLine();

                        boolean found = false;
                        for (PelanggaranHukum p : daftarPelanggaran) {
                            if (p.getId() == idUbah) { // Cek ID yang cocok
                                found = true;
                                System.out.print("Masukkan nama pelaku baru: ");
                                String namaBaru = scanner.nextLine();

                                System.out.println("Pilih jenis pelanggaran baru:");
                                System.out.println("1. Pencurian");
                                System.out.println("2. Penganiayaan");
                                System.out.println("3. Penipuan");
                                int jenisBaruPilihan = getValidIntInput(scanner, "Masukkan pilihan (1-3): ", 1, 3);
                                String jenisBaru;
                                switch (jenisBaruPilihan) {
                                    case 1: jenisBaru = "Pencurian"; break;
                                    case 2: jenisBaru = "Penganiayaan"; break;
                                    case 3: jenisBaru = "Penipuan"; break;
                                    default: jenisBaru = "Tidak Diketahui"; break;
                                }

                                System.out.println("Pilih sanksi baru:");
                                System.out.println("1. Denda");
                                System.out.println("2. Penjara");
                                int sanksiBaru = getValidIntInput(scanner, "Masukkan pilihan (1-2): ", 1, 2);
                                String sanksiPilihan = (sanksiBaru == 1) ? "Denda" : "Penjara";
                                double nilaiSanksiBaru;

                                if (sanksiPilihan.equalsIgnoreCase("Denda")) {
                                    nilaiSanksiBaru = getValidDoubleInput(scanner, "Masukkan jumlah denda baru (Rp): ");
                                } else {
                                    nilaiSanksiBaru = getValidDoubleInput(scanner, "Masukkan lama penjara baru (tahun): ");
                                }
                                scanner.nextLine();

                                // Update data menggunakan setter
                                p.setNamaPelaku(namaBaru);
                                p.setJenisPelanggaran(jenisBaru);
                                p.setSanksi(sanksiPilihan);
                                p.setNilaiSanksi(nilaiSanksiBaru);
                                System.out.println("Data pelanggaran berhasil diubah.");
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("ID tidak ditemukan.");
                        }
                    }
                    System.out.println("Tekan Enter untuk kembali...");
                    scanner.nextLine();
                    break;

                case 4: // Hapus Data Pelanggaran
                    bersihkanLayar();
                    if (daftarPelanggaran.isEmpty()) {
                        System.out.println("Belum ada data pelanggaran.");
                    } else {
                        tampilkanSemuaData(daftarPelanggaran); // Tampilkan data agar user bisa pilih ID
                        int idHapus = getValidIntInput(scanner, "Masukkan ID pelanggaran yang akan dihapus: ", 1, idCounter - 1);
                        scanner.nextLine();

                        boolean removed = false;
                        for (int i = 0; i < daftarPelanggaran.size(); i++) {
                            if (daftarPelanggaran.get(i).getId() == idHapus) { // Cek ID yang cocok
                                daftarPelanggaran.remove(i); // Hapus data dari ArrayList
                                System.out.println("Data pelanggaran berhasil dihapus.");
                                removed = true;
                                break;
                            }
                        }
                        if (!removed) {
                            System.out.println("ID tidak ditemukan.");
                        }
                    }
                    System.out.println("Tekan Enter untuk kembali...");
                    scanner.nextLine();
                    break;

                case 5: // Keluar dari Program
                    running = false; // Hentikan loop utama
                    bersihkanLayar();
                    System.out.println("Terima kasih telah menggunakan Sistem Pengelolaan Pelanggaran Hukum.");
                    break;
            }
        }
        scanner.close(); // Tutup scanner untuk menghindari resource leak
    }
}