package com.example.eatsnest;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReviewActivity extends AppCompatActivity {

    DatabaseHelper db;
    TextView textViewRestaurantName;
    EditText editTextReview;
    RatingBar ratingBar;
    Button buttonSubmitReview;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        db = new DatabaseHelper(this);

        textViewRestaurantName = findViewById(R.id.textViewRestaurantName);
        editTextReview = findViewById(R.id.editTextReview);
        ratingBar = findViewById(R.id.ratingBar);
        buttonSubmitReview = findViewById(R.id.buttonSubmitReview);
        btnBack = findViewById(R.id.btn_back11);

        textViewRestaurantName.setText(getString(R.string.welcome_to_hotel_dreamland));

        buttonSubmitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String restaurantName = textViewRestaurantName.getText().toString().trim();
                String review = editTextReview.getText().toString().trim();
                float rating = ratingBar.getRating();

                if (TextUtils.isEmpty(review)) {
                    Toast.makeText(ReviewActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else if (rating < 1 || rating > 5) {
                    Toast.makeText(ReviewActivity.this, "Rating must be between 1 and 5", Toast.LENGTH_SHORT).show();
                } else {
                    // Insert into database
                    boolean isInserted = db.insertReview(restaurantName, review, (int) rating);

                    if (isInserted) {
                        Toast.makeText(ReviewActivity.this, "Review submitted successfully", Toast.LENGTH_SHORT).show();
                        // Clear fields after successful submission
                        editTextReview.setText("");
                        ratingBar.setRating(0);
                    } else {
                        Toast.makeText(ReviewActivity.this, "Error submitting review", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ReviewActivity.this , UserConnectionActivity.class);
                startActivity(intent1);
            }
        });
    }
}
