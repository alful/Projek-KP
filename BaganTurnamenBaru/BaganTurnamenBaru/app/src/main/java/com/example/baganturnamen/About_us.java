package com.example.baganturnamen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.baganturnamen.aboutus.Anggota1;
import com.example.baganturnamen.aboutus.Anggota2;
import com.example.baganturnamen.aboutus.Anggota3;
import com.example.baganturnamen.aboutus.University;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class About_us extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navigation_vew);

        bottomNavigationView.setSelectedItemId(R.id.universitas);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        onNavigationItemSelected(bottomNavigationView.getMenu().getItem(0));
    }
    Anggota1 anggota1=new Anggota1();
    Anggota2 anggota2=new Anggota2();
    Anggota3 anggota3=new Anggota3();
    University university=new University();



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.anggotas1:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,anggota1).commit();
                return true;
            case R.id.anggotas2:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,anggota2).commit();
                return true;
            case R.id.anggotas3:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,anggota3).commit();
                return true;
            case R.id.universitas:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,university).commit();
                return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(About_us.this, DashboardAdmin.class);
        startActivity(intent);
    }


}