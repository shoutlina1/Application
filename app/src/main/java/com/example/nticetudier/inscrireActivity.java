package com.example.nticetudier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Model.Utilisateur;

public class inscrireActivity extends AppCompatActivity implements View.OnClickListener {

        EditText ETnom,ETprenom,ETusername,ETpassword,ETemail,ETnumero;
        Button BTinscription;
        FirebaseAuth auth;
        FirebaseUser user;
        DatabaseReference databaseReference;
        ProgressDialog progressDialog;
        //private CheckBox etudiant;
        //private CheckBox enseignent ;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_inscription);

            ETnom = findViewById(R.id.ETnom);
            ETprenom = findViewById(R.id.ETprenom);
            ETusername = findViewById(R.id.ETusername);
            ETpassword = findViewById(R.id.ETpassword);
            ETemail = findViewById(R.id.ETemail);
            ETnumero = findViewById(R.id.ETnumero);
            BTinscription = findViewById(R.id.Btn_inscription);

            progressDialog = new ProgressDialog(this);
            auth = FirebaseAuth.getInstance();

            BTinscription.setOnClickListener(this);

        }
        @Override
        protected void onStart() {
            super.onStart();

            if (auth.getCurrentUser() != null) {
                //handle the already login user
            }
        }

    public void registerUser(){

        final String Nom = ETnom.getText().toString().trim();
        final String Prenom = ETprenom.getText().toString().trim();
        final String Email = ETemail.getText().toString().trim();
        final String Username= ETusername.getText().toString().trim();
        final String Password = ETpassword.getText().toString().trim();
        final String Numero = ETnumero.getText().toString().trim();

        if (Nom.isEmpty()) {
            ETnom.setError("Veuillez introduire votre nom SVP !");
            ETnom.requestFocus();
            return;
        }

        if (Prenom.isEmpty()) {
            ETprenom.setError("Veuillez introduire votre prénom SVP !");
            ETprenom.requestFocus();
            return;
        }

        if (Username.isEmpty()) {
            ETusername.setError("Veuillez introduire votre username SVP !");
            ETusername.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            ETemail.setError("Veuillez introduire un email correct SVP !");
            ETemail.requestFocus();
            return;
        }

        if (Password.isEmpty()) {
            ETpassword.setError("Veuillez introduire un mot de passe SVP !");
            ETpassword.requestFocus();
            return;
        }

        if (Password.length() < 6) {
            ETpassword.setError("Veuillez introduire un mot de passe supérieur à 6 caractères !");
            ETpassword.requestFocus();
            return;
        }

        if (Numero.isEmpty()) {
            ETnumero.setError("Veuillez introduire un numéro de téléphone SVP !");
            ETnumero.requestFocus();
            return;
        }

        if (Numero.length() != 10) {
            ETnumero.setError("Veuillez introduire un numéro de téléphone SVP !");
            ETnumero.requestFocus();
            return;
        }

        //Log.d("My name ------------- ", u.getNom());
        auth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Utilisateur user = new Utilisateur(Nom,Prenom,Username,Password,Email,Numero);

                    FirebaseDatabase.getInstance().getReference("Utilisateurs")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    //progressDialog.dismiss();
                                                    Log.v("*** ------------- ","Ani hnaaaa");
                                                    Toast.makeText(inscrireActivity.this, "Inscription effectuée", Toast.LENGTH_SHORT).show();
                                                    //auth.signOut();
                                                    //Intent success = new Intent(inscrireActivity.this, acceuilActivity.class);
                                                    //startActivity(success);
                                                } else {
                                                }
                                            }
                                        });
                            }else {
                    Toast.makeText(inscrireActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                }

        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Btn_inscription:
                registerUser();
                break;
        }

    }
}
