package com.ventura.emilp.tournamentbrackets.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ventura.emilp.tournamentbrackets.Fragment.BracketsFragment;
import com.ventura.emilp.tournamentbrackets.R;
import com.ventura.emilp.tournamentbrackets.application.BracketsApplication;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    private BracketsFragment bracketFragment;
//    DatabaseReference databaseReference;
//    ArrayList<String> ALNama;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseBracketsFragment();
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("Peserta");
//
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                ALNama = new ArrayList<String>();
//                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
//                    String nama = dataSnapshot.child("nama").getValue(String.class);
//
//                    ALNama.add(nama);
//                }
//
//                Log.d("TAG", "setData: "+ALNama);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });



    }

    private void initialiseBracketsFragment() {

        bracketFragment = new BracketsFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, bracketFragment, "brackets_home_fragment");
        transaction.commit();
        manager.executePendingTransactions();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setScreenSize();

    }

    private void setScreenSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        BracketsApplication.getInstance().setScreenHeight(height);
    }
}
