package com.example.caragioiu_miruna_dam;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final static int COD_ADAUGA=300;
    final static int COD_UPDATE=400;
    final static String COD_ADD="cod_adauga";
    ListView listView;
    int pozitie;
    List<ClubBaschet> listaCluburi=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.LVClub);

        final AlertDialog alertDialog=new AlertDialog.Builder(this).setTitle(R.string.mesajInformare)
                .setMessage(R.string.mesajBunVenit).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create();
        alertDialog.show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pozitie=position;
                Intent it=new Intent(getApplicationContext(),AdaugaClubBaschet.class);
                it.putExtra(COD_ADD,listaCluburi.get(position));
                startActivityForResult(it,COD_UPDATE);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_club,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menuAdauga){
            Intent it=new Intent(getApplicationContext(), AdaugaClubBaschet.class);
            startActivityForResult(it, COD_ADAUGA);
        }

        if(item.getItemId()==R.id.menuPreluare){
            GetCluburiFromJSON getCluburiFromJSON=new GetCluburiFromJSON(){
                @Override
                protected void onPostExecute(List<ClubBaschet> clubBaschets) {
                    super.onPostExecute(clubBaschets);
                    for(ClubBaschet club: clubBaschets){
                        listaCluburi.add(club);
                        adaugaInLW(listaCluburi);
                    }
                }
            };
            getCluburiFromJSON.execute("https://extendsclass.com/api/json-storage/bin/fcbbccb");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == COD_ADAUGA && resultCode == RESULT_OK && data != null) {
            ClubBaschet club=(ClubBaschet)data.getSerializableExtra(AdaugaClubBaschet.COD_ADD);
            listaCluburi.add(club);
            adaugaInLW(listaCluburi);
        }
        if(requestCode==COD_UPDATE && resultCode == RESULT_OK && data != null){
            ClubBaschet club=(ClubBaschet)data.getSerializableExtra(AdaugaClubBaschet.COD_ADD);
            update(club);
            adaugaInLW(listaCluburi);
        }
    }


    private void update(ClubBaschet club) {
        listaCluburi.get(pozitie).setDenumire(club.getDenumire());
        listaCluburi.get(pozitie).setNumarMembrii(club.getNumarMembrii());
        listaCluburi.get(pozitie).setAnInfiintare(club.getAnInfiintare());
        listaCluburi.get(pozitie).setTip(club.getTip());
    }

    private void adaugaInLW(List<ClubBaschet> listaCluburi) {
        listView=findViewById(R.id.LVClub);
        //ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,listaCluburi);
        AdapterClub adapter=new AdapterClub(this, R.layout.club_layout,listaCluburi);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}

class GetCluburiFromJSON extends AsyncTask<String, Void, List<ClubBaschet>>{

    @Override
    protected List<ClubBaschet> doInBackground(String... strings) {
        List<ClubBaschet> cluburi=new ArrayList<>();
        try{
            URL url=new URL(strings[0]);
            HttpURLConnection http= (HttpURLConnection) url.openConnection();
            InputStream is=http.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(is));

            String line=null;
            StringBuilder stringBuilder=new StringBuilder();
            while((line=br.readLine())!=null){
                stringBuilder.append(line);
            }
            String objectString=stringBuilder.toString();
            JSONObject object=new JSONObject(objectString);
            JSONObject clubObj = object.getJSONObject("cluburi sportive");
            JSONArray clubArray=clubObj.getJSONArray("club baschet");
            for(int i=0;i<clubArray.length();i++){
                JSONObject clubObject =clubArray.getJSONObject(i);
                String denumire=clubObject.getString("denumire");
                int numarMembrii = clubObject.getInt("numarMembrii");
                int anInfiintare = clubObject.getInt("anInfiintare");
                String tipClub=clubObject.getString("tipClub");
                ClubBaschet club=new ClubBaschet(denumire,tipClub,numarMembrii,anInfiintare);
                cluburi.add(club);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return cluburi;
    }
}
