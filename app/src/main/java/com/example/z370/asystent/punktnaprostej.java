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
import static com.example.z370.asystent.FunkcjeObliczenia.kropka;
import static com.example.z370.asystent.Punkty.baza;
import static java.lang.Double.parseDouble;

public class punktnaprostej extends Activity {

    Button oblicz, zapisz, wroc, pomoc;
    EditText Ax, Ay, Bx, By, l, nazwa_P;
    TextView Px, Py;
    AutoCompleteTextView nazwa_A, nazwa_B;
    boolean dodany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punktnaprostej);

        oblicz = findViewById(R.id.B_oblicz_punktnaprost);
        zapisz = findViewById(R.id.B_zapisz_punktnaprost);
        wroc = findViewById(R.id.B_wroc4);
        pomoc = findViewById(R.id.bT_pomoc_punktnaprost);

        Ax = findViewById(R.id.eT_AX_punktnaprost);
        Ay = findViewById(R.id.eT_AY_punktnaprost);
        Bx = findViewById(R.id.eT_BX_punktnaprost);
        By = findViewById(R.id.eT_BY_punktnaprost);
        l = findViewById(R.id.eT_l_punktnaprost);

        Px = findViewById(R.id.tV_PX_punktnaprost);
        Py = findViewById(R.id.tV_PY_punktnaprost);

        nazwa_A = findViewById(R.id.eT_nazwa_A_punktnaprost);
        nazwa_B = findViewById(R.id.eT_nazwa_B_punktnaprost);
        nazwa_P =findViewById(R.id.eT_nazwa_P_punktnaprost);
        baza = new BazaPunktow(this);

        Cursor nazwyCursor = baza.pokazcalabaze(baza.idNazwaZbior(Global.WybranyZbior));
        ArrayList<String> ListaNazw = new ArrayList<String>();
        nazwyCursor.moveToFirst();
        while(!nazwyCursor.isAfterLast()) {
            ListaNazw.add(nazwyCursor.getString(nazwyCursor.getColumnIndex("Nazwa")));
            nazwyCursor.moveToNext();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(punktnaprostej.this, android.R.layout.simple_dropdown_item_1line, ListaNazw);


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
            }
        });

        oblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isEmpty(Ax.getText()) ||
                        isEmpty(Ay.getText()) ||
                        isEmpty(Bx.getText()) ||
                        isEmpty(By.getText()) ||
                        isEmpty(l.getText())
                        ) {
                    Toast.makeText(getApplicationContext(), "Wypełnij wszystkie pola!", Toast.LENGTH_SHORT).show();
                } else {

                    FunkcjeObliczenia.Punkt A = new FunkcjeObliczenia.Punkt();
                    FunkcjeObliczenia.Punkt B = new FunkcjeObliczenia.Punkt();
                    FunkcjeObliczenia.Punkt P;

                    double el;

                    A.X = parseDouble(String.valueOf(Ax.getText()));
                    A.Y = parseDouble(String.valueOf(Ay.getText()));
                    B.X = parseDouble(String.valueOf(Bx.getText()));
                    B.Y = parseDouble(String.valueOf(By.getText()));
                    el = parseDouble(String.valueOf(l.getText()));

                    P = FunkcjeObliczenia.punktnaprostej(A, B, el);

                    Px.setText(kropka(FunkcjeObliczenia.zaokraglij(P.X, 3)));
                    Py.setText(kropka(FunkcjeObliczenia.zaokraglij(P.Y, 3)));
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
                        Pomoc = new Dialog(punktnaprostej.this);
                        Pomoc.setContentView(R.layout.pomoc_punktnaprostej);
                        Pomoc.show();
                    }
                });
            }
        });

        zapisz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isEmpty(nazwa_P.getText()) || isEmpty(Px.getText())) {
                    Toast.makeText(getApplicationContext(), "Wypełnij pole Nazwa i oblicz!", Toast.LENGTH_SHORT).show();
                } else
                    dodany = baza.dodajpunkt(nazwa_P.getText().toString(), parseDouble(String.valueOf(Px.getText())), parseDouble(String.valueOf(Py.getText())), 0, baza.idNazwaZbior(Global.WybranyZbior));
                if(dodany == true)
                    Toast.makeText(punktnaprostej.this,"Punkt Zapisany", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(punktnaprostej.this,"Punkt nie Zapisany", Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(), Obliczenia.class));
    }
}
