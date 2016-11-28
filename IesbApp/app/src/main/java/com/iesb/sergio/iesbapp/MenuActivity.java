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

import com.google.android.gms.maps.MapFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



import java.util.ArrayList;


public class MenuActivity extends AppCompatActivity {
    private ListView mListView;
    private FirebaseAuth mAuth;

    static final int ASK_QUESTION_REQUEST = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        mListView = (ListView) findViewById(R.id.menuListView);
        mAuth = FirebaseAuth.getInstance();
        configureAuthStateListener();

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

                switch (position) {
                    case 0:
                        startActivity(new Intent(MenuActivity.this, UserDisciplineActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MenuActivity.this, GradesActivity.class));
                        break;
                    case 2:

                        Intent intent = new Intent(MenuActivity.this, DisciplineRegisteredActivity.class);
                        startActivityForResult(intent, ASK_QUESTION_REQUEST);

                        break;
                    case 3:
                        startActivity(new Intent("com.iesb.sergio.iesbapp.UserInfoActivity"));
                        break;
                    case 4:
                        startActivity(new Intent(MenuActivity.this, MapsActivity.class));
                        break;
                    case 5:
                        mAuth.signOut();
                        break;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ASK_QUESTION_REQUEST) {

            if (resultCode == RESULT_OK) {

                final String result = data.getStringExtra(DisciplineRegisteredActivity.Result_DATA);
                Toast.makeText(this,result, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void configureAuthStateListener() {
        FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MenuActivity.this, MainActivity.class));
                    finish();
                }
            }
        };
        mAuth.addAuthStateListener(authListener);
    }











}
