package com.example.eatsnest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProductsDisplay extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private LinearLayout productLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_display);
        Button btnBack = findViewById(R.id.btn_back5);

        dbHelper = new DatabaseHelper(this);
        productLayout = findViewById(R.id.productLayout);
        displayProducts();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ProductsDisplay.this, UserConnectionActivity.class);
                startActivity(intent1);
            }
        });
    }

    private void displayProducts() {
        Cursor cursor = dbHelper.getAllProducts();
        LayoutInflater inflater = LayoutInflater.from(this);

        while (cursor.moveToNext()) {
            @SuppressLint("InflateParams") View productView = inflater.inflate(R.layout.activity_product_list_item, null);

            TextView nameTextView = productView.findViewById(R.id.productName);
            TextView priceTextView = productView.findViewById(R.id.productPrice);
            TextView quantityTextView = productView.findViewById(R.id.productQuantity);
            ImageView productImageView = productView.findViewById(R.id.productImage);

            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PRODUCT_NAME));
            @SuppressLint("Range") double price = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_PRODUCT_PRICE));
            @SuppressLint("Range") int quantity = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_PRODUCT_QUANTITY));
            @SuppressLint("Range") byte[] imageByteArray = cursor.getBlob(cursor.getColumnIndex(DatabaseHelper.COLUMN_PRODUCT_IMAGE));

            nameTextView.setText(name);
            priceTextView.setText(String.valueOf(price));
            quantityTextView.setText(String.valueOf(quantity));

            if (imageByteArray != null && imageByteArray.length > 0) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
                productImageView.setImageBitmap(bitmap);
            } else {
                productImageView.setImageResource(R.drawable.chiken4); // Set a default image if no image exists
            }

            // Make the image view clickable
            final String productName = name;
            final double productPrice = price;
            final int productQuantity = quantity;
            productImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ProductsDisplay.this, OrderActivity.class);
                    intent.putExtra("productName", productName);
                    intent.putExtra("productPrice", productPrice);
                    intent.putExtra("productQuantity", productQuantity);
                    startActivity(intent);
                }
            });

            productLayout.addView(productView);
        }

        cursor.close();
    }
}
