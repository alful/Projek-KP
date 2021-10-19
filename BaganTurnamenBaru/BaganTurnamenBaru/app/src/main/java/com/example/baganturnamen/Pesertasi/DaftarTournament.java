package com.example.baganturnamen.Pesertasi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.baganturnamen.R;
import com.google.firebase.database.DatabaseReference;

public class DaftarTournament extends AppCompatActivity {

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_tournament);
    }
}