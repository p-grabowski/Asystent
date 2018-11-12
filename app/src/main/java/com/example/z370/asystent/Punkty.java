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

    EditText X,Y,H,NAZWA, ID;
    Button dodaj;
    TextView punkt, zmien,usun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punkty);

        baza = new BazaPunktow(this);


        NAZWA = findViewById(R.id.eT_punkty_Nazwa);
        X = findViewById(R.id.eT_punkty_X);
        Y = findViewById(R.id.eT_punkty_Y);
        H = findViewById(R.id.eT_punkty_H);
        ID = findViewById(R.id.eT_punkty_ID);
        dodaj = findViewById(R.id.bT_punkty_dodaj);
        zmien = findViewById(R.id.bT_punkty_zmien);
        usun = findViewById(R.id.bT_punkty_usun);


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
                        isEmpty(H.getText()) ||
                        isEmpty(ID.getText())
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
                if (isEmpty(ID.getText())
                        ) {
                    Toast.makeText(getApplicationContext(), "Wypełnij wszystkie pola (Id)!", Toast.LENGTH_SHORT).show();
                } else {
                    UsunDane();
                    AuktualizujListe();
                }
            }
        });

AuktualizujListe();


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
            Toast.makeText(getApplicationContext(),"usunięty",Toast.LENGTH_SHORT).show();
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
        boolean isUpdate = baza.zmienpunkt(
                ID.getText().toString(),
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

    public void UsunDane(){
        Integer  usuwanyWiersz = baza.usunpunkt(ID.getText().toString());
        if (usuwanyWiersz > 0)
            Toast.makeText(Punkty.this,"Punkt Usunięty", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(Punkty.this,"Punkt nie Usunięty", Toast.LENGTH_LONG).show();
    }

    public void AuktualizujListe(){
        Cursor punktyCursor = baza.pokazcalabaze();
        PuntyAdapter PunktyAdapter = new PuntyAdapter(Punkty.this, punktyCursor);
        ListView listView = findViewById(R.id.lV_punkty);
        listView.setAdapter(PunktyAdapter);
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}