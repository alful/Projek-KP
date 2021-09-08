package com.example.baganturnamen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DashboardAdmin extends AppCompatActivity {
    ImageView IVInputPeserta, IVDaftarPeserta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);
        IVInputPeserta = findViewById(R.id.idIVInputPeserta);
        IVDaftarPeserta = findViewById(R.id.idIVDaftarPeserta);

        IVInputPeserta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardAdmin.this, MainActivity.class);
                startActivity(intent);
            }
        });

        IVDaftarPeserta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(DashboardAdmin.this, DaftarPeserta.class);
                startActivity(intent);
            }
        });
    }
}