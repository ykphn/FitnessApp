package com.example.fitnessApp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.fitnessuygulamasi.R;

import java.util.ArrayList;

public class FragmentAccount extends Fragment {

    private Spinner spinnerCinsiyet;
    private EditText editTextBoyunuz, editTextKilonuz;
    private TextView textViewBMIBilgi;
    private Button buttonBMIHesapla;
    private String b, k, vki, xkilo;
    private float boy, kilo, bmi, kacKilo, maxBMI, minBMI, gerekliKilo;
    private TextView emailim;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View tasarim=inflater.inflate(R.layout.fragment_hesabim, container, false);

        editTextBoyunuz = (EditText) tasarim.findViewById(R.id.editTextBoyunuz);
        editTextKilonuz = (EditText) tasarim.findViewById(R.id.editTextKilonuz);
        textViewBMIBilgi = (TextView) tasarim.findViewById(R.id.textBmiHesap);
        buttonBMIHesapla = (Button) tasarim.findViewById(R.id.button_Bmi);
        spinnerCinsiyet = (Spinner) tasarim.findViewById(R.id.spinnerCinsiyet);

        emailim=tasarim.findViewById(R.id.emailimiz);
        emailim.setText(HomePageActivty.gelenEmail);
        cinsiyet();

        buttonBMIHesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bmi();
            }
        });
        return tasarim;
    }
    public void cinsiyet() {
        ArrayList<String> cinsiyet = new ArrayList<>();
        cinsiyet.add("Belirtmeyeceğim");
        cinsiyet.add("Erkek");
        cinsiyet.add("Kadın");
        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, cinsiyet);
        spinnerCinsiyet.setAdapter(arrayAdapter);
    }

    public void bmi(){
        b = editTextBoyunuz.getText().toString();
        k = editTextKilonuz.getText().toString();
        boy = Float.parseFloat(b);
        kilo = Float.parseFloat(k);
        bmi = kilo/(boy*boy);
        vki = Float.toString(bmi);
        textViewBMIBilgi.setText(vki);

        if (bmi<18.5){
            minBMI=(float) 18.5;
            kacKilo = minBMI*(boy*boy);
            gerekliKilo = kacKilo - kilo;
            xkilo = Float.toString(gerekliKilo);
            textViewBMIBilgi.setText("Vücut Kitle İndeksiniz="+vki+" "+xkilo+" kilo almalısınız.");
        } else if (bmi>=18.5 && bmi<24.9) {
            textViewBMIBilgi.setText("Vücut Kitle İndeksiniz= "+vki+" İdeal. Kilonuzu koruyunuz");
        } else if (bmi>=24.9 && bmi<29.9) {
            maxBMI = (float) 24.9;
            kacKilo = maxBMI*(boy*boy);
            gerekliKilo = kilo - kacKilo;
            xkilo = Float.toString(gerekliKilo);
            textViewBMIBilgi.setText("Vücut Kitle İndeksiniz="+vki+" "+xkilo+" kilo vermelisin.");
        } else if (bmi>=30) {
            maxBMI = (float) 24.9;
            kacKilo = maxBMI*(boy*boy);
            gerekliKilo = kilo - kacKilo;
            xkilo = Float.toString(gerekliKilo);
            textViewBMIBilgi.setText("Vücut Kitle İndeksiniz="+vki+" Obezsiniz. "+xkilo+" kilo vermeniz gerekiyor.");
        }
    }
}