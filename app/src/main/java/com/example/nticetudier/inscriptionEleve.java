package com.example.nticetudier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Model.Eleve;
import Model.Enseignant;

public class inscriptionEleve extends AppCompatActivity implements View.OnClickListener {
    EditText ETnom, ETprenom, ETusername, ETpassword, ETemail, ETnumero;
    EditText ETnum;
    Button BTinscription;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    //String profileUtilisateur;
    //private CheckBox etudiant;
    //private CheckBox enseignent

   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_inscrire2);


       // Bundle extras = getIntent().getExtras();
       // profileUtilisateur = extras.getString("type");

        ETnom = findViewById(R.id.ETnom);
        ETprenom = findViewById(R.id.ETprenom);
        ETusername = findViewById(R.id.ETusername);
        ETpassword = findViewById(R.id.ETpassword);
        ETemail = findViewById(R.id.ETemail);
        ETnumero = findViewById(R.id.ETnumero);
        BTinscription = findViewById(R.id.Btn_inscription);
        ETnum = findViewById(R.id.ETnum);


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

    public void registerUser() {


/*
        if(profileUtilisateur.matches("Parent")){
            addEtudiant();
            else{
                addEnseignant();
            }

*/


        //Log.d("My name ------------- ", u.getNom());

        //  public void addEtudiant () {

       final String Nom = ETnom.getText().toString().trim();
        final String Prenom = ETprenom.getText().toString().trim();
        final String Email = ETemail.getText().toString().trim();
        final String Username = ETusername.getText().toString().trim();
        final String Password = ETpassword.getText().toString().trim();
        final String Numero = ETnumero.getText().toString().trim();
        final String numdetudiant = ETnum.getText().toString().trim();

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

        auth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    user = auth.getCurrentUser();
                    user.sendEmailVerification();
                    // Utilisateur user = new Utilisateur(Nom, Prenom, Username, Password, Email, Numero);
                    Eleve e = new Eleve(Nom, Prenom, Username, Password, Email, Numero,numdetudiant);

                    FirebaseDatabase.getInstance().getReference("Eleve")
                            .child(String.valueOf(e.getId()))
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                progressDialog.setMessage("Registration...");
                                progressDialog.show();
                                progressDialog.dismiss();
                                Toast.makeText(inscriptionEleve.this, "Inscription effectuée", Toast.LENGTH_SHORT).show();
                                //auth.signOut();
                                //Intent success = new Intent(inscrireActivity.this, acceuilActivity.class);
                                //startActivity(success);
                            } else {
                            }
                        }
                    });
                } else {
                    Toast.makeText(inscriptionEleve.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }


        });

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Btn_inscription:
                registerUser();
                break;
        }


    }
    }
