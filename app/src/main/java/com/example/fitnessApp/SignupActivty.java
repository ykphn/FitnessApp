package com.example.fitnessApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitnessuygulamasi.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class SignupActivty extends AppCompatActivity {
    private Button kaydol;
    private EditText kayitAd,kayitEmail,kayitSifre;
    private String alAdi,alEmail,alSifre;

    //Firebase kullanıcı adı ,şifresi ve emailini tablo şeklinde tutma
    FirebaseAuth mAuth=FirebaseAuth.getInstance();;
    //Firebase kullanıcıları email ve password ile kaydetme

    //Firebase kullanıcı adı ,şifresi ve emailini tablo şeklinde tutma
    private HashMap<String,Object> mData;
    private FirebaseFirestore mFirestore=FirebaseFirestore.getInstance();
    private FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_activty);
        kaydol=findViewById(R.id.kayit);
        kayitAd=findViewById(R.id.kayit_ad);
        kayitEmail=findViewById(R.id.kayit_email);
        kayitSifre=findViewById(R.id.kayit_sifre);

        kaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alAdi=kayitAd.getText().toString();
                alEmail=kayitEmail.getText().toString();
                alSifre=kayitSifre.getText().toString().trim();
                //Buradan 61.satıra kadar kullanıcı emaili ve şifresi ile firebase de autuh a kullanıcı kaydı oluşturma alanı
                if(!TextUtils.isEmpty(alAdi) && !TextUtils.isEmpty(alSifre) && !TextUtils.isEmpty(alEmail)){
                    mAuth.createUserWithEmailAndPassword(alEmail,alSifre)
                            .addOnCompleteListener(SignupActivty.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        mUser=mAuth.getCurrentUser();
                                        mData=new HashMap<>();
                                        //Buradan 70.satıra kadar kullanıcı verilerini özel olarak tablo altına ad,şifre ve email i tutma
                                        mData.put("Kullanici Adi",alAdi);
                                        mData.put("Kullanici Sifre",alSifre);
                                        mData.put("Kullanici Email",alEmail);
                                        mData.put("Kullanici ID",mUser.getUid());
                                        mFirestore.collection("kullanicilar").document(FirebaseAuth.getInstance().getUid())
                                                .set(mData)
                                                .addOnCompleteListener(SignupActivty.this, new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

                                                    }
                                                });
                                        //Kayıt tamamlandığı anda giriş ekranına dönüş
                                        Toast.makeText(SignupActivty.this,"Kayıt Olma İşleminiz Gerçekleşmiştir",Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(SignupActivty.this,MainActivity.class);
                                        finish();
                                        startActivity(intent);
                                    }
                                    else{
                                        Toast.makeText(SignupActivty.this,"Veri Kaydı Kaydedilirken hata oluşmuştur",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else{
                    Toast.makeText(SignupActivty.this,"Lütfen Gerekli Tüm Alanları Doldurunuz",Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
    public void onBackPressed(){
        Intent geri=new Intent(SignupActivty.this,MainActivity.class);
        finish();
        startActivity(geri);
    }
}