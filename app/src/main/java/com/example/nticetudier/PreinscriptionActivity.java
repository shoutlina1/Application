package com.example.nticetudier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class PreinscriptionActivity extends AppCompatActivity {

    ImageButton Eleve ;
    ImageButton Enseignant ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preinscription);



        Eleve=findViewById(R.id.Eleve);
        Enseignant=findViewById(R.id.Enseig);

        Eleve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Vous avez choisi le profile : Elève !",Toast.LENGTH_LONG).show();
                Intent inscripEleve =new Intent(PreinscriptionActivity.this,inscriptionEleve.class);
                //inscrireActivityIntent.putExtra("Type","Eleve");
                startActivity(inscripEleve);
            }
        });



        Enseignant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Vous avez choisi le profile : Enseignant !",Toast.LENGTH_LONG).show();
                Intent inscripEnseignant =new Intent(PreinscriptionActivity.this , inscriptionEnseignant.class);
                //inscrireActivityIntent.putExtra("type","Enseignant");
                startActivity(inscripEnseignant);

            }
        });
    }
}