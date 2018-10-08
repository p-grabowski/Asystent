package com.example.z370.asystent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    Button odleglosc, domiarprost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    odleglosc = findViewById(R.id.odleglosc);
    domiarprost = findViewById(R.id.domiarprost);


    odleglosc.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
            startActivity(new Intent(getApplicationContext(), odleglosc.class));
        }
    });

    domiarprost.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
            startActivity(new Intent(getApplicationContext(), domiarprostokatny.class));
        }
    });
    }
}
