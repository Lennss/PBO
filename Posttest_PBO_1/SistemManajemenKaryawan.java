import java.util.ArrayList;
import java.util.Scanner;

class Employee {
    private static int idCounter = 1;
    private int id;
    private String name;
    private String position;
    private double salary;

    public Employee(String name, String position, double salary) {
        this.id = idCounter++;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nNama: " + name + "\nPosisi: " + position + "\nGaji: " + salary + "\n----------------------";
    }
}

public class SistemManajemenKaryawan {
    private static ArrayList<Employee> employees = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            clearScreen();
            System.out.println("\nSistem Pengelolaan Karyawan di Electrical Workshop");
            System.out.println("1. Tambah Karyawan");
            System.out.println("2. Tampilkan Karyawan");
            System.out.println("3. Perbarui Karyawan");
            System.out.println("4. Hapus Karyawan");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    displayEmployees();
                    pressEnterToContinue();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    deleteEmployee();
                    break;
                case 5:
                    System.out.println("Keluar dari program...");
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private static void addEmployee() {
        clearScreen();
        System.out.print("Masukkan Nama: ");
        String name = scanner.nextLine();

        System.out.println("Pilih Posisi:");
        System.out.println("1. Teknisi");
        System.out.println("2. Supervisor");
        System.out.println("3. Manajer");
        System.out.print("Pilihan: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        String position;
        switch (choice) {
            case 1:
                position = "Teknisi";
                break;
            case 2:
                position = "Supervisor";
                break;
            case 3:
                position = "Manajer";
                break;
            default:
                position = "Teknisi";
        }

        System.out.print("Masukkan Gaji: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); 

        employees.add(new Employee(name, position, salary));
        System.out.println("Karyawan berhasil ditambahkan!");

        pressEnterToContinue();
    }

    private static void displayEmployees() {
        clearScreen();
        if (employees.isEmpty()) {
            System.out.println("Tidak ada karyawan.");
        } else {
            for (Employee emp : employees) {
                System.out.println(emp);
            }
        }
    }

    private static void updateEmployee() {
        clearScreen();
        displayEmployees();
        System.out.print("Masukkan ID karyawan yang ingin diperbarui: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Employee emp : employees) {
            if (emp.getId() == id) {
                System.out.println("Pilih bagian yang ingin diperbarui:");
                System.out.println("1. Nama");
                System.out.println("2. Posisi");
                System.out.println("3. Gaji");
                System.out.print("Pilihan: ");
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        System.out.print("Masukkan Nama baru: ");
                        emp.setName(scanner.nextLine());
                        break;
                    case 2:
                        System.out.println("Pilih Posisi baru:");
                        System.out.println("1. Teknisi");
                        System.out.println("2. Supervisor");
                        System.out.println("3. Manajer");
                        System.out.print("Pilihan: ");
                        int posChoice = scanner.nextInt();
                        scanner.nextLine();
                        emp.setPosition(posChoice == 1 ? "Teknisi" : posChoice == 2 ? "Supervisor" : "Manajer");
                        break;
                    case 3:
                        System.out.print("Masukkan Gaji baru: ");
                        emp.setSalary(scanner.nextDouble());
                        scanner.nextLine();
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                        pressEnterToContinue();
                        return;
                }
                System.out.println("Data karyawan diperbarui!");
                pressEnterToContinue();
                return;
            }
        }
        System.out.println("Karyawan tidak ditemukan.");
        pressEnterToContinue();
    }

    private static void deleteEmployee() {
        clearScreen();
        displayEmployees();
        System.out.print("Masukkan ID karyawan yang ingin dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Employee emp : employees) {
            if (emp.getId() == id) {
                employees.remove(emp);
                System.out.println("Karyawan berhasil dihapus!");
                pressEnterToContinue();
                return;
            }
        }
        System.out.println("Karyawan tidak ditemukan.");
        pressEnterToContinue();
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void pressEnterToContinue() {
        System.out.println("\nTekan Enter untuk melanjutkan...");
        scanner.nextLine();
    }
}
