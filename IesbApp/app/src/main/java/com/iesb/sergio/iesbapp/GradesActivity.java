package com.iesb.sergio.iesbapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.iesb.sergio.iesbapp.model.Discipline;
import com.iesb.sergio.iesbapp.model.Grade;

import java.util.ArrayList;

public class GradesActivity extends AppCompatActivity {


    private static GradesListAdapter adapter;
    private ListView mListView;
    private ArrayList<Grade> grades = new ArrayList<>();
    private DatabaseReference mDatabase;
    private ProgressDialog progressDialog;

    private static String GRADES_TABLE_NAME = "GradesTable";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grades);

        progressDialog = new ProgressDialog(GradesActivity.this);
        progressDialog.setMessage("Efetuando Login....");
        progressDialog.setTitle("Aguarde");

        progressDialog.show();


        mDatabase = FirebaseDatabase.getInstance().getReference();
        mListView = (ListView) findViewById(R.id.gradeListView);

        mDatabase.child(GRADES_TABLE_NAME).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<Grade>> genericTypeIndicator = new GenericTypeIndicator<ArrayList<Grade>>() {};
                ArrayList<Grade> news = dataSnapshot.getValue(genericTypeIndicator);

                grades.addAll(news);

                adapter = new GradesListAdapter(getApplicationContext(),grades);
                mListView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

    }
}
