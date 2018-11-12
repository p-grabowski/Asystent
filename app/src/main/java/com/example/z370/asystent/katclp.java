package com.example.z370.asystent;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.text.TextUtils.isEmpty;
import static com.example.z370.asystent.Punkty.baza;
import static java.lang.Double.parseDouble;

public class katclp extends Activity {

    Button oblicz, wroc, pomoc;
    EditText Cx, Cy, Lx, Ly, Px, Py;
    TextView W;
AutoCompleteTextView nazwa_C, nazwa_L, nazwa_P;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katclp);

        oblicz = findViewById(R.id.B_oblicz_katclp);
        wroc = findViewById(R.id.B_wroc5);
        pomoc = findViewById(R.id.bT_pomoc_katclp);

        Cx = findViewById(R.id.eT_CX_katclp);
        Cy = findViewById(R.id.eT_CY_katclp);
        Lx = findViewById(R.id.eT_LX_katclp);
        Ly = findViewById(R.id.eT_LY_katclp);
        Px = findViewById(R.id.eT_PX_katclp);
        Py = findViewById(R.id.eT_PY_katclp);

        W = findViewById(R.id.tV_W_katclp);

        nazwa_C = findViewById(R.id.eT_nazwa_C_katclp);
        nazwa_L = findViewById(R.id.eT_nazwa_L_katclp);
        nazwa_P = findViewById(R.id.eT_nazwa_P_katclp);

        ////////////////////////     autouzupełniania
        baza = new BazaPunktow(this);

        Cursor nazwyCursor = baza.pokazcalabaze();
        ArrayList<String> ListaNazw = new ArrayList<String>();
        nazwyCursor.moveToFirst();
        while(!nazwyCursor.isAfterLast()) {
            ListaNazw.add(nazwyCursor.getString(nazwyCursor.getColumnIndex("Nazwa")));
            nazwyCursor.moveToNext();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(katclp.this, android.R.layout.simple_dropdown_item_1line, ListaNazw);

        nazwa_C.setAdapter(adapter);
        nazwa_C.setThreshold(1);
        nazwa_L.setAdapter(adapter);
        nazwa_L.setThreshold(1);
        nazwa_P.setAdapter(adapter);
        nazwa_P.setThreshold(1);

        nazwa_C.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nazwa="";

                Toast.makeText(parent.getContext(), "Wybrałeś punkt numer " + parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();

                nazwa = parent.getItemAtPosition(position).toString();

                Cursor XYHCursor = baza.pokazXYH(nazwa);
                XYHCursor.moveToFirst();
                Cx.setText(XYHCursor.getString(XYHCursor.getColumnIndex("X")));
                Cy.setText(XYHCursor.getString(XYHCursor.getColumnIndex("Y")));
            }
        });
        nazwa_L.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nazwa="";

                Toast.makeText(parent.getContext(), "Wybrałeś punkt numer " + parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();

                nazwa = parent.getItemAtPosition(position).toString();

                Cursor XYHCursor = baza.pokazXYH(nazwa);
                XYHCursor.moveToFirst();
                Lx.setText(XYHCursor.getString(XYHCursor.getColumnIndex("X")));
                Ly.setText(XYHCursor.getString(XYHCursor.getColumnIndex("Y")));
            }
        });
        nazwa_P.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nazwa="";

                Toast.makeText(parent.getContext(), "Wybrałeś punkt numer " + parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();

                nazwa = parent.getItemAtPosition(position).toString();

                Cursor XYHCursor = baza.pokazXYH(nazwa);
                XYHCursor.moveToFirst();
                Px.setText(XYHCursor.getString(XYHCursor.getColumnIndex("X")));
                Py.setText(XYHCursor.getString(XYHCursor.getColumnIndex("Y")));
            }
        });

        oblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(     isEmpty(Cx.getText()) ||
                        isEmpty(Cy.getText()) ||
                        isEmpty(Lx.getText()) ||
                        isEmpty(Ly.getText()) ||
                        isEmpty(Px.getText()) ||
                        isEmpty(Py.getText()))
                {
                    Toast.makeText(getApplicationContext(), "Wypełnij wszystkie pola!",Toast.LENGTH_SHORT).show();
                }
                else{

                FunkcjeObliczenia.Punkt C = new FunkcjeObliczenia.Punkt();
                FunkcjeObliczenia.Punkt L = new FunkcjeObliczenia.Punkt();
                FunkcjeObliczenia.Punkt P = new FunkcjeObliczenia.Punkt();

                double w;

                C.X = parseDouble(String.valueOf(Cx.getText()));
                C.Y = parseDouble(String.valueOf(Cy.getText()));
                L.X = parseDouble(String.valueOf(Lx.getText()));
                L.Y = parseDouble(String.valueOf(Ly.getText()));
                P.X = parseDouble(String.valueOf(Px.getText()));
                P.Y = parseDouble(String.valueOf(Py.getText()));


                w = FunkcjeObliczenia.katCLP(C, L, P);

                W.setText("Kąt = " + FunkcjeObliczenia.zaokraglij(w, 4));
            }
            }
        });


        wroc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), Obliczenia.class));

            }
        });
        pomoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pomoc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Dialog Pomoc;
                        Pomoc = new Dialog(katclp.this);
                        Pomoc.setContentView(R.layout.pomoc_katclp);
                        Pomoc.show();
                    }
                });
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(), Obliczenia.class));
    }
}
