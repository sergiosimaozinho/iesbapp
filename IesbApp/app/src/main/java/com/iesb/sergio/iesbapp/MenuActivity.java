package com.iesb.sergio.iesbapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/**
 * Created by henriquesantos on 26/11/16.
 */
public class MenuActivity extends AppCompatActivity {
    private ListView mListView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        mListView = (ListView) findViewById(R.id.menuListView);
        mAuth = FirebaseAuth.getInstance();

        final ArrayList<String> itensMenu = new ArrayList<>();
        itensMenu.add("Horário de Aulas");
        itensMenu.add("Notas e Frequencia");
        itensMenu.add("Disciplinas Matriculadas");
        itensMenu.add("Dados Cadastrais");
        itensMenu.add("Mapa Iesb Asa Sul");
        itensMenu.add("Sair");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, itensMenu);

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                if (position == 5) {
                    mAuth.signOut();
                }

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
