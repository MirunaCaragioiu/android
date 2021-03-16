package com.example.schimbcase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListaOferte extends AppCompatActivity {
    final static String COD_ADD="cod_adauga";
    final static int REQUEST_COD_ADD=100;
    ListView lv;
    ArrayList<HomeExchange> oferte=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_oferte);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_COD_ADD && resultCode==RESULT_OK && data!=null){
            HomeExchange homeExchange= (HomeExchange) data.getSerializableExtra(AdaugaOferta.COD_ADD);
            oferte.add(homeExchange);
            adaugaInLw(oferte);
        }
    }

    private void adaugaInLw(List<HomeExchange> oferte) {
        lv=findViewById(R.id.LW1);
        ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,oferte);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
