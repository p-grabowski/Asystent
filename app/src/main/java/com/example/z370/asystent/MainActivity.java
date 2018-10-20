package com.example.z370.asystent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    Button odleglosc, spadek, domiarprost, domiarbieg, katclp, przeciecie, punkt, liniowe, wprzod, wstecz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    odleglosc = findViewById(R.id.odleglosc);
    spadek = findViewById(R.id.spadek);
    domiarprost = findViewById(R.id.domiarprost);
    domiarbieg = findViewById(R.id.domiarbieg);
    katclp = findViewById(R.id.katclp);
    przeciecie = findViewById(R.id.przeciecie);
    punkt = findViewById(R.id.punkt);
    liniowe = findViewById(R.id.liniowe);
    wprzod = findViewById(R.id.wprzod);
    wstecz = findViewById(R.id.wstecz);

        odleglosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), odleglosc.class));
            }
        });
        spadek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), spadek.class));
            }
        });
        domiarprost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), domiarprostokatny.class));
            }
        });
        domiarbieg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), domiarbiegunowy.class));
            }
        });
        katclp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), katclp.class));
            }
        });
        przeciecie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), przeciecieprostych.class));
            }
        });
        punkt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), punktnaprostej.class));
            }
        });
        liniowe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), wciecieliniowe.class));
            }
        });
        wprzod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), wcieciewprzod.class));
            }
        });
        wstecz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), wcieciewstecz.class));
            }
        });
    }
}
