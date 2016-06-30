package com.example.pannam.shared_preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView myList;
    Button getChoice, clearAll;
    SharedPreferences sharedpreferences;
    public static final String MYPREFERENCES = "MyUserChoice";
    ArrayList<String> selectedItems = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myList =(ListView)findViewById(R.id.list);
        getChoice = (Button)findViewById(R.id.getchoice);
        clearAll = (Button)findViewById(R.id.clearall);

        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,
                getResources().getStringArray(R.array.Mobile_OS));

        myList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        myList.setAdapter(adapter);

        sharedpreferences = getSharedPreferences(MYPREFERENCES, Context.MODE_PRIVATE);

        if(sharedpreferences.contains(MYPREFERENCES)){
           LoadSelections();
        }

        getChoice.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selected ="";
                int cntChoice = myList.getCount();

                SparseBooleanArray sparseBooleanArray = myList.getCheckedItemPositions();
                for (int i = 0 ; i <cntChoice; i++){
                    if(sparseBooleanArray.get(i)){
                        selected += myList.getItemAtPosition(i).toString() +
                                "\n";
                       System.out.println("Checking list while adding:" +
                                myList.getItemAtPosition(i).toString());
                        SaveSelections();

                    }
                }
                Toast.makeText(MainActivity.this, selected,Toast.LENGTH_LONG).show();
            }
        });

        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelections();
            }
        });


    }

    private void SaveSelections() {

        SharedPreferences.Editor prefEditor = sharedpreferences.edit();
        String savedItems = getSavedItems();
    }

    private String getSavedItems() {
        String savedItems = "";
        int count = this. myList.getAdapter().getCount();
        for (int i = 0; i <count; i++){
            
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
