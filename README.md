# MovieKuh
### Cari dan temukan film favorit anda!

## Fitur
- **Data Paging:** Memungkinkan anda untuk scroll tanpa batas.
- **Save to Favorite:** Memungkina anda untuk menyimpan film kesukaan anda.

## Prerequisites

Sebelum menjalankan proyek ini, pastikan Anda memiliki:

## Konfigurasi Lokal

Proyek ini memerlukan konfigurasi API yang harus disimpan dalam file `local.properties` di direktori root proyek Anda. File ini tidak akan dimasukkan ke dalam kontrol versi dan biasanya digunakan untuk menyimpan konfigurasi lokal seperti URL API dan kunci API.

### Langkah-langkah untuk Mengonfigurasi

1. **Buka atau Buat File `local.properties`**

   Jika file `local.properties` belum ada di direktori root proyek Anda, buat file baru dengan nama `local.properties`.

2. **Tambahkan Konfigurasi API**

   Tambahkan entri berikut ke dalam file `local.properties`:

   ```properties
   # Lokasi AndroidSdk anda (tambahkan jika belum ada)
   sdk.dir=YOUR_SDK_DIR
   
   # URL dasar untuk API
   BASE_URL = https://api.themoviedb.org/3/

   # Kunci API untuk akses API, anda dapat mendapatkan API anda dengan registasi disini (https://developer.themoviedb.org)
   API_KEY = YOUR_API_KEY_HERE
