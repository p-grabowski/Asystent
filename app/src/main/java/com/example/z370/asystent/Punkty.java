package com.example.z370.asystent;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Punkty extends Activity {

    ListView listViewContextMenu;
    String number[]={"1","2","3","4","5","6","7"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punkty);
        listViewContextMenu=(ListView)findViewById(R.id.listViewContextMenu);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,number);
        listViewContextMenu.setAdapter(adapter);

        // Register the ListView  for Context menu
        registerForContextMenu(listViewContextMenu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select The Action");
        menu.add(0, v.getId(), 0, "Code");//groupId, itemId, order, title
        menu.add(0, v.getId(), 0, "Example");
        menu.add(0, v.getId(), 0, "Tutorial");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle()=="Code"){
            Toast.makeText(getApplicationContext(),"Selected Code",Toast.LENGTH_SHORT).show();
        }
        else if(item.getTitle()=="Example"){
            Toast.makeText(getApplicationContext(),"Selected Example",Toast.LENGTH_SHORT).show();
        } else if(item.getTitle()=="Tutorial") {
            Toast.makeText(getApplicationContext(), "Selected Tutorial", Toast.LENGTH_SHORT).show();
        }else{
            return false;
        }
        return true;
    }

}