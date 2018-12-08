package com.example.z370.asystent;

import android.app.Activity;
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

public class spadek extends Activity {

    Button oblicz, wroc;
    EditText Ax, Ay, Ah, Bx, By, Bh;
    TextView odleglosc, spadek;
    AutoCompleteTextView nazwa_A, nazwa_B;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spadek);

        Ax = findViewById(R.id.eT_AX_spad);
        Ay = findViewById(R.id.eT_AY_spad);
        Ah = findViewById(R.id.eT_AH_spad);

        Bx = findViewById(R.id.eT_BX_spad);
        By = findViewById(R.id.eT_BY_spad);
        Bh = findViewById(R.id.eT_BH_spad);

        odleglosc = findViewById(R.id.tV_odleglosc_spad);
        spadek = findViewById(R.id.tV_spadek);

        oblicz = findViewById(R.id.B_oblicz_spadek);
        wroc = findViewById(R.id.B_wroc10);

        nazwa_A = findViewById(R.id.eT_nazwa_A_spad);
        nazwa_B = findViewById(R.id.eT_nazwa_B_spad);

        baza = new BazaPunktow(this);
        Cursor nazwyCursor = baza.pokazcalabaze(baza.idNazwaZbior(Global.WybranyZbior));
        ArrayList<String> ListaNazw = new ArrayList<String>();
        nazwyCursor.moveToFirst();
        while(!nazwyCursor.isAfterLast()) {
            ListaNazw.add(nazwyCursor.getString(nazwyCursor.getColumnIndex("Nazwa")));
            nazwyCursor.moveToNext();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(spadek.this, android.R.layout.simple_dropdown_item_1line, ListaNazw);

        nazwa_A.setAdapter(adapter);
        nazwa_A.setThreshold(1);
        nazwa_B.setAdapter(adapter);
        nazwa_B.setThreshold(1);

        nazwa_A.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nazwa="";

                Toast.makeText(parent.getContext(), "Wybrałeś punkt numer " + parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();

                nazwa = parent.getItemAtPosition(position).toString();

                Cursor XYHCursor = baza.pokazXYH(nazwa, baza.idNazwaZbior(Global.WybranyZbior));
                XYHCursor.moveToFirst();
                Ax.setText(XYHCursor.getString(XYHCursor.getColumnIndex("X")));
                Ay.setText(XYHCursor.getString(XYHCursor.getColumnIndex("Y")));
                Ah.setText(XYHCursor.getString(XYHCursor.getColumnIndex("H")));

            }
        });

        nazwa_B.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nazwa="";

                Toast.makeText(parent.getContext(), "Wybrałeś punkt numer " + parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();

                nazwa = parent.getItemAtPosition(position).toString();

                Cursor XYHCursor = baza.pokazXYH(nazwa, baza.idNazwaZbior(Global.WybranyZbior));
                XYHCursor.moveToFirst();
                Bx.setText(XYHCursor.getString(XYHCursor.getColumnIndex("X")));
                By.setText(XYHCursor.getString(XYHCursor.getColumnIndex("Y")));
                Bh.setText(XYHCursor.getString(XYHCursor.getColumnIndex("H")));

            }
        });

        oblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isEmpty(Ax.getText()) ||
                        isEmpty(Ay.getText()) ||
                        isEmpty(Bx.getText()) ||
                        isEmpty(By.getText()) ||
                        isEmpty(Ah.getText()) ||
                        isEmpty(Bh.getText())
                        ) {
                    Toast.makeText(getApplicationContext(), "Wypełnij wszystkie pola!", Toast.LENGTH_SHORT).show();
                } else {

                    FunkcjeObliczenia.Punkt A = new FunkcjeObliczenia.Punkt();
                    FunkcjeObliczenia.Punkt B = new FunkcjeObliczenia.Punkt();

                    A.X = parseDouble(String.valueOf(Ax.getText()));
                    A.Y = parseDouble(String.valueOf(Ay.getText()));
                    A.H = parseDouble(String.valueOf(Ah.getText()));

                    B.X = parseDouble(String.valueOf(Bx.getText()));
                    B.Y = parseDouble(String.valueOf(By.getText()));
                    B.H = parseDouble(String.valueOf(Bh.getText()));

                    odleglosc.setText("Odległość A-B: " + FunkcjeObliczenia.zaokraglij(FunkcjeObliczenia.odleglosc(A, B), 3) + " m");
                    spadek.setText("Spadek A-B: " + FunkcjeObliczenia.zaokraglij(FunkcjeObliczenia.spadek(A, B), 2) + " %");
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


    }
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(), Obliczenia.class));
    }
}
