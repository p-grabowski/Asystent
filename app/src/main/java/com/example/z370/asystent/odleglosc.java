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

    oblicz = findViewById(R.id.obliczodleglosc);
    Ax = findViewById(R.id.editText_AX);
    Ay = findViewById(R.id.editText_AY);
    Bx = findViewById(R.id.editText_BX);
    By = findViewById(R.id.editText_BY);
    odleglosc = findViewById(R.id.odleglosc);
    azymut = findViewById(R.id.azymut);
    wroc1 = findViewById(R.id.wroc1);

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

                Intent i = new Intent(getApplicationContext(), MainActivity.class);

                finish(); // jeśli chcesz, zlikwidować poprzednie activity, aby nie było go na stosie.

                startActivity(i);

            }
        });

    }
}
