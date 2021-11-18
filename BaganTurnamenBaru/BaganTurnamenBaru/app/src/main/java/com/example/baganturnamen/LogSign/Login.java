package com.example.baganturnamen.LogSign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.baganturnamen.Dashboard.DashboardAdm;
import com.example.baganturnamen.DashboardAdmin;
import com.example.baganturnamen.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    EditText email,password;
    Button login,sign;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,databaseReference2;
    admin Admin;
    FirebaseUser firebaseUser;

    String UID;
    ArrayList<String> ALKey = new ArrayList<String>();
    ProgressBar progressBar;


//    @Override
//    protected void onStart() {
//        super.onStart();
//
//
//
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        Log.d("TAG", "onStarts: "+firebaseUser);
//        if(firebaseUser!=null)
//        {
//            Log.d("TAG", "onStart: "+firebaseUser);
////            String UID;
////            UID=firebaseUser.getUid();
////
////            databaseReference=FirebaseDatabase.getInstance().getReference().child("Admin").child(UID);
////            databaseReference.addValueEventListener(new ValueEventListener() {
//////            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
////                @Override
////                public void onDataChange(@NonNull DataSnapshot snapshot) {
////                    Integer manage=snapshot.child("manag").getValue(Integer.class);
////                    String key= snapshot.child("uid").getValue(String.class);
////                    String nama=snapshot.child("nama").getValue(String.class);
////                    String email=snapshot.child("email").getValue(String.class);
////                    String password=snapshot.child("password").getValue(String.class);
////                    Log.d("TAG", "onStart: "+manage);
//
//
////
////                    if (manage.equals(1))
////                    {
////                        Intent intent =new Intent(Login.this, DashboardAdmin.class);
////                        intent.putExtra("idkey",key);
//////                        intent.putExtra("idemail",email);
//////                        intent.putExtra("idpass",password);
//////                        intent.putExtra("idnama",nama);
////
////                        startActivity(intent);
////
////                        finish();
////                    }
//////                    else if (manage.equals("0"))
//////                    {
//////                        Intent intent =new Intent(Login.this,.class);
//////                        intent.putExtra("idkeys",key);
//////                        intent.putExtra("idemails",email);
//////                        intent.putExtra("idpasss",password);
//////                        intent.putExtra("idnamas",nama);
//////                        startActivity(intent);
//////                        finish();
//////                    }
////
////                }
////
////                @Override
////                public void onCancelled(@NonNull DatabaseError error) {
////
////                }
////            });
//
//
//        }
//        else if (firebaseUser==null){
//            // User is signed out
//            Log.d("TAG", "onAuthStateChanged:signed_out");
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.emailuserss);
        password=findViewById(R.id.passworduserss);
        login=findViewById(R.id.loginsss);
        sign=findViewById(R.id.signupsss);

        androidx.appcompat.widget.Toolbar toolbar= (androidx.appcompat.widget.Toolbar) findViewById(R.id.tolbar);
        toolbar.setTitle("Login Akun");
        setSupportActionBar(toolbar);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if(firebaseUser!=null)
        {
            Log.d("TAG", "onStarttnull: "+firebaseUser);
            UID = firebaseUser.getUid();
            Intent intent =new Intent(Login.this, DashboardAdmin.class);
            intent.putExtra("UID", UID);
            startActivity(intent);
//            String UID;
//            UID=firebaseUser.getUid();
//
//            databaseReference=FirebaseDatabase.getInstance().getReference().child("Admin").child(UID);
//            databaseReference.addValueEventListener(new ValueEventListener() {
////            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    Integer manage=snapshot.child("manag").getValue(Integer.class);
//                    String key= snapshot.child("uid").getValue(String.class);
//                    String nama=snapshot.child("nama").getValue(String.class);
//                    String email=snapshot.child("email").getValue(String.class);
//                    String password=snapshot.child("password").getValue(String.class);
//                    Log.d("TAG", "onStart: "+manage);


//
//                    if (manage.equals(1))
//                    {
//                        Intent intent =new Intent(Login.this, DashboardAdmin.class);
//                        intent.putExtra("idkey", UID);
//                        intent.putExtra("idkey",key);
////                        intent.putExtra("idemail",email);
////                        intent.putExtra("idpass",password);
////                        intent.putExtra("idnama",nama);
//
//                        startActivity(intent);
//
//                        finish();
//                    }
////                    else if (manage.equals("0"))
////                    {
////                        Intent intent =new Intent(Login.this,.class);
////                        intent.putExtra("idkeys",key);
////                        intent.putExtra("idemails",email);
////                        intent.putExtra("idpasss",password);
////                        intent.putExtra("idnamas",nama);
////                        startActivity(intent);
////                        finish();
////                    }
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });


        }
        else if (firebaseUser==null){
            // User is signed out
            Log.d("TAG", "onAuthStateChanged:signed_out");
        }


        Log.d("TAG", "onCreatelog: "+firebaseUser);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().isEmpty()==false && password.getText().toString().isEmpty()==false) {
                    Log.d("TAG", "onCreateaaaa1: " + firebaseUser);
//                if (valid) {
                    firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Intent intent = new Intent(Login.this, DashboardAdmin.class);
                                    startActivity(intent);
                                    Log.d("TAG", "onSuccessfirbase: ");
//                                    finish();
//
////                                    checkuserAccesLevel(authResult.getUser().getUid());
//                                    UID = authResult.getUser().getUid();
//                                    Log.d("TAG", "onCreateaaaa: " + UID);
//                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
//                                    DatabaseReference reference = databaseReference.child("Admin").child(UID).child("manag");
//                                    String a = reference.getDatabase().toString();
//                                    String as = reference.toString();
//                                    Log.d("TAG", "onSuccess: " + a);
//                                    Query akunfirebase = reference.orderByChild(UID);
//                                    Log.d("TAG", "checkuserAccesLevel: " + reference);
//                                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Admin").child(UID);
////                                    databaseReference.addValueEventListener(new ValueEventListener() {
//                                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//                                        @Override
//                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//
//
//                                            Integer l = snapshot.child("manag").getValue(Integer.class);
//                                            String key = snapshot.child("uid").getValue(String.class);
//                                            String nama = snapshot.child("nama").getValue(String.class);
//                                            String email = snapshot.child("email").getValue(String.class);
//                                            String password = snapshot.child("password").getValue(String.class);
//                                            Log.d("TAG", "onDataChangess: " + key);
//                                            Log.d("TAG", "onDataChangess: " + nama);
//                                            Log.d("TAG", "onDataChangess: " + email);
//                                            Log.d("TAG", "onDataChangess: " + password);
//                                            Toast.makeText(Login.this, "Berhasil Masukssss", Toast.LENGTH_SHORT).show();
//
//
//                                            if (l.equals(1)) {
//                                                Toast.makeText(Login.this, "User", Toast.LENGTH_LONG).show();
//                                                Toast.makeText(Login.this, "Berhasil Masuk", Toast.LENGTH_SHORT).show();
//
//                                                Intent intent = new Intent(Login.this, DashboardAdmin.class);
//                                                intent.putExtra("idkey", key);
////                                                intent.putExtra("idemail", email);
////                                                intent.putExtra("idpass", password);
////                                                intent.putExtra("idnama", nama);
//                                                startActivity(intent);
//                                                finish();
//
//                                            }
////                                            else if (l.equals("0")) {
////                                                Toast.makeText(Login.this, "ADM", Toast.LENGTH_LONG).show();
////
////                                                Intent intent = new Intent(Login.this, DashAdmin.class);
////                                                intent.putExtra("idkeys", key);
////                                                intent.putExtra("idemails", email);
////                                                intent.putExtra("idpasss", password);
////                                                intent.putExtra("idnamas", nama);
////                                                Log.d("TAG", "onClick: " + email);
////                                                Log.d("TAG", "onClick: " + password);
////
////                                                startActivity(intent);
////                                                finish();
////
////                                            }
//
//
//                                        }
//
//                                        @Override
//                                        public void onCancelled(@NonNull DatabaseError error) {
//
//                                        }
//                                    });
//
//

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else if (email.getText().toString().isEmpty()==false && password.getText().toString().isEmpty()==true) {
                    password.setError("Password Tidak Boleh Kosong");
                    Toast.makeText(Login.this, "Password Tidak Boleh Kosong", Toast.LENGTH_LONG).show();

                }
                else if (email.getText().toString().isEmpty()==true && password.getText().toString().isEmpty()==false) {
                    email.setError("Email Tidak Boleh Kosong");
                    Toast.makeText(Login.this, "Email Tidak Boleh Kosong", Toast.LENGTH_LONG).show();

                }
                else if (email.getText().toString().isEmpty()==true && password.getText().toString().isEmpty()==true) {
                    email.setError("Email Tidak Boleh Kosong");
                    password.setError("Password Tidak Boleh Kosong");
                    Toast.makeText(Login.this, "Email dan Password Tidak Boleh Kosong", Toast.LENGTH_LONG).show();

                }

//                }
            }
        });


        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));

            }
        });

    }




}