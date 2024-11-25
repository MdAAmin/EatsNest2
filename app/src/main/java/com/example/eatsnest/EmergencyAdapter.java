package com.example.eatsnest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class EmergencyAdapter extends CursorAdapter {

    public EmergencyAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    private static class ViewHolder {
        TextView NameView;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.emergency_list, parent, false);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.NameView = view.findViewById(R.id.NameTextView);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        @SuppressLint("Range") String productName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ORDER_PRODUCT_NAME));



        viewHolder.NameView.setText(productName);
    }
}
