package com.example.fitnessApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessuygulamasi.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button giris;
    private TextView kayit;
    private EditText email,sifre;
    private String gelEmail,gelSifre;
    private FirebaseAuth mAuth=FirebaseAuth.getInstance();;
    private FirebaseUser mUser;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        giris=findViewById(R.id.girisi);
        kayit=findViewById(R.id.tiklaKayit);
        email=findViewById(R.id.email);
        sifre=findViewById(R.id.sifre);

        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               girisYap();
            }
        });
        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent giris=new Intent(MainActivity.this,SignupActivty.class);
                finish();
                startActivity(giris);
            }
        });
    }
    public void girisYap(){
        //veriler EditTexten çekiliyor
        gelEmail=email.getText().toString();
        gelSifre=sifre.getText().toString();

        if(!TextUtils.isEmpty(gelEmail)&& !TextUtils.isEmpty(gelSifre)){
            mAuth.signInWithEmailAndPassword(gelEmail,gelSifre)
                    .addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            mUser=mAuth.getCurrentUser();
                            Intent intent=new Intent(MainActivity.this,HomePageActivty.class);
                            intent.putExtra("email",gelEmail);
                            finish();
                            startActivity(intent);
                        }
                    }).addOnFailureListener(this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "Kayıtlı Kullanıcı Bulunamadı", Toast.LENGTH_SHORT).show();
                        }
                    });
        }else
            Toast.makeText(this, "Email ve Sifre boş olamaz", Toast.LENGTH_SHORT).show();
    }
}