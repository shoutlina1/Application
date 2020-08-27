package com.example.nticetudier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class inscrireActivity extends AppCompatActivity {

     Button inscrir;
     RadioButton etudiant , enseignat ;
     EditText nom ;
     EditText prenom;
     EditText email ;
     EditText num ;
     EditText passe ;
     DatabaseReference databaseReference;
     String vous ="";
     FirebaseAuth firebaseAuth ;
     FirebaseDatabase firebaseDatabase ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscrire);

        inscrir= findViewById(R.id.inscri);
        etudiant= findViewById(R.id.etud);
        enseignat= findViewById(R.id.prof);
        nom= findViewById(R.id.nom);
        prenom= findViewById(R.id.prenom);
        email= findViewById(R.id.email);
        num= findViewById(R.id.num);
        passe= findViewById(R.id.passe);

        databaseReference=FirebaseDatabase.getInstance().getReference("user");




        inscrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final String NOM=nom.getText().toString();
                 final String  PRENOM=prenom.getText().toString();
                 final String EMAIL=email.getText().toString();
                 final String mot_de_passe =passe.getText().toString();
                String numero=num.getText().toString();

                if(etudiant.isChecked()){
                    vous="etudiant";
                }
                if(enseignat.isChecked()){
                    vous="enseignat";
                }





                if(EMAIL.isEmpty()){

                   email.setError("enter votre email");
                }
                if(PRENOM.isEmpty()){

                    prenom.setError("entrer votre prenom");
                }
                if(NOM.isEmpty()){
                    nom.setError("entrer votre nom");
                }
                if(mot_de_passe.isEmpty()){

                    passe.setError("entrer votre mot de passe");
                }





               /* firebaseAuth.createUserWithEmailAndPassword(EMAIL,mot_de_passe)
                        .addOnCompleteListener(inscrireActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    user information = new user(NOM, PRENOM, EMAIL, mot_de_passe, vous);


                                    FirebaseDatabase.getInstance().getReference("user")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(inscrireActivity.this, "inscription ...", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                                        }
                                    });
                                }


                                 else {


                                    // ...
                                }

      }




});*/
                 }


        });


    }
}









