package com.example.facturi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import java.util.Collections;
import java.util.List;

public class BarChart extends View {

    private List<Float> listaValori;

    public BarChart(Context context,List<Float> listaValori) {
        super(context);
        this.listaValori=listaValori;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Paint pensula=new Paint();
        pensula.setColor(getResources().getColor(R.color.color));
        pensula.setTextSize(20);
        float maxim= Collections.max(listaValori);
        float start=0;
        for(int i=0;i<listaValori.size();i++){
            canvas.drawRect(i*100+20,700-(listaValori.get(i)*300/maxim),start+100,700,pensula);
            canvas.drawText("Factura"+ (i+1),i*100+20,720,pensula);
            start+=100;
        }
    }
}
