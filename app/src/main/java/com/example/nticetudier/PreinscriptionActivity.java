package com.example.nticetudier;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import android.os.Bundle;
import android.widget.ImageView;

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
                Intent inscrireActivityIntent =new Intent(PreinscriptionActivity.this,inscriptionEleve.class);
                inscrireActivityIntent.putExtra("Type","Eleve");
                startActivity(inscrireActivityIntent);
            }
        });



        Enseignant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Vous avez choisi le profile : Enseignant !",Toast.LENGTH_LONG).show();
                Intent inscrireActivityIntent =new Intent(PreinscriptionActivity.this ,inscrireActivity.class);
                inscrireActivityIntent.putExtra("type","Enseignant");
                startActivity(inscrireActivityIntent);

            }
        });
    }
}