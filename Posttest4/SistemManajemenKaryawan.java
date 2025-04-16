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

    public String toString() {
        return "ID: " + id +
               "\nNama: " + nama +
               "\nPosisi: " + posisi +
               "\nGaji: " + gaji +
               "\n----------------------";
    }
}

class Teknisi extends Karyawan {
    public Teknisi(String nama, double gaji) {
        super(nama, "Teknisi", gaji);
    }

    @Override
    public String toString() {
        return super.toString() + "\nSpesialisasi: Listrik";
    }
}

class Manajer extends Karyawan {
    public Manajer(String nama, double gaji) {
        super(nama, "Manajer", gaji);
    }

    @Override
    public String toString() {
        return super.toString() + "\nDepartemen: Manajemen Proyek";
    }
}

public class SistemManajemenKaryawan {
    private static List<Karyawan> daftarKaryawan = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            clearScreen();
            System.out.println("\nSistem Pengelolaan Karyawan di ELECTRICAL WORKSHOP");
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
                    clearScreen();
                    System.out.println("==== Daftar Karyawan ====");
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

    // Method overloading: versi langsung dengan parameter
    private static void tambahKaryawan(String nama, String posisi, double gaji) {
        if (posisi.equalsIgnoreCase("Teknisi")) {
            daftarKaryawan.add(new Teknisi(nama, gaji));
        } else if (posisi.equalsIgnoreCase("Manajer")) {
            daftarKaryawan.add(new Manajer(nama, gaji));
        } else {
            System.out.println("Posisi tidak dikenal!");
        }
    }

    // Versi interaktif
    private static void tambahKaryawan() {
        clearScreen();
        System.out.println("==== Tambah Karyawan ====");
        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();

        System.out.println("Pilih Posisi:");
        System.out.println("1. Teknisi");
        System.out.println("2. Manajer");
        System.out.print("Pilihan: ");
        int pilihan = getValidIntInput();

        System.out.print("Masukkan Gaji: ");
        double gaji = getValidDoubleInput();

        if (pilihan == 1) {
            tambahKaryawan(nama, "Teknisi", gaji);  // Panggil overload
        } else {
            tambahKaryawan(nama, "Manajer", gaji);
        }

        System.out.println("\n==== Karyawan berhasil ditambahkan! ====");
        pressEnterToContinue();
    }

    private static void tampilkanKaryawan() {
        if (daftarKaryawan.isEmpty()) {
            System.out.println("\n==== Tidak ada karyawan ====");
        } else {
            for (Karyawan karyawan : daftarKaryawan) {
                System.out.println(karyawan);
            }
        }
    }

    private static void perbaruiKaryawan() {
        clearScreen();
        System.out.println("\n==== Ubah Data Karyawan ====");
        tampilkanKaryawan();
        System.out.print("Masukkan ID karyawan yang ingin diperbarui: ");
        int id = getValidIntInput();

        Karyawan karyawan = getKaryawanById(id);
        if (karyawan != null) {
            while (true) {
                clearScreen();
                System.out.println("Pilih data yang ingin diperbarui:");
                System.out.println("1. Nama");
                System.out.println("2. Posisi");
                System.out.println("3. Gaji");
                System.out.println("4. Kembali");
                System.out.print("Pilihan: ");
                int pilihan = getValidIntInput();

                switch (pilihan) {
                    case 1:
                        System.out.print("Masukkan Nama baru: ");
                        karyawan.setNama(scanner.nextLine());
                        System.out.println("\n==== Nama berhasil diperbarui ====");
                        pressEnterToContinue();
                        return;
                    case 2:
                        System.out.println("Pilih Posisi baru:");
                        System.out.println("1. Teknisi");
                        System.out.println("2. Manajer");
                        System.out.print("Pilihan: ");
                        int posisiPilihan = getValidIntInput();
                        if (posisiPilihan == 1) {
                            karyawan.setPosisi("Teknisi");
                        } else if (posisiPilihan == 2) {
                            karyawan.setPosisi("Manajer");
                        } else {
                            System.out.println("\n==== Pilihan tidak valid ====");
                            continue;
                        }
                        System.out.println("\n==== Posisi berhasil diperbarui ====");
                        pressEnterToContinue();
                        return;
                    case 3:
                        System.out.print("Masukkan Gaji baru: ");
                        karyawan.setGaji(getValidDoubleInput());
                        System.out.println("\n==== Gaji berhasil diperbarui ====");
                        pressEnterToContinue();
                        return;
                    case 4:
                        System.out.println("\n==== Kembali ====");
                        return;
                    default:
                        System.out.println("\n==== Pilihan tidak valid! Coba lagi ====");
                }
            }
        } else {
            System.out.println("\n==== Karyawan tidak ditemukan ====");
            pressEnterToContinue();
        }
    }

    private static void hapusKaryawan() {
        clearScreen();
        System.out.println("\n==== Hapus Data Karyawan ====");
        tampilkanKaryawan();
        System.out.print("Masukkan ID karyawan yang ingin dihapus: ");
        int id = getValidIntInput();

        Karyawan karyawan = getKaryawanById(id);
        if (karyawan != null) {
            daftarKaryawan.remove(karyawan);
            System.out.println("\n==== Karyawan berhasil dihapus ====");
        } else {
            System.out.println("\n==== Karyawan tidak ditemukan ====");
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
                System.out.println("\n==== Input harus berupa angka! Coba lagi ====");
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
                System.out.println("\n==== Input harus berupa angka! Coba lagi. ====");
                scanner.nextLine(); // Bersihkan input yang salah
            }
        }
    }

    private static void pressEnterToContinue() {
        System.out.print("Tekan Enter untuk melanjutkan...");
        scanner.nextLine();
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
