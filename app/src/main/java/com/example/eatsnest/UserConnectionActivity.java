package com.example.eatsnest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserConnectionActivity extends AppCompatActivity {

    private Button btnProductDisplay, btnEmergencyAssistance, btnOrderTracking, btnPersonalizedRecommendations, btnReview, btnPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_connection);

        // Initialize buttons
        btnProductDisplay = findViewById(R.id.BTN_Product_Display);
        btnEmergencyAssistance = findViewById(R.id.BTN_Emergency_Assistance);
        btnOrderTracking = findViewById(R.id.BTN_Order_Tracking);
        btnPersonalizedRecommendations = findViewById(R.id.BTN_Personalized_Recommendations);
        btnReview = findViewById(R.id.Review);
        btnPayment = findViewById(R.id.Payment);

        // Set click listeners
        btnProductDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserConnectionActivity.this, "Navigating to Product Display Activity", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserConnectionActivity.this, ProductsDisplay.class));
            }
        });

        btnEmergencyAssistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserConnectionActivity.this, "Navigating to Emergency Assistance Activity", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserConnectionActivity.this, EmergencyAssistanceActivity.class));
            }
        });

        btnOrderTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserConnectionActivity.this, "Navigating to Order Tracking Activity", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserConnectionActivity.this, OrderTrackingActivity.class));
            }
        });

        btnPersonalizedRecommendations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserConnectionActivity.this, "Navigating to Personalized Recommendations Activity", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserConnectionActivity.this, PersonalizedRecommendationsActivity.class));
            }
        });

        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserConnectionActivity.this, "Navigating to Review Activity", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserConnectionActivity.this, ReviewActivity.class));
            }
        });

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserConnectionActivity.this, "Navigating to Payment Activity", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserConnectionActivity.this, Payment.class));
            }
        });
    }
}
