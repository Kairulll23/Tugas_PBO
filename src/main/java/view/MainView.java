package view;

import controller.TugasController;
import model.*;

import java.util.Scanner;

public class MainView {
    private final TugasController controller = new TugasController();
    private final Scanner scan = new Scanner(System.in);

    public void olahMenu(Pengguna user) {
        boolean lanjut = true;
        System.out.println("\nSelamat Datang, " + user.getNama() + " (" + user.getNim() + ")!");

        while (lanjut) {
            System.out.println("\n=== SISTEM LIST TUGAS KULIAH ===");
            System.out.println("1. Tambah Tugas Baru (Create)");
            System.out.println("2. Tampilkan Daftar Tugas (Read)");
            System.out.println("3. Ubah Data Tugas (Update)");
            System.out.println("4. Hapus Tugas (Delete)");
            System.out.println("5. Keluar Aplikasi");
            System.out.print("Pilih menu (1-5): ");

            int pil = bacaAngka();

            switch (pil) {
                case 1 -> menuTambah();
                case 2 -> menuTampil();
                case 3 -> menuUbah();
                case 4 -> menuHapus();
                case 5 -> {
                    lanjut = false;
                    System.out.println("\nTerima kasih, program selesai.");
                }
                default -> System.out.println("[!] Pilihan menu tidak tersedia. Silakan pilih 1-5.");
            }
        }
    }

    private int bacaAngka() {
        while (!scan.hasNextInt()) {
            System.out.println("[!] Input harus berupa angka.");
            System.out.print("Pilih menu / input angka: ");
            scan.next();
        }
        int angka = scan.nextInt();
        scan.nextLine();
        return angka;
    }

    private String bacaTeks(String label) {
        String input;
        do {
            System.out.print(label);
            input = scan.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("[!] Inputan tidak boleh kosong.");
            }
        } while (input.isEmpty());
        return input;
    }

    private void menuTambah() {
        System.out.println("\n--- TAMBAH TUGAS BARU ---");
        System.out.println("Jenis Tugas:");
        System.out.println("1. Tugas Individu");
        System.out.println("2. Tugas Kelompok");
        System.out.print("Pilih Jenis (1/2): ");
        int jenis = bacaAngka();

        String kode = bacaTeks("Kode Tugas (contoh: T03): ");
        if (controller.cariTugas(kode) != null) {
            System.out.println("[!] Kode tugas sudah digunakan.");
            return;
        }

        String matkul = bacaTeks("Nama Mata Kuliah: ");
        String detail = bacaTeks("Catatan Tugas: ");
        String dateline = bacaTeks("Deadline (contoh: 2026-09-15 23:59): ");

        if (jenis == 1) {
            String platform = bacaTeks("Platform Pengumpulan (misal: Spada/Email): ");
            controller.tambahTugas(new TugasIndividu(kode, matkul, detail, dateline, platform));
        } else if (jenis == 2) {
            System.out.print("Jumlah Anggota Kelompok: ");
            int jml = bacaAngka();
            controller.tambahTugas(new TugasKelompok(kode, matkul, detail, dateline, jml));
        } else {
            System.out.println("[!] Jenis tugas tidak valid.");
            return;
        }
        System.out.println("[+] Tugas berhasil ditambahkan.");
    }

    private void menuTampil() {
        var list = controller.getAllTugas();
        if (list.isEmpty()) {
            System.out.println("\n[!] Belum ada data tugas yang tersimpan.");
            return;
        }

        System.out.println("\n========================================================================================================");
        System.out.printf("| %-8s | %-10s | %-18s | %-25s | %-20s |\n", "KODE", "TIPE", "MATA KULIAH", "CATATAN TUGAS", "DEADLINE");
        System.out.println("========================================================================================================");
        for (Storage t : list) {
            System.out.println(t.cetakBaris());
        }
        System.out.println("========================================================================================================");
    }

    private void menuUbah() {
        System.out.println("\n--- UBAH DATA TUGAS ---");
        String kode = bacaTeks("Masukkan Kode Tugas yang ingin diubah: ");
        Storage t = controller.cariTugas(kode);

        if (t == null) {
            System.out.println("[!] Kode tugas tidak ditemukan.");
            return;
        }

        t.setNamaMatkul(bacaTeks("Nama Mata Kuliah Baru: "));
        t.setDetailTugas(bacaTeks("Catatan Tugas Baru: "));
        t.setDateline(bacaTeks("Deadline Baru: "));

        if (t instanceof TugasIndividu ti) {
            ti.setPlatformPengumpulan(bacaTeks("Platform Pengumpulan Baru: "));
        } else if (t instanceof TugasKelompok tk) {
            System.out.print("Jumlah Anggota Baru: ");
            tk.setJumlahAnggota(bacaAngka());
        }

        System.out.println("[+] Data tugas berhasil diperbarui.");
    }

    private void menuHapus() {
        System.out.println("\n--- HAPUS TUGAS ---");
        String kode = bacaTeks("Masukkan Kode Tugas yang ingin dihapus: ");
        if (controller.hapusTugas(kode)) {
            System.out.println("[+] Tugas berhasil dihapus.");
        } else {
            System.out.println("[!] Kode tugas tidak ditemukan.");
        }
    }
}