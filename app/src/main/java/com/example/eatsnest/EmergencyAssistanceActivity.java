package com.example.eatsnest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class EmergencyAssistanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_emergency_assistance);

        EditText nameEditText = findViewById(R.id.nameEditText);
        Button emergencyButton = findViewById(R.id.emergencyButton);
        Button backButton = findViewById(R.id.btn_back7);
        backButton.setOnClickListener(v -> {
            Intent intent1 = new Intent(EmergencyAssistanceActivity.this,UserConnectionActivity.class);
            startActivity(intent1);
        });

        emergencyButton.setOnClickListener(v -> {
            String personName = nameEditText.getText().toString();
            if (personName.isEmpty()) {
                Toast.makeText(EmergencyAssistanceActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(EmergencyAssistanceActivity.this, "Emergency request saved for " + personName, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
