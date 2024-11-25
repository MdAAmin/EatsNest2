package com.example.eatsnest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Assumes EdgeToEdge class/method exists and works correctly
        setContentView(R.layout.activity_main);

        Button buttonToUser = findViewById(R.id.loginButton);
        buttonToUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, AdminLoginAndOldUser.class);
                startActivity(intent1);
                Toast.makeText(MainActivity.this, "You are going to AdminLoginAndOldUser Activity ", Toast.LENGTH_SHORT).show();
            }
        });

        Button buttonPerson = findViewById(R.id.registerButton);
        buttonPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, UserRegistration.class);
                startActivity(intent2);
                Toast.makeText(MainActivity.this, "You are going to UserRegistration Activity", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
