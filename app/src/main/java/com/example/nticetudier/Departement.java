package com.example.nticetudier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Departement extends AppCompatActivity {

    Button Trancommun, Licence, Master ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialite);

        Trancommun= findViewById(R.id.l1l2);
        Licence= findViewById(R.id.Lic);
        Master= findViewById(R.id.Master);

        Trancommun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TrancommunActivityIntent = new Intent(Departement.this,TrancommunActivity.class);
                startActivity(TrancommunActivityIntent);
            }
        });
        Licence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LicenceActivityIntent = new Intent(Departement.this,LicenceActivity.class);
                startActivity(LicenceActivityIntent);

            }
        });
        Master.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MasterActivityIntent = new Intent(Departement.this,MasterActivity.class);
                startActivity(MasterActivityIntent);
            }
        });
    }

}
