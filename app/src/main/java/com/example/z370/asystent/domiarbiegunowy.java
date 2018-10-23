package com.example.z370.asystent;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static java.lang.Double.parseDouble;

public class domiarbiegunowy extends Activity {

    Button oblicz, zapisz, wroc, pomoc, ok;
    EditText Ax, Ay, Bx, By, a, d;
    TextView Px, Py;
    Dialog Pomoc;
    ImageView obrazek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domiarbiegunowy);

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


        oblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

                Px.setText("P X = "+P.X);
                Py.setText("P Y = "+P.Y);
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

            }
        });
    }

    public void oknopomocy(){
        Pomoc = new Dialog(domiarbiegunowy.this);
        Pomoc.setContentView(R.layout.okno_pomocy);
        Pomoc.setTitle("Okno pomocy");

        ok = findViewById(R.id.bT_pomoc_ok);
        ok.setEnabled(true);

        obrazek = findViewById(R.id.iV_pomoc_obrazek);
        obrazek.setImage/////////////////////////////////////////


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pomoc.cancel();
            }
        });

        Pomoc.show();
    }

}
