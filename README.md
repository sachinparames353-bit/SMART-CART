# 🛒 SMART-CART : Supermarket Autonomous Billing & Navigation System

[![Android](https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/)
[![Java](https://img.shields.io/badge/Language-Java_17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![ML Kit](https://img.shields.io/badge/Scanner-Google_ML_Kit-4285F4?style=for-the-badge&logo=google&logoColor=white)](https://developers.google.com/ml-kit)
[![Gradle](https://img.shields.io/badge/Build-Gradle_8.x-02303A?style=for-the-badge&logo=gradle&logoColor=white)](https://gradle.org/)
[![CI Build](https://img.shields.io/badge/CI-GitHub_Actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white)](.github/workflows/build-apk.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=for-the-badge)](LICENSE)

An intelligent self-checkout and supermarket smart cart application designed to eliminate checkout queues, streamline in-store navigation, and deliver a frictionless shopping experience. 

Built with native Android (Java) and Google ML Kit Barcode Scanning, accompanied by an instant standalone browser simulation.

---

## 🌟 Key Features

* **📷 Fast On-Device QR & Barcode Scanning**: Powered by Google ML Kit's Vision Code Scanner for instant, accurate product detection without sending frames to the cloud.
* **🗺️ Smart Aisle & Shelf Locator**: Integrated aisle search that pinpoints product locations (e.g., *Pasta → Aisle 3*, *Milk → Aisle 1*) to help shoppers find items rapidly.
* **⚡ Live Cart & Dynamic Bill Calculator**: Automatically updates quantities, unit prices, discounts, and total amount in real time as items are scanned or removed.
* **🧾 Digital Checkout & Receipt Verification**: Generates an itemized digital receipt with transaction ID and checkout timestamp for security exit-gate checks.
* **🌐 Zero-Install Web Simulation**: Includes a full interactive mobile simulator (`web_preview.html` and `mobile_web/`) that runs instantly in any modern web browser without needing Android Studio or SDK setup.
* **🤖 Automated CI/CD Pipeline**: GitHub Actions workflow automatically compiles and publishes release/debug APK artifacts on every push.

---

## 🏗️ Architecture & Project Structure

```
SMART-CART/
├── .github/
│   └── workflows/
│       └── build-apk.yml               # GitHub Actions CI workflow to build debug APK
├── app/
│   ├── build.gradle                   # App module build script (ML Kit & AndroidX dependencies)
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml    # App permissions & Barcode scanner metadata
│           ├── java/com/example/smartbilling/
│           │   └── MainActivity.java  # Core application logic, scanner hooks & UI
│           └── res/
│               └── values/
│                   ├── colors.xml     # Brand palette & dark/light definitions
│                   ├── strings.xml    # Localization & UI string resources
│                   └── themes.xml     # Material component styling
├── mobile_web/
│   ├── index.html                     # PWA-ready responsive mobile preview
│   └── manifest.json                  # Web application manifest
├── build.gradle                       # Root Gradle build configuration
├── settings.gradle                    # Module registry
├── gradle.properties                  # JVM memory allocation & AndroidX settings
├── web_preview.html                   # Interactive browser simulator
├── start_app.bat                      # 1-click Windows launcher for quick testing
└── README.md                          # Documentation
```

---

## 🏷️ Pre-Configured Test Inventory

You can test product lookups and scans using these default items:

| Product Code | Item Name | Price | Store Location |
|---|---|---|---|
| `P101` | Milk (500 ml) | ₹30.00 | Aisle 1 (Dairy) |
| `P102` | Whole Wheat Bread | ₹40.00 | Aisle 2 (Bakery) |
| `P103` | Basmati Rice (1 kg) | ₹65.00 | Aisle 3 (Grains) |
| `P104` | Italian Pasta | ₹55.00 | Aisle 3 (Grains) |
| `P105` | Bathing Soap | ₹35.00 | Aisle 4 (Personal Care) |

---

## 🚀 How to Run & Build

### Option 1: Instant Browser Simulation (No setup required!)
Simply double-click or open `web_preview.html` in Chrome, Edge, Safari, or Firefox:
* Experience the full mobile interface.
* Test simulated barcode scans.
* Search aisles and products interactively.
* Add/remove items and trigger complete digital receipt checkout.

### Option 2: Open in Android Studio
1. Open **Android Studio**.
2. Select **File ➔ Open...** and navigate to this repository folder.
3. Allow Gradle to sync dependencies.
4. Connect an Android device (with USB debugging enabled) or start an Android Emulator.
5. Click **Run 'app'** (`Shift + F10` or the green ▶ button).

### Option 3: Build Debug APK via Command Line
Run the Gradle wrapper from the project root:
```bash
./gradlew assembleDebug
```
*(On Windows command prompt, use `gradlew.bat assembleDebug`)*

The generated APK will be available at:
```
app/build/outputs/apk/debug/app-debug.apk
```

---

## 🛠️ Tech Stack & Dependencies

* **Language**: Java 17
* **Platform**: Android SDK (Min SDK: 21, Target SDK: 34)
* **Computer Vision**: `com.google.android.gms:play-services-mlkit-barcode-scanning:18.3.0`
* **Code Scanner Bundle**: `com.google.mlkit:barcode-scanning-code-scanner:16.0.0-beta1`
* **UI Components**: Native Android View Hierarchy (`ScrollView`, `LinearLayout`, Material styling)
* **Web Simulator**: HTML5, CSS3 Glassmorphism, Vanilla JavaScript ES6
* **CI/CD**: GitHub Actions (`ubuntu-latest`, Temurin JDK 17, Android SDK action)

---

## 🔮 Future Roadmap

- [ ] RFID tag integration for physical trolley multi-item automatic detection.
- [ ] Direct payment gateway integration (UPI, Stripe, Razorpay).
- [ ] Interactive 2D store map floorplan with turn-by-turn aisle navigation.
- [ ] Personalized recommendations and basket-level discount coupons.

---

## 📄 License

This project is licensed under the [MIT License](LICENSE) - feel free to use and modify for learning or commercial deployment.
