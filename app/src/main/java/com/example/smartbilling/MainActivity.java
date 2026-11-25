package com.example.smartbilling;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.*;
import com.google.android.gms.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner;
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning;
import java.text.NumberFormat;
import java.util.*;

public class MainActivity extends Activity {
    static class Product {
        String id, name, aisle;
        int price;
        Product(String id, String name, int price, String aisle) {
            this.id = id; this.name = name; this.price = price; this.aisle = aisle;
        }
    }

    private final Map<String, Product> products = new LinkedHashMap<>();
    private final Map<String, Integer> cart = new LinkedHashMap<>();
    private LinearLayout cartLayout;
    private TextView totalText, statusText;
    private EditText searchInput;
    private final NumberFormat money = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        seedProducts();
        buildScreen();
        renderCart();
    }

    private void seedProducts() {
        products.put("P101", new Product("P101", "Milk 500 ml", 30, "Aisle 1"));
        products.put("P102", new Product("P102", "Bread", 40, "Aisle 2"));
        products.put("P103", new Product("P103", "Rice 1 kg", 65, "Aisle 3"));
        products.put("P104", new Product("P104", "Pasta", 55, "Aisle 3"));
        products.put("P105", new Product("P105", "Soap", 35, "Aisle 4"));
    }

    private void buildScreen() {
        ScrollView scroll = new ScrollView(this);
        LinearLayout page = new LinearLayout(this);
        page.setOrientation(LinearLayout.VERTICAL);
        page.setPadding(dp(18), dp(22), dp(18), dp(24));
        page.setBackgroundColor(0xFFF6F8FB);
        scroll.addView(page);

        TextView title = new TextView(this);
        title.setText("Supermarket Smart Billing");
        title.setTextSize(23);
        title.setTypeface(null, Typeface.BOLD);
        title.setTextColor(0xFF172B4D);
        page.addView(title);

        TextView subtitle = new TextView(this);
        subtitle.setText("Scan products, track your bill and shop smarter.");
        subtitle.setTextSize(14);
        subtitle.setPadding(0, dp(5), 0, dp(16));
        page.addView(subtitle);

        Button scanButton = makeButton("Scan QR code");
        page.addView(scanButton);
        scanButton.setOnClickListener(v -> scanQr());

        searchInput = new EditText(this);
        searchInput.setSingleLine(true);
        searchInput.setHint("Search product (e.g. Pasta)");
        page.addView(searchInput);

        Button searchButton = makeButton("Search product");
        page.addView(searchButton);
        searchButton.setOnClickListener(v -> searchProduct());

        statusText = new TextView(this);
        statusText.setText("Try product IDs: P101, P102, P103, P104 or P105");
        statusText.setPadding(0, dp(10), 0, dp(12));
        page.addView(statusText);

        TextView cartTitle = new TextView(this);
        cartTitle.setText("Your cart");
        cartTitle.setTextSize(19);
        cartTitle.setTypeface(null, Typeface.BOLD);
        cartTitle.setPadding(0, dp(10), 0, dp(8));
        page.addView(cartTitle);

        cartLayout = new LinearLayout(this);
        cartLayout.setOrientation(LinearLayout.VERTICAL);
        page.addView(cartLayout);

        totalText = new TextView(this);
        totalText.setTextSize(20);
        totalText.setTypeface(null, Typeface.BOLD);
        totalText.setTextColor(0xFF087443);
        totalText.setPadding(0, dp(18), 0, dp(12));
        page.addView(totalText);

        Button payButton = makeButton("Confirm and pay (demo)");
        page.addView(payButton);
        payButton.setOnClickListener(v -> checkout());

        TextView note = new TextView(this);
        note.setText("Product finder: search for an item to see its aisle. RFID hardware and live payment are not connected in this demo.");
        note.setTextSize(12);
        note.setPadding(0, dp(18), 0, 0);
        page.addView(note);

        setContentView(scroll);
    }

    private void scanQr() {
        GmsBarcodeScannerOptions options = new GmsBarcodeScannerOptions.Builder()
                .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
                .build();
        GmsBarcodeScanner scanner = GmsBarcodeScanning.getClient(this, options);
        scanner.startScan()
            .addOnSuccessListener(barcode -> {
                String value = barcode.getRawValue();
                if (value == null) {
                    statusText.setText("QR code is empty.");
                    return;
                }
                // QR code should contain a product ID such as P104.
                addProduct(value.trim().toUpperCase(Locale.ROOT));
            })
            .addOnCanceledListener(() -> statusText.setText("QR scan cancelled."))
            .addOnFailureListener(e -> statusText.setText("Scanner error: " + e.getMessage()));
    }

    private void searchProduct() {
        String query = searchInput.getText().toString().trim().toLowerCase(Locale.ROOT);
        if (query.isEmpty()) {
            statusText.setText("Enter a product name.");
            return;
        }
        for (Product p : products.values()) {
            if (p.name.toLowerCase(Locale.ROOT).contains(query)) {
                statusText.setText(p.name + " is located in " + p.aisle + ".");
                addProduct(p.id);
                return;
            }
        }
        statusText.setText("Product not found. Try Milk, Bread, Rice, Pasta or Soap.");
    }

    private void addProduct(String id) {
        Product p = products.get(id);
        if (p == null) {
            statusText.setText("Unknown product code: " + id + ". Use P101-P105.");
            return;
        }
        cart.put(id, cart.getOrDefault(id, 0) + 1);
        statusText.setText(p.name + " added. Location: " + p.aisle);
        renderCart();
    }

    private void renderCart() {
        cartLayout.removeAllViews();
        int total = 0;
        if (cart.isEmpty()) {
            TextView empty = new TextView(this);
            empty.setText("Your cart is empty.");
            cartLayout.addView(empty);
        }
        for (String id : cart.keySet()) {
            Product p = products.get(id);
            int qty = cart.get(id);
            total += p.price * qty;

            LinearLayout line = new LinearLayout(this);
            line.setGravity(Gravity.CENTER_VERTICAL);
            line.setPadding(0, dp(7), 0, dp(7));

            TextView item = new TextView(this);
            item.setText(p.name + " \u00d7 " + qty + "\n" + money.format(p.price) + " each");
            item.setTextSize(14);
            item.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
            line.addView(item);

            Button remove = new Button(this);
            remove.setText("-");
            remove.setOnClickListener(v -> {
                int current = cart.getOrDefault(id, 0);
                if (current <= 1) cart.remove(id); else cart.put(id, current - 1);
                renderCart();
            });
            line.addView(remove);
            cartLayout.addView(line);
        }
        totalText.setText("Total: " + money.format(total));
    }

    private void checkout() {
        if (cart.isEmpty()) {
            statusText.setText("Add at least one product before checkout.");
            return;
        }
        int total = 0;
        StringBuilder receipt = new StringBuilder("SMART BILLING RECEIPT\n\n");
        for (String id : cart.keySet()) {
            Product p = products.get(id);
            int qty = cart.get(id);
            int lineTotal = p.price * qty;
            total += lineTotal;
            receipt.append(p.name).append(" \u00d7 ").append(qty)
                   .append(" = ").append(money.format(lineTotal)).append("\n");
        }
        receipt.append("\nTOTAL: ").append(money.format(total))
               .append("\n\nDemo checkout only. No real payment was processed.");
        new AlertDialog.Builder(this)
            .setTitle("Digital receipt")
            .setMessage(receipt.toString())
            .setPositiveButton("Done", (dialog, which) -> {
                cart.clear();
                renderCart();
                statusText.setText("Demo order completed. Thank you!");
            })
            .setNegativeButton("Close", null)
            .show();
    }

    private Button makeButton(String label) {
        Button b = new Button(this);
        b.setText(label);
        return b;
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }
}
