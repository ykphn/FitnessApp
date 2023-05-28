package com.example.fitnessApp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fitnessuygulamasi.R;


public class FragmentSpor extends Fragment {
    private Button buttonHesapla;
    private EditText eTextSinav, eTextMekik, eTextBarfiks,eTextAgirlik;
    private TextView textHareketBilgi;
    private String s, m, b,a, kal;
    private int sinav, mekik, barfiks,agirlik, yakilanKalori;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     View tasarim=inflater.inflate(R.layout.fragment_spor, container, false);

     eTextSinav=tasarim.findViewById(R.id.sinav_Adet);
     eTextBarfiks=tasarim.findViewById(R.id.barfiks_Adet);
     eTextMekik=tasarim.findViewById(R.id.mekik_Adet);
     eTextAgirlik=tasarim.findViewById(R.id.halter_Adet);
     textHareketBilgi=tasarim.findViewById(R.id.gym_SonucText);
     buttonHesapla=tasarim.findViewById(R.id.gym_hesapla);


     buttonHesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kaloriHesabi();
            }
        });
        return tasarim;
    }
    public void kaloriHesabi(){
        s = eTextSinav.getText().toString();
        m = eTextMekik.getText().toString();
        b = eTextBarfiks.getText().toString();
        a = eTextAgirlik.getText().toString();
        sinav = Integer.parseInt(s);
        mekik = Integer.parseInt(m);
        barfiks = Integer.parseInt(b);
        agirlik=Integer.parseInt(a);

        yakilanKalori = (sinav*6)+(mekik*4)+(barfiks*5)+(agirlik*3);
        kal = Integer.toString(yakilanKalori);
        textHareketBilgi.setText(s+" şınav "+m+" mekik "+a+" halter "+b+" barfiks çektin. "+kal+" kalori harcadınız.");
    }
}