package com.example.facturi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import android.view.View;

public class Furnizori extends AppCompatActivity {


    public class GetJson extends AsyncTask<String, Void, String>{
        ListView lv=(ListView)findViewById(R.id.LV_furnizori);
        ArrayList<String> f=new ArrayList<>();


        @Override
        protected String doInBackground(String... strings) {
            String rezultat=null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection http = (HttpURLConnection)url.openConnection();
                InputStream is = http.getInputStream();
                BufferedReader reader =  new BufferedReader(new InputStreamReader(is));

                String linie = null;
                StringBuilder builder = new StringBuilder();
                while((linie=reader.readLine())!=null) {
                    builder.append(linie);
                }

                String textTotal = builder.toString();
                JSONObject object=new JSONObject(textTotal);
                JSONArray vector=object.getJSONArray("furnizor apa");
                for(int i=0;i<vector.length();i++) {
                    JSONObject furnizoriApa=vector.getJSONObject(i);
                    JSONObject adresa=furnizoriApa.getJSONObject("adresa");

                    rezultat="Nume: "+furnizoriApa.getString("furnizor")+ "\nNr telefon: " +furnizoriApa.getString("nr telefon")+ "\nEmail: "+furnizoriApa.getString("email");
                    rezultat+="\nStrada: "+adresa.getString("strada")+", nr. "+adresa.getString("numar")+", oras: "+adresa.getString("oras");
                    f.add(rezultat);
                }
                JSONArray vector2=object.getJSONArray("furnizor gaze");
                for(int i=0;i<vector2.length();i++) {
                    JSONObject furnizoriApa=vector2.getJSONObject(i);
                    JSONObject adresa=furnizoriApa.getJSONObject("adresa");

                    rezultat="Nume: "+furnizoriApa.getString("furnizor")+ "\nNr telefon: " +furnizoriApa.getString("nr telefon")+ "\nEmail: "+furnizoriApa.getString("email");
                    rezultat+="\nStrada: "+adresa.getString("strada")+", nr. "+adresa.getString("numar")+", oras: "+adresa.getString("oras");
                    f.add(rezultat);
                }
                JSONArray vector3=object.getJSONArray("furnizor energie electrica");
                for(int i=0;i<vector3.length();i++) {
                    JSONObject furnizoriApa=vector3.getJSONObject(i);
                    JSONObject adresa=furnizoriApa.getJSONObject("adresa");

                    rezultat="Nume: "+furnizoriApa.getString("furnizor")+ "\nNr telefon: " +furnizoriApa.getString("nr telefon")+ "\nEmail: "+furnizoriApa.getString("email");
                    rezultat+="\nStrada: "+adresa.getString("strada")+", nr. "+adresa.getString("numar")+", oras: "+adresa.getString("oras");
                    f.add(rezultat);
                }
                JSONArray vector4=object.getJSONArray("furnizor energie termina");
                for(int i=0;i<vector4.length();i++) {
                    JSONObject furnizoriApa=vector4.getJSONObject(i);
                    JSONObject adresa=furnizoriApa.getJSONObject("adresa");

                    rezultat="Nume: "+furnizoriApa.getString("furnizor")+ "\nNr telefon: " +furnizoriApa.getString("nr telefon")+ "\nEmail: "+furnizoriApa.getString("email");
                    rezultat+="\nStrada: "+adresa.getString("strada")+", nr. "+adresa.getString("numar")+", oras: "+adresa.getString("oras");
                    f.add(rezultat);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_furnizori);
        getSupportActionBar().setTitle(getString(R.string.detaliiFurnizori));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.color)));


        GetJson json=new GetJson(){
            //@Override
            protected void onPostExecute(String s) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Furnizori.this, android.R.layout.simple_list_item_1, f){
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view=super.getView(position,convertView,parent);
                        if(position%2==0){
                            view.setBackgroundColor(Color.RED);
                        }else{
                            view.setBackgroundColor(Color.GREEN);
                        }
                        return view;
                    }
                };
                lv.setAdapter(adapter);
            }
        };

        json.execute("https://api.myjson.com/bins/7h7gw");

        TextView twHarta=(TextView)findViewById(R.id.TW_harta);
        twHarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(Furnizori.this, MapsFurnizori.class);
                startActivity(it);
            }
        });


    }
}
