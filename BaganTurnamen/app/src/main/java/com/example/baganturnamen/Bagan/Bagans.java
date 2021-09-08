package com.example.baganturnamen.Bagan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.baganturnamen.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import de.blox.treeview.BaseTreeAdapter;
import de.blox.treeview.TreeNode;
import de.blox.treeview.TreeView;

public class Bagans extends AppCompatActivity {

    DatabaseReference databaseReference;
    ArrayAdapter arrayAdapter;
    ArrayList<String> arrayTampil=new ArrayList<>();
    ArrayList<String> arrayTampil1=new ArrayList<>();
    Button ssa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bagans);
        ssa=findViewById(R.id.ssa);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Peserta");
        Log.d("TAG", "onCreate: "+databaseReference);
        HashMap <String,String> datas=new HashMap<String,String>();

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                databaseReference = FirebaseDatabase.getInstance().getReference().child("Peserta").child(snapshot.getKey());
                String nama= (String)snapshot.child("nama").getValue();
                Log.d("TAG", "onChildAdded: "+nama);
                Long umur= (Long) snapshot.child("umur").getValue();
                String id=snapshot.getKey();
                String umr= Long.toString(umur);
                Log.d("TAG", "onChildAdded: "+umur);

                datas.put(id,nama);
                Log.d("TAG", "onChildAddedss: "+datas);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ssa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get a random entry from the HashMap.
                Object[] crunchifyKeys = datas.keySet().toArray();
                Object key = crunchifyKeys[new Random().nextInt(crunchifyKeys.length)];
                System.out.println("************ Random Value ************ \n" + key + " :: " + datas.get(key));

                List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(datas.entrySet());

                // Bonus Crunchify Tips: How to Shuffle a List??
                // Each time you get a different order...
                System.out.println("\n************ Now Let's start shuffling list ************");
                Collections.shuffle(list);
                for (Map.Entry<String, String> entry : list) {
                    System.out.println(entry.getKey() + " :: " + entry.getValue());
                }

            }
        });


    }
}
