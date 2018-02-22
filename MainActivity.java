package com.example.saikrishnapulluri.onlinedata;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.Manifest;
import android.app.Service;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.InputStream;


public class MainActivity extends Activity {


    LocationManager lm;
    android.location.Location l;
    EditText ed;
    Button b,listbtn;
    InputStream is;
  public  String lat,lan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lm = (LocationManager) getSystemService(Service.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "No permission requesting..", Toast.LENGTH_SHORT).show();
            int Permission=0;
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Permission);

            return;
        }
        l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
  /*      if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "No permission requesting..", Toast.LENGTH_SHORT).show();
            int Permissiongranted=0;
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Permissiongranted);

            return;
        }*/
        ed=(EditText) findViewById(R.id.editText);
        b=(Button)findViewById(R.id.button3);
        listbtn=(Button)findViewById(R.id.list);
        final double latitude=l.getLatitude();
        final double longitude=l.getLongitude();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lat = Double.toString(latitude);
                lan = Double.toString(longitude);
                ed.setText(Double.toString(latitude)+" "+Double.toString(longitude));
                //  Toast.makeText(getApplicationContext(),latitude,longitude,Toast.LENGTH_LONG).show();
            }






        });



        listbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this,Jsondata.class);
                intent.putExtra("latitude", lat);
                intent.putExtra("langitude", lan);
                startActivity(intent);


            }
        });


    }
}

