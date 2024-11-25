package com.example.eatsnest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AdminLoginAndOldUser extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText usernameEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_and_old_user_login);

        databaseHelper = new DatabaseHelper(this);

        usernameEditText = findViewById(R.id.AD_ed);
        passwordEditText = findViewById(R.id.pass_ed);

        Button loginButton = findViewById(R.id.BTN_LOGIN);
        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (databaseHelper.checkUser(username, password)) {
                Toast.makeText(AdminLoginAndOldUser.this, "User Login Successful", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(AdminLoginAndOldUser.this, UserConnectionActivity.class);
                startActivity(intent2);
            }
            });
                Button AdminButton = findViewById(R.id.BTN_ADMIN);
                AdminButton.setOnClickListener(v -> {
                    String username = usernameEditText.getText().toString();
                    String password = passwordEditText.getText().toString();
            if (databaseHelper.checkAdmin(username, password))
            {
                Toast.makeText(AdminLoginAndOldUser.this, "Admin Login Successful", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(AdminLoginAndOldUser.this, AdminHome.class);
                startActivity(intent1);

            }
          else {
                Toast.makeText(AdminLoginAndOldUser.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
            }
        });

        Button backButton = findViewById(R.id.BTN_Back);
        backButton.setOnClickListener(v -> {
            Intent intent3 = new Intent(AdminLoginAndOldUser.this, MainActivity.class);
            startActivity(intent3);
        });
    }
}
