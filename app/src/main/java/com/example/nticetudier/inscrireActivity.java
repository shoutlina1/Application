package com.example.nticetudier;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class inscrireActivity extends AppCompatActivity {

    EditText ETnom,ETprenom,ETusername,ETpassword,ETemail,ETnumero;
    Button BTinscription;
    FirebaseAuth auth;
    FirebaseUser user;
   // DatabaseReference databaseReference;

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

      //  databaseReference = FirebaseDatabase.getInstance().getReference("Utilisateur");
    BTinscription.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(getApplicationContext(),"Félicitations ! vous etes désormais inscrit :)",Toast.LENGTH_LONG).show();
        }
    });
  }



}
