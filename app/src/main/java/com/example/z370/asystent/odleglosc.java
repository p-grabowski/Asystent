package com.example.z370.asystent;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.text.TextUtils.isEmpty;
import static java.lang.Double.parseDouble;


public class odleglosc extends Activity {

Button oblicz, wroc1, pomoc;
EditText Ax, Ay, Bx, By;
TextView odleglosc, azymut;
Dialog Pomoc;

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
        pomoc = findViewById(R.id.bT_pomoc_odl);

        oblicz = findViewById(R.id.B_oblicz_odleglosc);
        wroc1 = findViewById(R.id.B_wroc1);
        pomoc = findViewById(R.id.bT_pomoc_odl);



            oblicz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(     isEmpty(Ax.getText()) ||
                            isEmpty(Ay.getText()) ||
                            isEmpty(Bx.getText()) ||
                            isEmpty(By.getText()) )
                    {
                        Toast.makeText(getApplicationContext(), "Wypełnij wszystkie pola!",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        FunkcjeObliczenia.Punkt A = new FunkcjeObliczenia.Punkt();
                        FunkcjeObliczenia.Punkt B = new FunkcjeObliczenia.Punkt();

                        A.X = parseDouble(String.valueOf(Ax.getText()));
                        A.Y = parseDouble(String.valueOf(Ay.getText()));
                        B.X = parseDouble(String.valueOf(Bx.getText()));
                        B.Y = parseDouble(String.valueOf(By.getText()));

                        odleglosc.setText("Odległość A-B: " + FunkcjeObliczenia.zaokraglij(FunkcjeObliczenia.odleglosc(A, B), 3) + " m");
                        azymut.setText("Azymut A-B: " + FunkcjeObliczenia.zaokraglij(FunkcjeObliczenia.azymut(A, B), 4));
                    }
                  }
                });


        wroc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), Obliczenia.class));
            }
        });

        pomoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pomoc = new Dialog(odleglosc.this);
                Pomoc.setContentView(R.layout.pomoc_azymut);
                Pomoc.show();
            }
        });
    }
}
