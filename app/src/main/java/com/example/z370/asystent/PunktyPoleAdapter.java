package com.example.z370.asystent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PunktyPoleAdapter  extends ArrayAdapter<PolePow.ListaP> {
        public PunktyPoleAdapter(Context context, ArrayList<PolePow.ListaP> ListaP) {
            super(context, 0, ListaP);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            PolePow.ListaP punkt = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.punkt, parent, false);
            }
            // Lookup view for data population
            TextView tvNazwa = (TextView) convertView.findViewById(R.id.tV_punkt_nazwa);
            TextView tvX = (TextView) convertView.findViewById(R.id.tV_punkt_x);
            TextView tvY = (TextView) convertView.findViewById(R.id.tV_punkt_y);
            TextView tvH = (TextView) convertView.findViewById(R.id.tV_punkt_h);

            // Populate the data into the template view using the data object
            tvNazwa.setText(punkt.Nazwa);
            tvX.setText("X: " + String.valueOf(punkt.X));
            tvY.setText("Y: " + String.valueOf(punkt.Y));
            tvH.setText("H: " + String.valueOf(punkt.H));
            // Return the completed view to render on screen
            return convertView;
        }
    }

