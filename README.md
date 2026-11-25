# Supermarket Smart Billing Mobile App

A complete Android mobile application with QR barcode scanning (Google ML Kit), aisle location finder, dynamic cart calculation, and digital receipt generation.

---

## 📁 Project Structure

```
SmartBilling/
├── build.gradle                          # Root Gradle build script
├── settings.gradle                       # Module definitions
├── gradle.properties                     # JVM and AndroidX settings
├── web_preview.html                      # Instant browser mobile simulation
└── app/
    ├── build.gradle                      # App module with ML Kit dependencies
    └── src/
        └── main/
            ├── AndroidManifest.xml       # Permissions & barcode metadata
            ├── java/com/example/smartbilling/
            │   └── MainActivity.java     # Full App Logic & UI
            └── res/
                └── values/
                    ├── strings.xml
                    ├── colors.xml
                    └── themes.xml
```

---

## 🚀 How to Run & Build the App

### Option 1: Instant Browser Test (No setup required!)
Double click or open `web_preview.html` in Chrome, Edge, or Firefox.
It contains the full mobile UI simulator with scan simulation, aisle search, dynamic cart updating, and receipt checkout.

### Option 2: Open in Android Studio
1. Open **Android Studio**.
2. Select **File -> Open...** and browse to:
   `C:\Users\seema\.gemini\antigravity\scratch\SmartBilling`
3. Wait for Gradle sync to finish.
4. Click **Run 'app'** (Green play button ▶) with an Android phone or Emulator connected.

### Option 3: Generate APK via Terminal
Open terminal in the project directory:
```bash
gradlew assembleDebug
```
The APK will be generated at:
`app/build/outputs/apk/debug/app-debug.apk`

---

## 🏷️ Test Product Barcodes
| Code | Product | Price | Location |
|---|---|---|---|
| `P101` | Milk 500 ml | ₹30.00 | Aisle 1 |
| `P102` | Bread | ₹40.00 | Aisle 2 |
| `P103` | Rice 1 kg | ₹65.00 | Aisle 3 |
| `P104` | Pasta | ₹55.00 | Aisle 3 |
| `P105` | Soap | ₹35.00 | Aisle 4 |
