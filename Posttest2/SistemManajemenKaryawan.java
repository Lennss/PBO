import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Karyawan {
    private static int idCounter = 1;
    private int id;
    private String nama;
    private String posisi;
    private double gaji;

    public Karyawan(String nama, String posisi, double gaji) {
        this.id = generateId();
        this.nama = nama;
        this.posisi = posisi;
        this.gaji = gaji;
    }

    protected static int generateId() {
        return idCounter++;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getPosisi() {
        return posisi;
    }

    public double getGaji() {
        return gaji;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public void setGaji(double gaji) {
        this.gaji = gaji;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nNama: " + nama + "\nPosisi: " + posisi + "\nGaji: " + gaji + "\n----------------------";
    }
}

public class SistemManajemenKaryawan {
    private static List<Karyawan> daftarKaryawan = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            clearScreen();
            System.out.println("\nSistem Pengelolaan Karyawan di Bengkel Elektrik");
            System.out.println("1. Tambah Karyawan");
            System.out.println("2. Tampilkan Karyawan");
            System.out.println("3. Perbarui Karyawan");
            System.out.println("4. Hapus Karyawan");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = getValidIntInput();

            switch (pilihan) {
                case 1:
                    tambahKaryawan();
                    break;
                case 2:
                    tampilkanKaryawan();
                    pressEnterToContinue();
                    break;
                case 3:
                    perbaruiKaryawan();
                    break;
                case 4:
                    hapusKaryawan();
                    break;
                case 5:
                    System.out.println("Keluar dari program...");
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
                    pressEnterToContinue();
            }
        }
    }

    private static void tambahKaryawan() {
        clearScreen();
        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();

        System.out.println("Pilih Posisi:");
        System.out.println("1. Teknisi");
        System.out.println("2. Supervisor");
        System.out.println("3. Manajer");
        System.out.print("Pilihan: ");
        int pilihan = getValidIntInput();

        String posisi = (pilihan == 1) ? "Teknisi" : (pilihan == 2) ? "Supervisor" : "Manajer";
        
        System.out.print("Masukkan Gaji: ");
        double gaji = getValidDoubleInput();

        daftarKaryawan.add(new Karyawan(nama, posisi, gaji));
        System.out.println("Karyawan berhasil ditambahkan!");

        pressEnterToContinue();
    }

    private static void tampilkanKaryawan() {
        clearScreen();
        if (daftarKaryawan.isEmpty()) {
            System.out.println("Tidak ada karyawan.");
        } else {
            for (Karyawan karyawan : daftarKaryawan) {
                System.out.println(karyawan);
            }
        }
    }

    private static void perbaruiKaryawan() {
        clearScreen();
        tampilkanKaryawan();
        System.out.print("Masukkan ID karyawan yang ingin diperbarui: ");
        int id = getValidIntInput();

        Karyawan karyawan = getKaryawanById(id);
        if (karyawan != null) {
            System.out.println("Pilih bagian yang ingin diperbarui:");
            System.out.println("1. Nama");
            System.out.println("2. Posisi");
            System.out.println("3. Gaji");
            System.out.print("Pilihan: ");
            int pilihan = getValidIntInput();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan Nama baru: ");
                    karyawan.setNama(scanner.nextLine());
                    break;
                case 2:
                    System.out.println("Pilih Posisi baru:");
                    System.out.println("1. Teknisi");
                    System.out.println("2. Supervisor");
                    System.out.println("3. Manajer");
                    System.out.print("Pilihan: ");
                    int posPilihan = getValidIntInput();
                    karyawan.setPosisi(posPilihan == 1 ? "Teknisi" : posPilihan == 2 ? "Supervisor" : "Manajer");
                    break;
                case 3:
                    System.out.print("Masukkan Gaji baru: ");
                    karyawan.setGaji(getValidDoubleInput());
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
            System.out.println("Data karyawan diperbarui!");
        } else {
            System.out.println("Karyawan tidak ditemukan.");
        }
        pressEnterToContinue();
    }

    private static void hapusKaryawan() {
        clearScreen();
        tampilkanKaryawan();
        System.out.print("Masukkan ID karyawan yang ingin dihapus: ");
        int id = getValidIntInput();

        Karyawan karyawan = getKaryawanById(id);
        if (karyawan != null) {
            daftarKaryawan.remove(karyawan);
            System.out.println("Karyawan berhasil dihapus!");
        } else {
            System.out.println("Karyawan tidak ditemukan.");
        }
        pressEnterToContinue();
    }

    private static Karyawan getKaryawanById(int id) {
        for (Karyawan karyawan : daftarKaryawan) {
            if (karyawan.getId() == id) {
                return karyawan;
            }
        }
        return null;
    }

    private static int getValidIntInput() {
        while (true) {
            try {
                int input = scanner.nextInt();
                scanner.nextLine(); // Bersihkan buffer
                return input;
            } catch (Exception e) {
                System.out.println("Input harus berupa angka! Coba lagi.");
                scanner.nextLine(); // Bersihkan input yang salah
            }
        }
    }

    private static double getValidDoubleInput() {
        while (true) {
            try {
                double input = scanner.nextDouble();
                scanner.nextLine(); // Bersihkan buffer
                return input;
            } catch (Exception e) {
                System.out.println("Input harus berupa angka! Coba lagi.");
                scanner.nextLine(); // Bersihkan input yang salah
            }
        }
    }

    private static void pressEnterToContinue() {
        System.out.print("Tekan Enter untuk melanjutkan...");
        scanner.nextLine();
    }

    private static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Gagal membersihkan layar.");
        }
    }
}
