import java.util.Scanner;
import java.util.ArrayList;

class Mahasiswa {
    String nama;
    String nim;
    double ipk;

    Mahasiswa(String nama, String nim, double ipk) {
        this.nama = nama;
        this.nim = nim;
        this.ipk = ipk;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        int menu;
        boolean validNama = false, validNim = false, validIpk = false, isFiltering = false;
        String keyword;
        Scanner sc = new Scanner(System.in);
        ArrayList<Mahasiswa> mahasiswaList = new ArrayList<>();
        ArrayList<Mahasiswa> filteredList = new ArrayList<>();

        do {
            System.out.println("===========================================================");
            System.out.println("|No|            Nama            |      NIM      |   IPK   |");
            System.out.println("===========================================================");
            if (isFiltering) {
                if (filteredList.isEmpty())
                    System.out.printf("|%38s                   |\n", "Data tidak tersedia");
                else {
                    for (int i = 0; i < filteredList.size(); i++) {
                        Mahasiswa mhs = filteredList.get(i);
                        System.out.printf("|%2d|%27s |%14s |%6.2f   |\n", i + 1, mhs.nama, mhs.nim, mhs.ipk);
                    }
                }
            } else {
                if (mahasiswaList.isEmpty())
                    System.out.printf("|%38s                   |\n", "Data tidak tersedia");
                else {
                    for (int i = 0; i < mahasiswaList.size(); i++) {
                        Mahasiswa mhs = mahasiswaList.get(i);
                        System.out.printf("|%2d|%27s |%14s |%6.2f   |\n", i + 1, mhs.nama, mhs.nim, mhs.ipk);
                    }
                }
            }
            System.out.println("===========================================================");
            System.out.println("1. Input Data");
            System.out.println("2. Filter Data");
            System.out.println("3. Tampil Semua Data");
            System.out.println("4. Exit");
            System.out.print("Menu: ");
            menu = sc.nextInt();
            sc.nextLine();
            if (menu == 1) {
                isFiltering = false;
                String nama = null, nim = null;
                double ipk = 0.0;
                while (!validNama) {
                    try {
                        System.out.print("Input nama: ");
                        nama = sc.nextLine();
                        if (nama == null || nama.isEmpty())
                            throw new IllegalArgumentException("Nama tidak boleh kosong");
                        if (!nama.matches("[a-zA-Z\\s]*"))
                            throw new IllegalArgumentException("Nama tidak boleh mengandung angka atau simbol");
                        nama = nama.replaceAll("\\s+", " ");
                        validNama = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage()); // e.toString()
                    }
                }

                while (!validNim) {
                    try {
                        System.out.print("Input NIM: ");
                        nim = sc.nextLine();
                        if (nim == null || nim.isEmpty())
                            throw new IllegalArgumentException("NIM tidak boleh kosong");
                        if (!nim.matches("\\d+"))
                            throw new IllegalArgumentException("NIM tidak boleh mengandung abjad atau simbol");
                        validNim = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                }

                while (!validIpk) {
                    try {
                        System.out.print("Input IPK: ");
                        String ipkStr = sc.nextLine();
                        if (ipkStr == null || ipkStr.isEmpty())
                            throw new IllegalArgumentException("IPK tidak boleh kosong");
                        if (!ipkStr.matches("^[\\d.,-]+$"))
                            throw new IllegalArgumentException("IPK tidak boleh mengandung abjad atau simbol");
                        ipkStr = ipkStr.replace(',', '.');
                        ipk = Double.parseDouble(ipkStr);
                        if (ipk < 0 || ipk > 4)
                            throw new IllegalArgumentException("Format IPK salah");
                        validIpk = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }

                mahasiswaList.add(new Mahasiswa(nama, nim, ipk));

                validNama = false;
                validNim = false;
                validIpk = false;
            } else if (menu == 2) {
                System.out.print("Masukkan keyword: ");
                keyword = sc.nextLine();

                isFiltering = true;
                filteredList.clear();

                for (Mahasiswa mhs : mahasiswaList) {
                    if (mhs.nama.toLowerCase().contains(keyword.toLowerCase())
                            || mhs.nim.toLowerCase().contains(keyword.toLowerCase())) {
                        filteredList.add(mhs);
                    }
                }

            } else if (menu == 3) {
                isFiltering = false;
            } else {
                System.out.println("Inputan salah");
            }
        } while (menu != 4);
    }
}
