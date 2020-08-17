package com.example.nticetudier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class acceuilActivity extends AppCompatActivity {
    private Button Trancommun ;
    private Button Licence ;
    private Button Master ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);

        Trancommun=(Button)findViewById(R.id.l1l2);
        Licence=(Button) findViewById(R.id.Lic);
        Master=(Button) findViewById(R.id.Master);

        Trancommun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TrancommunActivityIntent = new Intent(acceuilActivity.this,TrancommunActivity.class);
                startActivity(TrancommunActivityIntent);
            }
        });
        Licence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LicenceActivityIntent = new Intent(acceuilActivity.this,LicenceActivity.class);
                startActivity(LicenceActivityIntent);

            }
        });
        Master.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MasterActivityIntent = new Intent(acceuilActivity.this,MasterActivity.class);
                startActivity(MasterActivityIntent);
            }
        });
    }

}
