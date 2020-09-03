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
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Model.Eleve;
import Model.Enseignant;

public class loginActivity extends AppCompatActivity  {


    Button inscription, login;
    EditText username, password;
    private FirebaseAuth mAuth;
    private FirebaseUser futilisateur;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(loginActivity.this, FaculteActivity.class));
                }
            }
        };

      /*  if (mAuth.getCurrentUser() != null) {
            //close this activity
            finish();
            //opening profile activity
            startActivity(new Intent(getApplicationContext(), FaculteActivity.class));
        }*/

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        inscription = findViewById(R.id.inscription);
        login = findViewById(R.id.login);
        progressDialog = new ProgressDialog(this);

        //login.setEnabled(false);
     /*   login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startSignIn();

            }
        });*/
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startLogin();
            }
        });


        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inscriptionActivityIntent = new Intent(loginActivity.this, PreinscriptionActivity.class);
                startActivity(inscriptionActivityIntent);
            }
        });
    }

    //method for user login
    private void startLogin() {

        String[] user = {username.getText().toString().trim()};
        String pass = password.getText().toString().trim();

        if(TextUtils.isEmpty(user[0]) || TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Les champs sont vides", Toast.LENGTH_SHORT).show();
        }else{
            Log.v(isEmail(user[0])+"","");
            if(!isEmail(user[0])){
                loginWithUsername(user,pass);
            }else{
                loginWithEmail(user,pass);
            }
        }

    }

    public boolean isEmail(String email){
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public void loginWithUsername(final String[] user,final String pass){
        final String[] Email = {""};
        Log.v("checking if"+user[0]+" exists"," in table: "+"Eleve");
        DatabaseReference users = FirebaseDatabase.getInstance().getReference("Eleve");
        Query query = users.orderByChild("email").equalTo(user[0]);
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot ds:dataSnapshot.getChildren()){
                        final Eleve utilisateur= ds.getValue(Eleve.class);
                        Toast.makeText(loginActivity.this,utilisateur.getNom()+" "+utilisateur.getPrénom()+" "+utilisateur.getEmail()+"",Toast.LENGTH_SHORT).show();
                        Email[0] =utilisateur.getEmail();
                        progressDialog.setMessage("Signing In Please Wait..");
                        progressDialog.show();

                        mAuth.signInWithEmailAndPassword(Email[0], pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    Toast.makeText(loginActivity.this, "Identification réussite", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }else{
                    DatabaseReference users = FirebaseDatabase.getInstance().getReference("Enseignant");
                    Query query = users.orderByChild("email").equalTo(user[0]);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                for (DataSnapshot ds:dataSnapshot.getChildren()){
                                    final Enseignant utilisateur= ds.getValue(Enseignant.class);
                                    Toast.makeText(loginActivity.this,utilisateur.getNom()+" "+utilisateur.getPrénom()+" "+utilisateur.getEmail()+"",Toast.LENGTH_SHORT).show();
                                    Email[0] =utilisateur.getEmail();
                                    progressDialog.setMessage("Signing In Please Wait..");
                                    progressDialog.show();

                                    mAuth.signInWithEmailAndPassword(Email[0], pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            futilisateur=mAuth.getCurrentUser();
                                            progressDialog.dismiss();
                                            if (task.isSuccessful()) {
                                                if(futilisateur.isEmailVerified()){
                                                    Toast.makeText(loginActivity.this,"Connexion réussite",Toast.LENGTH_SHORT).show();
                                                }else{
                                                    Toast.makeText(loginActivity.this,"Veuillez vérifier votre émail",Toast.LENGTH_SHORT).show();
                                                    mAuth.signOut();
                                                }
                                            }
                                        }
                                    });
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    public void loginWithEmail(final String[] email,final String pass){
        final String[] Email = {""};
        Log.v("checking if"+email[0]+" exists"," in table: "+"Eleve");
        DatabaseReference users = FirebaseDatabase.getInstance().getReference("Eleve");
        Query query = users.orderByChild("email").equalTo(email[0]);
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot ds:dataSnapshot.getChildren()){
                        final Eleve utilisateur= ds.getValue(Eleve.class);
                        Toast.makeText(loginActivity.this,utilisateur.getNom()+" "+utilisateur.getPrénom()+" "+utilisateur.getEmail()+"",Toast.LENGTH_SHORT).show();
                        Email[0] =utilisateur.getEmail();
                        progressDialog.setMessage("Signing In Please Wait..");
                        progressDialog.show();

                        mAuth.signInWithEmailAndPassword(Email[0], pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    Toast.makeText(loginActivity.this,"Connexion réussite",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(loginActivity.this, FaculteActivity.class);
                                    startActivity(intent);
                                }
                            }
                        });
                    }
                }else{
                    DatabaseReference users = FirebaseDatabase.getInstance().getReference("Enseigant");
                    Query query = users.orderByChild("email").equalTo(email[0]);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                for (DataSnapshot ds:dataSnapshot.getChildren()){
                                    final Enseignant utilisateur= ds.getValue(Enseignant.class);
                                    Toast.makeText(loginActivity.this,utilisateur.getNom()+" "+utilisateur.getPrénom()+" "+utilisateur.getEmail()+"",Toast.LENGTH_SHORT).show();
                                    Email[0] =utilisateur.getEmail();
                                    progressDialog.setMessage("Signing In Please Wait..");
                                    progressDialog.show();

                                    mAuth.signInWithEmailAndPassword(Email[0], pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            futilisateur=mAuth.getCurrentUser();
                                            progressDialog.dismiss();
                                            if (task.isSuccessful()) {
                                                if(futilisateur.isEmailVerified()){
                                                    Toast.makeText(loginActivity.this,"Veuillez vérifier votre émail",Toast.LENGTH_SHORT).show();
                                                    /*Intent intent=new Intent(login.this, dashboard.class);
                                                    intent.putExtra("USER", utilisateur);
                                                    intent.putExtra("NIVEAU", utilisateur.getId_niveau());
                                                    startActivity(intent);*/
                                                }else{
                                                    Toast.makeText(loginActivity.this,"Veuillez vérifier votre émail",Toast.LENGTH_SHORT).show();
                                                    mAuth.signOut();
                                                }
                                            }
                                        }
                                    });
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
















