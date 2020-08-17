package com.example.nticetudier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText editText ;
    private EditText editText1 ;

    private Button button ;
    private Button button1 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=(EditText) findViewById(R.id.editText);
        editText1=(EditText) findViewById(R.id.editText1);

        button=(Button) findViewById(R.id.button);
        button1=(Button) findViewById(R.id.button1);
        button.setEnabled(false);

        editText.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                button.setEnabled(s.toString().length() != 0);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            }

            );
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inscrireActivityIntent = new Intent(MainActivity.this,inscrireActivity.class);
                startActivity(inscrireActivityIntent);

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent acceuilActivityIntent = new Intent(MainActivity.this,acceuilActivity.class);
                startActivity(acceuilActivityIntent);
            }
        });
        };
    }

