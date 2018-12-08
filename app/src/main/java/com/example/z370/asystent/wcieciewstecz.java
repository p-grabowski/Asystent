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

public class wcieciewstecz extends Activity {

    Button oblicz, zapisz, wroc, pomoc;
    EditText Ax, Ay, Bx, By, Cx , Cy, a, b, nazwa_P;
    TextView Px, Py;
    AutoCompleteTextView nazwa_A, nazwa_B, nazwa_C;
    boolean dodany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wcieciewstecz);

        oblicz = findViewById(R.id.B_oblicz_wstecz);
        zapisz = findViewById(R.id.B_zapisz_wstecz);
        wroc = findViewById(R.id.B_wroc9);
        pomoc = findViewById(R.id.bT_pomoc_wstecz);

        Ax = findViewById(R.id.eT_AX_wstecz);
        Ay = findViewById(R.id.eT_AY_wstecz);
        Bx = findViewById(R.id.eT_BX_wstecz);
        By = findViewById(R.id.eT_BY_wstecz);
        Cx = findViewById(R.id.eT_CX_wstecz);
        Cy = findViewById(R.id.eT_CY_wstecz);
        a = findViewById(R.id.eT_a_wstecz);
        b = findViewById(R.id.eT_b_wstecz);

        Px = findViewById(R.id.tV_PX_wstecz);
        Py = findViewById(R.id.tV_PY_wstecz);

        nazwa_A = findViewById(R.id.eT_nazwa_A_wstecz);
        nazwa_B = findViewById(R.id.eT_nazwa_B_wstecz);
        nazwa_C = findViewById(R.id.eT_nazwa_C_wstecz);
        nazwa_P =findViewById(R.id.eT_nazwa_P_wstecz);
        baza = new BazaPunktow(this);

        Cursor nazwyCursor = baza.pokazcalabaze(baza.idNazwaZbior(Global.WybranyZbior));
        ArrayList<String> ListaNazw = new ArrayList<String>();
        nazwyCursor.moveToFirst();
        while(!nazwyCursor.isAfterLast()) {
            ListaNazw.add(nazwyCursor.getString(nazwyCursor.getColumnIndex("Nazwa")));
            nazwyCursor.moveToNext();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(wcieciewstecz.this, android.R.layout.simple_dropdown_item_1line, ListaNazw);

        nazwa_A.setAdapter(adapter);
        nazwa_A.setThreshold(1);
        nazwa_B.setAdapter(adapter);
        nazwa_B.setThreshold(1);
        nazwa_C.setAdapter(adapter);
        nazwa_C.setThreshold(1);

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
        nazwa_C.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nazwa="";

                Toast.makeText(parent.getContext(), "Wybrałeś punkt numer " + parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();

                nazwa = parent.getItemAtPosition(position).toString();

                Cursor XYHCursor = baza.pokazXYH(nazwa, baza.idNazwaZbior(Global.WybranyZbior));
                XYHCursor.moveToFirst();
                Cx.setText(XYHCursor.getString(XYHCursor.getColumnIndex("X")));
                Cy.setText(XYHCursor.getString(XYHCursor.getColumnIndex("Y")));
            }
        });

        oblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isEmpty(Ax.getText()) ||
                        isEmpty(Ay.getText()) ||
                        isEmpty(Bx.getText()) ||
                        isEmpty(By.getText()) ||
                        isEmpty(Cx.getText()) ||
                        isEmpty(Cy.getText()) ||
                        isEmpty(a.getText()) ||
                        isEmpty(b.getText())
                        ) {
                    Toast.makeText(getApplicationContext(), "Wypełnij wszystkie pola!", Toast.LENGTH_SHORT).show();
                } else {

                    FunkcjeObliczenia.Punkt A = new FunkcjeObliczenia.Punkt();
                    FunkcjeObliczenia.Punkt B = new FunkcjeObliczenia.Punkt();
                    FunkcjeObliczenia.Punkt C = new FunkcjeObliczenia.Punkt();
                    FunkcjeObliczenia.Punkt P;

                    double ea, eb;

                    A.X = parseDouble(String.valueOf(Ax.getText()));
                    A.Y = parseDouble(String.valueOf(Ay.getText()));
                    B.X = parseDouble(String.valueOf(Bx.getText()));
                    B.Y = parseDouble(String.valueOf(By.getText()));
                    ea = parseDouble(String.valueOf(a.getText()));
                    eb = parseDouble(String.valueOf(b.getText()));

                    P = FunkcjeObliczenia.wcieciewstecz(A, B, C, ea, eb);

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
                        Pomoc = new Dialog(wcieciewstecz.this);
                        Pomoc.setContentView(R.layout.pomoc_wstecz);
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
                    Toast.makeText(wcieciewstecz.this,"Punkt Zapisany", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(wcieciewstecz.this,"Punkt nie Zapisany", Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(), Obliczenia.class));
    }
}
