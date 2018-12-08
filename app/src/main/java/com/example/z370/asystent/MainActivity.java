package com.example.z370.asystent;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import static android.text.TextUtils.isEmpty;
import static com.example.z370.asystent.Punkty.baza;
import static java.lang.Double.parseDouble;

public class MainActivity extends Activity {

    //static BazaPunktow baza;



    Button punkty, obliczenia, dodajzbior, usunzbior;
    Spinner zbiory;
    EditText nazwazbior;
    String dousuniecia="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    punkty = findViewById(R.id.punkty);
    obliczenia = findViewById(R.id.obliczenia);
    zbiory = findViewById(R.id.zbior);
    dodajzbior = findViewById(R.id.bT_dodaj_zbior);
    nazwazbior = findViewById(R.id.eT_nazwa_zbior);
    usunzbior = findViewById(R.id.bT_usun_zbior);

punkty.setEnabled(true);
obliczenia.setEnabled(true);

        baza = new BazaPunktow(this);

        DodajPierwszyZbior();

        //** funkcja wypelnij spinner
        AuktualizujListeZbiory();
        UstawWybranyZbior();

        punkty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), Punkty.class));
            }
        });
        obliczenia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), Obliczenia.class));
            }
        });
        dodajzbior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty(nazwazbior.getText()))     Toast.makeText(getApplicationContext(), "Wypełnij wszystkie pola (Nazwa zbioru)!", Toast.LENGTH_SHORT).show();
                 else{
                     DodajZbior();
                     AuktualizujListeZbiory();
                }
            }
        });
        usunzbior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baza.usunZbiorNazwa(dousuniecia);
                AuktualizujListeZbiory();
            }
        });
        zbiory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Global.WybranyZbior = parent.getItemAtPosition(position).toString();
                dousuniecia = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Już wychodzisz ?")
                .setMessage("Czy chcesz opuścić program ?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        MainActivity.super.onBackPressed();
                    }
                }).create().show();
    }
    public void AuktualizujListeZbiory(){
        Cursor nazwyCursor = baza.pokazZbiory();
        ArrayList<String> ListaNazw = new ArrayList<String>();
        nazwyCursor.moveToFirst();
        while(!nazwyCursor.isAfterLast()) {
            ListaNazw.add(nazwyCursor.getString(nazwyCursor.getColumnIndex("Nazwa_zbioru")));
            nazwyCursor.moveToNext();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, ListaNazw);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        zbiory.setAdapter(adapter);
    }

    public void DodajPierwszyZbior() {
        if (baza.sprawdzZbior("example")){}
        else{
            boolean dodany = baza.dodajZbior("example");
            if (dodany == true) {
                Toast.makeText(MainActivity.this, "Witaj", Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(MainActivity.this, "Jakiś kłopot", Toast.LENGTH_LONG).show();
        }
    }

    public void DodajZbior() {
        if (baza.sprawdzZbior(nazwazbior.getText().toString()))
            Toast.makeText(MainActivity.this, "Wybrana nazwa już istnieje, zmień ją!", Toast.LENGTH_LONG).show();
        else{
            boolean dodany = baza.dodajZbior(nazwazbior.getText().toString());
        if (dodany == true) {
            Toast.makeText(MainActivity.this, "Zbiór dodany", Toast.LENGTH_LONG).show();
            nazwazbior.setText("");
        } else
            Toast.makeText(MainActivity.this, "Zbiór nie dodany", Toast.LENGTH_LONG).show();
    }
    }

    public void UstawWybranyZbior(){ //ustawia aktywny zbiór do listy
        Cursor nazwyCursor = baza.pokazZbiory();
        ArrayList<String> ListaNazw = new ArrayList<String>();
        nazwyCursor.moveToFirst();
        while(!nazwyCursor.isAfterLast()) {
            ListaNazw.add(nazwyCursor.getString(nazwyCursor.getColumnIndex("Nazwa_zbioru")));
            nazwyCursor.moveToNext();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, ListaNazw);
        zbiory.setSelection(adapter.getPosition(Global.WybranyZbior));
    }
}



