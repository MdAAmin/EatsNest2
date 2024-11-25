package com.example.eatsnest;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductAdapter extends CursorAdapter {

    public ProductAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.activity_product_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameTextView = view.findViewById(R.id.productName);
        TextView priceTextView = view.findViewById(R.id.productPrice);
        TextView quantityTextView = view.findViewById(R.id.productQuantity);
        ImageView productImageView = view.findViewById(R.id.productImage);

        if (nameTextView == null) {
            Log.e("ProductAdapter", "nameTextView is null");
        }
        if (priceTextView == null) {
            Log.e("ProductAdapter", "priceTextView is null");
        }
        if (quantityTextView == null) {
            Log.e("ProductAdapter", "quantityTextView is null");
        }
        if (productImageView == null) {
            Log.e("ProductAdapter", "productImageView is null");
        }

        // Proceed only if all views are not null
        if (nameTextView != null && priceTextView != null && quantityTextView != null && productImageView != null) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PRODUCT_NAME));
            double price = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PRODUCT_PRICE));
            int quantity = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PRODUCT_QUANTITY));
            byte[] imageBytes = cursor.getBlob(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PRODUCT_IMAGE));

            // Set text and image
            nameTextView.setText(name);
            priceTextView.setText(String.valueOf(price));
            quantityTextView.setText(String.valueOf(quantity));
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            productImageView.setImageBitmap(bitmap);
        }
    }
}
