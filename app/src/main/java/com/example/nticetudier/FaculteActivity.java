package com.example.nticetudier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FaculteActivity extends AppCompatActivity {
    private Button ntic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculte2);


        ntic = findViewById(R.id.Ntic);

        ntic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent acceuilActivityIntent = new Intent(FaculteActivity.this, Departement.class);
                startActivity(acceuilActivityIntent);
            }
        });






    }}

