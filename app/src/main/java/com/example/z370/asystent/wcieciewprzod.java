package com.example.z370.asystent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Double.parseDouble;

public class wcieciewprzod extends Activity {

    Button oblicz, zapisz, wroc;
    EditText Ax, Ay, Bx, By, a, b;
    TextView Px, Py;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wcieciewprzod);

        oblicz = findViewById(R.id.B_oblicz_wprzod);
        zapisz = findViewById(R.id.B_zapisz_wprzod);
        wroc = findViewById(R.id.B_wroc7);

        Ax = findViewById(R.id.eT_AX_wprzod);
        Ay = findViewById(R.id.eT_AY_wprzod);
        Bx = findViewById(R.id.eT_BX_wprzod);
        By = findViewById(R.id.eT_BY_wprzod);
        a = findViewById(R.id.eT_a_wprzod);
        b = findViewById(R.id.eT_b_wprzod);

        Px = findViewById(R.id.tV_PX_wprzod);
        Py = findViewById(R.id.tV_PY_wprzod);


        oblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

                P = FunkcjeObliczenia.wcieciewprzod(A, B, ea, eb);

                Px.setText("P X = "+P.X);
                Py.setText("P Y = "+P.Y);
            }
        });


        wroc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });

    }
}
