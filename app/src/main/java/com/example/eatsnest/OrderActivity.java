package com.example.eatsnest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {

    private EditText productNameEditText;
    private EditText productPriceEditText;
    private EditText productQuantityEditText;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);



        productNameEditText = findViewById(R.id.productNameEditText);
        productPriceEditText = findViewById(R.id.productPriceEditText);
        productQuantityEditText = findViewById(R.id.productQuantityEditText);
        Button placeOrderButton = findViewById(R.id.placeOrderButton);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button butonBack = findViewById(R.id.btn_back8);
        butonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(OrderActivity.this , UserConnectionActivity.class);
                startActivity(intent1);
            }
        });
        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Get intent extras
        Intent intent = getIntent();
        if (intent != null) {
            String productName = intent.getStringExtra("productName");
            double productPrice = intent.getDoubleExtra("productPrice", 0.0);
            int productQuantity = intent.getIntExtra("productQuantity", 0);

            // Set values to EditTexts
            productNameEditText.setText(productName);
            productPriceEditText.setText(String.valueOf(productPrice));
            productQuantityEditText.setText(String.valueOf(productQuantity));
        }

        // Set productNameEditText and productPriceEditText as non-editable
        productNameEditText.setEnabled(false);
        productNameEditText.setFocusable(false);
        productPriceEditText.setEnabled(false);
        productPriceEditText.setFocusable(false);

        // Set click listener for the place order button
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder();
            }
        });
    }

    private void placeOrder() {
        String productName = productNameEditText.getText().toString();
        double productPrice = Double.parseDouble(productPriceEditText.getText().toString());
        String quantityStr = productQuantityEditText.getText().toString();

        if (productName.isEmpty() || productPrice <= 0 || quantityStr.isEmpty()) {
            Toast.makeText(this, "Please enter valid order details", Toast.LENGTH_SHORT).show();
            return;
        }

        int productQuantity;
        try {
            productQuantity = Integer.parseInt(quantityStr);
            if (productQuantity <= 0) {
                Toast.makeText(this, "Quantity must be greater than zero", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid quantity", Toast.LENGTH_SHORT).show();
            return;
        }

        // Insert order into the database
        databaseHelper.insertOrder(productName, productPrice, productQuantity);

        // Show success message
        Toast.makeText(this, "Order placed successfully", Toast.LENGTH_SHORT).show();

        productNameEditText.setText("");
         productPriceEditText.setText("");
        productQuantityEditText.setText("");
    }
}
