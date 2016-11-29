package com.iesb.sergio.iesbapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.iesb.sergio.iesbapp.model.Discipline;
import com.iesb.sergio.iesbapp.model.UserDiscipline;

import java.util.ArrayList;
import java.util.List;


public class UserDisciplineActivity extends AppCompatActivity {

    private static DisciplineListAdapter adapter;
    private ListView mListView;
    private ArrayList<Discipline> disciplines = new ArrayList<>();
    private DatabaseReference mDatabase;
    private ProgressDialog progressDialog;

    private static String USER_TABLE_NAME = "UserDiscipline";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_discipline);

        progressDialog = new ProgressDialog(UserDisciplineActivity.this);
        progressDialog.setMessage("Buscando Dados....");
        progressDialog.setTitle("Aguarde");

        progressDialog.show();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child(USER_TABLE_NAME).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<Discipline>> genericTypeIndicator = new GenericTypeIndicator<ArrayList<Discipline>>() {};
                ArrayList<Discipline> news = dataSnapshot.getValue(genericTypeIndicator);

                disciplines.addAll(news);
                adapter = new DisciplineListAdapter(getApplicationContext(),disciplines);
                mListView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressDialog.dismiss();

            }

        });

        mListView = (ListView) findViewById(R.id.disciplineListView);

    }

}
