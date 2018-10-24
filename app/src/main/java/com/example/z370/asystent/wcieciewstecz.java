package com.example.z370.asystent;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Double.parseDouble;

public class wcieciewstecz extends Activity {

    Button oblicz, zapisz, wroc, pomoc;
    EditText Ax, Ay, Bx, By, Cx , Cy, a, b;
    TextView Px, Py;


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


        oblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FunkcjeObliczenia.Punkt A = new FunkcjeObliczenia.Punkt();
                FunkcjeObliczenia.Punkt B = new FunkcjeObliczenia.Punkt();
                FunkcjeObliczenia.Punkt C = new FunkcjeObliczenia.Punkt();
                FunkcjeObliczenia.Punkt P = new FunkcjeObliczenia.Punkt();

                double ea, eb;

                A.X = parseDouble(String.valueOf(Ax.getText()));
                A.Y = parseDouble(String.valueOf(Ay.getText()));
                B.X = parseDouble(String.valueOf(Bx.getText()));
                B.Y = parseDouble(String.valueOf(By.getText()));
                ea = parseDouble(String.valueOf(a.getText()));
                eb = parseDouble(String.valueOf(b.getText()));

                P = FunkcjeObliczenia.wcieciewstecz(A, B, C, ea, eb);

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
    }
}
