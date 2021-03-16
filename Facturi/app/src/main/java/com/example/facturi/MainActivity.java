package com.example.facturi;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;

import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import java.io.InputStream;


public class MainActivity extends AppCompatActivity{
    private Switch mSwitch;
    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref=new SharedPref(this);
        if(sharedPref.loadDarkTheme()==true){
            setTheme(R.style.myTheme2);
        }else{
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(getString(R.string.EvidentaFcaturiDeAchitat));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.color)));

        Button btn1=(Button)findViewById(R.id.nav_feedback);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1=new Intent(getApplicationContext(),Feedback.class);
                startActivity(it1);
            }
        });


        Button btn4=(Button)findViewById(R.id.nav_facturi);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it3=new Intent(getApplicationContext(),Facturi.class);
                startActivity(it3);
            }
        });

        Button btn6=(Button)findViewById(R.id.nav_furnizori);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it6=new Intent(getApplicationContext(),Furnizori.class);
                startActivity(it6);
            }
        });

        Button btn5=(Button)findViewById(R.id.nav_statistica);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it4=new Intent(getApplicationContext(),Statistica.class);
                startActivity(it4);
            }
        });



        mSwitch=(Switch)findViewById(R.id.SW_DM);
        if(sharedPref.loadDarkTheme()==true){
            mSwitch.setChecked(true);
        }
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sharedPref.setDarkTheme(true);
                    restartApp();
                }else{
                    sharedPref.setDarkTheme(false);
                    restartApp();
                }
            }
        });


        ImageView imageView = (ImageView)findViewById(R.id.IW_img);
        String url = "http://www.pngall.com/wp-content/uploads/4/Welcome-PNG-Download-Image.png";
        DownloadImageWithURLTask descarca = new DownloadImageWithURLTask(imageView);
        descarca.execute(url);
    }


    public void restartApp(){
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();
    }


    private class DownloadImageWithURLTask extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        public DownloadImageWithURLTask(ImageView iw) {
            this.imageView = iw;
        }

        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap bitmap = null;
            try {
                InputStream is = new java.net.URL(url).openStream();
                bitmap = BitmapFactory.decodeStream(is);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bitmap;
        }
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }
}
