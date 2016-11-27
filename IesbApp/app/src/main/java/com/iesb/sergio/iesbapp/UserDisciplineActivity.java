package com.iesb.sergio.iesbapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iesb.sergio.iesbapp.model.Discipline;
import com.iesb.sergio.iesbapp.model.UserDiscipline;

import java.util.ArrayList;

/**
 * Created by henriquesantos on 27/11/16.
 */
public class UserDisciplineActivity extends AppCompatActivity {

    private static DisciplineListAdapter adapter;
    private ListView mListView;
    private ArrayList<Discipline> disciplines = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_discipline);

        mListView = (ListView) findViewById(R.id.disciplineListView);
        adapter = new DisciplineListAdapter(getApplicationContext(),disciplines);


    }

}
