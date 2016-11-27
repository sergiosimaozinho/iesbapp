package com.iesb.sergio.iesbapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by henriquesantos on 26/11/16.
 */
public class MenuActivity extends AppCompatActivity {
    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        mListView = (ListView) findViewById(R.id.menuListView);


        final ArrayList<String> itensMenu = new ArrayList<>();
        itensMenu.add("Horário de Aulas");
        itensMenu.add("Notas e Frequencia");
        itensMenu.add("Disciplinas Matriculadas");
        itensMenu.add("Dados Cadastrais");
        itensMenu.add("Mapa Iesb Asa Sul");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, itensMenu);

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // ListView Clicked item index
                int itemPosition  = position;

                // ListView Clicked item value
                String  itemValue    = (String) mListView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();
            }
        });

    }
}
