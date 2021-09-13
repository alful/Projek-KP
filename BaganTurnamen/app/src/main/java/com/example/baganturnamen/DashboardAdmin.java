package com.example.baganturnamen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.baganturnamen.LogSign.Login;
import com.google.firebase.auth.FirebaseAuth;

public class DashboardAdmin extends AppCompatActivity {
    ImageView IVInputPeserta, IVDaftarPeserta, IVLogOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);
        IVInputPeserta = findViewById(R.id.idIVInputPeserta);
        IVDaftarPeserta = findViewById(R.id.idIVDaftarPeserta);
        IVLogOff = findViewById(R.id.idIVLoggOff);
        androidx.appcompat.widget.Toolbar toolbar= (androidx.appcompat.widget.Toolbar) findViewById(R.id.tolbar);
        toolbar.setTitle("Dashboard");
        setSupportActionBar(toolbar);
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

        IVLogOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });
    }
}