package com.example.facturi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.R.style;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Calendar;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static androidx.room.Room.databaseBuilder;

public class Adauga extends AppCompatActivity {
    TextView mTextSelect;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    Intent intent;
    private FacturaDataBase  facturaDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga);
        intent=getIntent();

        getSupportActionBar().setTitle(getString(R.string.adaugaFac));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.color)));

        facturaDataBase = Room.databaseBuilder(this, FacturaDataBase.class, "DBFacturi").allowMainThreadQueries().build();

        mTextSelect=(TextView)findViewById(R.id.TW_select);
        mTextSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(Adauga.this,android.R.style.Theme_Material_Dialog_MinWidth,mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1=i1+1;
                Log.d(TAG, "onDateSet: dd/mm/yyyy:" + i2 + "." + i1 + "." +i);
                String date= i2 + "." + i1 + "." +i;
                mTextSelect.setText(date);
            }
        };


        Button okBtn = findViewById(R.id.BtnIdOK);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adaugaFactura(v);
            }
        });


    }

    public void adaugaFactura(View view){
        final String furnizor=((EditText)findViewById(R.id.ET_furnizor)).getText().toString();
        String val=((EditText)findViewById(R.id.ET_valoare)).getText().toString();
        final Float valoare=Float.parseFloat(val);
        final String data=((TextView)findViewById(R.id.TW_select)).getText().toString();
        CheckBox cb=(CheckBox)findViewById(R.id.CBFactura);
        final String status;
        if (cb.isChecked()){
            status="Platita";
        }
        else{
            status="Neplatita";
        }

        Factura f=new Factura(furnizor, valoare, data,status);
        facturaDataBase.getFacturaDAO().insert(f);
        intent.putExtra("Fac",  f);
        setResult(RESULT_OK, intent);
        finish();

    }

    public void cancelOperatiune(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

}
