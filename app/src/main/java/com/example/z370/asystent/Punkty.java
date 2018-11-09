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

import com.example.z370.asystent.FunkcjeObliczenia.PunktyLista;

import java.util.ArrayList;

import static android.text.TextUtils.isEmpty;
import static java.lang.Double.parseDouble;

public class Punkty extends Activity {

BazaPunktow baza;

EditText X,Y,H,NAZWA, ID;
Button dodaj;
TextView punkt, pokaz, zmien,usun;

    ListView listView;
    String number[]={"1","2","3","4","5","6","7","1","2","3","4","5","6","7","1","2","3","4","5","6","7"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punkty);

        baza = new BazaPunktow(this);


        NAZWA = findViewById(R.id.eT_NAZWA);
        X = findViewById(R.id.eT_X);
        Y = findViewById(R.id.eT_Y);
        H = findViewById(R.id.eT_H);
        ID = findViewById(R.id.eT_ID);
        dodaj = findViewById(R.id.bT_punkty_dodaj);
        punkt = findViewById(R.id.tV_dodaj);
        pokaz = findViewById(R.id.bT_punkty_pokaz);
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
                } else
                DodajDane();

            }
        });

        pokaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PokazDane();
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
                } else
                    ZmienDane();
            }
        });

        usun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty(ID.getText())
                        ) {
                    Toast.makeText(getApplicationContext(), "Wypełnij wszystkie pola (Id)!", Toast.LENGTH_SHORT).show();
                } else
                    UsunDane();
            }
        });


        ListView listView = findViewById(R.id.lV_punkty);

        Cursor punktyCursor = baza.pokazcalabaze();

// Setup cursor adapter using cursor from last step
        PuntyAdapter PunktyAdapter = new PuntyAdapter(this, punktyCursor);
// Attach cursor adapter to the ListView
        listView.setAdapter(PunktyAdapter);

        registerForContextMenu(listView);

        PunktyAdapter.changeCursor(punktyCursor); // Przełącz na nowy kursor i aktualizuj zawartość ListView




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

    public void PokazDane() {
        Cursor res = baza.pokazcalabaze();
        if (res.getCount() == 0) {
            ShowMessage("Error","Pusta lista");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id: " + res.getString(0) + "\n");
            buffer.append("Nazwa: " + res.getString(1) + "\n");
            buffer.append("X: " + res.getString(2) + "\n");
            buffer.append("Y: " + res.getString(3) + "\n");
            buffer.append("H: " + res.getString(4) + "\n\n");
        }
        ShowMessage("Dane", buffer.toString());
    }

    public void ShowMessage(String tittle,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(tittle);
        builder.setMessage(message);
        builder.show();
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

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
