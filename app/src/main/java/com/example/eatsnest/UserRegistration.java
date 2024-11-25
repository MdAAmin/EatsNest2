package com.example.eatsnest;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class UserRegistration extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText usernameEditText, passwordEditText, confirmPasswordEditText, emailEditText, phoneEditText, addressEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Enable edge-to-edge display
        setContentView(R.layout.activity_user_registration);

        databaseHelper = new DatabaseHelper(this);

        // Initialize EditText fields
        usernameEditText = findViewById(R.id.AD_ed);
        passwordEditText = findViewById(R.id.pass_ed);
        confirmPasswordEditText = findViewById(R.id.confirm_pass_ed);
        emailEditText = findViewById(R.id.email_ed);
        phoneEditText = findViewById(R.id.phone_ed);
        addressEditText = findViewById(R.id.address_ed);

        // Register Button Click Listener
        Button registerButton = findViewById(R.id.BTN_LOGIN);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String address = addressEditText.getText().toString();

                // Check if passwords match
                if (password.equals(confirmPassword)) {
                    // Check if all fields are filled
                    if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(email) &&
                            !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(address)) {
                        // Validate phone number
                        if (phone.length() == 11 && phone.startsWith("01")) {
                            // Validate email format
                           // Patterns.EMAIL_ADDRESS is a regular expression pattern provided by Android to validate email addresses.
                               //     matcher(email) creates a matcher object that applies the email pattern to the provided email string.
                           // matches() checks if the entire email string matches the pattern.
                            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                                // Add user to database
                                databaseHelper.addUser(username, password, email, phone, address);
                                Toast.makeText(UserRegistration.this, "User Registered", Toast.LENGTH_SHORT).show();

                                // Redirect to ProductsDisplay activity after successful registration
                                Intent intent1 = new Intent(UserRegistration.this, UserConnectionActivity.class);
                                startActivity(intent1);
                                Toast.makeText(UserRegistration.this, "You are going to UserConnection Activity", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(UserRegistration.this, "Invalid email format. It must be something@something.com", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(UserRegistration.this, "Invalid phone number. It must be 11 digits and start with 01", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(UserRegistration.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(UserRegistration.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Back Button Click Listener
        Button backButton = findViewById(R.id.BTN_Back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(UserRegistration.this, MainActivity.class);
                startActivity(intent2);
            }
        });
    }
}
