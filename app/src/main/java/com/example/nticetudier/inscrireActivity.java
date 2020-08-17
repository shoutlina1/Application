package com.example.nticetudier;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class inscrireActivity extends AppCompatActivity {
    private EditText Nom;
    private EditText Prenom ;
    private EditText Email;
    private EditText num ;
    private EditText motdpass;
    private Button inscrir;
    private CheckBox etudiant;
    private CheckBox enseignent ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscrire);
        this.etudiant=(CheckBox) this.findViewById(R.id.etud);
        this.enseignent=(CheckBox) this.findViewById(R.id.prof);
        this.inscrir=(Button) this.findViewById(R.id.inscri);

inscrir.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(getApplicationContext(),"vous etes inscrit !",Toast.LENGTH_LONG).show();
    }
});
  }



}
