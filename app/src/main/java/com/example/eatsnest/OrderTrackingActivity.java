package com.example.eatsnest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class OrderTrackingActivity extends AppCompatActivity {

    private TextView orderStatusTextView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_tracking);

        orderStatusTextView = findViewById(R.id.orderStatusTextView);
        Button checkStatusButton = findViewById(R.id.checkStatusButton);
        Button buttonBack= findViewById(R.id.btn_back9);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(OrderTrackingActivity.this , UserConnectionActivity.class);
                startActivity(intent1);
            }
        });
        checkStatusButton.setOnClickListener(v -> {
            orderStatusTextView.setText("Your package is on the way");
            new Handler().postDelayed(() -> orderStatusTextView.setText("Your product has arrived"),  60 * 1000); // 1 minutes
        });
    }
}
