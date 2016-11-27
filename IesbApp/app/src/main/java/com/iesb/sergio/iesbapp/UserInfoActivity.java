package com.iesb.sergio.iesbapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by henriquesantos on 27/11/16.
 */
public class UserInfoActivity extends AppCompatActivity {

    private TextView userNameTextView;
    private TextView emailTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);

        userNameTextView = (TextView) findViewById(R.id.userDateName);
        emailTextView = (TextView) findViewById(R.id.userDateEmail);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            if (FirebaseAuth.getInstance().getCurrentUser().getDisplayName() != null) {
                userNameTextView.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
            }else {
                userNameTextView.setText("Não informado");
            }

            emailTextView.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        }
    }
}
