package com.example.nticetudier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LicenceActivity extends AppCompatActivity {
    private Button TI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licence);

        TI = findViewById(R.id.TI);

        TI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ForumActivityIntent = new Intent(LicenceActivity.this,ForumActivity.class);
                startActivity(ForumActivityIntent);
            }
        });
    }
}