package com.example.caragioiu_miruna_dam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AdaugaClubBaschet extends AppCompatActivity {
    final static String COD_ADD="cod_adauga";
    EditText etDenumire;
    EditText etNumarMembrii;
    EditText etAnInfiintare;
    Spinner spinnerTip;
    Button btnSave;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga_club_baschet);
        intent=getIntent();

        etDenumire=findViewById(R.id.ETDenumireClub);
        etNumarMembrii=findViewById(R.id.ETNumarMembrii);
        etAnInfiintare=findViewById(R.id.ETAnInfiintare);
        spinnerTip=findViewById(R.id.SpinnerTip);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(getApplicationContext(),R.array.tipClub, android.R.layout.simple_dropdown_item_1line);
        spinnerTip.setAdapter(adapter);

        btnSave=findViewById(R.id.BTNSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(valid()) {
                    ClubBaschet club = createClub();
                    intent.putExtra(COD_ADD, club);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        if(intent.hasExtra(COD_ADD)){
            ClubBaschet club=(ClubBaschet)intent.getSerializableExtra(COD_ADD);
            update(club);
        }
    }

    private void update(ClubBaschet club) {
        etDenumire.setText(club.getDenumire());
        etNumarMembrii.setText(""+club.getNumarMembrii());
        etAnInfiintare.setText(""+club.getAnInfiintare());
        ArrayAdapter<String> adapter=(ArrayAdapter<String>) spinnerTip.getAdapter();
        for(int i=0;i<adapter.getCount();i++){
            if(adapter.getItem(i).equals(club.getTip())){
                spinnerTip.setSelection(i);
            }
        }
    }

    private boolean valid() {
        if(etDenumire.getText().toString()==null || etDenumire.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.denInvalida,Toast.LENGTH_SHORT).show();
            return false;
        }
        if(etNumarMembrii.getText().toString()==null || etNumarMembrii.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.nrInvalid,Toast.LENGTH_SHORT).show();
            return false;
        }
        if(etAnInfiintare.getText().toString()==null || etAnInfiintare.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.anInvalid, Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private ClubBaschet createClub() {
        String denumire=etDenumire.getText().toString();
        int numarMembrii=Integer.parseInt(etNumarMembrii.getText().toString());
        int anInfiintare=Integer.parseInt(etAnInfiintare.getText().toString());
        String tipClub=spinnerTip.getSelectedItem().toString();
        return new ClubBaschet(denumire,tipClub,numarMembrii,anInfiintare);
    }
}
