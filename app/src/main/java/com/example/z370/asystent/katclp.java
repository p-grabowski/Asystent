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

public class katclp extends Activity {

    Button oblicz, wroc, pomoc;
    EditText Cx, Cy, Lx, Ly, Px, Py;
    TextView W;


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
}
