package com.example.eatsnest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Payment extends AppCompatActivity {

    private EditText cardNumber, expiryDate, cvv, amount;
    private TextView paymentResult;
    private DatabaseHelper databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        cardNumber = findViewById(R.id.cardNumber);
        expiryDate = findViewById(R.id.expiryDate);
        cvv = findViewById(R.id.cvv);
        amount = findViewById(R.id.amount);
        paymentResult = findViewById(R.id.paymentResult);
        Button submitPayment = findViewById(R.id.submitPayment);
        Button backButton = findViewById(R.id.BTN_Back);  // Back button initialization

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        submitPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processPayment();
            }
        });

        // Back Button Click Listener
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to ProductsDisplay activity
                Intent intent = new Intent(Payment.this, UserConnectionActivity.class);
                startActivity(intent);
            }
        });
    }

    private void processPayment() {
        String cardNum = cardNumber.getText().toString();
        String expDate = expiryDate.getText().toString();
        String cvvCode = cvv.getText().toString();
        String amt = amount.getText().toString();

        if (validateInputs(cardNum, expDate, cvvCode, amt)) {
            // Since we are not performing real payment processing, assume it's successful
            paymentResult.setText("Payment processed successfully!");
            Toast.makeText(this, "Payment successful", Toast.LENGTH_SHORT).show();
            // Insert payment into database
            databaseHelper.insertPayment(cardNum, expDate, cvvCode, amt);
        } else {
            paymentResult.setText("Payment failed. Please check your details.");
            Toast.makeText(this, "Please fill in all fields correctly.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateInputs(String cardNum, String expDate, String cvvCode, String amt) {
        if (cardNum.isEmpty() || expDate.isEmpty() || cvvCode.isEmpty() || amt.isEmpty()) {
            return false;
        }
        if (!cardNum.matches("\\d{16}")) {
            return false;
        }
        if (!cvvCode.matches("\\d{3}")) {
            return false;
        }
        // Add additional validation for expiry date format if needed (e.g., MM/YY)
        return true;
    }
}
