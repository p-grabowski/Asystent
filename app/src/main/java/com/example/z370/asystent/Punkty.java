package com.example.z370.asystent;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.text.TextUtils.isEmpty;
import static java.lang.Double.parseDouble;

public class Punkty extends Activity {

    static BazaPunktow baza;

    EditText X,Y,H,NAZWA;
    Button dodaj, zmien, usun;
    TextView punkt;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punkty);

        baza = new BazaPunktow(this);


        NAZWA = findViewById(R.id.eT_punkty_Nazwa);
        X = findViewById(R.id.eT_punkty_X);
        Y = findViewById(R.id.eT_punkty_Y);
        H = findViewById(R.id.eT_punkty_H);
        dodaj = findViewById(R.id.bT_punkty_dodaj);
        zmien = findViewById(R.id.bT_punkty_zmien);
        usun = findViewById(R.id.bT_punkty_usun);
        listView = findViewById(R.id.lV_punkty);

        AuktualizujListe();


        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty(NAZWA.getText()) ||
                        isEmpty(X.getText()) ||
                        isEmpty(Y.getText()) ||
                        isEmpty(H.getText())
                        ) {
                    Toast.makeText(getApplicationContext(), "Wypełnij wszystkie pola (Nazwa, X, Y, H)!", Toast.LENGTH_SHORT).show();
                } else {
                    DodajDane();
                    AuktualizujListe();
                }
            }
        });

        zmien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty(NAZWA.getText()) ||
                        isEmpty(X.getText()) ||
                        isEmpty(Y.getText()) ||
                        isEmpty(H.getText())
                        ) {
                    Toast.makeText(getApplicationContext(), "Wypełnij wszystkie pola (Nazwa, X, Y, H, ID)!", Toast.LENGTH_SHORT).show();
                } else {
                    ZmienDane();
                    AuktualizujListe();
                }
            }
        });

        usun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty(NAZWA.getText())
                        ) {
                    Toast.makeText(getApplicationContext(), "Wypełnij wszystkie pola (Nazwa)!", Toast.LENGTH_SHORT).show();
                } else {
                    UsunDaneNazwa(NAZWA.getText().toString());
                    AuktualizujListe();
                }
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Co chcesz zrobić ?");
        menu.add(0, v.getId(), 0, "Edytuj");//groupId, itemId, order, title
        menu.add(0, v.getId(), 0, "Usun");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle()=="Edytuj"){

            Toast.makeText(getApplicationContext(),"do edycji",Toast.LENGTH_SHORT).show();
        }
        else if(item.getTitle()=="Usun"){
      /*      UsunDaneNazwa();   // wpisz nazwe usuwanego wiersza
            AuktualizujListe();
            Toast.makeText(getApplicationContext(), ,Toast.LENGTH_SHORT).show();*/

        } else{
            return false;
        }
        return true;
    }

    public void DodajDane(){
        boolean dodany = baza.dodajpunkt(NAZWA.getText().toString(),
                parseDouble(String.valueOf(X.getText())),
                parseDouble(String.valueOf(Y.getText())),
                parseDouble(String.valueOf(H.getText()))
        );
        if(dodany == true)
            Toast.makeText(Punkty.this,"Punkt Dodany", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(Punkty.this,"Punkt nie Dodany", Toast.LENGTH_LONG).show();
    }


    public void ZmienDane(){
        Cursor XYHCursor = baza.pokazXYH(NAZWA.getText().toString());
        XYHCursor.moveToFirst();
        boolean isUpdate = baza.zmienpunkt(
                XYHCursor.getString(XYHCursor.getColumnIndex("_id")),
                NAZWA.getText().toString(),
                parseDouble(String.valueOf(X.getText())),
                parseDouble(String.valueOf(Y.getText())),
                parseDouble(String.valueOf(H.getText()))
        );
        if(isUpdate == true)
            Toast.makeText(Punkty.this,"Punkt Zmieniony", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(Punkty.this,"Punkt nie Zmieniony", Toast.LENGTH_LONG).show();
    }

    public void UsunDaneNazwa(String nazwa){
        Integer usuwanyWiersz = baza.usunpunktnazwa(nazwa);
        if (usuwanyWiersz > 0)
            Toast.makeText(Punkty.this,"Punkt Usunięty", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(Punkty.this,"Punkt nie Usunięty", Toast.LENGTH_LONG).show();
    }

   /* public void UsunDane(){
        Integer  usuwanyWiersz = baza.usunpunktnazwa(ID.getText().toString());
        if (usuwanyWiersz > 0)
            Toast.makeText(Punkty.this,"Punkt Usunięty", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(Punkty.this,"Punkt nie Usunięty", Toast.LENGTH_LONG).show();
    }*/

    public void AuktualizujListe(){
        Cursor punktyCursor = baza.pokazcalabaze();
        PuntyAdapter PunktyAdapter = new PuntyAdapter(Punkty.this, punktyCursor);
        listView.setAdapter(PunktyAdapter);
        registerForContextMenu(listView);
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}