package com.example.nticetudier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

public class inscrireActivity extends AppCompatActivity {

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

       // this.etudiant=(CheckBox) this.findViewById(R.id.etud);
       // this.enseignent=(CheckBox) this.findViewById(R.id.prof);
       // this.inscrir=(Button) this.findViewById(R.id.inscri);

        ETnom = findViewById(R.id.ETnom);
        ETprenom = findViewById(R.id.ETprenom);
        ETusername = findViewById(R.id.ETusername);
        ETpassword = findViewById(R.id.ETpassword);
        ETemail = findViewById(R.id.ETemail);
        ETnumero = findViewById(R.id.ETnumero);
        BTinscription = findViewById(R.id.Btn_inscription);

        //databaseReference = FirebaseDatabase.getInstance().getReference("Utilisateur");
        progressDialog = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();

    BTinscription.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        final String Nom = ETnom.getText().toString();
        final String Prenom = ETprenom.getText().toString();
        final String Email = ETemail.getText().toString();
        final String Username= ETusername.getText().toString().trim();
        final String Password = ETpassword.getText().toString();
        final String Numero = ETnumero.getText().toString();

        if(TextUtils.isEmpty(Nom) || TextUtils.isEmpty(Prenom) || TextUtils.isEmpty(Email) || TextUtils.isEmpty(Username)
                || TextUtils.isEmpty(Password) || TextUtils.isEmpty(Numero)){
            Toast.makeText(inscrireActivity.this,"Veuillez remplir tous les champs SVP !",Toast.LENGTH_SHORT).show();
        }

        progressDialog.setMessage("Registration User...");
        progressDialog.show();
        Utilisateur u = new Utilisateur(Nom,Prenom,Username,Password,Email,Numero);
        //Log.d("My email ------------- ", u.getEmail());
        registerUser(u);

        }
    });

    }

    public void registerUser(final Utilisateur u){
        //Log.d("My name ------------- ", u.getNom());
        auth.createUserWithEmailAndPassword(u.getEmail(),u.getMot_de_passe()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    user = auth.getCurrentUser();
                    user.sendEmailVerification()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        FirebaseDatabase.getInstance().getReference("Utilisateur")
                                                .child(String.valueOf(u.getId())).setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(inscrireActivity.this, "Inscription effectuée", Toast.LENGTH_SHORT).show();
                                                auth.signOut();
                                                Intent success = new Intent(inscrireActivity.this, acceuilActivity.class);
                                                startActivity(success);
                                            }
                                        });
                                    } else {

                                    }

                                }
                            });
                }else {
                }
            }
        });
    }

}
