package com.example.z370.asystent;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class PuntyAdapter extends CursorAdapter {
    public PuntyAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.punkt, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView tvNazwa = (TextView) view.findViewById(R.id.tV_punkt_nazwa);
        TextView tvX = (TextView) view.findViewById(R.id.tV_punkt_x);
        TextView tvY = (TextView) view.findViewById(R.id.tV_punkt_y);
        TextView tvH = (TextView) view.findViewById(R.id.tV_punkt_h);

        // Extract properties from cursor
        String Nazwa = cursor.getString(cursor.getColumnIndexOrThrow("Nazwa"));
        double x = cursor.getDouble(cursor.getColumnIndexOrThrow("X"));
        double y = cursor.getDouble(cursor.getColumnIndexOrThrow("Y"));
        double h = cursor.getDouble(cursor.getColumnIndexOrThrow("H"));

        // Populate fields with extracted properties
        tvNazwa.setText(Nazwa);
        tvX.setText(String.valueOf(x));
        tvY.setText(String.valueOf(y));
        tvH.setText(String.valueOf(h));
    }
}
