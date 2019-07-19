package com.example.try2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LatLong extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Crime> cData= new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crime_r_view);
        recyclerView=findViewById(R.id.RecyclerViewCrime);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getdata();

    }



    private void getdata() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://data.police.uk")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Latllong iFace=retrofit.create(Latllong.class);
        if(getIntent().hasExtra("Lat") && getIntent().hasExtra("Long")){
             final String latitude= getIntent().getStringExtra("Lat");
             final String longitude=getIntent().getStringExtra("Long");
             final String month=getIntent().getStringExtra("Month");

        Call<List<Crime>> call=iFace.getCrimes("2017-"+month, latitude, longitude);
        call.enqueue(new Callback<List<Crime>>() {
            @Override
            public void onResponse(Call<List<Crime>> call, Response<List<Crime>> response) {
                if(response.body()!=null){
                cData= new ArrayList<>(response.body());
                cAdapter adapter=new cAdapter(LatLong.this, cData);
                recyclerView.setAdapter(adapter);
                }
                else{
                    Toast.makeText(LatLong.this,"null",Toast.LENGTH_LONG).show();}

            }

            @Override
            public void onFailure(Call<List<Crime>> call, Throwable t) {
                Toast.makeText(LatLong.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }});}}}
