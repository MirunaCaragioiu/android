package com.example.facturi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;


import java.util.ArrayList;
import java.util.List;

public class Statistica extends AppCompatActivity {
    List<Float>  listaValori=new ArrayList<>();
    private FacturaDataBase facturaDataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_statistica);

        getSupportActionBar().setTitle(getString(R.string.StatisticaValorilorFacturilor));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.color)));

        facturaDataBase = Room.databaseBuilder(this, FacturaDataBase.class, "DBFacturi").allowMainThreadQueries().build();


        BarChart bc=new BarChart(this,facturaDataBase.getFacturaDAO().getValoareDB());
        setContentView(bc);

    }

}
