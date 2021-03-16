package com.example.caragioiu_miruna_dam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdapterClub extends ArrayAdapter<ClubBaschet>{
    private int resersaID;

    public AdapterClub(@NonNull Context context, int resource, @NonNull List<ClubBaschet> objects) {
        super(context, resource, objects);
        resersaID=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ClubBaschet club=getItem(position);
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View v=inflater.inflate(resersaID,null);

        TextView denumireClub=v.findViewById(R.id.TW3);
        TextView numarMembrii =v.findViewById(R.id.TW1);
        TextView anInfiintare=v.findViewById(R.id.TW2);

        denumireClub.setText(club.getDenumire());
        numarMembrii.setText("Numar membrii: "+club.getNumarMembrii());
        anInfiintare.setText("An infiintare: "+club.getAnInfiintare());

        return v;
    }
}
