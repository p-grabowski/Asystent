package com.example.z370.asystent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Double.parseDouble;


public class odleglosc extends Activity {

Button oblicz, wroc1;
EditText Ax, Ay, Bx, By;
TextView odleglosc, azymut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odleglosc);

    Ax = findViewById(R.id.eT_AX_odl);
    Ay = findViewById(R.id.eT_AY_odl);
    Bx = findViewById(R.id.eT_BX_odl);
    By = findViewById(R.id.eT_BY_odl);

    odleglosc = findViewById(R.id.tV_odleglosc);
    azymut = findViewById(R.id.tV_azymut);

    oblicz = findViewById(R.id.B_oblicz_odleglosc);
    wroc1 = findViewById(R.id.B_wroc1);

        oblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FunkcjeObliczenia.Punkt A = new FunkcjeObliczenia.Punkt();
                FunkcjeObliczenia.Punkt B = new FunkcjeObliczenia.Punkt();

                A.X = parseDouble(String.valueOf(Ax.getText()));
                A.Y = parseDouble(String.valueOf(Ay.getText()));
                B.X = parseDouble(String.valueOf(Bx.getText()));
                B.Y = parseDouble(String.valueOf(By.getText()));

                odleglosc.setText("Odległość A-B: "+FunkcjeObliczenia.odleglosc(A, B));
                azymut.setText("Azymut A-B: "+FunkcjeObliczenia.azymut(A,B));

            }
        });

        wroc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }
}
