package com.example.z370.asystent;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.z370.asystent.FunkcjeObliczenia.PunktyLista;

import java.util.ArrayList;

public class Punkty extends Activity {

    ListView listView;
    String number[]={"1","2","3","4","5","6","7","1","2","3","4","5","6","7","1","2","3","4","5","6","7"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punkty);
        listView=(ListView)findViewById(R.id.lV_punkty);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,number);
        listView.setAdapter(adapter);


        PunktyLista punktylsta = new PunktyLista();

        FunkcjeObliczenia.Punkt nowypunkt = new FunkcjeObliczenia.Punkt();
        nowypunkt.Nazwa = "123123";
        nowypunkt.Y = 123;
        nowypunkt.X = 134;
        nowypunkt.H = 125;

        punktylsta.ListaPunktow.add(nowypunkt);



        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        //menu.setHeaderTitle("Select The Action");
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
}