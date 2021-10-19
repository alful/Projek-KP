package com.example.baganturnamen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class BotNav extends AppCompatActivity {
    ArrayList<String> ALKey = new ArrayList<String>();
    ArrayList<String> ALKey2 = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot_nav);

        ALKey.add("a");
        ALKey.add("b");
        ALKey.add("c");
        ALKey.add("d");
        ALKey.add("e");
        ALKey.add("f");

        ALKey2.add("1");
        ALKey2.add("2");
        ALKey2.add("3");
        ALKey2.add("4");
        ALKey2.add("5");
        ALKey2.add("6");
        long seed=System.nanoTime();
        Collections.shuffle(ALKey,new Random(seed));
        Collections.shuffle(ALKey2,new Random(seed));

        Log.d("TAG", "onCreate: "+ALKey+"on Create : "+ALKey2);
        long seed2=System.nanoTime();
        Collections.shuffle(ALKey,new Random(seed2));
        Collections.shuffle(ALKey2,new Random(seed2));

        Log.d("TAG", "onCreate: "+ALKey+"on Create : "+ALKey2);


    }
}