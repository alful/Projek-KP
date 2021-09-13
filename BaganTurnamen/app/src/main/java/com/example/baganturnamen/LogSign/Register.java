package com.example.baganturnamen.LogSign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.baganturnamen.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText email,nama,password;
    Button sign,login;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("Admin");
    admin Admin;
    Integer manag=1;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email= findViewById(R.id.regemailusers);
        nama= findViewById(R.id.regnamausers);
        password= findViewById(R.id.regpasswordusers);
        sign=findViewById(R.id.daftars);
        login=findViewById(R.id.masuks);
//        toolbar=findViewById(R.id.toolbars);

//        setSupportActionBar(toolbar);
        androidx.appcompat.widget.Toolbar toolbar= (androidx.appcompat.widget.Toolbar) findViewById(R.id.tolbar);
        toolbar.setTitle("Register Akun");
        setSupportActionBar(toolbar);


        firebaseAuth=FirebaseAuth.getInstance();
        Admin=new admin();
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(Register.this);
                builder.setTitle("Registrasi Admin");
                builder.setMessage("Apakah Yakin Registrasi Dengan Data Ini?");
                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (nama.getText().toString().isEmpty()==false && email.getText().toString().isEmpty()==false)
                        {
                            if (password.getText().toString().length() <7)
                                password.setError("Password Minimal 7 Huruf");
                            else{
                                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        FirebaseUser user=firebaseAuth.getCurrentUser();
                                        Admin.setNama(nama.getText().toString());
                                        Admin.setEmail(email.getText().toString());
                                        Admin.setPassword(password.getText().toString());
                                        Admin.setManag(manag);
                                        String ID= databaseReference.push().getKey();
                                        Admin.setID(ID);
                                        Admin.setUID(user.getUid());
                                        Toast.makeText(Register.this,"Akun Berhasil Dibuat",Toast.LENGTH_SHORT).show();

                                        databaseReference.child(user.getUid()).setValue(Admin);
                                        FirebaseAuth.getInstance().signOut();
                                        startActivity(new Intent(getApplicationContext(),Login.class));
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Register.this,"Akun Gagal Dibuat",Toast.LENGTH_SHORT).show();

                                    }
                                });
                            }
                        }
                        else if (nama.getText().toString().isEmpty()==false && email.getText().toString().isEmpty()==false && password.getText().toString().isEmpty()==true){
                            password.setError("Password Tidak Boleh Kosong");
                        }
                        else if (nama.getText().toString().isEmpty()==false && email.getText().toString().isEmpty()==true && password.getText().toString().isEmpty()==false){
                            email.setError("Email Tidak Boleh Kosong");
                        }
                        else if (nama.getText().toString().isEmpty()==true && email.getText().toString().isEmpty()==false && password.getText().toString().isEmpty()==false){
                            nama.setError("Nama Tidak Boleh Kosong");
                        }
                        else if (nama.getText().toString().isEmpty()==true && email.getText().toString().isEmpty()==true && password.getText().toString().isEmpty()==true){
                            nama.setError("Nama Tidak Boleh Kosong");
                            email.setError("Email Tidak Boleh Kosong");
                            password.setError("Password Tidak Boleh Kosong");


                        }

                    }
                });
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }

    private void setSupportActionBar(Toolbar toolbar) {

    }
}