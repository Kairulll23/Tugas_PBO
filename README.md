# Sistem Manajemen List Tugas Kuliah

Sistem Manajemen List Tugas Kuliah adalah aplikasi CLI berbasis Java yang dibangun menggunakan prinsip Pemrograman Berbasis Objek (OOP) serta arsitektur **MVC (Model-View-Controller)** / **Layered Architecture**. Aplikasi ini memudahkan mahasiswa dalam mencatat, memantau, memperbarui, dan menghapus daftar tugas kuliah baik berbentuk tugas individu maupun kelompok.

---

## Identitas Mahasiswa

* **Nama** : Khairul Ikhsan
* **NIM**  : 2509116097

---

## Penjelasan Studi Kasus

Aplikasi ini dikembangkan untuk menangani permasalahan mahasiswa dalam mengelola berbagai macam tugas perkuliahan. Dalam dunia perkuliahan, tugas terbagi menjadi dua kategori utama, yaitu:
1. **Tugas Individu**: Memiliki properti khusus berupa media/platform pengumpulan (misalnya: Spada, Email, Google Classroom).
2. **Tugas Kelompok**: Memiliki properti khusus berupa jumlah anggota dalam tim.

Aplikasi menyediakan fitur **CRUD (Create, Read, Update, Delete)** lengkap dengan validasi input data, pemuatan dummy data otomatis saat program pertama kali dijalankan, serta pemisahan logika program yang rapi menggunakan pola MVC.

---

## Struktur Packages & Diagram Hierarki Class

### Struktur Directory / Package (Pola MVC) 
<img width="638" height="305" alt="image" src="https://github.com/user-attachments/assets/6764ad4f-61d5-495e-82da-41e1c3a5bb91" />


### Diagram Hierarki Class
               +-----------------------+
               |       Pengguna        |
               +-----------------------+
               | - nim : String        |
               | - nama : String       |
               +-----------------------+

               +-----------------------+
               |        Storage        |  <--- Superclass
               +-----------------------+
               | - kodeTugas : String  |
               | - namaMatkul : String |
               | - detailTugas : String|
               | - dateline : String   |
               +-----------------------+
               | + getTipeTugas()      |
               | + cetakBaris()        |
               +-----------------------+
                             ^
                             |
            +----------------+----------------+
            |                                 |   
          +-------------------+             +--------------------+
          |   TugasIndividu   |             |   TugasKelompok    |
          +-------------------+             +--------------------+
          | - platform...     |             | - jumlahAnggota    |
          +-------------------+             +--------------------+
          | + getTipeTugas()  |             | + getTipeTugas()   |
          | + cetakBaris()    |             | + cetakBaris()     |
          +-------------------+             +--------------------+

### Penjelasan Penerapan Inheritance
### 1. Inheritance (Pewarisan)
Inheritance diterapkan dengan menjadikan class `Storage` sebagai **Superclass**, sedangkan `TugasIndividu` dan `TugasKelompok` bertindak sebagai **Subclass**.

* **`Storage` (Superclass)**: Menyimpan atribut umum yang dimiliki oleh semua jenis tugas, seperti `kodeTugas`, `namaMatkul`, `detailTugas`, dan `dateline`.
* **`TugasIndividu` (Subclass 1)**: Mewarisi seluruh atribut dan method dari `Storage` menggunakan kata kunci `extends`, serta menambahkan atribut khusus `platformPengumpulan`.
* **`TugasKelompok` (Subclass 2)**: Mewarisi seluruh atribut dan method dari `Storage` menggunakan kata kunci `extends`, serta menambahkan atribut khusus `jumlahAnggota`.

#### Contoh Potongan Kode Inheritance:

```java
package sistem;

// Subclass 1: TugasIndividu mewarisi Superclass Storage
public class TugasIndividu extends Storage {
    private String platformPengumpulan;

    public TugasIndividu(String kodeTugas, String namaMatkul, String detailTugas, String dateline, String platformPengumpulan) {
        super(kodeTugas, namaMatkul, detailTugas, dateline); // Memanggil constructor milik Superclass (Storage)
        this.platformPengumpulan = platformPengumpulan;
    }

    public String getPlatformPengumpulan() { return platformPengumpulan; }
    public void setPlatformPengumpulan(String platformPengumpulan) { this.platformPengumpulan = platformPengumpulan; }
} 
```
### Tangkapan Layar (Running Program)

Berikut adalah bukti dokumentasi jalannya program saat dieksekusi melalui terminal / console:

### 1. Menu Utama & Menampilkan Data (Read)
Menampilkan menu utama aplikasi beserta *dummy data* awal yang otomatis dimuat ke dalam `ArrayList` saat aplikasi pertama kali dijalankan.

<img width="1043" height="521" alt="image" src="https://github.com/user-attachments/assets/6b58bf77-5567-4ee9-9222-e45b2bd9f920" />


---

### 2. Tambah Data Tugas Baru (Create)
Proses penambahan tugas baru dengan memilih kategori tugas (Individu/Kelompok) serta pengisian data lengkap beserta validasi input.

<img width="505" height="416" alt="image" src="https://github.com/user-attachments/assets/65752bd8-65ef-4818-9919-ad281a403909" />

---

### 3. Ubah Data Tugas (Update)
Proses pencarian tugas berdasarkan `Kode Tugas` dan memperbarui informasi detail tugas yang dipilih.

<img width="427" height="337" alt="image" src="https://github.com/user-attachments/assets/db523706-af3b-41aa-888c-e34c80b0f4f4" />

---

### 4. Hapus Data Tugas (Delete)
Proses menghapus data tugas dari sistem berdasarkan `Kode Tugas`.

<img width="495" height="245" alt="image" src="https://github.com/user-attachments/assets/11f7ad87-3f28-47c0-a4aa-d5a403f78324" />

---

### 5. Validasi Input System
Pengujian fitur validasi saat pengguna memasukkan input kosong atau tipe data yang salah (huruf pada input angka).

<img width="517" height="182" alt="image" src="https://github.com/user-attachments/assets/101d2928-c85f-4393-a96c-2b15f48af370" />
