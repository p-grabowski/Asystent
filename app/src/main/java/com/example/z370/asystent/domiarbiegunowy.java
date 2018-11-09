package com.example.z370.asystent;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.text.TextUtils.isEmpty;
import static com.example.z370.asystent.Punkty.baza;
import static java.lang.Double.parseDouble;

public class domiarbiegunowy extends Activity {

    Button oblicz, zapisz, wroc, pomoc;
    EditText Ax, Ay, Bx, By, a, d, nazwa_P;
    AutoCompleteTextView nazwa_A, nazwa_B;
    TextView Px, Py;
    Dialog Pomoc;
    boolean dodany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domiarbiegunowy);

        baza = new BazaPunktow(this);

        oblicz = findViewById(R.id.B_oblicz_domiarbieg);
        zapisz = findViewById(R.id.B_zapisz_domiarbieg);
        wroc = findViewById(R.id.B_wroc3);
        pomoc = findViewById(R.id.bT_pomoc_domiarbieg);

        Ax = findViewById(R.id.eT_AX_domiarbieg);
        Ay = findViewById(R.id.eT_AY_domiarbieg);
        Bx = findViewById(R.id.eT_BX_domiarbieg);
        By = findViewById(R.id.eT_BY_domiarbieg);
        a = findViewById(R.id.eT_a_domiarbieg);
        d = findViewById(R.id.eT_d_domiarbieg);

        Px = findViewById(R.id.tV_PX_domiarbieg);
        Py = findViewById(R.id.tV_PY_domiarbieg);

        nazwa_A = findViewById(R.id.eT_nazwa_A_domiarbieg);
        nazwa_B = findViewById(R.id.eT_nazwa_B_domiarbieg);
        nazwa_P =findViewById(R.id.eT_nazwa_P_domiarbieg);

        oblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (    isEmpty(Ax.getText()) ||
                        isEmpty(Ay.getText()) ||
                        isEmpty(Bx.getText()) ||
                        isEmpty(By.getText()) ||
                        isEmpty(a.getText()) ||
                        isEmpty(d.getText())) {
                    Toast.makeText(getApplicationContext(), "Wypełnij wszystkie pola!", Toast.LENGTH_SHORT).show();
                } else {

                    FunkcjeObliczenia.Punkt A = new FunkcjeObliczenia.Punkt();
                    FunkcjeObliczenia.Punkt B = new FunkcjeObliczenia.Punkt();
                    FunkcjeObliczenia.Punkt P = new FunkcjeObliczenia.Punkt();

                    double ea, ed;

                    A.X = parseDouble(String.valueOf(Ax.getText()));
                    A.Y = parseDouble(String.valueOf(Ay.getText()));
                    B.X = parseDouble(String.valueOf(Bx.getText()));
                    B.Y = parseDouble(String.valueOf(By.getText()));
                    ea = parseDouble(String.valueOf(a.getText()));
                    ed = parseDouble(String.valueOf(d.getText()));

                    P = FunkcjeObliczenia.domiarbiegunowy(A, B, ed, ea);

                    Px.setText(FunkcjeObliczenia.kropka(FunkcjeObliczenia.zaokraglij(P.X, 3)));
                    Py.setText(FunkcjeObliczenia.kropka(FunkcjeObliczenia.zaokraglij(P.Y, 3)));
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
                        Pomoc = new Dialog(domiarbiegunowy.this);
                        Pomoc.setContentView(R.layout.pomoc_biegunowy);
                        Pomoc.show();
                    }
                });
            }
        });

        zapisz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isEmpty(nazwa_P.getText())) {
                    Toast.makeText(getApplicationContext(), "Wypełnij wszystkie pola (Nazwa)!", Toast.LENGTH_SHORT).show();
                } else
                dodany = baza.dodajpunkt(nazwa_P.getText().toString(), parseDouble(String.valueOf(Px.getText())), parseDouble(String.valueOf(Py.getText())), 0);
                if(dodany == true)
                    Toast.makeText(domiarbiegunowy.this,"Punkt Zapisany", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(domiarbiegunowy.this,"Punkt nie Zapisany", Toast.LENGTH_LONG).show();
            }
        });

    }
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(), Obliczenia.class));
    }
}
