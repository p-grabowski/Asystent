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

public class przeciecieprostych extends Activity {

    Button oblicz, zapisz, wroc, pomoc;
    EditText Ax, Ay, Bx, By, Cx, Cy, Dx, Dy;
    TextView Px, Py;


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

                    FunkcjeObliczenia.Punkt P = new FunkcjeObliczenia.Punkt();

                    A.X = parseDouble(String.valueOf(Ax.getText()));
                    A.Y = parseDouble(String.valueOf(Ay.getText()));
                    B.X = parseDouble(String.valueOf(Bx.getText()));
                    B.Y = parseDouble(String.valueOf(By.getText()));
                    C.X = parseDouble(String.valueOf(Cx.getText()));
                    C.Y = parseDouble(String.valueOf(Cy.getText()));
                    D.X = parseDouble(String.valueOf(Dx.getText()));
                    D.Y = parseDouble(String.valueOf(Dy.getText()));

                    P = FunkcjeObliczenia.przeciecieprostych(A, B, C, D);

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
                        Pomoc = new Dialog(przeciecieprostych.this);
                        Pomoc.setContentView(R.layout.pomoc_przeciecieprostych);
                        Pomoc.show();
                    }
                });
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(), Obliczenia.class));
    }
}
