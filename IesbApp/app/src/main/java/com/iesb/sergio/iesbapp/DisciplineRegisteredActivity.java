package com.iesb.sergio.iesbapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iesb.sergio.iesbapp.model.RegisteredDiscipline;

import java.util.ArrayList;
import java.util.List;


public class DisciplineRegisteredActivity extends AppCompatActivity {

    private static DisciplineRegisteredAdapter adapter;
    private ListView mListView;
    private ArrayList<RegisteredDiscipline> grades = new ArrayList<>();
    private DatabaseReference mDatabase;
    private Button saveOfflineButton;

    private static String REGISTERED_DISCIPLINE_TABLE = "RegisteredDiscpline";
    public static final String Result_DATA = "Sucesso!";
    private static final String STORE_FILE_NAME = "offlineData";
    private static final String STORE_REGISTERED_DATA = "RegisteredDiscplineOffline";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discipline_registered);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mListView = (ListView) findViewById(R.id.registeredListView);
        saveOfflineButton = (Button) findViewById(R.id.registeredSaveButton);

        saveOfflineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveInSharedPreferences();
            }
        });

        if (!isOnline()) {
            List<RegisteredDiscipline> localGrades = getInSharedPreferences();
            grades.addAll(localGrades);

            adapter = new DisciplineRegisteredAdapter(getApplicationContext(),grades);
            mListView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }else {
            mDatabase.child(REGISTERED_DISCIPLINE_TABLE).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    GenericTypeIndicator<ArrayList<RegisteredDiscipline>> genericTypeIndicator = new GenericTypeIndicator<ArrayList<RegisteredDiscipline>>() {};
                    ArrayList<RegisteredDiscipline> news = dataSnapshot.getValue(genericTypeIndicator);

                    grades.addAll(news);

                    adapter = new DisciplineRegisteredAdapter(getApplicationContext(),grades);
                    mListView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });
        }


    }


    private void saveInSharedPreferences() {
        SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences(STORE_FILE_NAME, getBaseContext().MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(grades);

        editor.putString(STORE_REGISTERED_DATA, json);
        editor.commit();

        //Result
        Intent sendIntent = new Intent();
        sendIntent.putExtra(Result_DATA, "Dados Salvos com Sucesso!");
        setResult(RESULT_OK, sendIntent);
        finish();
    }

    private List<RegisteredDiscipline> getInSharedPreferences() {
        SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences(STORE_FILE_NAME, getBaseContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();

        String string = sharedPreferences.getString(STORE_REGISTERED_DATA,null);

        if (string == null)
            return new ArrayList<>();

        ArrayList<RegisteredDiscipline> localGrades = gson.fromJson(string, new TypeToken<ArrayList<RegisteredDiscipline>>(){}.getType());

        return localGrades;

    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

}
