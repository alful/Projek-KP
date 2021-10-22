package com.example.baganturnamen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.baganturnamen.LogSign.Login;
import com.example.baganturnamen.LogSign.admin;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_SCREEN = 2000;
    DatabaseReference databaseReference,databaseReference2;
    FirebaseUser firebaseUser;

    String UID;
    @Override
    protected void onStart() {
        super.onStart();



        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser!=null)
        {
            String UID;
            UID=firebaseUser.getUid();

            databaseReference= FirebaseDatabase.getInstance().getReference().child("Admin").child(UID);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Integer manage=snapshot.child("manag").getValue(Integer.class);
                    String key= snapshot.child("uid").getValue(String.class);
                    String nama=snapshot.child("nama").getValue(String.class);
                    String email=snapshot.child("email").getValue(String.class);
                    String password=snapshot.child("password").getValue(String.class);
                    Log.d("TAG", "onStart: "+manage);

                    if (manage.equals(1))
                    {
//                        intent.putExtra("idemail",email);
//                        intent.putExtra("idpass",password);
//                        intent.putExtra("idnama",nama);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                            }
                        },SPLASH_SCREEN);
                        Intent intent =new Intent(SplashScreen.this, DashboardAdmin.class);
                        intent.putExtra("idkey",key);
                        startActivity(intent);
                        finish();

//                        startActivity(intent);
//
//                        finish();
                    }
//                    else if (manage.equals("0"))
//                    {
//                        Intent intent =new Intent(Login.this,.class);
//                        intent.putExtra("idkeys",key);
//                        intent.putExtra("idemails",email);
//                        intent.putExtra("idpasss",password);
//                        intent.putExtra("idnamas",nama);
//                        startActivity(intent);
//                        finish();
//                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }
        else if (firebaseUser==null){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent =new Intent(SplashScreen.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            },SPLASH_SCREEN);

            // User is signed out
            Log.d("TAG", "onAuthStateChanged:signed_out");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        if(firebaseUser!=null) {
//
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    Intent intent = new Intent(SplashScreen.this, Login.class);
//                    startActivity(intent);
//                    finish();
//                }
//            }, SPLASH_SCREEN);
//        }
//        else {
//            Intent intent = new Intent(SplashScreen.this, Login.class);
//            startActivity(intent);
//            finish();
//        }
    }

}