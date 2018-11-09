package com.example.z370.asystent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.text.TextUtils.isEmpty;
import static java.lang.Double.parseDouble;

public class spadek extends Activity {

    Button oblicz, wroc;
    EditText Ax, Ay, Ah, Bx, By, Bh;
    TextView odleglosc, spadek;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spadek);

        Ax = findViewById(R.id.eT_AX_spad);
        Ay = findViewById(R.id.eT_AY_spad);
        Ah = findViewById(R.id.eT_AH_spad);

        Bx = findViewById(R.id.eT_BX_spad);
        By = findViewById(R.id.eT_BY_spad);
        Bh = findViewById(R.id.eT_BH_spad);

        odleglosc = findViewById(R.id.tV_odleglosc_spad);
        spadek = findViewById(R.id.tV_spadek);

        oblicz = findViewById(R.id.B_oblicz_spadek);
        wroc = findViewById(R.id.B_wroc10);

        oblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isEmpty(Ax.getText()) ||
                        isEmpty(Ay.getText()) ||
                        isEmpty(Bx.getText()) ||
                        isEmpty(By.getText()) ||
                        isEmpty(Ah.getText()) ||
                        isEmpty(Bh.getText())
                        ) {
                    Toast.makeText(getApplicationContext(), "Wypełnij wszystkie pola!", Toast.LENGTH_SHORT).show();
                } else {

                    FunkcjeObliczenia.Punkt A = new FunkcjeObliczenia.Punkt();
                    FunkcjeObliczenia.Punkt B = new FunkcjeObliczenia.Punkt();

                    A.X = parseDouble(String.valueOf(Ax.getText()));
                    A.Y = parseDouble(String.valueOf(Ay.getText()));
                    A.H = parseDouble(String.valueOf(Ah.getText()));

                    B.X = parseDouble(String.valueOf(Bx.getText()));
                    B.Y = parseDouble(String.valueOf(By.getText()));
                    B.H = parseDouble(String.valueOf(Bh.getText()));

                    odleglosc.setText("Odległość A-B: " + FunkcjeObliczenia.zaokraglij(FunkcjeObliczenia.odleglosc(A, B), 3) + " m");
                    spadek.setText("Spadek A-B: " + FunkcjeObliczenia.zaokraglij(FunkcjeObliczenia.spadek(A, B), 2) + " %");
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


    }
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(), Obliczenia.class));
    }
}
