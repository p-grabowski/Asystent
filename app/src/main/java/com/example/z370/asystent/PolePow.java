package com.example.z370.asystent;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import static android.text.TextUtils.isEmpty;
import static com.example.z370.asystent.Punkty.baza;
import static java.lang.Double.doubleToLongBits;
import static java.lang.Double.parseDouble;
import static java.lang.Double.toHexString;

public class PolePow extends Activity {

    Button oblicz, wroc, dodaj;
    TextView wynik;
    ListView lista;
    EditText x, y;
    AutoCompleteTextView nazwa;
    int j =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pole_pow);

        dodaj = findViewById(R.id.bT_dodaj_pole);
        wynik = findViewById(R.id.tV_wynik_pole);
        oblicz = findViewById(R.id.B_oblicz_pole);
        wroc = findViewById(R.id.B_wroc11);
        lista = findViewById(R.id.lV_punkty_pole);
        nazwa = findViewById(R.id.eT_nazwa_pole);
        x = findViewById(R.id.eT_X_pole);
        y = findViewById(R.id.eT_Y_pole);

        //////////
        final double[][]tab;
        tab = new double[100][2];


        final ArrayList<ListaP> listap = new ArrayList<ListaP>();







        Cursor nazwyCursor = baza.pokazcalabaze(baza.idNazwaZbior(Global.WybranyZbior));
        ArrayList<String> ListaNazw = new ArrayList<String>();
        nazwyCursor.moveToFirst();
        while(!nazwyCursor.isAfterLast()) {
            ListaNazw.add(nazwyCursor.getString(nazwyCursor.getColumnIndex("Nazwa")));
            nazwyCursor.moveToNext();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PolePow.this, android.R.layout.simple_dropdown_item_1line, ListaNazw);

        nazwa.setAdapter(adapter);
        nazwa.setThreshold(1);
        nazwa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nazwa="";

                Toast.makeText(parent.getContext(), "Wybrałeś punkt numer " + parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();

                nazwa = parent.getItemAtPosition(position).toString();

                Cursor XYHCursor = baza.pokazXYH(nazwa, baza.idNazwaZbior(Global.WybranyZbior));
                XYHCursor.moveToFirst();
                x.setText(XYHCursor.getString(XYHCursor.getColumnIndex("X")));
                y.setText(XYHCursor.getString(XYHCursor.getColumnIndex("Y")));
            }
        });

        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty(nazwa.getText()) ||
                        isEmpty(x.getText()) ||
                        isEmpty(y.getText())
                        ) {
                    Toast.makeText(getApplicationContext(), "Wypełnij wszystkie pola (Nazwa, X, Y, H)!", Toast.LENGTH_SHORT).show();
                } else {
                    DodajPunktDoListy(tab, listap);
                   // wynik.setText(tab[0][1] + " " +tab[0][2]);
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

    public void DodajPunktDoListy(double[][]tab, ArrayList<ListaP> listap) {
/// dodaj do tablicy i listy
        double h = 0;
        PunktyPoleAdapter adapter = new PunktyPoleAdapter(this, listap);
        lista.setAdapter(adapter);

        ListaP newP = new ListaP(nazwa.getText().toString(),
                parseDouble(String.valueOf(x.getText())),
                parseDouble(String.valueOf(y.getText())),
                h);
        adapter.add(newP);


        tab[j][0]=parseDouble(String.valueOf(x.getText()));
        tab[j][1]=parseDouble(String.valueOf(y.getText()));
        j++;

        Toast.makeText(PolePow.this, "Punkt Dodany", Toast.LENGTH_LONG).show();
        nazwa.setText("");
        x.setText("");
        y.setText("");
    }
    public void AuktualizujListe(){
        Cursor punktyCursor = baza.pokazcalabaze(baza.idNazwaZbior(Global.WybranyZbior));
        PuntyAdapter PunktyAdapter = new PuntyAdapter(PolePow.this, punktyCursor);

        ArrayList<ListaP> arrayOfpoints = new ArrayList<>();
// Create the adapter to convert the array to views
        PunktyPoleAdapter adapter = new PunktyPoleAdapter(this, arrayOfpoints);
// Attach the adapter to a ListView

        //lista.setAdapter(PunktyAdapter);
        registerForContextMenu(lista);
    }

    public class ListaP{
        public String Nazwa;
        public double X;
        public double Y;
        public double H;

        public ListaP(String Nazwa, double X, double Y, double H) {
            this.Nazwa=Nazwa;
            this.X=X;
            this.Y=Y;
            this.H=H;
        }

        String getNazwa(){return Nazwa;}
        Double getX(){return X;}
        Double getY(){return Y;}
        Double getH(){return H;}
    }
}
