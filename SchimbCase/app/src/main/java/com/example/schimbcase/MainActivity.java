package com.example.schimbcase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    SharedPref mySharedPref;
    final static String CODE_ADD="cod_Adauga";
    final static int REQUEST_CODE_ADD=100;
    Intent intent;
    ArrayList<HomeExchange> oferte=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySharedPref = new SharedPref(this);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US);
        long date = new Date().getTime();
        String data = dateFormat.format(date);
        mySharedPref.setUserDate(data);
        intent = getIntent();
    }

    public void despreApp(View view) {
        String numeUtilizator=getResources().getString(R.string.user);
        String dataDeschidereApp=mySharedPref.getUsertDate();
        Toast.makeText(getApplicationContext(), numeUtilizator+" - "+dataDeschidereApp, Toast.LENGTH_SHORT).show();
    }

    public void adaugaOferta(View view) {
        Intent it=new Intent(getApplicationContext(),AdaugaOferta.class);
        it.putExtra(CODE_ADD,"adaugare");
        startActivityForResult(it,REQUEST_CODE_ADD);
    }

    public void listaOferte(View view) {
        Intent it=new Intent(getApplicationContext(),ListaOferte.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("colectie",oferte);
        intent.putExtras(bundle);
        startActivity(it);
    }
}
