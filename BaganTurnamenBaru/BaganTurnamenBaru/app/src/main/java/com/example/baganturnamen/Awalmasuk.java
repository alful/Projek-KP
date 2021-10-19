package com.example.baganturnamen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.baganturnamen.LogSign.Login;
import com.example.baganturnamen.Pesertasi.DaftarTournament;

public class Awalmasuk extends AppCompatActivity {

    Button panitia,peserta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awalmasuk);

        panitia=findViewById(R.id.panitias);
        peserta=findViewById(R.id.pesertas);

        panitia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Awalmasuk.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        peserta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Awalmasuk.this, DaftarTournament.class);
                startActivity(intent);
                finish();
            }
        });
    }
}