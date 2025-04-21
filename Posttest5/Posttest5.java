import java.util.ArrayList;
import java.util.Scanner;

abstract class Karyawan {
    private static int idCounter = 1;
    private int id;
    protected final String nama; // Final variable
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

    public final int getId() { // Final method
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

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public void setGaji(double gaji) {
        this.gaji = gaji;
    }

    // Abstract method yang wajib diimplementasikan oleh subclass
    public abstract String getDetailTambahan();

    public String toString() {
        return "ID: " + id +
               "\nNama: " + nama +
               "\nPosisi: " + posisi +
               "\nGaji: " + gaji +
               "\n" + getDetailTambahan() +
               "\n-------------------------";
    }
}

class Teknisi extends Karyawan {
    public Teknisi(String nama, double gaji) {
        super(nama, "Teknisi", gaji);
    }

    @Override
    public String getDetailTambahan() {
        return "Spesialisasi: Listrik";
    }
}

class Manajer extends Karyawan {
    public Manajer(String nama, double gaji) {
        super(nama, "Manajer", gaji);
    }

    @Override
    public String getDetailTambahan() {
        return "Departemen: Manajemen Proyek";
    }
}

final class SistemManajemenKaryawan {
    private ArrayList<Karyawan> daftarKaryawan = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void tampilkanMenu() {
        int pilihan;

        do {
            System.out.println("===== Sistem Manajemen Karyawan =====");
            System.out.println("1. Tambah Karyawan");
            System.out.println("2. Tampilkan Semua Karyawan");
            System.out.println("3. Perbarui Data Karyawan");
            System.out.println("4. Hapus Karyawan");
            System.out.println("5. Keluar");
            System.out.print("Pilihan Anda: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    tambahKaryawan();
                    break;
                case 2:
                    tampilkanKaryawan();
                    break;
                case 3:
                    perbaruiKaryawan();
                    break;
                case 4:
                    hapusKaryawan();
                    break;
                case 5:
                    System.out.println("Keluar dari program...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 5);
    }

    private void tambahKaryawan() {
        System.out.print("Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Gaji: ");
        double gaji = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Pilih Jabatan:");
        System.out.println("1. Teknisi");
        System.out.println("2. Manajer");
        System.out.print("Pilihan: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();

        Karyawan karyawan;
        if (pilihan == 1) {
            karyawan = new Teknisi(nama, gaji);
        } else if (pilihan == 2) {
            karyawan = new Manajer(nama, gaji);
        } else {
            System.out.println("Pilihan tidak valid.");
            return;
        }

        daftarKaryawan.add(karyawan);
        System.out.println("Karyawan berhasil ditambahkan.");
    }

    private void tampilkanKaryawan() {
        if (daftarKaryawan.isEmpty()) {
            System.out.println("Tidak ada data karyawan.");
        } else {
            for (Karyawan karyawan : daftarKaryawan) {
                System.out.println(karyawan);
            }
        }
    }

    private void perbaruiKaryawan() {
        System.out.print("Masukkan ID karyawan yang ingin diperbarui: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Karyawan karyawan : daftarKaryawan) {
            if (karyawan.getId() == id) {
                System.out.print("Posisi Baru: ");
                String posisi = scanner.nextLine();
                karyawan.setPosisi(posisi);

                System.out.print("Gaji Baru: ");
                double gaji = scanner.nextDouble();
                scanner.nextLine();

                karyawan.setGaji(gaji);

                System.out.println("Data berhasil diperbarui.");
                return;
            }
        }

        System.out.println("Karyawan dengan ID tersebut tidak ditemukan.");
    }

    private void hapusKaryawan() {
        System.out.print("Masukkan ID karyawan yang ingin dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Karyawan karyawan : daftarKaryawan) {
            if (karyawan.getId() == id) {
                daftarKaryawan.remove(karyawan);
                System.out.println("Karyawan berhasil dihapus.");
                return;
            }
        }

        System.out.println("Karyawan dengan ID tersebut tidak ditemukan.");
    }
}

public class Posttest5 {
    public static void main(String[] args) {
        SistemManajemenKaryawan sistem = new SistemManajemenKaryawan();
        sistem.tampilkanMenu();
    }
}
