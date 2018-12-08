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

public class przeciecieprostych extends Activity {

    Button oblicz, zapisz, wroc, pomoc;
    EditText Ax, Ay, Bx, By, Cx, Cy, Dx, Dy, nazwa_P;
    TextView Px, Py;
    AutoCompleteTextView nazwa_A, nazwa_B, nazwa_C, nazwa_D;
    boolean dodany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_przeciecieprostych);

        oblicz = findViewById(R.id.B_oblicz_przeciecie);
        zapisz = findViewById(R.id.B_zapisz_przeciecie);
        wroc = findViewById(R.id.B_wroc6);
        pomoc = findViewById(R.id.bT_pomoc_przeciecie);

        Ax = findViewById(R.id.eT_AX_przeciecie);
        Ay = findViewById(R.id.eT_AY_przeciecie);
        Bx = findViewById(R.id.eT_BX_przeciecie);
        By = findViewById(R.id.eT_BY_przeciecie);
        Cx = findViewById(R.id.eT_CX_przeciecie);
        Cy = findViewById(R.id.eT_CY_przeciecie);
        Dx = findViewById(R.id.eT_DX_przeciecie);
        Dy = findViewById(R.id.eT_DY_przeciecie);

        Px = findViewById(R.id.tV_PX_przeciecie);
        Py = findViewById(R.id.tV_PY_przeciecie);

        nazwa_A = findViewById(R.id.eT_nazwa_A_przeciecie);
        nazwa_B = findViewById(R.id.eT_nazwa_B_przeciecie);
        nazwa_C = findViewById(R.id.eT_nazwa_C_przeciecie);
        nazwa_D = findViewById(R.id.eT_nazwa_D_przeciecie);
        nazwa_P = findViewById(R.id.eT_nazwa_P_przeciecie);

        ////////////////////////     autouzupełniania
        baza = new BazaPunktow(this);

        Cursor nazwyCursor = baza.pokazcalabaze(baza.idNazwaZbior(Global.WybranyZbior));
        ArrayList<String> ListaNazw = new ArrayList<String>();
        nazwyCursor.moveToFirst();
        while(!nazwyCursor.isAfterLast()) {
            ListaNazw.add(nazwyCursor.getString(nazwyCursor.getColumnIndex("Nazwa")));
            nazwyCursor.moveToNext();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(przeciecieprostych.this, android.R.layout.simple_dropdown_item_1line, ListaNazw);

        nazwa_A.setAdapter(adapter);
        nazwa_A.setThreshold(1);
        nazwa_B.setAdapter(adapter);
        nazwa_B.setThreshold(1);
        nazwa_C.setAdapter(adapter);
        nazwa_C.setThreshold(1);
        nazwa_D.setAdapter(adapter);
        nazwa_D.setThreshold(1);

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

        nazwa_D.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nazwa="";

                Toast.makeText(parent.getContext(), "Wybrałeś punkt numer " + parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();

                nazwa = parent.getItemAtPosition(position).toString();

                Cursor XYHCursor = baza.pokazXYH(nazwa, baza.idNazwaZbior(Global.WybranyZbior));
                XYHCursor.moveToFirst();
                Dx.setText(XYHCursor.getString(XYHCursor.getColumnIndex("X")));
                Dy.setText(XYHCursor.getString(XYHCursor.getColumnIndex("Y")));
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
                        isEmpty(Dx.getText()) ||
                        isEmpty(Dy.getText())
                        ) {
                    Toast.makeText(getApplicationContext(), "Wypełnij wszystkie pola!", Toast.LENGTH_SHORT).show();
                } else {

                    FunkcjeObliczenia.Punkt A = new FunkcjeObliczenia.Punkt();
                    FunkcjeObliczenia.Punkt B = new FunkcjeObliczenia.Punkt();
                    FunkcjeObliczenia.Punkt C = new FunkcjeObliczenia.Punkt();
                    FunkcjeObliczenia.Punkt D = new FunkcjeObliczenia.Punkt();

                    FunkcjeObliczenia.Punkt P;

                    A.X = parseDouble(String.valueOf(Ax.getText()));
                    A.Y = parseDouble(String.valueOf(Ay.getText()));
                    B.X = parseDouble(String.valueOf(Bx.getText()));
                    B.Y = parseDouble(String.valueOf(By.getText()));
                    C.X = parseDouble(String.valueOf(Cx.getText()));
                    C.Y = parseDouble(String.valueOf(Cy.getText()));
                    D.X = parseDouble(String.valueOf(Dx.getText()));
                    D.Y = parseDouble(String.valueOf(Dy.getText()));

                    P = FunkcjeObliczenia.przeciecieprostych(A, B, C, D);

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
                        Pomoc = new Dialog(przeciecieprostych.this);
                        Pomoc.setContentView(R.layout.pomoc_przeciecieprostych);
                        Pomoc.show();
                    }
                });
            }
        });

        zapisz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isEmpty(nazwa_P.getText()) || isEmpty(Px.getText())) {
                    Toast.makeText(getApplicationContext(), "Wypełnij wszystkie pole Nazwa i oblicz!", Toast.LENGTH_SHORT).show();
                } else
                    dodany = baza.dodajpunkt(nazwa_P.getText().toString(), parseDouble(String.valueOf(Px.getText())), parseDouble(String.valueOf(Py.getText())), 0, baza.idNazwaZbior(Global.WybranyZbior));
                if(dodany == true)
                    Toast.makeText(przeciecieprostych.this,"Punkt Zapisany", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(przeciecieprostych.this,"Punkt nie Zapisany", Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(), Obliczenia.class));
    }
}
