package com.example.facturi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class FacturaAdapter extends ArrayAdapter<Factura> {
    private int resursaID;

    public FacturaAdapter(@NonNull Context context, int resource, @NonNull List<Factura> objects) {
        super(context, resource, objects);
        resursaID=resource;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        Factura f=getItem(position);
        LayoutInflater inflater =LayoutInflater.from(getContext());
        View v = inflater.inflate(resursaID,null);

        TextView furnizorFactura=v.findViewById(R.id.TW1);
        TextView valoareFactura=v.findViewById(R.id.TW2);
        TextView dataFactura=v.findViewById(R.id.TW3);
        TextView statusFactura=v.findViewById(R.id.TW4);

        furnizorFactura.setText( f.getFurnizor());
        valoareFactura.setText(""+f.getValoare());
        dataFactura.setText(f.getDataSacdenta());
        statusFactura.setText(f.getStatus());


        return v;
    }
}
