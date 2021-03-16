package com.example.facturi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Facturi extends AppCompatActivity {
    private int code = 345;
    private List<Factura> facturi=new ArrayList<>();
    ListView lv;
    private FacturaDataBase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facturi);
        getSupportActionBar().setTitle(getString(R.string.listaFacturi));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.color)));

        ListView lv = findViewById(R.id.LV_facturi);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv=(TextView)view.findViewById(R.id.TW4);
                tv.setText("Platita");
            }
        });

        database= Room.databaseBuilder(this,FacturaDataBase.class,"DBFacturi").allowMainThreadQueries().build();

        TextView rap1=(TextView)findViewById(R.id.TW_rap1);
        rap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(Facturi.this, RaportValoare.class);
                startActivity(it);
            }
        });
        TextView rap2=(TextView)findViewById(R.id.TW_rap2);
        rap2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it2=new Intent(Facturi.this, RaportStatus.class);
                startActivity(it2);
            }
        });

    }


    public void AdaugaFactura (View v){
        if (v.getId() == R.id.BTNAdd) {
            Intent it = new Intent(getApplicationContext(), Adauga.class);
            startActivityForResult(it, code);
        }
    }

//    private void initLV() {
//        facturi.add(new Factura("ENEL", 250, "20.12.2019","Neplatita"));
//        facturi.add(new Factura("APA NOVA", 300, "15.12.2019","Neplatita"));
//        facturi.add(new Factura("DISTRIGAZ SUD", 150, "18.1.2020","Neplatita"));
//        facturi.add(new Factura("RADET", 400, "09.3.2020","Neplatita"));
//
//    }

    private void adaugaInLV() {
//        if (facturi.size() == 0) {
//            initLV();
//        }
        lv =(ListView)findViewById(R.id.LV_facturi);
        lv.invalidate();
        facturi=database.getFacturaDAO().getFacturi();
        final FacturaAdapter adapter = new FacturaAdapter(this, R.layout.factura_layout, facturi);
        lv.setAdapter(adapter);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                database.getFacturaDAO().delete(facturi.get(position));
                adapter.remove(facturi.get(position));
                lv.requestLayout();
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adaugaInLV();
    }

    @Override
    protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
        if (this.code == requestCode) {
            if (resultCode == RESULT_OK) {
                Intent it = getIntent();
                Factura f = data.getParcelableExtra("Fac");
                facturi.add(f);
                adaugaInLV();
            }
        }
    }

}

