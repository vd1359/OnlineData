package com.example.saikrishnapulluri.onlinedata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Jsondata extends Activity {

   ListView listView,listView1;
    TextView latit,lang;
    LinearLayout linlaHeaderProgress;
    public String latitude,langitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsondata);
       listView = (ListView) findViewById(R.id.listView);
        listView1 = (ListView) findViewById(R.id.listView1);
        latit = (TextView) findViewById(R.id.lati);
        lang = (TextView) findViewById(R.id.lan);
         linlaHeaderProgress = (LinearLayout) findViewById(R.id.linlaHeaderProgress);

        latitude=getIntent().getExtras().getString("latitude").toString();
        langitude=getIntent().getExtras().getString("langitude").toString();
        latit.setText(langitude);
        lang.setText(langitude);

        getJSON("https://notifylikes.000webhostapp.com/jsondata.php");
    }


    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                linlaHeaderProgress.setVisibility(View.VISIBLE);
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                // CHANGE THE LOADINGMORE STATUS TO PERMIT FETCHING MORE DATA


                // HIDE THE SPINNER AFTER LOADING FEEDS

               try {
                    loadIntoListView(s);


                } catch (JSONException e) {
                   e.printStackTrace();
               }
                linlaHeaderProgress.setVisibility(View.GONE);
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }



    private void loadIntoListView(String json) throws JSONException {



        JSONArray jsonArray = new JSONArray(json);

   /*     String[] interestarr = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            interestarr[i] = obj.getString("interest");

        } */

        String[] company = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            company[i] = obj.getString("company");

        }

        String[] type = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            type[i] = obj.getString("type");

        }


     /*   String[] langitudearr = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            langitudearr[i] = obj.getString("langitude");


        } */

        String[] lattitudearr = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            lattitudearr[i] = obj.getString("latitude");


        }






    /*    ArrayList<String> myfinaldata = new ArrayList<String>();
        myfinaldata.add("foo");
        myfinaldata.add("bar");
      /*  for (int j=0;j<jsonArray.length();j++){

            if(latitude==lattitudearr[j].toString())
            {
                myfinaldata.add("company:"+company[j].toString());
            }

        } */



        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, company);
        listView.setAdapter(arrayAdapter);


       ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, type);

       listView1.setAdapter(arrayAdapter1);



    }

}
