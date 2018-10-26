package com.example.z370.asystent;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.text.TextUtils.isEmpty;
import static java.lang.Double.parseDouble;

public class domiarprostokatny extends Activity {

    Button oblicz, zapisz, wroc, pomoc;
    EditText Ax, Ay, Bx, By, l, h;
    TextView Px, Py;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domiarprostokatny);

        oblicz = findViewById(R.id.B_oblicz_domiarprost);
        zapisz = findViewById(R.id.B_zapisz_domiarprost);
        wroc = findViewById(R.id.B_wroc2);
        pomoc = findViewById(R.id.bT_pomoc_domiarprost);

        Ax = findViewById(R.id.eT_AX_domiarprost);
        Ay = findViewById(R.id.eT_AY_domiarprost);
        Bx = findViewById(R.id.eT_BX_domiarprost);
        By = findViewById(R.id.eT_BY_domiarprost);
        l = findViewById(R.id.eT_l_domiarprost);
        h = findViewById(R.id.eT_h_domiarprost);

        Px = findViewById(R.id.tV_PX_domiarprost);
        Py = findViewById(R.id.tV_PY_domiarprost);


        oblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isEmpty(Ax.getText()) ||
                        isEmpty(Ay.getText()) ||
                        isEmpty(Bx.getText()) ||
                        isEmpty(By.getText()) ||
                        isEmpty(l.getText()) ||
                        isEmpty(h.getText())
                        ) {
                    Toast.makeText(getApplicationContext(), "Wypełnij wszystkie pola!", Toast.LENGTH_SHORT).show();
                } else {

                    FunkcjeObliczenia.Punkt A = new FunkcjeObliczenia.Punkt();
                    FunkcjeObliczenia.Punkt B = new FunkcjeObliczenia.Punkt();
                    FunkcjeObliczenia.Punkt P = new FunkcjeObliczenia.Punkt();

                    double el, eh;

                    A.X = parseDouble(String.valueOf(Ax.getText()));
                    A.Y = parseDouble(String.valueOf(Ay.getText()));
                    B.X = parseDouble(String.valueOf(Bx.getText()));
                    B.Y = parseDouble(String.valueOf(By.getText()));
                    el = parseDouble(String.valueOf(l.getText()));
                    eh = parseDouble(String.valueOf(h.getText()));

                    P = FunkcjeObliczenia.domiarprost(A, B, el, eh);

                    Px.setText("P X = " + FunkcjeObliczenia.zaokraglij(P.X, 3));
                    Py.setText("P Y = " + FunkcjeObliczenia.zaokraglij(P.Y, 3));
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
            public void onClick(View view) {
                pomoc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Dialog Pomoc;
                        Pomoc = new Dialog(domiarprostokatny.this);
                        Pomoc.setContentView(R.layout.pomoc_prostokatny);
                        Pomoc.show();
                    }
                });
            }
        });
    }
}
