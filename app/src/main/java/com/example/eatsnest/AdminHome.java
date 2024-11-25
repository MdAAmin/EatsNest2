package com.example.eatsnest; // package under all java class belong

import android.content.Intent;   //from content package importing Intent class
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminHome extends AppCompatActivity {

    @Override     // Overriding the onCreate method to initialize the activity

    protected void onCreate(Bundle savedInstanceState) {  //  savedInstanceState previous state saved
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home); // show ui component

        Button btnInsertProduct = findViewById(R.id.btn_insert_product); // initialize Ui component
        Button btnViewProduct = findViewById(R.id.btn_view_product);
        Button btnUpdateProduct = findViewById(R.id.btn_update_product);
        Button btnDeleteProduct = findViewById(R.id.btn_delete_product);
        Button btnViewOrders = findViewById(R.id.btn_view_orders);

        // clickable
        btnInsertProduct.setOnClickListener(v -> {
            Intent intent1 = new Intent(AdminHome.this, InsertProductActivity.class);
            startActivity(intent1); // start the new activity
        });

        btnViewProduct.setOnClickListener(v -> {
            Intent intent2 = new Intent(AdminHome.this, ViewProductActivity.class);
            startActivity(intent2);
        });

        btnUpdateProduct.setOnClickListener(v -> {
            Intent intent3 = new Intent(AdminHome.this, UpdateProductActivity.class);
            startActivity(intent3);
        });

        btnDeleteProduct.setOnClickListener(v -> {
            Intent intent4 = new Intent(AdminHome.this, DeleteProductActivity.class);
            startActivity(intent4);
        });

        btnViewOrders.setOnClickListener(v -> {
            Intent intent5 = new Intent(AdminHome.this, AdminOrderDetailsActivity.class);
            startActivity(intent5);
        });
        Button backButton = findViewById(R.id.btn_back4);
        backButton.setOnClickListener(v -> {
            Intent intent6 = new Intent(AdminHome.this, AdminLoginAndOldUser.class);
            startActivity(intent6);
        });
    }
}
