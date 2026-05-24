# Aetheria Backend

Spring Boot backend untuk Aetheria dengan PostgreSQL.

## Prasyarat
- JDK 17
- PostgreSQL

## Konfigurasi
Edit `backend/src/main/resources/application.properties` sesuai kredensial PostgreSQL kamu.

## Menjalankan
```powershell
Set-Location "C:\Users\danis\Documents\SEMESTER 4\Proyek OOP"
.\gradlew.bat :backend:bootRun --console plain
```

## Endpoint Utama
- `GET /api/players`
- `GET /api/items`
- `GET /api/inventories`
- `GET /api/enemies`
- `GET /api/bosses`
- `GET /api/boss-progress`

CRUD lengkap tersedia untuk setiap resource di atas dengan `POST`, `PUT`, `DELETE`.

