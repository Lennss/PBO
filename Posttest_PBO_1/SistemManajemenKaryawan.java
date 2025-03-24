import java.util.ArrayList;
import java.util.Scanner;

class Karyawan {
    private static int idOtomatis = 1;
    private int id;
    private String nama;
    private String posisi;
    private double gaji;

    public Karyawan(String nama, String posisi, double gaji) {
        this.id = idOtomatis++;
        this.nama = nama;
        this.posisi = posisi;
        this.gaji = gaji;
    }

    public int getId() {
        return id;
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
    private static ArrayList<Karyawan> daftarKaryawan = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            bersihkanLayar();
            System.out.println("\nSistem Pengelolaan Karyawan di Bengkel Elektrikal");
            System.out.println("1. Tambah Karyawan");
            System.out.println("2. Tampilkan Karyawan");
            System.out.println("3. Perbarui Karyawan");
            System.out.println("4. Hapus Karyawan");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    tambahKaryawan();
                    break;
                case 2:
                    tampilkanKaryawan();
                    tekanEnterUntukLanjut();
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
            }
        }
    }

    private static void tambahKaryawan() {
        bersihkanLayar();
        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();

        System.out.println("Pilih Posisi:");
        System.out.println("1. Teknisi");
        System.out.println("2. Supervisor");
        System.out.println("3. Manajer");
        System.out.print("Pilihan: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();

        String posisi = (pilihan == 1) ? "Teknisi" : (pilihan == 2) ? "Supervisor" : "Manajer";

        System.out.print("Masukkan Gaji: ");
        double gaji = scanner.nextDouble();
        scanner.nextLine(); 

        daftarKaryawan.add(new Karyawan(nama, posisi, gaji));
        System.out.println("Karyawan berhasil ditambahkan!");

        tekanEnterUntukLanjut();
    }

    private static void tampilkanKaryawan() {
        bersihkanLayar();
        if (daftarKaryawan.isEmpty()) {
            System.out.println("Tidak ada karyawan.");
        } else {
            for (Karyawan karyawan : daftarKaryawan) {
                System.out.println(karyawan);
            }
        }
    }

    private static void perbaruiKaryawan() {
        bersihkanLayar();
        tampilkanKaryawan();
        System.out.print("Masukkan ID karyawan yang ingin diperbarui: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Karyawan karyawan : daftarKaryawan) {
            if (karyawan.getId() == id) {
                System.out.println("Pilih bagian yang ingin diperbarui:");
                System.out.println("1. Nama");
                System.out.println("2. Posisi");
                System.out.println("3. Gaji");
                System.out.print("Pilihan: ");
                int opsi = scanner.nextInt();
                scanner.nextLine();

                switch (opsi) {
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
                        int posisiBaru = scanner.nextInt();
                        scanner.nextLine();
                        karyawan.setPosisi(posisiBaru == 1 ? "Teknisi" : posisiBaru == 2 ? "Supervisor" : "Manajer");
                        break;
                    case 3:
                        System.out.print("Masukkan Gaji baru: ");
                        karyawan.setGaji(scanner.nextDouble());
                        scanner.nextLine();
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                        tekanEnterUntukLanjut();
                        return;
                }
                System.out.println("Data karyawan diperbarui!");
                tekanEnterUntukLanjut();
                return;
            }
        }
        System.out.println("Karyawan tidak ditemukan.");
        tekanEnterUntukLanjut();
    }

    private static void hapusKaryawan() {
        bersihkanLayar();
        tampilkanKaryawan();
        System.out.print("Masukkan ID karyawan yang ingin dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Karyawan karyawan : daftarKaryawan) {
            if (karyawan.getId() == id) {
                daftarKaryawan.remove(karyawan);
                System.out.println("Karyawan berhasil dihapus!");
                tekanEnterUntukLanjut();
                return;
            }
        }
        System.out.println("Karyawan tidak ditemukan.");
        tekanEnterUntukLanjut();
    }

    private static void bersihkanLayar() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void tekanEnterUntukLanjut() {
        System.out.println("\nTekan Enter untuk melanjutkan...");
        scanner.nextLine();
    }
}
