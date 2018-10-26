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

public class wciecieliniowe extends Activity {

    Button oblicz, zapisz, wroc, pomoc;
    EditText Ax, Ay, Bx, By, a, b;
    TextView Px, Py;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wciecieliniowe);

        oblicz = findViewById(R.id.B_oblicz_liniowe);
        zapisz = findViewById(R.id.B_zapisz_liniowe);
        wroc = findViewById(R.id.B_wroc8);
        pomoc = findViewById(R.id.bT_pomoc_liniowe);

        Ax = findViewById(R.id.eT_AX_liniowe);
        Ay = findViewById(R.id.eT_AY_liniowe);
        Bx = findViewById(R.id.eT_BX_liniowe);
        By = findViewById(R.id.eT_BY_liniowe);
        a = findViewById(R.id.eT_a_liniowe);
        b = findViewById(R.id.eT_b_liniowe);

        Px = findViewById(R.id.tV_PX_liniowe);
        Py = findViewById(R.id.tV_PY_liniowe);


        oblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isEmpty(Ax.getText()) ||
                        isEmpty(Ay.getText()) ||
                        isEmpty(Bx.getText()) ||
                        isEmpty(By.getText()) ||
                        isEmpty(a.getText()) ||
                        isEmpty(b.getText())
                        ) {
                    Toast.makeText(getApplicationContext(), "Wypełnij wszystkie pola!", Toast.LENGTH_SHORT).show();
                } else {

                    FunkcjeObliczenia.Punkt A = new FunkcjeObliczenia.Punkt();
                    FunkcjeObliczenia.Punkt B = new FunkcjeObliczenia.Punkt();
                    FunkcjeObliczenia.Punkt P = new FunkcjeObliczenia.Punkt();

                    double ea, eb;

                    A.X = parseDouble(String.valueOf(Ax.getText()));
                    A.Y = parseDouble(String.valueOf(Ay.getText()));
                    B.X = parseDouble(String.valueOf(Bx.getText()));
                    B.Y = parseDouble(String.valueOf(By.getText()));
                    ea = parseDouble(String.valueOf(a.getText()));
                    eb = parseDouble(String.valueOf(b.getText()));

                    P = FunkcjeObliczenia.wciecieliniowe(A, B, ea, eb);

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
            public void onClick(View v) {
                pomoc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Dialog Pomoc;
                        Pomoc = new Dialog(wciecieliniowe.this);
                        Pomoc.setContentView(R.layout.pomoc_liniowe);
                        Pomoc.show();
                    }
                });
            }
        });
    }
}
