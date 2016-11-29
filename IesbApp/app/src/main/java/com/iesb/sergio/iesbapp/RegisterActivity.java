package com.iesb.sergio.iesbapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


public class RegisterActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button btnRegister;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setMessage("Registrando....");
        progressDialog.setTitle("Aguarde");

        mAuth = FirebaseAuth.getInstance();

        nameEditText = (EditText)   findViewById(R.id.nameRegisterEditText);
        emailEditText = (EditText)  findViewById(R.id.emailRegisterEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordRegisterEditText);
        btnRegister = (Button) findViewById(R.id.btnRegisterScreen);

        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();


                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Informe o E-mail!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Informe a senha!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "senha muito curta, deve ter no mínimo 6 caracteres!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressDialog.show();

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Falha ao Cadastrar usuário, verifique se o e-mail ja foi cadastrado",
                                    Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        } else {
                            saveData();
                            Toast.makeText(RegisterActivity.this, "Registrado com sucesso !",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            progressDialog.dismiss();
                            finish();
                        }
                    }
                });
            }
        });
    }

    private void saveData() {
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            String name = nameEditText.getText().toString();
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(name).build();

            user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Log.d("Sucesso", "User profile updated.");
                    }
                }
            });
        }
    }
}
