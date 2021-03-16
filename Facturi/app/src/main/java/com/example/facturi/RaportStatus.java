package com.example.facturi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RaportStatus extends AppCompatActivity {
    private List<Factura> facturi=new ArrayList<>();
    private FacturaDataBase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raport_status);
        getSupportActionBar().setTitle(getString(R.string.facNeplat));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.color)));

        ListView lv = findViewById(R.id.LV_rapData);
        database= Room.databaseBuilder(this,FacturaDataBase.class,"DBFacturi").allowMainThreadQueries().build();


        facturi=database.getFacturaDAO().getFacturiDupaValoare("Neplatita");
        FacturaAdapter adapter = new FacturaAdapter(this, R.layout.factura_layout, facturi);
        lv.setAdapter(adapter);

        FileOutputStream fos = null;
        try {
            fos = openFileOutput("RaportStatus.txt",MODE_PRIVATE);
            fos.write(facturi.toString().getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fos!=null){
                try{
                    fos.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
