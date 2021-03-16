package com.example.schimbcase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AdaugaOferta extends AppCompatActivity {
    final static String COD_ADD="cod_adauga";
    final static String DATE_PATTERN="dd/MM/yyyy";
    EditText etAdresa;
    EditText etNrCamere;
    EditText etSuprafata;
    EditText etPerioada;
    Spinner spinner;
    Button btnSave;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga_oferta);
        intent=getIntent();

        etAdresa=findViewById(R.id.etAdresa);
        etNrCamere=findViewById(R.id.etNrCamere);
        etSuprafata=findViewById(R.id.etPerioada);
        etPerioada=findViewById(R.id.etPerioada);
        spinner=findViewById(R.id.spinnerTipL);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getApplicationContext(),R.array.tipLocuinta,android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        btnSave=findViewById(R.id.BTNSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeExchange homeExchange=createHome();
                intent.putExtra(COD_ADD,homeExchange);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private HomeExchange createHome() {
        String adresa=etAdresa.getText().toString();
        int numarCamere=Integer.parseInt(etNrCamere.getText().toString());
        float suprafata=Float.parseFloat(etSuprafata.getText().toString());
        String data=etPerioada.getText().toString();
        Date date=null;
        try {
            date=new SimpleDateFormat(DATE_PATTERN, Locale.US).parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String tipLocuinta=spinner.getSelectedItem().toString();
        return new HomeExchange(adresa,numarCamere,suprafata,date,tipLocuinta);
    }
}
